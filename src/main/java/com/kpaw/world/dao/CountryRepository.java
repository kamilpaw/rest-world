package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.Country;

public interface CountryRepository {

	List<Country> findAll();

	List<Country> searchBy(String theCode, String theName, String theRegion);

	Country findById(String theCountryCode);

}
