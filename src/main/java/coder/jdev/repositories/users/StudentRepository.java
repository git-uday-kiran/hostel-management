package coder.jdev.repositories.users;

import coder.jdev.exceptions.users.StudentException;
import coder.jdev.exceptions.users.UserException;
import coder.jdev.models.users.Student;
import coder.jdev.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static coder.jdev.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByJoiningDate(LocalDate date);

    List<Student> findAllByJoiningDateGreaterThanEqual(LocalDate date);

    List<Student> findAllByRoomId(long roomId);

    default Student fetchById(final long id) {
        return findById(id).orElseThrow(runtimeExceptionSupplier("student is not exist with id %d".formatted(id), StudentException.class));
    }
}
