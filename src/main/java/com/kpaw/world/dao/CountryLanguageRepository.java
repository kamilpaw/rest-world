package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.CountryLanguage;

public interface CountryLanguageRepository {
	
	List<CountryLanguage> findAll();

	List<CountryLanguage> searchBy(String theLanguage, String theCountry);

}
