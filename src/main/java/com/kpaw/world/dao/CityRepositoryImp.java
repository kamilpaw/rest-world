package com.kpaw.world.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kpaw.world.entity.City;


@Repository
public class CityRepositoryImp implements CityRepository{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<City> findAll() {
		Session currentSession = sessionFactory.openSession();
		Query<City> theQuery = 
				currentSession.createQuery("from City", City.class);
		List<City> cities = theQuery.getResultList();
		return cities;
	}

	
	@Override
	public void save(City theCity) {
		Session currentSession = sessionFactory.openSession();
		currentSession.saveOrUpdate(theCity);
		
	}

	@Override
	public void deleteCityById(int theId) {
		Session currentSession = sessionFactory.openSession();
		currentSession.beginTransaction();
		Query theQuery = 
				currentSession.createQuery("delete from City where id=:cityId");
		theQuery.setParameter("cityId", theId);
		theQuery.executeUpdate();	
		currentSession.getTransaction().commit();
		currentSession.close();
		
	}

	@Override
	public City findById(int theId) {
		Session currentSession = sessionFactory.openSession();
		City theCity = currentSession.get(City.class, theId);
		return theCity;
	};
	
	@Override
	public List<City> findByNameAndCountry(String theName, String theCountry){
		Session currentSession = sessionFactory.openSession();
		Query<City> theQuery = 
				currentSession.createQuery("from City where name like :theName and country.name like :theCountry", City.class);
		theQuery.setParameter("theName", "%" + theName + "%");
		theQuery.setParameter("theCountry", "%" + theCountry + "%");
		List<City> cities = theQuery.getResultList();
		return cities;
	};

	public List<City> sortByNameAsc(){
		Session currentSession = sessionFactory.openSession();
		Query<City> theQuery = 
				currentSession.createQuery("from City order by name", City.class);
		List<City> cities = theQuery.getResultList();
		return cities;
	};

	public List<City> sortByCountryNameAsc(){
		Session currentSession = sessionFactory.openSession();
		Query<City> theQuery = 
				currentSession.createQuery("from City order by country.name", City.class);
		List<City> cities = theQuery.getResultList();
		return cities;
	};

	public List<City> sortByPopulationAsc(){
		Session currentSession = sessionFactory.openSession();
		Query<City> theQuery = 
				currentSession.createQuery("from City order by population asc", City.class);
		List<City> cities = theQuery.getResultList();
		return cities;
	}

}
