package management.hostel.dto.response.hostel;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomResponse {

	private Long id;

	private int roomNo;

	private int floorNo;

	private long hostelId;

	private int capacity;

	private List<Long> userIds;

}
