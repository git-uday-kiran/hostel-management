package coder.jdev.dto.request.users;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long roomId;

    @NotNull
    @Positive
    private Long collegeId;

    @NotNull
    private LocalDate joiningDate;

    private LocalDate leaveDate;

}
