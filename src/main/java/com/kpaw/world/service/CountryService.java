package com.kpaw.world.service;

import java.util.List;

import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.entity.Country;

public interface CountryService {

	public List<CountryDTO> findAll();

	public List<CountryDTO> searchBy(String theCode, String theName, String theRegion);

	public Country findCountryById(String theCountryCode);
}
