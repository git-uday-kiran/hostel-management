package management.hostel.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityResponse {

	private Long id;

	private String name;

	private String stateCode;

	private String countryCode;

	private StateResponse stateResponse;

}
