package management.hostel.repositories.users;

import management.hostel.exceptions.users.StaffException;
import management.hostel.models.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static management.hostel.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	List<Staff> findAllByJoiningDate(LocalDate date);

	List<Staff> findAllByJoiningDateIsGreaterThanEqual(LocalDate date);

	List<Staff> findAllByLeaveDate(LocalDate date);

	List<Staff> findAllByLeaveDateIsGreaterThanEqual(LocalDate date);

	default Staff fetchById(final long id) {
		return findById(id).orElseThrow(runtimeExceptionSupplier("staff is not exist with id %d".formatted(id), StaffException.class));
	}

}
