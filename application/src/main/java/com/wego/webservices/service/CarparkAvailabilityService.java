package com.wego.webservices.service;

import com.wego.webservices.domain.CarpakAvailabilityApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Component
public class CarparkAvailabilityService {
    private static Logger logger = LoggerFactory.getLogger(CarparkAvailabilityService.class);

    @Value("${carpark.availablity.webservice.url}")
    private String url;

    public ResponseEntity getCarparkAvailability()
    {
        try {
            ResponseEntity<CarpakAvailabilityApiResponse> responseEntity = new RestTemplate().getForEntity(
                    url, CarpakAvailabilityApiResponse.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK)
            {
                return ResponseEntity.ok(responseEntity.getBody().getFirstItem());
            }
            else
            {
                return ResponseEntity.status(responseEntity.getStatusCode()).body("Request failed.");
            }
        } catch (HttpClientErrorException | HttpServerErrorException | UnknownHttpStatusCodeException exception)
        {
            logger.error(exception.getMessage());
            return fail("Internal service error");
        }
    }

    ResponseEntity fail(String message)
    {
        return ResponseEntity.badRequest().body(message);
    }

}
