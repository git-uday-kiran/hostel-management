package coder.jdev.repositories.users;

import coder.jdev.models.users.FileRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileRecordRepository extends JpaRepository<FileRecord, Long> {

    List<FileRecord> findAllByUploadedDateTime(LocalDateTime dateTime);

    List<FileRecord> findAllByUploadedDateTimeGreaterThanEqual(LocalDateTime dateTime);

    List<FileRecord> findAllByUserId(long userId);
}
