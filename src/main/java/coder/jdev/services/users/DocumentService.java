package coder.jdev.services.users;

import coder.jdev.exceptions.users.DocumentUploadFailed;
import coder.jdev.models.users.Document;
import coder.jdev.models.users.User;
import coder.jdev.repositories.users.FileRecordRepository;
import coder.jdev.repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

	private final FileRecordRepository repository;
	private final UserRepository userRepository;

	public Map<Long, String> findAll() {
		return repository.findAll().stream()
			.collect(Collectors.toMap(Document::getId, Document::getName));
	}

	public Optional<Resource> getResource(long fileId) {
		Optional<Document> byId = repository.findById(fileId);
		if (byId.isPresent()) {
			Document document = byId.get();
			Resource resource = new ByteArrayResource(document.getData(), document.getName()) {
				@Override
				public String getFilename() {
					return document.getName();
				}
			};
			return Optional.of(resource);
		}
		return Optional.empty();
	}

	public long addDocument(long userId, MultipartFile file) {
		Optional<User> byId = userRepository.findById(userId);
		if (byId.isPresent()) {
			String fileName = file.getOriginalFilename();
			try {
				Document record = Document.builder()
					.name(fileName)
					.user(byId.get())
					.data(file.getBytes())
					.build();
				return repository.saveAndFlush(record).getId();
			} catch (Exception e) {
				throw new DocumentUploadFailed("something went wrong", e);
			}
		} else {
			throw new DocumentUploadFailed("user with id %d not exist".formatted(userId));
		}
	}

	public List<Document> findAllByUploadedDateTime(LocalDateTime dateTime) {
		return repository.findAllByCreatedAt(dateTime);
	}

	public List<Document> findAllByUploadedDateTimeGreaterThanEqual(LocalDateTime fromDateTime) {
		return repository.findAllByCreatedAtGreaterThanEqual(fromDateTime);
	}

	public List<Document> findAllByUserId(long userId) {
		return repository.findAllByUserId(userId);
	}

}
