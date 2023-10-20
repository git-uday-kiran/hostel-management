package coder.jdev.controllers.hostel;

import coder.jdev.dto.request.hostel.RoomRequest;
import coder.jdev.dto.response.hostel.RoomResponse;
import coder.jdev.exceptions.handlers.HostelManagementExceptionHandler;
import coder.jdev.exceptions.handlers.ValidationExceptionHandler;
import coder.jdev.services.hostel.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rooms")
public class RoomController implements HostelManagementExceptionHandler, ValidationExceptionHandler {

    private final RoomService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> getRooms() {
        return service.findAll();
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse addRoom(@RequestBody @Valid RoomRequest request) {
        return service.addRoom(request);
    }

    @GetMapping("by-hostel")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> getRoomsByHostelId(@RequestParam("id") long hostelId) {
        return service.getRoomsByHostelId(hostelId);
    }

}
