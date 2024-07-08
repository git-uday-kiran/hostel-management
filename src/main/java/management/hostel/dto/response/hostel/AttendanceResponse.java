package management.hostel.dto.response.hostel;

import management.hostel.dto.response.users.StaffResponse;
import management.hostel.dto.response.users.StudentResponse;
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
