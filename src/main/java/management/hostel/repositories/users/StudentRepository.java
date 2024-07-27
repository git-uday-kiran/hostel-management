package management.hostel.repositories.users;

import management.hostel.exceptions.users.StudentException;
import management.hostel.models.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static management.hostel.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findAllByJoiningDate(LocalDate date);

	List<Student> findAllByJoiningDateGreaterThanEqual(LocalDate date);

	default Student fetchById(final long id) {
		return findById(id).orElseThrow(runtimeExceptionSupplier("student is not exist with id %d".formatted(id), StudentException.class));
	}
}
