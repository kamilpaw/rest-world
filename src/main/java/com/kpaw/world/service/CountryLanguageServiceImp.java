package com.kpaw.world.service;

import com.kpaw.world.dao.CountryLanguageRepository;
import com.kpaw.world.entity.CountryLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryLanguageServiceImp implements CountryLanguageService {

    private final CountryLanguageRepository countryLanguageRepository;

    @Autowired
    public CountryLanguageServiceImp(CountryLanguageRepository theCountryLanguageRepository) {
        this.countryLanguageRepository = theCountryLanguageRepository;
    }

    @Override
    @Transactional
    public List<CountryLanguage> findAll() {

        return countryLanguageRepository.findAll();
    }

    @Override
    @Transactional
    public List<CountryLanguage> searchBy(String theLanguage, String theCountry) {
        return countryLanguageRepository.searchBy(theLanguage,
                theCountry);
    }
}
