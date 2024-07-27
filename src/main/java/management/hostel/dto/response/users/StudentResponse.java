package management.hostel.dto.response.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import management.hostel.dto.response.hostel.RoomResponse;
import management.hostel.dto.response.identity.AddressResponse;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.models.users.Gender;

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

	@JsonProperty(value = "address")
	private AddressResponse addressResponse;

	private Gender gender;

	@JsonProperty(value = "room")
	private RoomResponse roomResponse;

	@JsonProperty(value = "college")
	private CollegeResponse collegeResponse;

	private LocalDate joiningDate;

	private LocalDate leaveDate;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;


}
