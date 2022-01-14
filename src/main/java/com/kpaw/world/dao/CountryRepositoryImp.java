package com.kpaw.world.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kpaw.world.entity.Country;

@Repository
public class CountryRepositoryImp implements CountryRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Country> findAll() {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country", Country.class);
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

	@Override
	public List<Country> searchBy(String theCode, String theName, String theRegion) {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country where code like :code and name like :name and region like :region", Country.class);
		theQuery.setParameter("code", "%" + theCode + "%");
		theQuery.setParameter("name", "%" + theName + "%");
		theQuery.setParameter("region", "%" + theRegion + "%");
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

	@Override
	public List<Country> orderByRegion() {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by region", Country.class);
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

	@Override
	public List<Country> orderByName() {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by name", Country.class);
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

	@Override
	public List<Country> orderBySurface() {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by surfaceArea asc", Country.class);
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

	@Override
	public List<Country> orderByCode() {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by code", Country.class);
		List<Country> countries = theQuery.getResultList();
		return countries;
	}

	@Override
	public Country findById(String theCountryCode) {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country where code =:countryCode", Country.class);
		theQuery.setParameter("countryCode", theCountryCode);
		Country theCountry = theQuery.getSingleResult();
		return theCountry;
	}

}
