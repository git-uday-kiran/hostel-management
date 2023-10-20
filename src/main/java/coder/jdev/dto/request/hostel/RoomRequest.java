package coder.jdev.dto.request.hostel;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    @NotNull
    @Positive
    private Long hostelId;

    @Min(1)
    @Max(9)
    @NotNull
    @Positive
    private Integer floorNo;

}
