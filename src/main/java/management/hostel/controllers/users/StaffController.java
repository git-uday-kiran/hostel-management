package management.hostel.controllers.users;

import management.hostel.dto.request.users.StaffRequest;
import management.hostel.dto.response.users.StaffResponse;
import management.hostel.exceptions.handlers.HostelManagementExceptionHandler;
import management.hostel.exceptions.handlers.ValidationExceptionHandler;
import management.hostel.services.users.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("staff")
public class StaffController implements HostelManagementExceptionHandler, ValidationExceptionHandler {

	private final StaffService service;

	@GetMapping
	public List<StaffResponse> getAllStaff() {
		return service.getAllStaff();
	}

	@PostMapping("add")
	@ResponseStatus(HttpStatus.OK)
	public StaffResponse addStaff(@RequestBody @Valid StaffRequest request) {
		return service.addStaff(request);
	}
}
