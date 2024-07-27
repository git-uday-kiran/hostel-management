package management.hostel.repositories.hostel;

import management.hostel.exceptions.hostel.RoomException;
import management.hostel.models.hostel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static management.hostel.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findAllByHostelId(long hostelId);


	Long countAllByHostelId(final long hostelId);

	void removeById(final long roomId);


	@Query(value = "delete from room where room_no = ? and floor_no = ? and hostel_id = ?", nativeQuery = true)
	int removeByRoomNoAndFloorNoAndHostel(int roomNo, int floorNo, long hostelId);

	default Room fetchById(final long id) {
		return findById(id).orElseThrow(runtimeExceptionSupplier("room with id %d does not exist.".formatted(id), RoomException.class));
	}
}
