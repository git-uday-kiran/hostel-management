package management.hostel.services.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.configs.Configs;
import management.hostel.dto.response.identity.CityResponse;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.models.identity.City;
import management.hostel.models.identity.Country;
import management.hostel.models.identity.State;
import management.hostel.repositories.identity.CityRepository;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static management.hostel.utils.Utils.checkPageSize;

@Service
@RequiredArgsConstructor
public class CityService {

	private final Configs configs;

	private final CityRepository repository;

	public List<CityResponse> findAll(Pageable pageable) {
		return toResponseList(repository.findAll(pageable).toList());
	}

	public City findById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("city does not exist with id %d".formatted(id)));
	}

	public CityResponse getCityResponseById(long id) {
		return responseOf(findById(id));
	}

	public List<CityResponse> getCitiesByStateId(long stateId) {
		return toResponseList(repository.findAllByStateId(stateId));
	}

	public List<MapResponse> getCitiesByName(final String cityName, Pageable pageable) {
		checkPageSize(pageable.getPageSize(), configs.getCitiesMaxPageSize());
		List<City> cities = repository.findAllByNameLikeIgnoreCase("%" + cityName + "%", pageable);

		return cities.stream()
			.map(city -> MapResponse.create()
				.set("id", city.getId())
				.set("name", city.getName()))
			.toList();
	}

	public List<CityResponse> toResponseList(List<City> cities) {
		return cities.stream()
			.map(this::responseOf)
			.toList();
	}

	public CityResponse responseOf(City city) {
		State state = city.getState();
		Country country = city.getCountry();
		return CityResponse.builder()
			.id(city.getId())
			.name(city.getName())
			.stateCode(city.getStateCode())
			.countryCode(city.getCountryCode())
			.stateName(state.getName())
			.countryName(country.getName())
			.capital(country.getCapital())
			.currencyName(country.getCurrencyName())
			.countryEmoji(country.getEmoji())
			.phoneCode(country.getPhoneCode())
			.build();
	}

}
