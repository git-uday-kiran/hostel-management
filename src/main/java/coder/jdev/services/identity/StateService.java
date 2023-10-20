package coder.jdev.services.identity;

import coder.jdev.dto.response.TemplateResponse;
import coder.jdev.dto.response.identity.StateResponse;
import coder.jdev.exceptions.identity.StateException;
import coder.jdev.models.identity.State;
import coder.jdev.repositories.identity.StateRepository;
import lombok.RequiredArgsConstructor;
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

    public List<TemplateResponse> getStatesByName(final String stateName, final Pageable pageable) {
        List<State> states = repository.findAllByNameLikeIgnoreCase("%" + stateName + "%", pageable);
        return states.stream()
                .map(state -> TemplateResponse.builder()
                        .id(state.getId())
                        .name(state.getName())
                        .build())
                .toList();
    }

    public List<StateResponse> toResponseList(List<State> states) {
        return states.stream()
                .map(this::responseOf)
                .toList();
    }

    public StateResponse responseOf(State state) {
        return StateResponse.builder()
                .id(state.getId())
                .name(state.getName())
                .countryCode(state.getCountryCode())
                .fipsCode(state.getFipsCode())
                .type(state.getType())
                .countryResponse(countryService.responseOf(state.getCountry()))
                .build();
    }

}
