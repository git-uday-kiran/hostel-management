package management.hostel.dto.response.hostel;

import management.hostel.dto.response.identity.AddressResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HostelResponse {

	private Long id;

	private String name;

	private AddressResponse addressResponse;

	private String email;

	private String mobile;

	private List<Long> rooms;
}
