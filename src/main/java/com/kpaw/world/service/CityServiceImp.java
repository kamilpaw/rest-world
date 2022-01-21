package com.kpaw.world.service;

import com.kpaw.world.controller.CityNotFoundException;
import com.kpaw.world.dao.CityRepository;
import com.kpaw.world.entity.dto.CityDTO;
import com.kpaw.world.entity.dto.Mapper;
import com.kpaw.world.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImp implements CityService {

	private Mapper mapper;
	private CityRepository cityRepository;
	private CountryService countryService;

	@Autowired
	public CityServiceImp(CityRepository theCityRepository, Mapper theMapper, CountryService countryService) {
		this.cityRepository = theCityRepository;
		this.countryService = countryService;
		this.mapper = theMapper;
	}

	@Override
	@Transactional
	public List<CityDTO> findAll() {
		return cityRepository.findAll().stream().map(mapper::toCityDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<CityDTO> searchBy(String theName, String theCountry) {
		return cityRepository.findByNameAndCountry(theName, theCountry).stream().map(mapper::toCityDto).collect(Collectors.toList());
	}


	@Override
	@Transactional
	public void save(CityDTO cityDTO) {
		cityRepository.save(mapper.toCity(cityDTO, countryService));
	}

	@Override
	public void deleteCityById(int theId) {
		cityRepository.deleteCityById(theId);
	}

	@Override
	@Transactional
	public CityDTO findById(int theId) {
		City theCity = cityRepository.findById(theId);
		if (theCity==null){
			throw new CityNotFoundException("City not found, id doesn't exist: " + theId);
		}
		return mapper.toCityDto(theCity);
	}

	@Override
	public City findCityById(int theId) {
		return cityRepository.findById(theId);
	}

}
