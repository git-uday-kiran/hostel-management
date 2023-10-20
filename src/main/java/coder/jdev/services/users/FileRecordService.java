package coder.jdev.services.users;

import coder.jdev.dto.request.users.FileRecordRequest;
import lombok.RequiredArgsConstructor;
import coder.jdev.models.users.FileRecord;
import coder.jdev.repositories.users.FileRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileRecordService {

    private final FileRecordRepository repository;

    public List<FileRecord> findAll() {
        return repository.findAll();
    }

    public List<FileRecord> findAllByUploadedDateTime(LocalDateTime dateTime) {
        return repository.findAllByUploadedDateTime(dateTime);
    }

    public List<FileRecord> findAllByUploadedDateTimeGreaterThanEqual(LocalDateTime fromDateTime) {
        return repository.findAllByUploadedDateTimeGreaterThanEqual(fromDateTime);
    }

    public List<FileRecord> findAllByUserId(long userId) {
        return repository.findAllByUserId(userId);
    }

}
