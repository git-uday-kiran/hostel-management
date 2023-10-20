package coder.jdev.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateResponse {

    private Long id;

    private String name;

    private CountryResponse countryResponse;

    private String countryCode;

    private String fipsCode;

    private String type;
}
