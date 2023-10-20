package coder.jdev.controllers.users;

import coder.jdev.dto.request.users.StudentRequest;
import coder.jdev.dto.response.users.StudentResponse;
import coder.jdev.exceptions.handlers.HostelManagementExceptionHandler;
import coder.jdev.exceptions.handlers.ValidationExceptionHandler;
import coder.jdev.services.users.StudentService;
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
