package com.udacity.pricing.config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udacity.pricing.entity.Price;
import com.udacity.pricing.exception.PriceException;
import com.udacity.pricing.repository.PriceRepository;

@Component
public class PricingConfig {

	@Autowired
	private PriceRepository priceRepository;

	/**
	 * Holds {ID: Price} pairings (current implementation allows for 20 vehicles)
	 */
	private static final Map<Long, Price> PRICES = LongStream.range(1, 20)
			.mapToObj(i -> new Price("USD", randomPrice(), i)).collect(Collectors.toMap(Price::getVehicleId, p -> p));

	/**
	 * If a valid vehicle ID, gets the price of the vehicle from the stored array.
	 * 
	 * @param vehicleId ID number of the vehicle the price is requested for.
	 * @return price of the requested vehicle
	 * @throws PriceException vehicleID was not found
	 */
	public static Price getPrice(Long vehicleId) throws PriceException {

		if (!PRICES.containsKey(vehicleId)) {
			throw new PriceException("Cannot find price for Vehicle " + vehicleId);
		}

		return PRICES.get(vehicleId);
	}

	/**
	 * Gets a random price to fill in for a given vehicle ID.
	 * 
	 * @return random price for a vehicle
	 */
	private static BigDecimal randomPrice() {
		return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5)).multiply(new BigDecimal(5000d)).setScale(2,
				RoundingMode.HALF_UP);
	}

	@PostConstruct
	private void postConstruct() {
		for (long i = 1; i <= 10; i++) {
			priceRepository.save(new Price("USD", randomPrice(), i));
		}
	}

}
