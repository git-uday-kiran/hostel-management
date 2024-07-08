package management.hostel.controllers.users;

import management.hostel.dto.request.users.UserRequest;
import management.hostel.dto.response.users.UserResponse;
import management.hostel.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

	private final UserService service;

	@GetMapping
	public List<UserResponse> getUsers() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public UserResponse getUser(@PathVariable Long id) {
		return service.getUserResponseById(id);
	}

	@GetMapping("by-email/{email}")
	public UserResponse getUserByEmail(@PathVariable String email) {
		return service.getUserResponseByEmail(email);
	}


	@GetMapping("by-mobile/{mobile}")
	public UserResponse getUserByMobile(@PathVariable String mobile) {
		return service.getUserResponseByMobile(mobile);
	}

	@PostMapping
	public Long addUser(@RequestBody @Valid UserRequest request) {
		return service.addUser(request);
	}

}
