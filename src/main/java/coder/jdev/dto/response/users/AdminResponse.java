package coder.jdev.dto.response.users;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminResponse {

    private Long userId;

    private LocalDateTime expiration;

}
