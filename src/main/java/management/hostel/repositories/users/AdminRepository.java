package management.hostel.repositories.users;

import management.hostel.exceptions.users.AdminException;
import management.hostel.models.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static management.hostel.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	List<Admin> findAllByExpirationIsGreaterThan(LocalDateTime dateTime);

	default Admin fetchById(final long id) {
		return findById(id).orElseThrow(runtimeExceptionSupplier("admin is not exist with id %d".formatted(id), AdminException.class));
	}
}
