package com.wego;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(value = { ApplicationTestConfig.class })
@Ignore
public class CarparkControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void helloworld() {
		String body = this.restTemplate.getForObject("/", String.class);
	}
}
