package com.kpaw.world.dto;

import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.entity.CountryLanguage;
import com.kpaw.world.service.CityService;
import com.kpaw.world.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {

    public CityDTO toCityDto(City city) {
        return new CityDTO(city.getId(), city.getName(), city.getCountry().getCode(),
                city.getDistrict(), city.getPopulation());

    }

    public City toCity(CityDTO cityDTO, CountryService countryService) {
        Country country = countryService.findCountryById(cityDTO.getCountryCode());
        City city = new City(cityDTO.getId(), cityDTO.getName(), country, cityDTO.getDistrict(),
                cityDTO.getPopulation());
        return city;
    }

    public CountryDTO toCountryDTO(Country country){
        if(country.getCapital()==null){
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

    public Country toCountry(CountryDTO countryDTO, CityService cityService){
        City city = cityService.findCityById(countryDTO.getCapital());
        return new Country(countryDTO.getCode(), countryDTO.getName(), countryDTO.getRegion(), countryDTO.getSurfaceArea(),
                countryDTO.getIndepYear(), countryDTO.getPopulation(), countryDTO.getLifeExpectancy(), countryDTO.getgNP(),
                countryDTO.getgNPOld(), countryDTO.getLocalName(), countryDTO.getGovernmentForm(), countryDTO.getHeadOfState(), city,
                countryDTO.getCode2());
    }

    public CountryLanguageDTO toCountryLanguageDTO(CountryLanguage countryLanguage){
        return new CountryLanguageDTO(countryLanguage.getCountryCode().getCode(),
                countryLanguage.getLanguage(), countryLanguage.getIsOfficial(),countryLanguage.getPercentage());
    }

    public CountryLanguage toCountryLanguage(CountryLanguageDTO countryLanguageDTO, CountryService countryService){
        Country country = countryService.findCountryById(countryLanguageDTO.getCountryCode());
        return new CountryLanguage(country, countryLanguageDTO.getLanguage(), countryLanguageDTO.getIsOfficial(),countryLanguageDTO.getPercentage());
    }
}

