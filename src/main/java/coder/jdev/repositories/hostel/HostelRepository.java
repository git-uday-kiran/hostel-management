package coder.jdev.repositories.hostel;

import coder.jdev.exceptions.hostel.HostelException;
import coder.jdev.models.hostel.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static coder.jdev.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

    Optional<Hostel> findByEmail(String email);

    Optional<Hostel> findByMobile(String mobile);

    Boolean existsByName(final String name);

    Boolean existsByEmail(final String email);

    void removeById(final long hostelId);

    default Hostel fetchById(final long id) {
        return findById(id).orElseThrow(runtimeExceptionSupplier("Hostel with id: %d does not exist".formatted(id), HostelException.class));
    }

}
