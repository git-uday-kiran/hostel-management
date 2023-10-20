package coder.jdev.utils;

import coder.jdev.dto.request.hostel.HostelRequest;
import coder.jdev.dto.request.hostel.RoomRequest;
import coder.jdev.dto.request.users.StaffRequest;
import coder.jdev.dto.request.users.StudentRequest;
import coder.jdev.dto.request.users.UserRequest;
import coder.jdev.exceptions.hostel.HostelException;
import coder.jdev.exceptions.users.StaffException;
import coder.jdev.exceptions.users.StudentException;
import coder.jdev.exceptions.users.UserException;
import coder.jdev.repositories.hostel.HostelRepository;
import coder.jdev.repositories.hostel.RoomRepository;
import coder.jdev.repositories.users.StaffRepository;
import coder.jdev.repositories.users.StudentRepository;
import coder.jdev.repositories.users.UserRepository;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static coder.jdev.utils.Utils.*;

@UtilityClass
public final class RequestValidators {
    private static final int MAX_ROOMS = 10;

    public static void validateUserRequest(final UserRequest request, final UserRepository repository) {
        final List<String> failList = new ArrayList<>();

        if (repository.existsByUsername(request.getUsername())) failList.add("username");
        if (repository.existsByEmail(request.getEmail())) failList.add("email");
        if (repository.existsByMobile(request.getMobile())) failList.add("mobile");

        if (not(failList.isEmpty())) {
            final String message = String.join(", ", failList) + (failList.size() == 1 ? " is " : " are ") + "already taken.";
            throwException(message, UserException.class);
        }
    }

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

        final List<String> failList = new ArrayList<>();
        if (not(hostelRepository.existsById(request.getHostelId()))) {
            throwException("hostel with id %d does not exist.".formatted(request.getHostelId()), HostelException.class);
        }


        if (not(failList.isEmpty())) {
            final String message = String.join(", ", failList) + (failList.size() == 1 ? " is " : " are ") + "already taken.";
            throwException(message, StudentException.class);
        }
    }

    public static void validateStudentRequest(final StudentRequest request, final StudentRepository studentRepository, final UserRepository userRepository) {
        if (studentRepository.existsById(request.getUserId())) {
            throwException("student with id %d is already exist.".formatted(request.getUserId()),StudentException.class);
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
