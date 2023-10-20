package coder.jdev.configs;

import coder.jdev.dto.request.hostel.HostelRequest;
import coder.jdev.dto.request.hostel.RoomRequest;
import coder.jdev.dto.request.identity.AddressRequest;
import coder.jdev.dto.request.users.StudentRequest;
import coder.jdev.dto.request.users.UserRequest;
import coder.jdev.dto.response.hostel.HostelResponse;
import coder.jdev.dto.response.hostel.RoomResponse;
import coder.jdev.dto.response.users.StudentResponse;
import coder.jdev.dto.response.users.UserResponse;
import coder.jdev.models.users.User;
import coder.jdev.services.hostel.HostelService;
import coder.jdev.services.hostel.RoomService;
import coder.jdev.services.users.StudentService;
import coder.jdev.services.users.UserService;
import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Log4j2
@Component
@RequiredArgsConstructor
public class FakeDataLoader implements CommandLineRunner {


    private final Faker faker = new Faker();
    private Name name = faker.name();
    private PhoneNumber phoneNumber = faker.phoneNumber();
    private Address address = faker.address();
    private Random random = new Random();

    private final HostelService hostelService;
    private final UserService userService;
    private final RoomService roomService;
    private final StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        var hostels = loadHostels();
        var users = addUsers();
        var rooms = addRooms(hostels);
        var students = addStudents(users, rooms);
    }

    public List<Long> loadHostels() {
        return IntStream.generate(() -> 0)
                .limit(100)
                .mapToObj(e -> new HostelRequest(
                        name.fullName(),
                        addressRequest(),
                        email(),
                        mobile()
                ))
                .map(hostelService::addHostel)
                .map(HostelResponse::getId)
                .toList();
    }

    public List<Long> addUsers() {
        return IntStream.generate(() -> 0)
                .limit(100)
                .mapToObj(e -> new UserRequest(
                        name.username(),
                        email(),
                        mobile(),
                        addressRequest(),
                        LocalDate.ofInstant(faker.date().birthday(17, 30).toInstant(), ZoneId.systemDefault()),
                        gender()
                ))
                .map(userService::addUser)
                .map(UserResponse::getId)
                .toList();
    }

    public List<Long> addRooms(List<Long> hostels) {
        return IntStream.generate(() -> 0)
                .limit(100)
                .mapToObj(e -> new RoomRequest(
                        hostels.get(random.nextInt(hostels.size())),
                        random.nextInt(20)
                ))
                .map(roomService::addRoom)
                .map(RoomResponse::getId)
                .toList();
    }

    private List<Long> addStudents(List<Long> users, List<Long> rooms) {
        int max = Math.min(users.size(), rooms.size());
        return IntStream.range(0, max / 2)
                .mapToObj(id -> {
                    return new StudentRequest(
                            users.get(id),
                            rooms.get(id),
                            random.nextLong(38376),
                            localDate(faker.date().past(1000, TimeUnit.DAYS)),
                            null
                    );
                })
                .map(studentService::addStudent)
                .map(StudentResponse::getId)
                .toList();
    }

    private String mobile() {
        return phoneNumber.subscriberNumber(10);
    }

    private String email() {
        return name.name().replaceAll("\\W", "") + "@gmail.com";
    }

    private AddressRequest addressRequest() {
        return new AddressRequest(
                address.fullAddress(),
                address.city(),
                random.nextLong(100, 200),
                address.zipCode().replaceAll("\\D", "").transform(Integer::valueOf)
        );
    }

    private User.Gender gender() {
        return User.Gender.values()[(int) (Math.random() * 1)];
    }

    private LocalDate localDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
