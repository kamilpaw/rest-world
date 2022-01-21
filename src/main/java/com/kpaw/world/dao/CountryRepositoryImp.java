package com.kpaw.world.dao;

import com.kpaw.world.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepositoryImp implements CountryRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Country> findAll() {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country", Country.class);
		return theQuery.getResultList();
	}

	@Override
	public List<Country> searchBy(String theCode, String theName, String theRegion) {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country where code like :code and name like :name and region like :region", Country.class);
		theQuery.setParameter("code", "%" + theCode + "%");
		theQuery.setParameter("name", "%" + theName + "%");
		theQuery.setParameter("region", "%" + theRegion + "%");
		return theQuery.getResultList();
	}

	@Override
	public Country findById(String theCountryCode) {
		Session currentSession = sessionFactory.openSession();
		Query<Country> theQuery = 
				currentSession.createQuery("from Country where code =:countryCode", Country.class);
		theQuery.setParameter("countryCode", theCountryCode);
		return theQuery.getSingleResult();
	}

}
