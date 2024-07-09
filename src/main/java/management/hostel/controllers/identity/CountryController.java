package management.hostel.controllers.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.CountryResponse;
import management.hostel.services.identity.CountryService;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("countries")
public class CountryController {

	private final CountryService service;

	@GetMapping
	public List<CountryResponse> getAllCountries(Pageable pageable) {
		return service.getAllCountriesResponses(pageable);
	}

	@GetMapping("/{countryId}")
	public CountryResponse getCountryById(@PathVariable long countryId) {
		return service.getCountryResponseById(countryId);
	}

	@GetMapping("/name/{countryName}")
	public List<MapResponse> getCountriesByName(@PathVariable String countryName, Pageable pageable) {
		return service.getCountriesByName(countryName, pageable);
	}

}
