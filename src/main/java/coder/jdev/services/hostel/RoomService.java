package coder.jdev.services.hostel;

import coder.jdev.dto.request.hostel.RoomRequest;
import coder.jdev.dto.response.hostel.RoomResponse;
import coder.jdev.models.hostel.Hostel;
import coder.jdev.models.hostel.Room;
import coder.jdev.repositories.hostel.HostelRepository;
import coder.jdev.repositories.hostel.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static coder.jdev.utils.RequestValidators.validateRoomRequest;
import static java.util.Objects.requireNonNullElse;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    private final HostelService hostelService;
    private final HostelRepository hostelRepository;

    private final EntityManager entityManager;

    public List<RoomResponse> getRoomsByHostelId(long hostelId) {
        return toResponseList(repository.findAllByHostelId(hostelId));
    }

    public List<RoomResponse> findAll() {
        return toResponseList(repository.findAll());
    }

    public RoomResponse addRoom(RoomRequest request) {
        validateRoomRequest(request, repository, hostelRepository);
        Room room = modelOf(request);
        repository.saveAndFlush(room);
        log.info("Room with id: {} is saved.", room.getId());
        return responseOf(room);
    }

    public void removeRoomById(final long roomId) {
        repository.removeById(roomId);
    }

    public void removeRoomByRoomNoAndHostelId(final String roomNo, final long hostelId) {
        repository.removeByRoomNoAndHostelId(roomNo, hostelId);
    }

    public List<RoomResponse> findAllRoomsByFloorNoAndHostelId(long floorNo, long hostelId) {
        return toResponseList(repository.findAllByFloorNoAndHostelId(floorNo, hostelId));
    }

    public void removeAllRoomsByFloorNoAndHostelId(final long floorNo, final long hostelId) {
        repository.removeAllByFloorNoAndHostelId(floorNo, hostelId);
    }

    private String nextRoomNumberForHostel(Long hostelId, int floorNo) {
        String jpql = """
                    SELECT MAX(CAST(SUBSTR(r.roomNo, LENGTH(:prefix) + 1) AS INTEGER))
                    FROM Room as r
                    WHERE r.hostel.id = :hostelId
                    AND r.floorNo = :floorNo
                """;

        final String roomPrefix = "ROOM_";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("hostelId", hostelId);
        query.setParameter("prefix", roomPrefix); // Replace "Room" with your actual room number prefix if needed
        query.setParameter("floorNo", floorNo);

        Integer lastRoomNumber = requireNonNullElse(query.getSingleResult(), floorNo * 100);
        return roomPrefix + String.format("%03d", lastRoomNumber + 1);
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
//                .hostelResponse((hostelService.responseOf(room.getHostel())))
                .build();
    }

    public Room fetchById(final long roomId) {
        return repository.fetchById(roomId);
    }

    public Room modelOf(RoomRequest request) {
        final Hostel hostel = hostelService.fetchById(request.getHostelId());
        return Room.builder()
                .roomNo(nextRoomNumberForHostel(request.getHostelId(), request.getFloorNo()))
                .floorNo(request.getFloorNo())
                .hostel(hostel)
                .build();
    }
}
