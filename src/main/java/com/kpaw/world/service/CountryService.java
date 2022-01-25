package com.kpaw.world.service;

import java.util.List;

import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.entity.Country;

public interface CountryService {

	List<Country> findAll();

	List<Country> searchBy(String theCode, String theName, String theRegion);

	Country findCountryById(String theCountryCode);
}
