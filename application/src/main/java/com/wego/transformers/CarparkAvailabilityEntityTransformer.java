package com.wego.transformers;

import com.wego.db.entity.CarparkAvailabilityEntity;
import com.wego.domain.CarparkAvailability;

public class CarparkAvailabilityEntityTransformer {

    public CarparkAvailabilityEntity transform(CarparkAvailability domain)
    {
        final CarparkAvailabilityEntity entity = new CarparkAvailabilityEntity();
        entity.setCarparkNumber(domain.getCarparkNumber());
        entity.setTimestamp(domain.getTimestamp());
        entity.setUpdateTime(domain.getUpdateTime());
        entity.setTotalLots(domain.getTotalLots());
        entity.setLotsAvailable(domain.getLotsAvailable());
        entity.setLotType(domain.getLotType());

        return entity;
    }

}
