package coder.jdev.services.users;

import coder.jdev.dto.request.users.StudentRequest;
import coder.jdev.dto.response.hostel.RoomResponse;
import coder.jdev.dto.response.identity.AddressResponse;
import coder.jdev.dto.response.identity.CollegeResponse;
import coder.jdev.dto.response.users.StudentResponse;
import coder.jdev.exceptions.users.StudentException;
import coder.jdev.models.hostel.Room;
import coder.jdev.models.identity.College;
import coder.jdev.models.users.Student;
import coder.jdev.models.users.User;
import coder.jdev.repositories.users.StudentRepository;
import coder.jdev.repositories.users.UserRepository;
import coder.jdev.services.hostel.RoomService;
import coder.jdev.services.identity.AddressService;
import coder.jdev.services.identity.CollegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static coder.jdev.utils.RequestValidators.validateStudentRequest;
import static coder.jdev.utils.Utils.*;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final UserRepository userRepository;
    private final RoomService roomService;
    private final CollegeService collegeService;
    private final AddressService addressService;

    public List<Student> findAllByJoiningDate(LocalDate date) {
        return repository.findAllByJoiningDate(date);
    }

    public List<Student> findAllByJoiningDateGreaterThanEqual(LocalDate formDate) {
        return repository.findAllByJoiningDateGreaterThanEqual(formDate);
    }

    public List<Student> findAllByRoomId(long roomId) {
        return repository.findAllByRoomId(roomId);
    }

    public Student findById(long id) throws StudentException {
        return repository.findById(id).orElseThrow(() -> new StudentException("student with id: %d does not exist".formatted(id)));
    }

    public List<StudentResponse> findAll() {
        return toResponseList(repository.findAll());
    }

    public StudentResponse addStudent(final StudentRequest request) {
        validateStudentRequest(request, repository, userRepository);
        final Student student = modelOf(request);
        repository.saveAndFlush(student);
        log.info("Student with id: {} is saved.", student.getUser().getId());
        return responseOf(student);
    }

    public List<StudentResponse> toResponseList(final List<Student> students) {
        return students.stream()
                .map(this::responseOf)
                .toList();
    }

    public StudentResponse responseOf(final Student student) {
        final User user = userRepository.findById(student.getUser().getId()).orElseThrow(userIsNotExistWithIdSupplier(student.getUser().getId()));
        final AddressResponse addressResponse = addressService.responseOf(user.getAddress());
        final RoomResponse roomResponse = roomService.responseOf(student.getRoom());
        final CollegeResponse collegeResponse = collegeService.responseOf(student.getCollege());

        return StudentResponse.builder()
                .id(student.getUser().getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .addressResponse(addressResponse)
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .roomResponse(roomResponse)
                .collegeResponse(collegeResponse)
                .joiningDate(student.getJoiningDate())
                .leaveDate(student.getLeaveDate())
                .build();
    }

    public Student modelOf(StudentRequest request) {
        final User user = userRepository.fetchById(request.getUserId());
        final Room room = roomService.fetchById(request.getRoomId());
        final College college = collegeService.fetchById(request.getCollegeId());
        Student student = new Student();
        student.setUser(user);
        student.setRoom(room);
        student.setCollege(college);
        student.setJoiningDate(request.getJoiningDate());
        student.setLeaveDate(request.getLeaveDate());
        return student;
    }

}

