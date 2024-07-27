package management.hostel.services.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.CountryResponse;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.models.identity.Country;
import management.hostel.repositories.identity.CountryRepository;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository repository;

	public Country getCountryById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country does not exist with id %d".formatted(id)));
	}

	public CountryResponse getCountryResponseById(long id) {
		return responseOf(getCountryById(id));
	}

	public List<CountryResponse> getAllCountriesResponses(Pageable pageable) {
		return toResponseList(repository.findAll(pageable).toList());
	}

	public List<MapResponse> getCountriesByName(final String countryName, final Pageable pageable) {
		List<Country> countries = repository.findAllByNameLikeIgnoreCase("%" + countryName + "%", pageable);
		return countries.stream()
			.map(country -> MapResponse.create()
				.set("id", country.getId())
				.set("country_name", country.getName()))
			.toList();
	}


	public List<CountryResponse> toResponseList(List<Country> countries) {
		return countries.stream()
			.map(this::responseOf)
			.toList();
	}

	public CountryResponse responseOf(Country country) {
		return CountryResponse.builder()
			.id(country.getId())
			.countryName(country.getName())
			.capital(country.getCapital())
			.currencyName(country.getCurrencyName())
			.currencySymbol(country.getCurrencySymbol())
			.phoneCode(country.getPhoneCode())
			.countryEmoji(country.getEmoji()).build();
	}

}
