package com.wego.controller;

import com.wego.domain.Carpark;
import com.wego.service.CarparkSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarparkController {
	private static Logger logger = LoggerFactory.getLogger(CarparkController.class);
	@Autowired
	CarparkSystemService carparkSystemService;

	@ResponseBody
	@RequestMapping(value = "/getCarpark", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Carpark getCarpark(@RequestParam("id") String id)
	{
		Carpark carpark = null;
		if (!StringUtils.isEmpty(id))
		{
			carpark = carparkSystemService.getCarpark(id);
		}

		return carpark;
	}
}
