package coder.jdev.dto.response.identity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private Long id;

    private String address;

    private String district;

    private CityResponse cityResponse;

    private Integer pincode;

}
