package coder.jdev.repositories.identity;

import coder.jdev.models.identity.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAllByNameLikeIgnoreCase(final String regex, final Pageable pageable);

}
