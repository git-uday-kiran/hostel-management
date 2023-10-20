package coder.jdev.controllers.identity;

import coder.jdev.dto.response.TemplateResponse;
import coder.jdev.dto.response.identity.StateResponse;
import coder.jdev.services.identity.StateService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("find")
    @ResponseStatus(HttpStatus.OK)
    public List<TemplateResponse> getStatesByName(@RequestParam String name, Pageable pageable) {
        return service.getStatesByName(name, pageable);
    }

}

