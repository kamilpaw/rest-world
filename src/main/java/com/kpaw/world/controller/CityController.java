package com.kpaw.world.controller;

import com.kpaw.world.dto.CityDTO;
import com.kpaw.world.dto.Mapper;
import com.kpaw.world.entity.City;
import com.kpaw.world.service.CityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/world")
public class CityController {

    private final CityService cityService;
    private final Mapper mapper;

    public CityController(CityService cityService, Mapper mapper) {
        this.cityService = cityService;
        this.mapper = mapper;
    }

    @GetMapping("/cities")
    public List<CityDTO> findAll() {
        return cityService.findAll().stream().map(mapper::toCityDto).collect(Collectors.toList());
    }

    @GetMapping("/cities/{cityId}")
    public CityDTO getCity(@PathVariable int cityId) {
        return mapper.toCityDto(cityService.findById(cityId));
    }


    @PostMapping(value = "/cities",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
        public CityDTO addCity(@RequestBody CityDTO theCityDTO) {
        theCityDTO.setId(0);
        City city = mapper.toCity(theCityDTO);
        cityService.save(city);
        return theCityDTO;
    }

    @PutMapping(value = "/cities",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CityDTO updateCity(@RequestBody @Valid CityDTO theCityDTO) {
        cityService.save(mapper.toCity(theCityDTO));
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
            return cityService.findAll().stream().map(mapper::toCityDto).collect(Collectors.toList());
        } else {
            return cityService.searchBy(name, country).stream().map(mapper::toCityDto).collect(Collectors.toList());
        }
    }
}







