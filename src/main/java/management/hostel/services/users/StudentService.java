package management.hostel.services.users;

import management.hostel.dto.request.users.StudentRequest;
import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.dto.response.identity.AddressResponse;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.dto.response.users.StudentResponse;
import management.hostel.exceptions.users.StudentException;
import management.hostel.models.hostel.Room;
import management.hostel.models.identity.College;
import management.hostel.models.users.Student;
import management.hostel.models.users.User;
import management.hostel.repositories.users.StudentRepository;
import management.hostel.repositories.users.UserRepository;
import management.hostel.services.hostel.RoomService;
import management.hostel.services.identity.AddressService;
import management.hostel.services.identity.CollegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static management.hostel.utils.RequestValidators.validateStudentRequest;
import static management.hostel.utils.Utils.userIsNotExistWithIdSupplier;

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
	private final StudentRepository studentRepository;

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
		log.info("Student with id: {} is saved.", student.getId());
		return responseOf(student);
	}

	public List<StudentResponse> toResponseList(final List<Student> students) {
		return students.stream()
			.map(this::responseOf)
			.toList();
	}

	public StudentResponse responseOf(final Student student) {
		final User user = userRepository.findById(student.getId()).orElseThrow(userIsNotExistWithIdSupplier(student.getId()));
		final AddressResponse addressResponse = addressService.responseOf(user.getAddress());
		final RoomResponse roomResponse = roomService.responseOf(student.getRoom());
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
			.build();
	}

	public Student modelOf(StudentRequest request) {
		User user = userRepository.fetchById(request.getUserId());
		final Student student = new Student();
		final Room room = roomService.fetchById(request.getRoomId());
		final College college = collegeService.fetchById(request.getCollegeId());
		student.setId(user.getId());
		student.setRoom(room);
		student.setCollege(college);
		student.setJoiningDate(request.getJoiningDate());
		student.setLeaveDate(request.getLeaveDate());
		return student;
	}

}

