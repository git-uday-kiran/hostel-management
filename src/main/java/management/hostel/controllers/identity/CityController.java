package management.hostel.controllers.identity;

import management.hostel.dto.response.TemplateResponse;
import management.hostel.dto.response.identity.CityResponse;
import management.hostel.exceptions.handlers.HostelManagementExceptionHandler;
import management.hostel.repositories.identity.CityRepository;
import management.hostel.services.identity.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cities")
public class CityController implements HostelManagementExceptionHandler {

	private final CityService service;

	private final CityRepository repository;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CityResponse> getAllCities(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("find")
	@ResponseStatus(HttpStatus.OK)
	public List<TemplateResponse> getCitiesByName(@RequestParam String name, Pageable pageable) {
		return service.getCitiesByName(name, pageable);
	}

}
