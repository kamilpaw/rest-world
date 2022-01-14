package com.kpaw.world.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kpaw.world.dto.CityDTO;
import com.kpaw.world.dto.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.kpaw.world.entity.City;
import com.kpaw.world.service.CityService;

import javax.validation.Valid;

@RestController
@RequestMapping("/world")
public class CityController {

    private CityService cityService;
    private Mapper mapper;

    public CityController(CityService theCityService, Mapper theMapper) {
        this.cityService = theCityService;
        this.mapper = theMapper;

    }

    @GetMapping("/cities")
    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        for (City c : cityService.findAll()) {
            cityDTOS.add(mapper.toDto(c));
        }
        return cityDTOS;
    }

    @GetMapping("/cities/{cityId}")
    public CityDTO getCity(@PathVariable int cityId) {
        City theCity = cityService.findById(cityId);
        if (theCity == null) {
            throw new CityNotFoundException("City id not found - " + cityId);
        }
        return mapper.toDto(theCity);
    }

    @PostMapping("/cities")
    public CityDTO addCity(@RequestBody @Valid CityDTO theCityDTO) {
        theCityDTO.setId(0);
        cityService.save(mapper.toCity(theCityDTO));
        return theCityDTO;
    }

    @PutMapping("/cities")
    public CityDTO updateCity(@RequestBody @Valid CityDTO theCityDTO) {
        City theCity = cityService.findById(theCityDTO.getId());
        if (theCity == null) {
            throw new CityNotFoundException("City id not found - " + theCityDTO.getId());
        }
        cityService.save(theCity);
        return theCityDTO;
    }

    @DeleteMapping("/cities/{cityId}")
    public String deleteCity(@PathVariable int cityId) {
        City theCity = cityService.findById(cityId);
        if (theCity == null) {
            throw new CityNotFoundException("City id not found - " + cityId);
        }
        cityService.deleteCityById(cityId);
        return "Delete city id - " + cityId;
    }

    @GetMapping( "/cities/search")
    public List<CityDTO> searchByNameAndCountry(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String country) {
        List<CityDTO> cityDTOS = new ArrayList<>();
        List<City> theCities;
        if (name.trim().isEmpty() && country.isEmpty()) {
            theCities = cityService.findAll();
        } else {
            theCities = cityService.searchBy(name, country);
        }
        for (City c : theCities) {
            cityDTOS.add(mapper.toDto(c));
        }
        return cityDTOS;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> ValidationExceptions(MethodArgumentNotValidException ex){

        Map<String, String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String errorMessage=error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}







