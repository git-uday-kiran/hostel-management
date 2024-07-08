package management.hostel.services.identity;

import management.hostel.dto.response.TemplateResponse;
import management.hostel.dto.response.identity.CountryResponse;
import management.hostel.exceptions.identity.CountryException;
import management.hostel.models.identity.Country;
import management.hostel.repositories.identity.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository repository;

	public Country findById(long id) {
		return repository.findById(id).orElseThrow(() -> new CountryException("Country with id: %d does not exist".formatted(id)));
	}

	public List<CountryResponse> findAll(Pageable pageable) {
		return toResponseList(repository.findAll(pageable).toList());
	}

	public List<TemplateResponse> getCountriesByName(final String countryName, final Pageable pageable) {
		List<Country> countries = repository.findAllByNameLikeIgnoreCase("%" + countryName + "%", pageable);
		return countries.stream()
			.map(country -> TemplateResponse.builder()
				.id(country.getId())
				.name(country.getName())
				.build())
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
			.name(country.getName())
			.capital(country.getCapital())
			.currencyName(country.getCurrencyName())
			.phoneCode(country.getPhoneCode())
			.emoji(country.getEmoji()).build();
	}

}
