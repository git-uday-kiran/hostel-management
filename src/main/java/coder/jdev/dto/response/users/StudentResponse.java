package coder.jdev.dto.response.users;

import coder.jdev.dto.response.hostel.RoomResponse;
import coder.jdev.dto.response.identity.AddressResponse;
import coder.jdev.dto.response.identity.CollegeResponse;
import coder.jdev.models.users.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class StudentResponse {

    private Long id;

    private String username;

    private String email;

    private String mobile;

    private AddressResponse addressResponse;

    public LocalDate dateOfBirth;

    private User.Gender gender;

    private RoomResponse roomResponse;

    private CollegeResponse collegeResponse;

    private LocalDate joiningDate;

    private LocalDate leaveDate;

    private LocalDateTime lastUpdated;

}
