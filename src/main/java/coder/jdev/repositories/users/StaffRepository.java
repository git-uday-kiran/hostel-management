package coder.jdev.repositories.users;

import coder.jdev.exceptions.users.StaffException;
import coder.jdev.exceptions.users.UserException;
import coder.jdev.models.users.Staff;
import coder.jdev.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static coder.jdev.utils.Utils.runtimeExceptionSupplier;

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
