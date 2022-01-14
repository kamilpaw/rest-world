package com.kpaw.world.service;

import java.util.List;

import com.kpaw.world.entity.City;

public interface CityService {

	public List<City> findAll();

	public List<City> searchBy(String theName, String theCountry);

	public List<City> orderByName();

	public List<City> orderByCountry();

	public List<City> orderByPopulation();

	public void save(City theCity);

	public void deleteCityById(int theId);

	public City findById(int theId);
}
