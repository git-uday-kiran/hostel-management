package management.hostel.controllers.identity;

import management.hostel.dto.response.TemplateResponse;
import management.hostel.dto.response.identity.CollegeResponse;
import management.hostel.services.identity.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("colleges")
public class CollegeController {

	private final CollegeService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CollegeResponse> getAllCities(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("find")
	@ResponseStatus(HttpStatus.OK)
	public List<TemplateResponse> getCitiesByName(@RequestParam String name, Pageable pageable) {
		return service.getCollegesByName(name, pageable);
	}
}
