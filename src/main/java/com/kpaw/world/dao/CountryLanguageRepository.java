package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.CountryLanguage;

public interface CountryLanguageRepository {
	
	public List<CountryLanguage> findAll();

	public List<CountryLanguage> searchBy(String theLanguage, String theCountry);

	public List<CountryLanguage> orderByCountry();

	public List<CountryLanguage> orderByLanguage();

}
