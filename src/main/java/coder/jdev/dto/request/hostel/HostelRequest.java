package coder.jdev.dto.request.hostel;

import coder.jdev.dto.request.identity.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class HostelRequest {

    @NotNull
    @NotBlank
    private String name;

    @Valid
    @NotNull
    private AddressRequest addressRequest;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @Pattern(regexp = "(\\+?(91)?\\s{0,2})\\d{10}", message = "doesn't seem to be a valid mobile number")
    private String mobile;

}
