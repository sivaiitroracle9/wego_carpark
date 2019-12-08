package com.wego.dao;

import com.wego.db.model.CarparkModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class CarparkSystemDaoImpl implements CarparkSystemDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory getSessionFactory()
    {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public CarparkModel getCarparkDao(String carparkNumber) {
        return getSessionFactory().openSession().get(CarparkModel.class, carparkNumber);
    }
}
