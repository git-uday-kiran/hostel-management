package coder.jdev.dto.request.users;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileRecordRequest {

    private Long userId;

    private String fileName;

    private LocalDateTime uploadedDateTime;

    private byte[] fileData;
}
