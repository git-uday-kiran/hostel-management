package coder.jdev.repositories.identity;

import coder.jdev.models.identity.City;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAllByStateId(long stateId);

    List<City> findAllByNameLikeIgnoreCase(final String nameRegex, Pageable pageable);

}
