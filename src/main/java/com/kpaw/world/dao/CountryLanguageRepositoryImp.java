package com.kpaw.world.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kpaw.world.entity.CountryLanguage;

@Repository
public class CountryLanguageRepositoryImp implements CountryLanguageRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CountryLanguage> findAll() {
		Session currentSession = sessionFactory.openSession();
		Query<CountryLanguage> theQuery = currentSession.createQuery("from CountryLanguage", CountryLanguage.class);
		List<CountryLanguage> countryLanguages = theQuery.getResultList();
		return countryLanguages;
	}

	@Override
	public List<CountryLanguage> searchBy(String theLanguage, String theCountry) {
		Session currentSession = sessionFactory.openSession();
		Query<CountryLanguage> theQuery = currentSession.createQuery(
				"from CountryLanguage where language like :language and countryCode.name like :country",
				CountryLanguage.class);
		theQuery.setParameter("language", "%" + theLanguage + "%");
		theQuery.setParameter("country", "%" + theCountry + "%");
		List<CountryLanguage> countryLanguages = theQuery.getResultList();
		return countryLanguages;
	}

	@Override
	public List<CountryLanguage> orderByCountry() {
		Session currentSession = sessionFactory.openSession();
		Query<CountryLanguage> theQuery = currentSession.createQuery("from CountryLanguage order by countryCode.name",
				CountryLanguage.class);
		List<CountryLanguage> countryLanguages = theQuery.getResultList();
		return countryLanguages;
	}

	@Override
	public List<CountryLanguage> orderByLanguage() {
		Session currentSession = sessionFactory.openSession();
		Query<CountryLanguage> theQuery =
				currentSession.createQuery("from CountryLanguage order by language", CountryLanguage.class);
		List<CountryLanguage> countryLanguages = theQuery.getResultList();
		return countryLanguages;
	}

}























