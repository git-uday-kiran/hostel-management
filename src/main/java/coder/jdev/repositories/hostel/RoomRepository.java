package coder.jdev.repositories.hostel;

import coder.jdev.exceptions.hostel.RoomException;
import coder.jdev.models.hostel.Room;
import coder.jdev.models.users.Student;
import coder.jdev.utils.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static coder.jdev.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByHostelId(long hostelId);

    List<Room> findAllByFloorNoAndHostelId(long floorNo, long hostelId);

    Long countAllByHostelId(final long hostelId);

    void removeById(final long roomId);

    Integer removeAllByHostelId(final long hostelId);

    void removeAllByFloorNoAndHostelId(final long floorNo, final long hostelId);

    void removeByRoomNoAndHostelId(final String roomNo, final long hostelId);

    default Room fetchById(final long id) {
        return findById(id).orElseThrow(runtimeExceptionSupplier("room with id %d does not exist.".formatted(id), RoomException.class));
    }
}
