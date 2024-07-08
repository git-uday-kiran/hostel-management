package management.hostel.dto.request.users;

import management.hostel.dto.request.identity.AddressRequest;
import management.hostel.models.users.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotNull
	@Past(message = "data of birth should be past")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	public LocalDate dateOfBirth;

	@NotNull
	@NotBlank
	private String username;

	@Email
	private String email;

	@NotNull
	@Pattern(regexp = "(\\+91|91)?\\d{10}", message = "doesn't seem to be a valid mobile number")
	private String mobile;

	@Valid
	@NotNull
	@JsonProperty("address")
	private AddressRequest address;

	@NotNull
	private Gender gender;

}
