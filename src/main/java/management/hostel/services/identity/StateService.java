package management.hostel.services.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.StateResponse;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.exceptions.identity.StateException;
import management.hostel.models.identity.Country;
import management.hostel.models.identity.State;
import management.hostel.repositories.identity.StateRepository;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {

	private final StateRepository repository;

	private final CountryService countryService;

	public State findById(long id) {
		return repository.findById(id).orElseThrow(() -> new StateException("State with id: %d doest not exist".formatted(id)));
	}

	public List<StateResponse> findAllByCountryId(long countryId) {
		return toResponseList(repository.findAllByCountryId(countryId));
	}

	public List<StateResponse> findAll(Pageable pageable) {
		return toResponseList(repository.findAll(pageable).toList());
	}

	public List<MapResponse> getStatesByName(final String stateName, final Pageable pageable) {
		List<State> states = repository.findAllByNameLikeIgnoreCase("%" + stateName + "%", pageable);
		return states.stream()
			.map(state -> MapResponse.create()
				.set("id", state.getId())
				.set("name", state.getName()))
			.toList();
	}

	public List<StateResponse> toResponseList(List<State> states) {
		return states.stream()
			.map(this::responseOf)
			.toList();
	}

	public StateResponse responseOf(State state) {
		Country country = state.getCountry();
		return StateResponse.builder()
			.id(state.getId())
			.name(state.getName())
			.countryCode(state.getCountryCode())
			.countryName(country.getName())
			.capital(country.getCapital())
			.phoneCode(country.getPhoneCode())
			.countryEmoji(country.getEmoji())
			.currencyName(country.getCurrencyName())
			.build();
	}

	public StateResponse getStateById(long stateId, Pageable pageable) {
		State state = repository.findById(stateId).orElseThrow(() -> new ResourceNotFoundException("State does not exist with id %d".formatted(stateId)));
		return responseOf(state);
	}
}
