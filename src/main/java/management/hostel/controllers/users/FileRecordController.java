package management.hostel.controllers.users;

import management.hostel.services.users.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FileRecordController {

	private final DocumentService service;

	@GetMapping
	public Map<Long, String> getFiles() {
		return service.findAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<?> getFileRecord(@PathVariable long id) {
		Optional<Resource> optionalResource = service.getResource(id);
		if (optionalResource.isPresent()) {
			Resource resource = optionalResource.get();
			return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=" + resource.getFilename())
				.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadFileRecord(@RequestParam long userId, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) return "please-provide-file";
		return String.valueOf(service.addDocument(userId, file));
	}

}
