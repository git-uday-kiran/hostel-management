package coder.jdev.dto.request.users;

import coder.jdev.dto.request.identity.AddressRequest;
import coder.jdev.models.users.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull
    @NotBlank
    private String username;

    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "(\\+?(91)?\\s{0,2})\\d{10}", message = "doesn't seem to be a valid mobile number")
    private String mobile;

    @Valid
    @NotNull
    private AddressRequest addressRequest;

    @Past
    @NotNull
    public LocalDate dateOfBirth;

    @NotNull
    private User.Gender gender;

}
