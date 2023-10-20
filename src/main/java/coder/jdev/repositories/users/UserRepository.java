package coder.jdev.repositories.users;

import coder.jdev.exceptions.users.StudentException;
import coder.jdev.exceptions.users.UserException;
import coder.jdev.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static coder.jdev.utils.Utils.runtimeExceptionSupplier;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    Boolean existsByUsernameOrEmailOrMobile(final String username, final String email, final String mobile);

    Boolean deleteUserById(final Long userId);

    Boolean existsByUsername(final String username);

    Boolean existsByEmail(final String email);

    Boolean existsByMobile(final String mobile);

    default User fetchById(final long id) {
        return findById(id).orElseThrow(runtimeExceptionSupplier("user is not exist with id %d".formatted(id), UserException.class));
    }

}
