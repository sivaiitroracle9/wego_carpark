package com.wego.controller;

import com.wego.common.InputValidator;
import com.wego.domain.CarparkView;
import com.wego.domain.NearestCarparkView;
import com.wego.service.CarparkSystemService;
import com.wego.webservices.service.CarparkAvailabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/carparks")
public class CarparkApplicationController {
	private static Logger logger = LoggerFactory.getLogger(CarparkApplicationController.class);
	@Autowired
	CarparkSystemService carparkSystemService;

	@Autowired
	CarparkAvailabilityService carparkAvailabilityService;

	@ResponseBody
	@RequestMapping(value = "/carpark/{carpark_number}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public CarparkView getCarpark(@PathVariable("carpark_number") String carparkNumber)
	{
		CarparkView carparkView = null;
		if (!StringUtils.isEmpty(carparkNumber))
		{
			carparkView = carparkSystemService.getCarpark(carparkNumber);
		}
		return carparkView;
	}

	@ResponseBody
	@RequestMapping(value = "/availability/latest",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getLatestCarparkAvailability()
	{
		return carparkAvailabilityService.getCarparkAvailability();
	}

	@ResponseBody
	@RequestMapping(value = "/availability/latest/load",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity loadLatestCarparkAvailabilityData()
	{
		if (carparkSystemService.loadCarparkAvailabilityData())
		{
			return ResponseEntity.ok("Latest Carpark Availability data updated.");
		}

		return ResponseEntity.badRequest().body("Failed to load latest data.");
	}

	@ResponseBody
	@RequestMapping(value = "/nearest", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity nearest(@RequestParam(value = "latitude") double latitude,
								  @RequestParam(value = "longitude") double longitude,
								  @RequestParam(value = "page", defaultValue = "1") int page,
								  @RequestParam(value = "per_page", defaultValue = "5") int per_page)
	{
		if(!InputValidator.validate(latitude, longitude, page, per_page)) {
			return fail(String.format("Invalid query parameters: latitude=%s, longitude=%s, page=%s, per_page=%s",
					latitude, longitude, page, per_page));
		}

		List<NearestCarparkView> avblNearest = null;

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
