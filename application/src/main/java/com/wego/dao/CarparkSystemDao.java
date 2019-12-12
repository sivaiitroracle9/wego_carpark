package com.wego.dao;

import com.wego.db.entity.CarparkEntity;
import com.wego.db.entity.NearestCarpark;
import com.wego.domain.CarparkAvailability;

import java.util.Collection;
import java.util.List;

public interface CarparkSystemDao {

	CarparkEntity getCarparkEntity(String carparkNumber);

	boolean updateCarparkAvailabilityEntity(Collection<CarparkAvailability> carparkAvailabilitySet);

	List<NearestCarpark> nearestCarparks(double latitude, double longitude, int page, int per_page);
}
