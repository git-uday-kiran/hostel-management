package management.hostel.dto.request.identity;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

	@NotNull
	@NotBlank
	@Size(min = 5, max = 50, message = "address must be within {min} to {max} digits")
	private String address;

	@NotNull
	@NotBlank
	private String district;

	@NotNull
	@Positive
	private Long cityId;

	@NotNull
	@Pattern(regexp = "\\d{6}", message = "pincode should be six digits")
	private String pincode;
}


