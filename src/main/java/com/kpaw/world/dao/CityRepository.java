package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.City;

public interface CityRepository {
	
	List<City> findAll();
	
	void save(City theCity);

	void deleteCityById(int theId);

	City findById(int theId);

	City findByName(String theName);

	List<City> findByNameAndCountry(String theName, String theCountry);

}
