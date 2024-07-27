package management.hostel.services.hostel;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import management.hostel.dto.request.hostel.RoomRequest;
import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.models.BaseId;
import management.hostel.models.hostel.Hostel;
import management.hostel.models.hostel.Room;
import management.hostel.repositories.hostel.RoomRepository;
import management.hostel.services.users.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository repository;

	private final HostelService hostelService;
	private final UserService userService;

	public Room getRoomById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room does not exist with id %d".formatted(id)));
	}

	public RoomResponse getRoomResponseById(long id) {
		return responseOf(getRoomById(id));
	}

	public List<RoomResponse> getAllRoomResponses() {
		return toResponseList(repository.findAll());
	}

	public List<Room> getRoomsByHostelId(long hostelId) {
		return repository.findAllByHostelId(hostelId);
	}

	public List<RoomResponse> getRoomsAsResponseListByHostelId(long hostelId) {
		return toResponseList(getRoomsByHostelId(hostelId));
	}

	public RoomResponse addRoom(RoomRequest request) {
		Room room = modelOf(request);
		repository.saveAndFlush(room);
		log.info("Room with id: {} is saved.", room.getId());
		return responseOf(room);
	}

	public void removeRoomById(long roomId) {
		repository.removeById(roomId);
	}

	public int removeRoomByRoomNoFloorNoAndHostelId(int roomNo, int floorNo, long hostelId) {
		return repository.removeByRoomNoAndFloorNoAndHostel(roomNo, floorNo, hostelId);
	}


	public List<RoomResponse> toResponseList(List<Room> rooms) {
		return rooms.stream()
			.map(this::responseOf)
			.toList();
	}

	public RoomResponse responseOf(Room room) {
		return RoomResponse.builder()
			.id(room.getId())
			.roomNo(room.getRoomNo())
			.floorNo(room.getFloorNo())
			.hostelId(room.getHostel().getId())
			.capacity(room.getCapacity())
			.userIds(room.getUsers().stream()
				.map(BaseId::getId)
				.toList())
			.build();
	}

	public Room fetchById(final long roomId) {
		return repository.fetchById(roomId);
	}

	public Room modelOf(RoomRequest request) {
		final Hostel hostel = hostelService.getHostelById(request.getHostelId());
		return Room.builder()
			.roomNo(request.getRoomNo())
			.floorNo(request.getFloorNo())
			.capacity(request.getCapacity())
			.hostel(hostel)
			.users(new ArrayList<>())
			.build();
	}
}
