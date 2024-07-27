package management.hostel.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateResponse {

	private Long id;

	private String name;

	private String countryCode;

	private String countryName;

	private String capital;

	private String phoneCode;

	private String currencyName;

	private String countryEmoji;

}


