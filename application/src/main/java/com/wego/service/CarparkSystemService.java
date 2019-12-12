package com.wego.service;

import com.wego.domain.CarparkView;
import com.wego.domain.NearestCarparkView;

import java.util.List;

public interface CarparkSystemService {

	CarparkView getCarpark(String carparkNumber);

	boolean loadCarparkAvailabilityData();

	List<NearestCarparkView> nearestCarparks(double latitude, double longitude, int page, int per_page);
}
