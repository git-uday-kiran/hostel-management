package coder.jdev.services.users;

import coder.jdev.dto.request.users.StaffRequest;
import coder.jdev.dto.response.users.StaffResponse;
import coder.jdev.models.hostel.Hostel;
import coder.jdev.models.users.User;
import coder.jdev.repositories.hostel.HostelRepository;
import coder.jdev.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import coder.jdev.models.users.Staff;
import coder.jdev.repositories.users.StaffRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static coder.jdev.utils.RequestValidators.validateStaffRequest;

@Log4j2
@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository repository;
    private final UserRepository userRepository;
    private final HostelRepository hostelRepository;

    public List<StaffResponse> getAllStaff() {
        return toResponseList(repository.findAll());
    }

    public List<Staff> findAllByJoiningDate(LocalDate date) {
        return repository.findAllByJoiningDate(date);
    }

    public List<Staff> findAllByJoiningDateIsGreaterThanEqual(LocalDate fromDate) {
        return repository.findAllByJoiningDateIsGreaterThanEqual(fromDate);
    }

    public List<Staff> findAllByLeaveDate(LocalDate date) {
        return repository.findAllByLeaveDate(date);
    }

    public List<Staff> findAllByLeaveDateIsGreaterThanEqual(LocalDate fromDate) {
        return repository.findAllByLeaveDateIsGreaterThanEqual(fromDate);
    }

    public StaffResponse addStaff(StaffRequest request) {
        validateStaffRequest(request, repository);
        final Staff staff = modelOf(request);
        repository.saveAndFlush(staff);
        log.info("Staff with id: {} is saved", staff.getId());
        return responseOf(staff);
    }

    public List<StaffResponse> toResponseList(final List<Staff> staff) {
        return staff.stream()
                .map(this::responseOf)
                .toList();
    }

    public StaffResponse responseOf(Staff staff) {
        return StaffResponse.builder()
                .role(staff.getRole())
                .salary(staff.getSalary())
                .joiningDate(staff.getJoiningDate())
                .leaveDate(staff.getLeaveDate())
                .lastUpdated(staff.getLastUpdated())
                .build();
    }

    public Staff modelOf(StaffRequest request) {
        final User user = userRepository.fetchById(request.getUserId());
        final Hostel hostel = hostelRepository.fetchById(request.getHostelId());
        return Staff.builder()
                .id(request.getUserId())
                .user(user)
                .role(request.getRole())
                .salary(request.getSalary())
                .hostel(hostel)
                .joiningDate(request.getJoiningDate())
                .leaveDate(request.getLeaveDate())
                .build();
    }
}
