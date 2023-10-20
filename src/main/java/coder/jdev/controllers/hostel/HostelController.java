package coder.jdev.controllers.hostel;

import coder.jdev.dto.request.hostel.HostelRequest;
import coder.jdev.dto.response.hostel.HostelResponse;
import coder.jdev.exceptions.handlers.HostelManagementExceptionHandler;
import coder.jdev.exceptions.handlers.ValidationExceptionHandler;
import coder.jdev.services.hostel.HostelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public HostelResponse addHostel(@RequestBody @Valid HostelRequest request) {
        return service.addHostel(request);
    }


    @GetMapping("by-email")
    @ResponseStatus(HttpStatus.OK)
    public HostelResponse findByEmail(@RequestParam String email) {
        return service.getHostelByEmail(email);
    }

    @GetMapping("by-mobile")
    @ResponseStatus(HttpStatus.OK)
    public HostelResponse findByMobile(@RequestParam String mobile) {
        return service.getHostelByMobile(mobile);
    }

    @DeleteMapping("remove")
    @ResponseStatus(HttpStatus.OK)
    public void removeHostelById(@RequestParam("id") long hostelId) {
        service.removeHostelById(hostelId);
    }

}

