package management.hostel.repositories.identity;

import management.hostel.exceptions.identity.CollegeException;
import management.hostel.models.identity.College;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static management.hostel.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

	List<College> findAllByCollegeNameLikeIgnoreCase(final String regex, final Pageable pageable);

	default College fetchById(final long id) {
		return findById(id).orElseThrow(runtimeExceptionSupplier("College with id: %d does not exist.".formatted(id), CollegeException.class));
	}
}
