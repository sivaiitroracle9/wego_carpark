package com.wego.service;

import com.wego.dao.CarparkSystemDao;
import com.wego.db.model.CarparkModel;
import com.wego.domain.Carpark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarparkSystemServiceImpl implements CarparkSystemService {

	@Autowired
	CarparkSystemDao dao;

	@Override
	public Carpark getCarpark(String carparkNumber) {
		CarparkModel carparkDao = dao.getCarparkDao(carparkNumber);

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
}
