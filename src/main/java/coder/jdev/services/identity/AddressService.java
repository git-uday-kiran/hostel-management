package coder.jdev.services.identity;

import coder.jdev.dto.request.identity.AddressRequest;
import coder.jdev.dto.response.identity.AddressResponse;
import coder.jdev.exceptions.identity.AddressException;
import coder.jdev.models.identity.Address;
import coder.jdev.models.identity.City;
import coder.jdev.models.identity.Country;
import coder.jdev.models.identity.State;
import coder.jdev.repositories.identity.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AddressService {

    private final CityService cityService;
    private final AddressRepository repository;

    public Address findById(long id) {
        return repository.findById(id).orElseThrow(() -> new AddressException("Address with id: %d does not exist.".formatted(id)));
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public AddressResponse addAddress(AddressRequest request) {
        Address address = modelOf(request);
        repository.saveAndFlush(address);
        log.info("Address with id: {} is saved.", address.getId());
        return responseOf(address);
    }

    public List<AddressResponse> toResponseList(List<Address> addresses) {
        return addresses.stream()
                .map(this::responseOf)
                .toList();
    }

    public AddressResponse responseOf(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .address(address.getAddress())
                .district(address.getDistrict())
                .cityResponse(cityService.responseOf(address.getCity()))
                .pincode(address.getPincode())
                .build();
    }

    public Address modelOf(AddressRequest request) {
        final City city = cityService.findById(request.getCityId());
        final State state = city.getState();
        final Country country = state.getCountry();

        return Address.builder()
                .address(request.getAddress())
                .district(request.getDistrict())
                .pincode(request.getPincode())
                .city(city)
                .state(state)
                .country(country)
                .build();
    }

}
