package coder.jdev.controllers.identity;

import coder.jdev.dto.response.TemplateResponse;
import coder.jdev.dto.response.identity.CountryResponse;
import coder.jdev.services.identity.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("countries")
public class CountryController {

    private final CountryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CountryResponse> getAllCountries(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("find")
    @ResponseStatus(HttpStatus.OK)
    public List<TemplateResponse> getCountriesByName(@RequestParam String name, Pageable pageable) {
        return service.getCountriesByName(name, pageable);
    }

}
