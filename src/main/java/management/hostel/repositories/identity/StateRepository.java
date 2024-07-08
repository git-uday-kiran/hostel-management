package management.hostel.repositories.identity;

import management.hostel.models.identity.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	List<State> findAllByCountryId(long countryId);

	List<State> findAllByNameLikeIgnoreCase(final String regex, final Pageable pageable);

}
