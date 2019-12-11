package com.wego.dao;

import com.wego.db.Queries;
import com.wego.db.QueriesHelper;
import com.wego.db.datasource.DbManager;
import com.wego.db.entity.CarparkAvailabilityEntity;
import com.wego.db.entity.CarparkEntity;
import com.wego.db.entity.NearestCarpark;
import com.wego.domain.CarparkAvailability;
import com.wego.transformers.CarparkAvailabilityEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;
import java.util.function.Function;

@Component
public class CarparkSystemDaoImpl implements CarparkSystemDao {

    @Autowired
    DbManager dbManager;

    CarparkAvailabilityEntityTransformer transformer = new CarparkAvailabilityEntityTransformer();

    @Override
    public CarparkEntity getCarparkDao(String carparkNumber) {
        return dbManager.getObject(CarparkEntity.class, carparkNumber);
    }

    @Override
    public boolean updateCarparkAvailabilityEntity(Collection<CarparkAvailability> carparkAvailabilityList) {
        List<CarparkAvailabilityEntity> entities = new ArrayList<>();
        for(CarparkAvailability carparkAvailability : carparkAvailabilityList)
        {
            entities.add(transformer.transform(carparkAvailability));
        }

        return dbManager.saveOrUpdate(entities);
    }

    @Override
    public List<NearestCarpark> nearestCarparks(double latitude, double longitude, int page, int per_page)
    {
        List<NearestCarpark> nearestCarparks = new ArrayList<>();
        dbManager.execute(Queries.NEAREST_CARPARK_PROC_QUERY1,
                Arrays.asList(latitude, longitude, page, per_page),
                transformNearestCarpark(latitude, longitude, page, per_page),
                nearestCarparks);
        return nearestCarparks;
    }

    private Function<ResultSet, NearestCarpark> transformNearestCarpark(double latitude, double longitude, int page, int per_page)
    {
        return resultSet -> {
            try {
                NearestCarpark carpark = new NearestCarpark();
                carpark.setAddress(resultSet.getString("address"));
                carpark.setLatitude(resultSet.getDouble("latitude"));
                carpark.setLongitude(resultSet.getDouble("longitude"));
                carpark.setTotalLots(resultSet.getInt("total_lots"));
                carpark.setAvailableLots(resultSet.getInt("lot_available"));
                return carpark;
            }
            catch (Exception e)
            {
                return null;
            }
        };
    }

}
