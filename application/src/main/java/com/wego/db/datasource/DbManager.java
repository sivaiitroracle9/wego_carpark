package com.wego.db.datasource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Table;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

@Component
public class DbManager {
    private static Logger logger = LoggerFactory.getLogger(DbManager.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory getSessionFactory()
    {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    private Session getSession()
    {
        return getSessionFactory().openSession();
    }

    public <ENTITY> ENTITY getObject(Class<ENTITY> domainClass, String id)
    {
        return getSession().get(domainClass, id);
    }

    public <ENTITY> boolean truncateAndInsert(List<ENTITY> entityList, Class<ENTITY> entityClass)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            final String tableName = entityClass.getAnnotation(Table.class).name();
            final String truncateQuery = "TRUNCATE TABLE " + tableName;
            logger.debug("Deleting the availability data : " + truncateQuery);
            session.createNativeQuery(truncateQuery).executeUpdate();

            for (ENTITY entity : entityList)
            {
                session.save(entity);
            }

            transaction.commit();
            return true;
        }
        catch (Exception exception)
        {
            logger.error("Error during updating the database ", exception.getMessage());
            transaction.rollback();
        }
        finally {
            session.close();
        }

        return false;
    }

    public <ENTITY> void execute(String procedureQuery, List<Object> inputs,
                                     Function<ResultSet, ENTITY> transform, List<ENTITY> results) {
        getSession().doWork(
                connection -> {
                    CallableStatement callableStatement = connection.prepareCall(procedureQuery);
                    for (int i = 0; i < inputs.size(); i++)
                    {
                        callableStatement.setObject(i + 1, inputs.get(i));
                    }
                    if (callableStatement.execute()) {
                        ResultSet resultSet = callableStatement.getResultSet();
                        while (resultSet.next()) {
                            results.add(transform.apply(resultSet));
                        }
                    }
                }
        );
    }
}
