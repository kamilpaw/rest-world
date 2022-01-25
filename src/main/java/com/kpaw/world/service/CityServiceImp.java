package com.kpaw.world.service;

import com.kpaw.world.controller.CityNotFoundException;
import com.kpaw.world.dao.CityRepository;
import com.kpaw.world.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImp implements CityService {

    private final CityRepository cityRepository;


    @Autowired
    public CityServiceImp(CityRepository theCityRepository) {
        this.cityRepository = theCityRepository;
    }

    @Override
    @Transactional
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    public List<City> searchBy(String theName, String theCountry) {
        return cityRepository.findByNameAndCountry(theName, theCountry);
    }


    @Override
    @Transactional
    public void save(City theCity) {
        cityRepository.save(theCity);
    }

    @Override
    public void deleteCityById(int theId) {
        cityRepository.deleteCityById(theId);
    }

    @Override
    @Transactional
    public City findById(int theId) {
        City theCity = cityRepository.findById(theId);
        if (theCity == null) {
            throw new CityNotFoundException("City not found, id doesn't exist: " + theId);
        }
        return theCity;
    }



}
