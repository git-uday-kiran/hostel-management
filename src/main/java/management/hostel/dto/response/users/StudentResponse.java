package management.hostel.dto.response.users;

import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.dto.response.identity.AddressResponse;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.models.users.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class StudentResponse {

	public LocalDate dateOfBirth;
	private Long id;
	private String username;
	private String email;
	private String mobile;
	private AddressResponse addressResponse;
	private Gender gender;

	private RoomResponse roomResponse;

	private CollegeResponse collegeResponse;

	private LocalDate joiningDate;

	private LocalDate leaveDate;

	private LocalDateTime lastUpdated;

}
