package coder.jdev.dto.response.hostel;

import coder.jdev.dto.response.identity.AddressResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostelResponse {

    private Long id;

    private String name;

    private AddressResponse addressResponse;

    private String email;

    private String mobile;

}
