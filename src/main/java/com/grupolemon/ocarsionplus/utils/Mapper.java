package com.grupolemon.ocarsionplus.utils;

import com.grupolemon.ocarsionplus.dto.CarDTO;
import com.grupolemon.ocarsionplus.dto.PriceDTO;
import com.grupolemon.ocarsionplus.model.Car;
import com.grupolemon.ocarsionplus.model.Price;

public final class Mapper {

	private Mapper() {
		
	}
	public static CarDTO map(Car car, Price price) {
		CarDTO carDTO = new CarDTO();
		carDTO.setIdBrand(car.getBrand().getId());
		carDTO.setIdCar(car.getId());
		carDTO.setColor(car.getColor());
		PriceDTO priceDTO = new PriceDTO();
		priceDTO.setEffectiveDate(price.getEffectiveDate());
		priceDTO.setEndEffectiveDate(price.getEndEffectiveDate());
		priceDTO.setValue(price.getValue());
		carDTO.setPrice(priceDTO);
		return carDTO;
	}

}
