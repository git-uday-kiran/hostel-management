package coder.jdev.services.identity;

import coder.jdev.configs.Configs;
import coder.jdev.dto.response.TemplateResponse;
import coder.jdev.dto.response.identity.CityResponse;
import coder.jdev.exceptions.identity.CityException;
import coder.jdev.models.identity.City;
import coder.jdev.repositories.identity.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static coder.jdev.utils.Utils.*;

@Service
@RequiredArgsConstructor
public class CityService {

    private final Configs configs;

    private final CityRepository repository;

    private final StateService stateService;

    public List<CityResponse> findAll(Pageable pageable) {
        return toResponseList(repository.findAll(pageable).toList());
    }

    public City findById(long id) {
        return repository.findById(id).orElseThrow(() -> new CityException("City with id: %d does not exist.".formatted(id)));
    }

    public List<CityResponse> findAllByStateId(long stateId) {
        return toResponseList(repository.findAllByStateId(stateId));
    }

    public List<TemplateResponse> getCitiesByName(final String cityName, Pageable pageable) {
        checkPageSize(pageable.getPageSize(), configs.getCitiesMaxPageSize());
        List<City> cities = repository.findAllByNameLikeIgnoreCase("%" + cityName + "%", pageable);
        return cities.stream()
                .map(city -> TemplateResponse.builder()
                        .id(city.getId())
                        .name(city.getName())
                        .build())
                .toList();
    }

    public List<CityResponse> toResponseList(List<City> cities) {
        return cities.stream()
                .map(this::responseOf)
                .toList();
    }

    public CityResponse responseOf(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .stateCode(city.getStateCode())
                .countryCode(city.getCountryCode())
                .stateResponse(stateService.responseOf(city.getState()))
                .build();
    }

}
