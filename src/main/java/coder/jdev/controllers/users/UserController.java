package coder.jdev.controllers.users;

import coder.jdev.dto.request.users.UserRequest;
import coder.jdev.dto.response.users.UserResponse;
import coder.jdev.exceptions.handlers.HostelManagementExceptionHandler;
import coder.jdev.exceptions.handlers.ValidationExceptionHandler;
import coder.jdev.services.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController implements HostelManagementExceptionHandler, ValidationExceptionHandler {

    private final UserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getUsers() {
        return service.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody @Valid UserRequest request) {
        return service.addUser(request);
    }

}
