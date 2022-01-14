package com.kpaw.world.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpaw.world.dao.CityRepository;
import com.kpaw.world.entity.City;

@Service
public class CityServiceImp implements CityService {

	private CityRepository cityRepository;

	@Autowired
	public CityServiceImp(CityRepository theCityRepository) {
		this.cityRepository = theCityRepository;

	}

	@Override
	@Transactional
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Override
	@Transactional
	public List<City> searchBy(String theName, String theCountry) {
		return cityRepository.findByNameAndCountry(theName, theCountry);
	}

	@Override
	@Transactional
	public List<City> orderByName() {
		return cityRepository.sortByNameAsc();
	}

	@Override
	@Transactional
	public List<City> orderByCountry() {
		return cityRepository.sortByCountryNameAsc();
	}

	@Override
	@Transactional
	public List<City> orderByPopulation() {
		return cityRepository.sortByPopulationAsc();
	}

	@Override
	@Transactional
	public void save(City theCity) {
		cityRepository.save(theCity);

	}

	@Override
	public void deleteCityById(int theId) {
		cityRepository.deleteCityById(theId);
	}

	@Override
	@Transactional
	public City findById(int theId) {
		City theCity = cityRepository.findById(theId);
		return theCity;
	}

}
