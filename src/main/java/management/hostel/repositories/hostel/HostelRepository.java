package management.hostel.repositories.hostel;

import management.hostel.models.hostel.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

	Optional<Hostel> findByEmail(String email);

	Optional<Hostel> findByMobile(String mobile);

	Boolean existsByName(final String name);

	Boolean existsByEmail(final String email);

	void removeById(final long hostelId);

}
