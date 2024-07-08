package management.hostel.controllers.users;

import management.hostel.dto.request.users.StudentRequest;
import management.hostel.dto.response.users.StudentResponse;
import management.hostel.exceptions.handlers.HostelManagementExceptionHandler;
import management.hostel.exceptions.handlers.ValidationExceptionHandler;
import management.hostel.services.users.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController implements HostelManagementExceptionHandler, ValidationExceptionHandler {

	private final StudentService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<StudentResponse> getStudents() {
		return service.findAll();
	}

	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentResponse addStudent(@RequestBody @Valid StudentRequest request) {
		return service.addStudent(request);
	}

}
