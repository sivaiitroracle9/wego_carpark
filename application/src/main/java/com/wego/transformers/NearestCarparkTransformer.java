package com.wego.transformers;

import com.wego.domain.NearestCarpark;

public class NearestCarparkTransformer {

    public NearestCarpark transform(com.wego.db.entity.NearestCarpark nearestCarpark)
    {
        NearestCarpark carpark = new NearestCarpark();
        carpark.setAddress(nearestCarpark.getAddress());
        carpark.setLatitude(nearestCarpark.getLatitude());
        carpark.setLongitude(nearestCarpark.getLongitude());
        carpark.setTotalLots(nearestCarpark.getTotalLots());
        carpark.setAvailableLots(nearestCarpark.getAvailableLots());
        return carpark;
    }

}
