package management.hostel.controllers.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.services.identity.CollegeService;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("colleges")
public class CollegeController {

	private final CollegeService service;

	@GetMapping
	public List<CollegeResponse> getAllColleges(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("/{collegeId}")
	public CollegeResponse getAllColleges(@PathVariable long collegeId) {
		return service.getCollegeResponseById(collegeId);
	}

	@GetMapping("/name/{name}")
	public List<MapResponse> getCollegeByName(@PathVariable String name, Pageable pageable) {
		return service.getCollegesByName(name, pageable);
	}
}
