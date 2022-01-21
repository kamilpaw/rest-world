package com.kpaw.world.dao;

import com.kpaw.world.entity.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryLanguageRepositoryImp implements CountryLanguageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CountryLanguage> findAll() {
        Session currentSession = sessionFactory.openSession();
        Query<CountryLanguage> theQuery = currentSession.createQuery("from CountryLanguage", CountryLanguage.class);
        return theQuery.getResultList();
    }

    @Override
    public List<CountryLanguage> searchBy(String theLanguage, String theCountry) {
        Session currentSession = sessionFactory.openSession();
        Query<CountryLanguage> theQuery = currentSession.createQuery(
                "from CountryLanguage where language like :language and countryCode.name like :country",
                CountryLanguage.class);
        theQuery.setParameter("language", "%" + theLanguage + "%");
        theQuery.setParameter("country", "%" + theCountry + "%");
        return theQuery.getResultList();
    }
}























