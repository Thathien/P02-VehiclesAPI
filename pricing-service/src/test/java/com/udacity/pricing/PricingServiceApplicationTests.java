package com.udacity.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.udacity.pricing.entity.Price;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	/* at least one additional test has been implemented outside of contextLoads() for the overall Pricing Service Application.*/
	@Test
	public void getPricingPrice() {
		long vehicleId = 1L;
		ResponseEntity<Price> responseEntity = this.testRestTemplate
				.getForEntity("http://localhost:" + port + "/services/price?vehicleId=" + vehicleId, Price.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
}
