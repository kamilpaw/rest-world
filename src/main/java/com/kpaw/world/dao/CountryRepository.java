package com.kpaw.world.dao;

import com.kpaw.world.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path= "countries")
public interface CountryRepository extends JpaRepository<Country, String> {

}
