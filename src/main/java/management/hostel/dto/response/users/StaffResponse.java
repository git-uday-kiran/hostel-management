package management.hostel.dto.response.users;

import management.hostel.models.users.Staff;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class StaffResponse {
	private Staff.Role role;
	private Double salary;
	private LocalDate joiningDate;
	private LocalDate leaveDate;
	private LocalDateTime lastUpdated;
}
