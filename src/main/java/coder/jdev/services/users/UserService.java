package coder.jdev.services.users;

import coder.jdev.dto.request.users.UserRequest;
import coder.jdev.dto.response.users.UserResponse;
import coder.jdev.exceptions.users.UserException;
import coder.jdev.models.users.User;
import coder.jdev.repositories.users.UserRepository;
import coder.jdev.services.identity.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static coder.jdev.utils.RequestValidators.validateUserRequest;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final AddressService addressService;

    public List<UserResponse> findAll() {
        return toResponseList(repository.findAll());
    }

    public UserResponse findById(final long userId) throws UserException {
        return responseOf(fetchById(userId));
    }

    public UserResponse findByEmail(final String email) throws UserException {
        return responseOf(repository.findByEmail(email).orElseThrow(() -> new UserException("user with email: %s does not exist".formatted(email))));
    }

    public UserResponse findByMobile(final String mobile) throws UserException {
        return responseOf(repository.findByMobile(mobile).orElseThrow(() -> new UserException("user with mobile: %s does not exist ".formatted(mobile))));
    }

    public UserResponse addUser(UserRequest request) {
        validateUserRequest(request, repository);
        User user = modelOf(request);
        repository.saveAndFlush(user);
        log.info("User with id: {} is saved", user.getId());
        return responseOf(user);
    }

    public List<UserResponse> toResponseList(List<User> hostels) {
        return hostels.stream()
                .map(this::responseOf)
                .toList();
    }

    public UserResponse responseOf(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .addressResponse(addressService.responseOf(user.getAddress()))
                .build();
    }

    public User fetchById(final long userId) {
        return repository.fetchById(userId);
    }

    public User modelOf(UserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .address(addressService.modelOf(request.getAddressRequest()))
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .build();
    }

}
