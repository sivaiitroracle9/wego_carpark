package com.wego.transformers;

import com.wego.webservices.domain.CarparkData;
import com.wego.webservices.domain.CarparkInfo;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

public class ApplicationTransformers {

    public static Function<com.wego.domain.CarparkAvailability, com.wego.db.entity.CarparkAvailabilityEntity> transformCarparkAvailabilityDomainToEntity()
    {
        return domain -> {
            final com.wego.db.entity.CarparkAvailabilityEntity entity = new com.wego.db.entity.CarparkAvailabilityEntity();
            entity.setCarparkNumber(domain.getCarparkNumber());
            entity.setTimestamp(domain.getTimestamp());
            entity.setUpdateTime(domain.getUpdateTime());
            entity.setTotalLots(domain.getTotalLots());
            entity.setLotsAvailable(domain.getLotsAvailable());
            entity.setLotType(domain.getLotType());
            return entity;
        };
    }

    public static Function<ResultSet, com.wego.db.entity.NearestCarpark> transformResultSetToNearestCarpark()
    {
        return resultSet -> {
            try {
                com.wego.db.entity.NearestCarpark carpark = new com.wego.db.entity.NearestCarpark();
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

    public static Function<com.wego.db.entity.NearestCarpark, com.wego.domain.NearestCarparkView> transformNearestCarparkDbEntityToView()
    {
        return entity -> {
            com.wego.domain.NearestCarparkView carpark = new com.wego.domain.NearestCarparkView();
            carpark.setAddress(entity.getAddress());
            carpark.setLatitude(entity.getLatitude());
            carpark.setLongitude(entity.getLongitude());
            carpark.setTotalLots(entity.getTotalLots());
            carpark.setAvailableLots(entity.getAvailableLots());
            return carpark;
        };
    }

    public static Function<com.wego.webservices.domain.CarparkAvailability, Collection<com.wego.domain.CarparkAvailability>> transformAvailabilityWebserviceToDomain()
    {
        return webserviceEntity -> {
            final HashMap<String, com.wego.domain.CarparkAvailability> carparkAvailabilityMap = new HashMap<>();
            if (webserviceEntity == null)
            {
                return carparkAvailabilityMap.values();
            }

            String timestamp = webserviceEntity.getTimestamp();
            for(CarparkData data : webserviceEntity.getCarparkData())
            {
                com.wego.domain.CarparkAvailability carparkAvl = new com.wego.domain.CarparkAvailability();
                carparkAvl.setCarparkNumber(data.getCarparkNumber());
                carparkAvl.setUpdateTime(LocalDateTime.parse(data.getUpdateTime(), DateTimeFormatter.ISO_DATE_TIME));
                carparkAvl.setTimestamp(LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_DATE_TIME));

                CarparkInfo carparkInfo = data.getCarparkInfo().get(0);
                carparkAvl.setLotType(carparkInfo.getLotType());
                carparkAvl.setLotsAvailable(carparkInfo.getLotsAvailable());
                carparkAvl.setTotalLots(carparkInfo.getTotalLots());

                if (carparkAvailabilityMap.get(carparkAvl.getCarparkNumber()) == null ||
                        carparkAvl.getUpdateTime().isAfter(carparkAvailabilityMap.get(carparkAvl.getCarparkNumber()).getUpdateTime()))
                {
                    carparkAvailabilityMap.put(carparkAvl.getCarparkNumber(), carparkAvl);
                }
            }
            return carparkAvailabilityMap.values();
        };

    }
}
