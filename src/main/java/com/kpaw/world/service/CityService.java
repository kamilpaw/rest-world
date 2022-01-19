package com.kpaw.world.service;

import java.util.List;
import com.kpaw.world.dto.CityDTO;
import com.kpaw.world.entity.City;

public interface CityService {

	public List<CityDTO> findAll();

	public List<CityDTO> searchBy(String theName, String theCountry);


	public void save(CityDTO theCityDTO);

	public void deleteCityById(int theId);

	public CityDTO findById(int theId);
}
