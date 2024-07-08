package management.hostel.services.users;

import management.hostel.dto.request.users.UserRequest;
import management.hostel.dto.response.users.UserResponse;
import management.hostel.exceptions.UserNotFound;
import management.hostel.exceptions.users.UserException;
import management.hostel.models.users.User;
import management.hostel.repositories.users.UserRepository;
import management.hostel.services.identity.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


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

	public User getUserById(long userId) {
		Optional<User> byId = repository.findById(userId);
		return byId.orElseThrow(() -> new UserNotFound("could not find user with id %d".formatted(userId)));
	}

	public User getUserById(String email) {
		Objects.requireNonNull(email, "email can not be null");
		Optional<User> byEmail = repository.findByEmail(email);
		return byEmail.orElseThrow(() -> new UserNotFound("could not find user with email %s".formatted(email)));
	}

	public User getUserByMobile(String mobile) {
		Objects.requireNonNull(mobile, "mobile can not be null");
		Optional<User> byEmail = repository.findByMobile(mobile);
		return byEmail.orElseThrow(() -> new UserNotFound("could not find user with mobile %s".formatted(mobile)));
	}

	public UserResponse getUserResponseById(long userId) {
		return responseOf(getUserById(userId));
	}

	public UserResponse getUserResponseByEmail(final String email) throws UserException {
		return responseOf(getUserById(email));
	}

	public UserResponse getUserResponseByMobile(final String mobile) throws UserException {
		return responseOf(getUserByMobile(mobile));
	}

	public Long addUser(UserRequest request) {
		log.info("adding user {}", request);
		User user = modelOf(request);
		repository.saveAndFlush(user);
		log.info("User with id: {} is saved", user.getId());
		return user.getId();
	}

	public List<UserResponse> toResponseList(List<User> hostels) {
		return hostels.stream().map(this::responseOf).toList();
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

	public User modelOf(UserRequest request) {
		return User.builder()
			.username(request.getUsername())
			.email(request.getEmail())
			.mobile(request.getMobile())
			.address(addressService.modelOf(request.getAddress()))
			.dateOfBirth(request.getDateOfBirth())
			.gender(request.getGender())
			.build();
	}

}
