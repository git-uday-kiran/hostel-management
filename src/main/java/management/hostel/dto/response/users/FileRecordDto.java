package management.hostel.dto.response.users;

import management.hostel.models.users.Document;
import management.hostel.models.users.User;
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

	public static FileRecordDto wrapWith(Document fileRecord) {
		return FileRecordDto.builder()
			.id(fileRecord.getId())
			.user(fileRecord.getUser())
			.fileName(fileRecord.getName())
			.uploadedDateTime(fileRecord.getUpdatedAt())
			.fileData(fileRecord.getData())
			.build();
	}

}
