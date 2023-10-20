package coder.jdev.services.hostel;

import coder.jdev.dto.request.hostel.AttendanceRequest;
import coder.jdev.dto.response.hostel.AttendanceResponse;
import coder.jdev.dto.response.users.StaffResponse;
import coder.jdev.dto.response.users.StudentResponse;
import coder.jdev.exceptions.hostel.AttendanceException;
import coder.jdev.models.hostel.Attendance;
import coder.jdev.repositories.hostel.AttendanceRepository;
import coder.jdev.services.users.StaffService;
import coder.jdev.services.users.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository repository;
    private final StudentService studentService;
    private final StaffService staffService;

    public AttendanceResponse findByDate(LocalDate date) throws AttendanceException {
        return responseOf(repository.findByDate(date).orElseThrow(() -> new AttendanceException("Attendance on date: %s does not exist".formatted(date.toString()))));
    }

    public AttendanceResponse addAttendance(final AttendanceRequest request) {
        final Attendance attendance = modelOf(request);
        repository.saveAndFlush(attendance);
        log.info("Attendance on {} is created.", request.getDate());
        return responseOf(attendance);
    }

    public List<AttendanceResponse> toResponseList(final List<Attendance> attendanceList) {
        return attendanceList.stream()
                .map(this::responseOf)
                .toList();
    }

    public AttendanceResponse responseOf(final Attendance attendance) {
        final List<StudentResponse> studentResponseList = studentService.toResponseList(attendance.getStudents());
        final List<StaffResponse> staffResponseList = staffService.toResponseList(attendance.getStaff());
        return AttendanceResponse.builder()
                .date(attendance.getDate())
                .studentResponseList(studentResponseList)
                .staffResponseList(staffResponseList)
                .build();
    }

    public Attendance modelOf(final LocalDate date) {
        return Attendance.builder()
                .staff(new ArrayList<>())
                .students(new ArrayList<>())
                .date(date)
                .build();
    }

    public Attendance modelOf(AttendanceRequest request) {
        return modelOf(request.getDate());
    }

}
