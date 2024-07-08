package management.hostel.utils;

import management.hostel.dto.request.hostel.HostelRequest;
import management.hostel.dto.request.hostel.RoomRequest;
import management.hostel.dto.request.users.StaffRequest;
import management.hostel.dto.request.users.StudentRequest;
import management.hostel.exceptions.hostel.HostelException;
import management.hostel.exceptions.users.StaffException;
import management.hostel.exceptions.users.StudentException;
import management.hostel.repositories.hostel.HostelRepository;
import management.hostel.repositories.hostel.RoomRepository;
import management.hostel.repositories.users.StaffRepository;
import management.hostel.repositories.users.StudentRepository;
import management.hostel.repositories.users.UserRepository;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static management.hostel.utils.Utils.*;

@UtilityClass
public final class RequestValidators {
	private static final int MAX_ROOMS = 10;

	public static void validateHostelRequest(final HostelRequest request, final HostelRepository repository) {
		final List<String> failList = new ArrayList<>();
		if (repository.existsByName(request.getName())) failList.add("hostel name");
		if (repository.existsByEmail(request.getEmail())) failList.add("email");

		if (not(failList.isEmpty())) {
			final String message = String.join(", ", failList) + (failList.size() == 1 ? " is " : " are ") + "already taken.";
			throwException(message, HostelException.class);
		}
	}

	public static void validateRoomRequest(final RoomRequest request, final RoomRepository repository, final HostelRepository hostelRepository) {
		if (repository.countAllByHostelId(request.getHostelId()) >= MAX_ROOMS) {
			throwException("maximum rooms %d exceeded to this hostel".formatted(MAX_ROOMS), HostelException.class);
		}

		if (not(hostelRepository.existsById(request.getHostelId()))) {
			throwException("hostel with id %d does not exist.".formatted(request.getHostelId()), HostelException.class);
		}
	}

	public static void validateStudentRequest(final StudentRequest request, final StudentRepository studentRepository, final UserRepository userRepository) {
		if (studentRepository.existsById(request.getUserId())) {
			throwException("student with id %d is already exist.".formatted(request.getUserId()), StudentException.class);
		}
		if (not(userRepository.existsById(request.getUserId()))) {
			throw userIsNotExistWithIdSupplier(request.getUserId()).get();
		}
	}

	public static void validateStaffRequest(final StaffRequest request, final StaffRepository staffRepository) {
		if (staffRepository.existsById(request.getUserId())) {
			throwException("staff with id %d is already exist.".formatted(request.getUserId()), StaffException.class);
		}
	}

}
