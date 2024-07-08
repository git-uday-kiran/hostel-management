package coder.jdev.repositories.users;

import coder.jdev.models.users.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FileRecordRepository extends JpaRepository<Document, Long> {

	List<Document> findAllByCreatedAt(LocalDateTime dateTime);

	List<Document> findAllByCreatedAtGreaterThanEqual(LocalDateTime dateTime);

	List<Document> findAllByUserId(long userId);
}
