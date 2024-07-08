package coder.jdev.dto.response.users;

import coder.jdev.dto.response.identity.AddressResponse;
import coder.jdev.models.users.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {

	public LocalDate dateOfBirth;

	private Long id;

	private String username;

	private String email;

	private String mobile;

	@JsonProperty(value = "address")
	private AddressResponse addressResponse;

	private Gender gender;

}
