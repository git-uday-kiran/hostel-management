package coder.jdev.dto.request.identity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50, message = "address must be within {min} to {max} digits")
    private String address;

    @NotNull
    @NotBlank
    private String district;

    @NotNull
    @Positive
    private Long cityId;

    @NotNull
    @Min(value = 100000, message = "pin-code must be six digits")
    @Max(value = 999999, message = "pin-code must be six digits")
    private int pincode;

}


