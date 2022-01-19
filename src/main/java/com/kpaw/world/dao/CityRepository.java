package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.City;

public interface CityRepository {
	
	public List<City> findAll();
	
	public void save(City theCity);

	public void deleteCityById(int theId);

	public City findById(int theId);

	public List<City> findByNameAndCountry(String theName, String theCountry);

}
