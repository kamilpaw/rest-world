package com.kpaw.world.service;

import com.kpaw.world.dao.CountryRepository;
import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.dto.Mapper;
import com.kpaw.world.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImp implements CountryService {

	private final CountryRepository countryRepository;
	private final Mapper mapper;

	@Autowired
	public CountryServiceImp(CountryRepository theCountryRepository, Mapper theMapper) {
		countryRepository = theCountryRepository;
		mapper = theMapper;
	}

	@Override
	@Transactional
	public List<CountryDTO> findAll() {
		return countryRepository.findAll().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CountryDTO> searchBy(String theCode, String theName, String theRegion) {
		return countryRepository.searchBy(theCode, theName,
				theRegion).stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}


	@Override
	public Country findCountryById(String theCountryCode) {
		return countryRepository.findById(theCountryCode);
	}

}
