package coder.jdev.dto.response.users;

import coder.jdev.dto.response.identity.AddressResponse;
import coder.jdev.models.users.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String mobile;

    private AddressResponse addressResponse;

    public LocalDate dateOfBirth;

    private User.Gender gender;

}
