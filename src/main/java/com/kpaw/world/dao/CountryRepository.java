package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.Country;

public interface CountryRepository {

	public List<Country> findAll();

	public List<Country> searchBy(String theCode, String theName, String theRegion);

	public List<Country> orderByRegion();

	public List<Country> orderByName();

	public List<Country> orderBySurface();

	public List<Country> orderByCode();

	public Country findById(String theCountryCode);

}
