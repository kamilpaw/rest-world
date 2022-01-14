package com.kpaw.world.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpaw.world.dao.CountryLanguageRepository;
import com.kpaw.world.entity.CountryLanguage;

@Service
public class CountryLanguageServiceImp implements CountryLanguageService {

	private CountryLanguageRepository countryLanguageRepository;

	@Autowired
	public CountryLanguageServiceImp(CountryLanguageRepository theCountryLanguageRepository) {
		this.countryLanguageRepository = theCountryLanguageRepository;
	}

	@Override
	@Transactional
	public List<CountryLanguage> findAll() {
		return countryLanguageRepository.findAll();
	}

	@Override
	@Transactional
	public List<CountryLanguage> searchBy(String theLanguage, String theCountry) {
		return countryLanguageRepository.searchBy(theLanguage,
				theCountry);
	}

	@Override
	@Transactional
	public List<CountryLanguage> orderByName() {
		return countryLanguageRepository.orderByCountry();
	}

	@Override
	@Transactional
	public List<CountryLanguage> orderByLanguage() {
		return countryLanguageRepository.orderByLanguage();
	}

}
