package com.kpaw.world.dao;

import java.util.List;

import com.kpaw.world.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

	List<City> findByNameContainsAndCountryNameContainsAllIgnoreCase(String theName, String theCountry);
}
