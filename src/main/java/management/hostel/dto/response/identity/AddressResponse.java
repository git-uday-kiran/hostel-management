package management.hostel.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

	private Long id;

	private String address;

	private String district;

	private String city;

	private String state;

	private String country;

	private String pincode;

}
