package management.hostel.configs;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import management.hostel.dto.request.hostel.HostelRequest;
import management.hostel.dto.request.hostel.RoomRequest;
import management.hostel.dto.request.identity.AddressRequest;
import management.hostel.dto.request.users.StudentRequest;
import management.hostel.dto.request.users.UserRequest;
import management.hostel.dto.response.hostel.HostelResponse;
import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.dto.response.users.StudentResponse;
import management.hostel.models.users.Gender;
import management.hostel.services.hostel.HostelService;
import management.hostel.services.hostel.RoomService;
import management.hostel.services.users.StudentService;
import management.hostel.services.users.UserService;
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
	private final HostelService hostelService;
	private final UserService userService;
	private final RoomService roomService;
	private final StudentService studentService;
	private final Name name = faker.name();
	private final PhoneNumber phoneNumber = faker.phoneNumber();
	private final Address address = faker.address();
	private final Random random = new Random();

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
				LocalDate.ofInstant(faker.date().birthday(17, 30).toInstant(), ZoneId.systemDefault()),
				name.username(),
				email(),
				mobile(),
				addressRequest(),
				gender()
			))
			.map(userService::addUser)
			.toList();
	}

	public List<Long> addRooms(List<Long> hostels) {
		return IntStream.generate(() -> 0)
			.limit(100)
			.mapToObj(i -> new RoomRequest(1, 1, 100, 1L))
			.map(roomService::addRoom)
			.map(RoomResponse::getId)
			.toList();
	}

	private List<Long> addStudents(List<Long> users, List<Long> rooms) {
		int max = Math.min(users.size(), rooms.size());
		return IntStream.range(0, max / 2)
			.mapToObj(id -> new StudentRequest(
				users.get(id),
				rooms.get(id),
				random.nextLong(38376),
				localDate(faker.date().past(1000, TimeUnit.DAYS)),
				null
			))
			.map(studentService::addStudent)
			.map(StudentResponse::getId)
			.toList();
	}

	private String mobile() {
		return "+91" + phoneNumber.subscriberNumber(10);
	}

	private String email() {
		return name.name().replaceAll("\\W", "") + "@gmail.com";
	}

	private AddressRequest addressRequest() {
		return new AddressRequest(
			address.fullAddress(),
			address.city(),
			random.nextLong(100, 200),
			"123456"
		);
	}

	private Gender gender() {
		return Gender.values()[(int) (Math.random() * 1)];
	}

	private LocalDate localDate(Date date) {
		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
}
