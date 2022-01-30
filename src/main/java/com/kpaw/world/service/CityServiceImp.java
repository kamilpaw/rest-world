package com.kpaw.world.service;

import com.kpaw.world.controller.CityNotFoundException;
import com.kpaw.world.dao.CityRepository;
import com.kpaw.world.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImp implements CityService {

    private final CityRepository cityRepository;


    @Autowired
    public CityServiceImp(CityRepository theCityRepository) {
        this.cityRepository = theCityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> searchBy(String theName, String theCountry) {
        return cityRepository.findByNameContainsAndCountryNameContainsAllIgnoreCase(theName, theCountry);
    }

    @Override
    public void save(City theCity) {
        cityRepository.save(theCity);
    }

    @Override
    public void deleteCityById(int theId) {
        cityRepository.deleteById(theId);
    }

    @Override
    public City findById(int theId) {
        Optional<City> result = cityRepository.findById(theId);

        if (result.isEmpty()) {
            throw new CityNotFoundException("City not found, id doesn't exist: " + theId);
        }
        return result.get();
    }

}
