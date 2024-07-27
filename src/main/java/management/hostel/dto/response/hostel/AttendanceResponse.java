package management.hostel.dto.response.hostel;

import lombok.Builder;
import lombok.Data;
import management.hostel.dto.response.users.UserResponse;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AttendanceResponse {

	LocalDate date;

	List<UserResponse> users;

}
