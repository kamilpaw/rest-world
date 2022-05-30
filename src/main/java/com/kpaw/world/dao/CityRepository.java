package com.kpaw.world.dao;

import com.kpaw.world.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path= "cities")
public interface CityRepository extends JpaRepository<City, Integer> {

}
