package coder.jdev.repositories.users;

import coder.jdev.exceptions.users.AdminException;
import coder.jdev.exceptions.users.UserException;
import coder.jdev.models.users.Admin;
import coder.jdev.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static coder.jdev.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAllByExpirationIsGreaterThan(LocalDateTime dateTime);

    default Admin fetchById(final long id) {
        return findById(id).orElseThrow(runtimeExceptionSupplier("admin is not exist with id %d".formatted(id), AdminException.class));
    }
}
