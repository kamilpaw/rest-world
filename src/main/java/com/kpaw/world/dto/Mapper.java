package com.kpaw.world.dto;

import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    CountryService countryService;

    public CityDTO toDto(City city) {
        CityDTO cityDTO = new CityDTO(city.getId(), city.getName(), city.getCountry().getCode(),
                city.getDistrict(), city.getPopulation());
        return cityDTO;
    }

    public City toCity(CityDTO cityDTO) {
        Country country = countryService.findById(cityDTO.getCountryCode());
        City city = new City(cityDTO.getId(), cityDTO.getName(), country, cityDTO.getDistrict(),
                cityDTO.getPopulation());
        return city;
    }
}

