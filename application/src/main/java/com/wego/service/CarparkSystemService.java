package com.wego.service;

import com.wego.domain.Carpark;
import com.wego.domain.NearestCarpark;

import java.util.List;

public interface CarparkSystemService {

	Carpark getCarpark(String carparkNumber);

	boolean loadCarparkAvailabilityData();

	List<NearestCarpark> nearestCarparks(double latitude, double longitude, int page, int per_page);
}
