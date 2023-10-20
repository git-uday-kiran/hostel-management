package coder.jdev.controllers.identity;

import coder.jdev.dto.response.TemplateResponse;
import coder.jdev.dto.response.identity.CityResponse;
import coder.jdev.exceptions.handlers.HostelManagementExceptionHandler;
import coder.jdev.repositories.identity.CityRepository;
import coder.jdev.services.identity.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("cities")
public class CityController implements HostelManagementExceptionHandler {

    private final CityService service;

    private final CityRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityResponse> getAllCities(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("find")
    @ResponseStatus(HttpStatus.OK)
    public List<TemplateResponse> getCitiesByName(@RequestParam String name, Pageable pageable) {
        return service.getCitiesByName(name, pageable);
    }

}
