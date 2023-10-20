package coder.jdev.controllers.users;

import coder.jdev.dto.request.users.StaffRequest;
import coder.jdev.dto.response.users.StaffResponse;
import coder.jdev.exceptions.handlers.HostelManagementExceptionHandler;
import coder.jdev.exceptions.handlers.ValidationExceptionHandler;
import coder.jdev.models.hostel.Hostel;
import coder.jdev.services.users.StaffService;
import jakarta.validation.ConstraintViolation;
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
