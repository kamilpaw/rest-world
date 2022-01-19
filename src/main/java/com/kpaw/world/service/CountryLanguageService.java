package com.kpaw.world.service;

import com.kpaw.world.dto.CountryLanguageDTO;
import com.kpaw.world.entity.CountryLanguage;

import java.util.List;

public interface CountryLanguageService {

	public List<CountryLanguageDTO> findAll();

	public List<CountryLanguageDTO> searchBy(String theLanguage, String theCountry);


}
