package com.wego;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(value = { ApplicationTestConfig.class })
public class CarparkViewControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void helloworld() {

		String body = restTemplate.getForObject("/", String.class);
	}

	@Test
	public void nearestOK()
	{
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("latitude", 101d);
		queryMap.put("longitude", 110d);
		queryMap.put("page", 1);
		queryMap.put("per_page", 5);

		URI uri = URI.create("/carparks/nearest?latitude=110&longitude=110&page=1&per_page=5");
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void nearestFail()
	{
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("longitude", 110d);
		queryMap.put("page", 1);
		queryMap.put("per_page", 5);

		ResponseEntity responseEntity = restTemplate.getForEntity("/carparks/nearest", Object.class, queryMap);
		assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

}
