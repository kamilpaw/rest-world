package com.kpaw.world.service;

import com.kpaw.world.dao.CityRepository;
import com.kpaw.world.entity.City;
import com.kpaw.world.entity.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CityServiceImpTest {

    private City city1, city2;
    private List<City> cities;

    @Mock
    Country country;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    CityServiceImp service;

    @BeforeEach
    void setUp() {
        city1 = new City(1, "name", country, "District", 1);
        city2 = new City(2, "name2", country, "District", 2);
        cities = new ArrayList<>();
        cities.add(city1);
        cities.add(city2);
    }

    @Test
    void testFindAll() {
        given(cityRepository.findAll()).willReturn(cities);
        service.findAll();
        then(cityRepository).should().findAll();
        assertTrue(cityRepository.findAll().size() == 2);
        assertTrue(service.findAll().size() == 2);

    }

    @Test
    void testSearchBy() {
        given(cityRepository.findByNameAndCountry("city", "country")).willReturn((List<City>) cities);
        service.searchBy("city", "country");
        then(cityRepository).should().findByNameAndCountry("city", "country");
    }

    @Test
    void testSave() {
        service.save(city1);
        then(cityRepository).should().save(city1);
    }

    @Test
    void testDeleteCityById() {
        service.deleteCityById(1);
        verify(cityRepository, times(1)).deleteCityById(1);
    }

    @Test
    void testFindById() {
        City city = new City();
        given(cityRepository.findById(1)).willReturn(city);
        City foundCity = service.findById(1);
        then(cityRepository).should().findById(1);
        assertThat(foundCity).isNotNull();
    }

    @Test
    void testFindByIdNotFound() {
        given(cityRepository.findById(1)).willReturn(null);
        assertThrows(RuntimeException.class, () -> service.findById(1));
    }

}
















