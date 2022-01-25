package com.kpaw.world.service;

import com.kpaw.world.dao.CountryRepository;
import com.kpaw.world.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImp implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImp(CountryRepository theCountryRepository) {
        countryRepository = theCountryRepository;
    }

    @Override
    @Transactional
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional
    public List<Country> searchBy(String theCode, String theName, String theRegion) {
        return countryRepository.searchBy(theCode, theName,
                theRegion);
    }

    @Override
    public Country findCountryById(String theCountryCode) {
        return countryRepository.findById(theCountryCode);
    }

}
