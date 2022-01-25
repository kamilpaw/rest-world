package com.kpaw.world.service;

import com.kpaw.world.entity.CountryLanguage;

import java.util.List;

public interface CountryLanguageService {

    List<CountryLanguage> findAll();

    List<CountryLanguage> searchBy(String theLanguage, String theCountry);

}
