package coder.jdev.services.hostel;

import coder.jdev.dto.request.hostel.HostelRequest;
import coder.jdev.dto.response.hostel.HostelResponse;
import coder.jdev.exceptions.hostel.HostelException;
import coder.jdev.models.hostel.Hostel;
import coder.jdev.models.identity.Address;
import coder.jdev.repositories.hostel.HostelRepository;
import coder.jdev.services.identity.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static coder.jdev.utils.RequestValidators.validateHostelRequest;

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

    public HostelResponse findById(long id) {
        return responseOf(repository.findById(id).orElseThrow(() -> new HostelException("Hostel with id: %d does not exist".formatted(id))));
    }

    public HostelResponse getHostelByEmail(String email) throws HostelException {
        return responseOf(repository.findByEmail(email).orElseThrow(() -> new HostelException("Hostel with email: %s does not exist.".formatted(email))));
    }

    public HostelResponse getHostelByMobile(String mobile) throws HostelException {
        return responseOf(repository.findByMobile(mobile).orElseThrow(() -> new HostelException("Hostel with mobile: %s does not exist.".formatted(mobile))));
    }

    public HostelResponse addHostel(HostelRequest hostelRequest) {
        validateHostelRequest(hostelRequest, repository);
        Hostel hostel = modelOf(hostelRequest);
        repository.saveAndFlush(hostel);
        log.info("Hostel with id: {} is saved.", hostel.getId());
        return responseOf(hostel);
    }

    public void removeHostelById(final long hostelId) {
        repository.removeById(hostelId);
    }

    public List<HostelResponse> toResponseList(List<Hostel> hostels) {
        return hostels.stream().map(this::responseOf).toList();
    }

    public HostelResponse responseOf(Hostel hostel) {
        return HostelResponse.builder()
                .id(hostel.getId())
                .name(hostel.getName())
                .email(hostel.getEmail())
                .mobile(hostel.getMobile())
                .addressResponse(addressService.responseOf(hostel.getAddress()))
                .build();
    }

    public Hostel fetchById(long hostelId) {
        return repository.fetchById(hostelId);
    }

    public Hostel modelOf(HostelRequest request) {
        final Address address = addressService.modelOf(request.getAddressRequest());
        return Hostel.builder().name(request.getName()).address(address).email(request.getEmail()).mobile(request.getMobile()).build();
    }

}
