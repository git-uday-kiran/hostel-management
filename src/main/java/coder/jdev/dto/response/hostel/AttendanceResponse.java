package coder.jdev.dto.response.hostel;

import coder.jdev.dto.response.users.StaffResponse;
import coder.jdev.dto.response.users.StudentResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AttendanceResponse {

    LocalDate date;

    private List<StudentResponse> studentResponseList;

    private List<StaffResponse> staffResponseList;

}
