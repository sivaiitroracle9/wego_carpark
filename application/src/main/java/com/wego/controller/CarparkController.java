package com.wego.controller;

import com.wego.common.InputValidator;
import com.wego.domain.Carpark;
import com.wego.domain.NearestCarpark;
import com.wego.service.CarparkSystemService;
import com.wego.webservices.service.CarparkAvailabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarparkController {
	private static Logger logger = LoggerFactory.getLogger(CarparkController.class);
	@Autowired
	CarparkSystemService carparkSystemService;

	@Autowired
	CarparkAvailabilityService carparkAvailabilityService;

	@ResponseBody
	@RequestMapping(value = "/getCarpark",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Carpark getCarpark(@RequestParam("id") String id)
	{
		Carpark carpark = null;
		if (!StringUtils.isEmpty(id))
		{
			carpark = carparkSystemService.getCarpark(id);
		}
        https://api.data.gov.sg/v1/transport/carpark-availability?date_time=2019-12-09T13%3A00%3A00
		return carpark;
	}

	@ResponseBody
	@RequestMapping(value = "/loadCarparkAvailability",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity loadCarparkAvailability()
	{
		return carparkAvailabilityService.getCarparkAvailability();
	}

	@ResponseBody
	@RequestMapping(value = "/loadCarparkAvailabilityData",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity loadCarparkAvailabilityData()
	{
		if (carparkSystemService.loadCarparkAvailabilityData())
		{
			return ResponseEntity.ok("Latest Carpark Availability data updated.");
		}

		return ResponseEntity.badRequest().body("Failed to load latest data.");
	}

	@ResponseBody
	@RequestMapping(value = "/carparks/nearest", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity nearest(@RequestParam(value = "latitude") String latitude,
								  @RequestParam(value = "longitude") String longitude,
								  @RequestParam(value = "page", defaultValue = "1") String page,
								  @RequestParam(value = "per_page", defaultValue = "5") String per_page)
	{
		if(!InputValidator.validate(latitude, longitude, page, per_page)) {
			return fail("Invalid query parameters.");
		}

		List<NearestCarpark> avblNearest = null;

		try {
			avblNearest = carparkSystemService.nearestCarparks(Double.valueOf(latitude), Double.valueOf(longitude),
					Integer.valueOf(page), Integer.valueOf(per_page));
		}
		catch (Exception ex)
		{
			fail("Error pulling the data.");
		}
		return ResponseEntity.ok().body(avblNearest);
	}

	private ResponseEntity fail(String message)
	{
		return ResponseEntity.badRequest().body(message);
	}
}
