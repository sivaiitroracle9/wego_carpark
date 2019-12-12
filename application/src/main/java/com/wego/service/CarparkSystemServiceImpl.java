package com.wego.service;

import com.wego.dao.CarparkSystemDao;
import com.wego.domain.CarparkView;
import com.wego.domain.NearestCarparkView;
import com.wego.webservices.service.CarparkAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wego.transformers.ApplicationTransformers.transformAvailabilityWebserviceToDomain;
import static com.wego.transformers.ApplicationTransformers.transformNearestCarparkDbEntityToView;

@Service
public class CarparkSystemServiceImpl implements CarparkSystemService {

	@Autowired
	private CarparkSystemDao dao;

	@Autowired
	private CarparkAvailabilityService carparkAvailabilityService;

	@Override
	public CarparkView getCarpark(String carparkNumber) {
		return Optional.ofNullable(dao.getCarparkEntity(carparkNumber))
				.map(entity ->
						new CarparkView(entity.getCarparkNumber(),
								entity.getAddress(),
								entity.getXcoordinate(),
								entity.getYcoordinate(),
								entity.getLatitude(),
								entity.getLongitude(),
								entity.getCarParkType()))
				.orElse(null);
	}

	@Override
	public boolean loadCarparkAvailabilityData() {
		return Optional.ofNullable(carparkAvailabilityService.getCarparkAvailability())
				.filter(re -> re.getStatusCode() == HttpStatus.OK)
				.map(responseEntity -> (com.wego.webservices.domain.CarparkAvailability)responseEntity.getBody())
				.map(transformAvailabilityWebserviceToDomain())
				.map(entityList -> dao.updateCarparkAvailabilityEntity(entityList))
				.orElse(false);
	}

	@Override
	public List<NearestCarparkView> nearestCarparks(double latitude, double longitude, int page, int per_page)
	{
		return dao.nearestCarparks(latitude, longitude, page, per_page).stream()
				.map(transformNearestCarparkDbEntityToView())
				.collect(Collectors.toList());
	}
}
