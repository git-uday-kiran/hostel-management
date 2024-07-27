package management.hostel.services.hostel;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import management.hostel.dto.request.hostel.HostelRequest;
import management.hostel.dto.response.hostel.HostelResponse;
import management.hostel.exceptions.ResourceNotFoundException;
import management.hostel.exceptions.hostel.HostelException;
import management.hostel.models.hostel.Hostel;
import management.hostel.models.hostel.Room;
import management.hostel.models.identity.Address;
import management.hostel.repositories.hostel.HostelRepository;
import management.hostel.services.identity.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class HostelService {

	private final HostelRepository repository;

	private final AddressService addressService;

	public List<HostelResponse> findAll() {
		return toResponseList(repository.findAll());
	}

	public Hostel getHostelById(long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hostel does not exist with id %d".formatted(id)));
	}

	public HostelResponse getHostelResponseById(long id) {
		return responseOf(getHostelById(id));
	}

	public Hostel getHostelByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Hostel with email: %s does not exist.".formatted(email)));
	}

	public HostelResponse getHostelResponseByEmail(String email) {
		return responseOf(getHostelByEmail(email));
	}

	public Hostel getHostelByMobile(String mobile) throws HostelException {
		return repository.findByMobile(mobile).orElseThrow(() -> new ResourceNotFoundException("Hostel with mobile: %s does not exist.".formatted(mobile)));
	}

	public HostelResponse getHostelResponseByMobile(String mobile) {
		return responseOf(getHostelByMobile(mobile));
	}

	public HostelResponse addHostel(HostelRequest hostelRequest) {
		Hostel hostel = modelOf(hostelRequest);
		repository.saveAndFlush(hostel);
		log.info("Hostel with id: {} is saved.", hostel.getId());
		return responseOf(hostel);
	}

	public void removeHostelById(long hostelId) {
		repository.removeById(hostelId);
	}

	public List<HostelResponse> toResponseList(List<Hostel> hostels) {
		return hostels.stream()
			.map(this::responseOf)
			.toList();
	}

	public HostelResponse responseOf(Hostel hostel) {
		return HostelResponse.builder()
			.id(hostel.getId())
			.name(hostel.getName())
			.email(hostel.getEmail())
			.mobile(hostel.getMobile())
			.addressResponse(addressService.responseOf(hostel.getAddress()))
			.rooms(hostel.getRooms().stream()
				.map(Room::getId)
				.toList())
			.build();
	}

	public Hostel modelOf(HostelRequest request) {
		final Address address = addressService.modelOf(request.getAddressRequest());
		return Hostel.builder()
			.name(request.getName())
			.address(address)
			.email(request.getEmail())
			.mobile(request.getMobile())
			.rooms(new ArrayList<>())
			.build();
	}

}
