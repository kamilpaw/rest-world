package com.kpaw.world.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kpaw.world.dto.CountryLanguageDTO;
import com.kpaw.world.dto.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpaw.world.dao.CountryLanguageRepository;
import com.kpaw.world.entity.CountryLanguage;

@Service
public class CountryLanguageServiceImp implements CountryLanguageService {

	private Mapper mapper;
	private CountryLanguageRepository countryLanguageRepository;

	@Autowired
	public CountryLanguageServiceImp(CountryLanguageRepository theCountryLanguageRepository, Mapper theMapper) {
		this.countryLanguageRepository = theCountryLanguageRepository;
		this.mapper = theMapper;
	}

	@Override
	@Transactional
	public List<CountryLanguageDTO> findAll() {

		return countryLanguageRepository.findAll().stream().map(mapper::toCountryLanguageDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CountryLanguageDTO> searchBy(String theLanguage, String theCountry) {
		return countryLanguageRepository.searchBy(theLanguage,
				theCountry).stream().map(mapper::toCountryLanguageDTO).collect(Collectors.toList());
	}
}
