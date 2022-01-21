package com.kpaw.world.service;

import com.kpaw.world.dto.CountryLanguageDTO;

import java.util.List;

public interface CountryLanguageService {

	List<CountryLanguageDTO> findAll();

	List<CountryLanguageDTO> searchBy(String theLanguage, String theCountry);


}
