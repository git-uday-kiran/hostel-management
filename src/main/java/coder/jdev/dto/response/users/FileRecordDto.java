package coder.jdev.dto.response.users;

import coder.jdev.models.users.FileRecord;
import coder.jdev.models.users.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FileRecordDto {

    private Long id;

    private User user;

    private String fileName;

    private LocalDateTime uploadedDateTime;

    private byte[] fileData;

    public static FileRecordDto wrapWith(FileRecord fileRecord) {
        return FileRecordDto.builder()
                .id(fileRecord.getId())
                .user(fileRecord.getUser())
                .fileName(fileRecord.getFileName())
                .uploadedDateTime(fileRecord.getUploadedDateTime())
                .fileData(fileRecord.getFileData())
                .build();
    }

}
