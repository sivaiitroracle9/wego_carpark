package com.wego.dao;

import com.wego.db.Queries;
import com.wego.db.datasource.DbManager;
import com.wego.db.entity.CarparkAvailabilityEntity;
import com.wego.db.entity.CarparkEntity;
import com.wego.db.entity.NearestCarpark;
import com.wego.domain.CarparkAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.wego.transformers.ApplicationTransformers.transformCarparkAvailabilityDomainToEntity;
import static com.wego.transformers.ApplicationTransformers.transformResultSetToNearestCarpark;

@Component
public class CarparkSystemDaoImpl implements CarparkSystemDao {

    @Autowired
    DbManager dbManager;

    @Override
    public CarparkEntity getCarparkEntity(String carparkNumber) {
        return dbManager.getObject(CarparkEntity.class, carparkNumber);
    }

    @Override
    public boolean updateCarparkAvailabilityEntity(Collection<CarparkAvailability> carparkAvailabilityList) {

         List<CarparkAvailabilityEntity> entities = carparkAvailabilityList.stream()
                .map(transformCarparkAvailabilityDomainToEntity())
                .collect(Collectors.toList());

        return dbManager.truncateAndInsert(entities, CarparkAvailabilityEntity.class);
    }

    @Override
    public List<NearestCarpark> nearestCarparks(double latitude, double longitude, int page, int per_page)
    {
        final List<NearestCarpark> nearestCarparks = new ArrayList<>();
        dbManager.execute(Queries.NEAREST_CARPARK_PROC_QUERY,
                Arrays.asList(latitude, longitude, page, per_page),
                transformResultSetToNearestCarpark(),
                nearestCarparks);
        return nearestCarparks;
    }

}
