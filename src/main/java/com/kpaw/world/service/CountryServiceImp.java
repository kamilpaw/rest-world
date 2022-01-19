package com.kpaw.world.service;

import com.kpaw.world.dao.CountryRepository;
import com.kpaw.world.dto.CountryDTO;
import com.kpaw.world.dto.Mapper;
import com.kpaw.world.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImp implements CountryService {

	private CountryRepository countryRepository;
	private Mapper mapper;

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
	@Transactional
	public List<CountryDTO> orderByRegion() {
		return countryRepository.orderByRegion().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CountryDTO> orderByName() {
		return countryRepository.orderByName().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CountryDTO> orderBySurface() {
		return countryRepository.orderBySurface().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CountryDTO> orderByCode() {
		return countryRepository.orderByCode().stream().map(mapper::toCountryDTO).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CountryDTO findCountryDTOById(String theCountryCode) {
		return mapper.toCountryDTO(countryRepository.findById(theCountryCode));
	}

	@Override
	public Country findCountryById(String theCountryCode) {
		return countryRepository.findById(theCountryCode);
	}

}
