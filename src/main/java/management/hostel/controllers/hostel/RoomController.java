package management.hostel.controllers.hostel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.hostel.dto.request.hostel.RoomRequest;
import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.exceptions.handlers.HostelManagementExceptionHandler;
import management.hostel.exceptions.handlers.ValidationExceptionHandler;
import management.hostel.services.hostel.RoomService;
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
		return service.getAllRoomResponses();
	}

	@GetMapping("/{roomId}")
	@ResponseStatus(HttpStatus.OK)
	public RoomResponse getRoomById(@PathVariable long roomId) {
		return service.getRoomResponseById(roomId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RoomResponse addRoom(@RequestBody @Valid RoomRequest request) {
		return service.addRoom(request);
	}

	@DeleteMapping("{roomId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteRoomById(@PathVariable long roomId) {
		service.removeRoomById(roomId);
	}

	@GetMapping("/by-hostel/{hostelId}")
	@ResponseStatus(HttpStatus.OK)
	public List<RoomResponse> getRoomsByHostelId(@PathVariable long hostelId) {
		return service.getRoomsAsResponseListByHostelId(hostelId);
	}

	@DeleteMapping("remove")
	@ResponseStatus(HttpStatus.OK)
	public int removeRoomByRoomNoFloorNoAndHostelId(@RequestParam int roomNo, @RequestParam int floorNo, @RequestParam int hostelId) {
		return service.removeRoomByRoomNoFloorNoAndHostelId(roomNo, floorNo, hostelId);
	}

}
