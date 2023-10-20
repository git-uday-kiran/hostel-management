package coder.jdev.dto.request.users;

import coder.jdev.models.users.Staff;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class StaffRequest {

    @NotNull
    private Long userId;

    @NotNull
    @Positive
    private Long hostelId;

    @NotNull
    private Staff.Role role;

    @NotNull
    private Double salary;

    @NotNull
    private LocalDate joiningDate;

    private LocalDate leaveDate;

}
