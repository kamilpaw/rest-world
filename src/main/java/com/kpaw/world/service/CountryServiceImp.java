package com.kpaw.world.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpaw.world.dao.CountryRepository;
import com.kpaw.world.entity.Country;

@Service
public class CountryServiceImp implements CountryService {

	private CountryRepository countryRepository;

	@Autowired
	public CountryServiceImp(CountryRepository theCountryRepository) {
		countryRepository = theCountryRepository;
	}

	@Override
	@Transactional
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	@Transactional
	public List<Country> searchBy(String theCode, String theName, String theRegion) {
		return countryRepository.searchBy(theCode, theName,
				theRegion);
	}

	@Override
	@Transactional
	public List<Country> orderByRegion() {
		return countryRepository.orderByRegion();
	}

	@Override
	@Transactional
	public List<Country> orderByName() {
		return countryRepository.orderByName();
	}

	@Override
	@Transactional
	public List<Country> orderBySurface() {
		return countryRepository.orderBySurface();
	}

	@Override
	@Transactional
	public List<Country> orderByCode() {
		return countryRepository.orderByCode();
	}

	@Override
	@Transactional
	public Country findById(String theCountryCode) {
		Country theCountry = countryRepository.findById(theCountryCode);
		return theCountry;
	}

}
