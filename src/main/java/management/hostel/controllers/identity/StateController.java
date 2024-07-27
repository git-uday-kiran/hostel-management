package management.hostel.controllers.identity;

import lombok.RequiredArgsConstructor;
import management.hostel.dto.response.identity.StateResponse;
import management.hostel.services.identity.StateService;
import management.hostel.utils.MapResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("states")
public class StateController {

	private final StateService service;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<StateResponse> getAllStates(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping("/{stateId}")
	@ResponseStatus(HttpStatus.OK)
	public StateResponse getStateById(@PathVariable long stateId, Pageable pageable) {
		return service.getStateById(stateId, pageable);
	}

	@GetMapping("/name/{stateName}")
	@ResponseStatus(HttpStatus.OK)
	public List<MapResponse> getStatesByName(@PathVariable String stateName, Pageable pageable) {
		return service.getStatesByName(stateName, pageable);
	}

}

