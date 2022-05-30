package com.kpaw.world.dao;

import com.kpaw.world.entity.CountryLanguage;
import com.kpaw.world.entity.CountryLanguageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path= "languages")
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageKey> {

}
