package com.kpaw.world.controller;

import com.kpaw.world.entity.dto.CityDTO;
import com.kpaw.world.service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/world")
public class CityController {

    private final CityService cityService;

    public CityController(CityService theCityService) {
        this.cityService = theCityService;
    }

    @GetMapping("/cities")
    public List<CityDTO> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/cities/{cityId}")
    public CityDTO getCity(@PathVariable int cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping("/cities")
    public CityDTO addCity(@RequestBody @Valid CityDTO theCityDTO) {
        theCityDTO.setId(0);
        cityService.save(theCityDTO);
        return theCityDTO;
    }

    @PutMapping("/cities")
    public CityDTO updateCity(@RequestBody @Valid CityDTO theCityDTO) {
        cityService.save(theCityDTO);
        return theCityDTO;
    }

    @DeleteMapping("/cities/{cityId}")
    public String deleteCity(@PathVariable int cityId) {
        cityService.deleteCityById(cityId);
        return "Delete city id - " + cityId;
    }

    @GetMapping("/cities/search")
    public List<CityDTO> searchByNameAndCountry(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String country) {
        if (name.trim().isEmpty() && country.isEmpty()) {
            return cityService.findAll();
        } else {
            return cityService.searchBy(name, country);
        }
    }
}







