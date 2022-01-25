package com.kpaw.world.dto;

import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.entity.CountryLanguage;
import com.kpaw.world.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    CountryService countryService;

    public Mapper(CountryService countryService) {
        this.countryService = countryService;
    }

    public CityDTO toCityDto(City city) {
        return new CityDTO(city.getId(), city.getName(), city.getCountry().getCode(),
                city.getDistrict(), city.getPopulation());

    }

    public City toCity(CityDTO cityDTO) {
        Country country = countryService.findCountryById(cityDTO.getCountryCode());
        return new City(cityDTO.getId(), cityDTO.getName(), country, cityDTO.getDistrict(),
                cityDTO.getPopulation());
    }

    public CountryDTO toCountryDTO(Country country) {
        if (country.getCapital() == null) {
            return new CountryDTO(country.getCode(), country.getName(), country.getRegion(), country.getSurfaceArea(),
                    country.getIndepYear(), country.getPopulation(), country.getLifeExpectancy(), country.getgNP(),
                    country.getgNPOld(), country.getLocalName(), country.getGovernmentForm(), country.getHeadOfState(),
                    null, country.getCode2());
        }
        return new CountryDTO(country.getCode(), country.getName(), country.getRegion(), country.getSurfaceArea(),
                country.getIndepYear(), country.getPopulation(), country.getLifeExpectancy(), country.getgNP(),
                country.getgNPOld(), country.getLocalName(), country.getGovernmentForm(), country.getHeadOfState(),
                country.getCapital().getId(), country.getCode2());
    }

    public CountryLanguageDTO toCountryLanguageDTO(CountryLanguage countryLanguage) {
        return new CountryLanguageDTO(countryLanguage.getCountryCode().getCode(),
                countryLanguage.getLanguage(), countryLanguage.getIsOfficial(), countryLanguage.getPercentage());
    }


}

