package management.hostel.services.identity;

import management.hostel.dto.request.identity.AddressRequest;
import management.hostel.dto.response.identity.AddressResponse;
import management.hostel.exceptions.identity.AddressException;
import management.hostel.models.identity.Address;
import management.hostel.models.identity.City;
import management.hostel.models.identity.Country;
import management.hostel.models.identity.State;
import management.hostel.repositories.identity.AddressRepository;
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
			.city(address.getCity().getName())
			.state(address.getState().getName())
			.country(address.getCountry().getName())
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
