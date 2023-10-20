package coder.jdev.dto.response.hostel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponse {

    private Long id;

    private String roomNo;

    private int floorNo;

//    private HostelResponse hostelResponse;

}
