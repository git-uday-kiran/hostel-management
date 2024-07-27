package management.hostel.controllers.hostel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.hostel.dto.request.hostel.HostelRequest;
import management.hostel.dto.response.hostel.HostelResponse;
import management.hostel.exceptions.handlers.HostelManagementExceptionHandler;
import management.hostel.exceptions.handlers.ValidationExceptionHandler;
import management.hostel.services.hostel.HostelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("hostels")
public class HostelController implements HostelManagementExceptionHandler, ValidationExceptionHandler {

	private final HostelService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HostelResponse> findAll() {
		return service.findAll();
	}

	@GetMapping("{hostelId}")
	@ResponseStatus(HttpStatus.OK)
	public HostelResponse getHostelById(@PathVariable Long hostelId) {
		return service.getHostelResponseById(hostelId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HostelResponse addHostel(@RequestBody @Valid HostelRequest request) {
		return service.addHostel(request);
	}


	@GetMapping("/by-email/{email}")
	@ResponseStatus(HttpStatus.OK)
	public HostelResponse findByEmail(@PathVariable String email) {
		return service.getHostelResponseByEmail(email);
	}

	@GetMapping("/by-mobile/{mobile}")
	@ResponseStatus(HttpStatus.OK)
	public HostelResponse findByMobile(@PathVariable String mobile) {
		return service.getHostelResponseByMobile(mobile);
	}

	@DeleteMapping("{hostelId}")
	@ResponseStatus(HttpStatus.OK)
	public void removeHostelById(@PathVariable long hostelId) {
		service.removeHostelById(hostelId);
	}

}

