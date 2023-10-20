package coder.jdev.dto.request.users;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminRequest {

    private Long userId;

    private LocalDateTime expiration;

}
