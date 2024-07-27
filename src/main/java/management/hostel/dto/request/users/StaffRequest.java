package management.hostel.dto.request.users;

import management.hostel.models.users.Staff;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffRequest {

	@NotNull
	private Long userId;

	@NotNull
	@Positive
	private Long hostelId;

	@NotNull
	private Staff.Role role;

	@NotNull
	private Double salary;

	@NotNull
	private LocalDate joiningDate;

	private LocalDate leaveDate;

}
