package com.kpaw.world.service;

import com.kpaw.world.dao.CountryRepository;
import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import com.kpaw.world.dto.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CountryServiceImpTest {

    @Mock
    private CountryRepository countryRepository;
    @Mock
    private Mapper mapper;

    @InjectMocks
    CountryServiceImp service;

    Country country, country2;
    List<Country> countries;

    @Mock
    City city;


    @BeforeEach
    void setUp() {
        country = new Country( "code","name", "regoin", 10.0,(short) 100, 10, 100.0, 100.0, 100.0, "localName", "governmentForm", "headOfState", city,"codeB");
        country2 = new Country("code2","name2", "regoin2", 10.0, (short) 100, 10, 100.0, 100.0, 100.0, "localName2", "governmentForm", "headOfState", city, "codeB2");
        countries = new ArrayList<>();
        countries.add(country);
        countries.add(country2);
    }

    @Test
    void findAll() {
        service.findAll();
        then(countryRepository).should().findAll();
    }

    @Test
    void searchBy() {
        service.searchBy("code", "name", "region");
        then(countryRepository).should().searchBy("code", "name", "region");
    }

    @Test
    void findCountryById() {
        given(countryRepository.findById(anyString())).willReturn(country);
        service.findCountryById("code");
        then(countryRepository).should().findById("code");

    }
}