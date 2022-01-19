package com.kpaw.world.service;

import java.util.List;
import com.kpaw.world.dto.CityDTO;
import com.kpaw.world.entity.City;

public interface CityService {

	List<CityDTO> findAll();

	List<CityDTO> searchBy(String theName, String theCountry);

	void save(CityDTO theCityDTO);

	void deleteCityById(int theId);

	CityDTO findById(int theId);

	City findCityById(int theId);
}
