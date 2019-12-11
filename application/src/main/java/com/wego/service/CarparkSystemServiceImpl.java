package com.wego.service;

import com.wego.domain.CarparkAvailability;
import com.wego.dao.CarparkSystemDao;
import com.wego.db.entity.CarparkEntity;
import com.wego.domain.Carpark;
import com.wego.domain.NearestCarpark;
import com.wego.transformers.CarparkAvailabilityTransformer;
import com.wego.transformers.NearestCarparkTransformer;
import com.wego.webservices.service.CarparkAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarparkSystemServiceImpl implements CarparkSystemService {

	@Autowired
	private CarparkSystemDao dao;

	@Autowired
	private CarparkAvailabilityService carparkAvailabilityService;

	private CarparkAvailabilityTransformer carparkAvlTransformer = new CarparkAvailabilityTransformer();

	private NearestCarparkTransformer nearestCarparkTransformer = new NearestCarparkTransformer();

	@Override
	public Carpark getCarpark(String carparkNumber) {
		CarparkEntity carparkDao = dao.getCarparkDao(carparkNumber);

		if (carparkDao == null)
		{
			return null;
		}

		return new Carpark(carparkDao.getCarparkNumber(),
				carparkDao.getAddress(),
				carparkDao.getXcoordinate(),
				carparkDao.getYcoordinate(),
				carparkDao.getLatitude(),
				carparkDao.getLongitude(),
				carparkDao.getCarParkType());
	}

	@Override
	public boolean loadCarparkAvailabilityData() {

		ResponseEntity<com.wego.webservices.domain.CarparkAvailability> responseEntity = carparkAvailabilityService.getCarparkAvailability();

		if (responseEntity.getStatusCode() == HttpStatus.OK)
		{
			com.wego.webservices.domain.CarparkAvailability availabilityData = responseEntity.getBody();
			Collection<CarparkAvailability> carparkAvailabilityList = carparkAvlTransformer.transform(availabilityData);

			return dao.updateCarparkAvailabilityEntity(carparkAvailabilityList);
		}

		return false;
	}

	@Override
	public List<NearestCarpark> nearestCarparks(double latitude, double longitude, int page, int per_page)
	{
		List<com.wego.db.entity.NearestCarpark> entities = dao.nearestCarparks(latitude, longitude, page, per_page);
		return entities.stream().map(nearestCarparkTransformer::transform).collect(Collectors.toList());
	}
}
