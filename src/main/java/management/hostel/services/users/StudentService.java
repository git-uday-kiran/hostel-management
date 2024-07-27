package management.hostel.services.users;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import management.hostel.dto.request.users.StudentRequest;
import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.dto.response.identity.AddressResponse;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.dto.response.users.StudentResponse;
import management.hostel.exceptions.ResourceAlreadyExistException;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.models.hostel.Room;
import management.hostel.models.identity.College;
import management.hostel.models.users.Student;
import management.hostel.models.users.User;
import management.hostel.repositories.users.StudentRepository;
import management.hostel.repositories.users.UserRepository;
import management.hostel.services.hostel.RoomService;
import management.hostel.services.identity.AddressService;
import management.hostel.services.identity.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

	private final UserService userService;
	private final StudentRepository repository;
	private final RoomService roomService;
	private final CollegeService collegeService;
	private final AddressService addressService;
	private final StudentRepository studentRepository;
	private final UserRepository userRepository;

	public List<Student> findAllByJoiningDate(LocalDate date) {
		return repository.findAllByJoiningDate(date);
	}

	public List<Student> findAllByJoiningDateGreaterThanEqual(LocalDate formDate) {
		return repository.findAllByJoiningDateGreaterThanEqual(formDate);
	}

	public Student getStudentById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student with id: %d does not exist".formatted(id)));
	}

	public StudentResponse getStudentResponseById(long id) {
		return responseOf(getStudentById(id));
	}

	public List<StudentResponse> getAllStudentResponses() {
		return toResponseList(repository.findAll());
	}

	public StudentResponse addStudent(StudentRequest request) {
		User user = userService.getUserById(request.getUserId());
		if (studentRepository.existsById(request.getUserId())) {
			throw new ResourceAlreadyExistException("student already exist with id %d".formatted(request.getUserId()));
		}
		if (user.getRoom() != null) {
			throw new ResourceAlreadyExistException("user has already assigned room");
		}
		Room room = roomService.fetchById(request.getRoomId());
		user.setRoom(room);
		userRepository.saveAndFlush(user);
		Student student = repository.saveAndFlush(modelOf(request, user));
		log.info("Student with id: {} is saved.", student.getId());
		return responseOf(student);
	}

	public List<StudentResponse> toResponseList(final List<Student> students) {
		return students.stream()
			.map(this::responseOf)
			.toList();
	}

	public StudentResponse responseOf(final Student student) {
		final User user = userService.getUserById(student.getUser().getId());
		final AddressResponse addressResponse = addressService.responseOf(user.getAddress());
		final RoomResponse roomResponse = roomService.responseOf(user.getRoom());
		final CollegeResponse collegeResponse = collegeService.responseOf(student.getCollege());

		return StudentResponse.builder()
			.id(student.getId())
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
			.createdAt(student.getCreatedAt())
			.updatedAt(student.getUpdatedAt())
			.build();
	}

	public Student modelOf(StudentRequest request, User user) {
		final Student student = new Student();
		final College college = collegeService.fetchById(request.getCollegeId());
		student.setId(user.getId());
		student.setUser(user);
		student.setCollege(college);
		student.setJoiningDate(request.getJoiningDate());
		student.setLeaveDate(request.getLeaveDate());
		return student;
	}

}

