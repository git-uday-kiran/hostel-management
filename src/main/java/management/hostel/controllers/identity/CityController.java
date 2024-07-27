package management.hostel.controllers.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.CityResponse;
import management.hostel.exceptions.handlers.HostelManagementExceptionHandler;
import management.hostel.services.identity.CityService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cities")
public class CityController implements HostelManagementExceptionHandler {

	private final CityService service;

	@GetMapping
	public List<CityResponse> getAllCities(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("/{cityId}")
	public CityResponse getCityById(@PathVariable Long cityId) {
		return service.getCityResponseById(cityId);
	}

	@GetMapping("/name/{cityName}")
	public List<?> getCitiesByName(@PathVariable String cityName, Pageable pageable) {
		return service.getCitiesByName(cityName, pageable);
	}

	@GetMapping("/state/{stateId}")
	public List<?> getCitiesByState(@PathVariable long stateId, Pageable pageable) {
		return service.getCitiesByStateId(stateId);
	}

}
