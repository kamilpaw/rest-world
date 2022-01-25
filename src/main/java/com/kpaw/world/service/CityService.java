package com.kpaw.world.service;

import com.kpaw.world.entity.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    List<City> searchBy(String theName, String theCountry);

    void save(City theCity);

    void deleteCityById(int theId);

    City findById(int theId);

}
