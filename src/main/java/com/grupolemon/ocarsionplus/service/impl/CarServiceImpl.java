package com.grupolemon.ocarsionplus.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.grupolemon.ocarsionplus.dto.CarDTO;
import com.grupolemon.ocarsionplus.dto.FilteredCarsDTO;
import com.grupolemon.ocarsionplus.model.Car;
import com.grupolemon.ocarsionplus.model.Color;
import com.grupolemon.ocarsionplus.model.Price;
import com.grupolemon.ocarsionplus.repository.CarRepository;
import com.grupolemon.ocarsionplus.repository.CarSpecification;
import com.grupolemon.ocarsionplus.repository.PriceRepository;
import com.grupolemon.ocarsionplus.service.CarService;
import com.grupolemon.ocarsionplus.service.exceptions.CarServiceException;
import com.grupolemon.ocarsionplus.service.exceptions.EntityNotFoundException;
import com.grupolemon.ocarsionplus.utils.CarExcelGenerator;
import com.grupolemon.ocarsionplus.utils.ErrorConstants;
import com.grupolemon.ocarsionplus.utils.Mapper;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private PriceRepository priceRepository;

	@Override
	public CarDTO getCar(Long idCar, LocalDate date) {

		if (null == date) {
			date = LocalDate.now();
		}

		Optional<Car> optionalCar = carRepository.findById(idCar);

		if (optionalCar.isPresent()) {
			Car car = optionalCar.get();
			CarDTO carDTO = getCarPrice(date, car);
			if (null != carDTO)
				return carDTO;
		}
		throw new EntityNotFoundException(ErrorConstants.NOT_FOUND_CAR);
	}

	private CarDTO getCarPrice(LocalDate date, Car car) {
		Optional<Price> optionalPrice = priceRepository.findByCarAndEndEffectiveDateAfterAndEffectiveDateBefore(car,
				date);

		if (optionalPrice.isPresent()) {
			Price price = null;
			price = optionalPrice.get();
			return Mapper.map(car, price);

		}
		return null;

	}

	@Override
	public FilteredCarsDTO get(String filtersParam) {
		List<Car> cars = null;
		if (null == filtersParam) {
			cars = carRepository.findAll();
		} else {
			Specification<Car> specification = null;
			specification = applySpecification(specification, filtersParam);

			cars = carRepository.findAll(specification);
		}
		FilteredCarsDTO filteredCars = new FilteredCarsDTO();

  		List<CarDTO> listCarDTO = new ArrayList<>();

		for (Car car : cars) {
			CarDTO aCarDTO = getCarPrice(LocalDate.now(), car);
			if (null != aCarDTO)
				listCarDTO.add(aCarDTO);
		}
		filteredCars.setCars(listCarDTO);
		return filteredCars;
	}

	private Specification<Car> applySpecification(Specification<Car> specification, String filter) {
		String[] myList = filter.split(" eq ");

		switch (myList[0]) {
		case "name":
			if (specification == null) {
				specification = Specification
						.where(myList[0] == null ? null : CarSpecification.modelNameContains(myList[1].toUpperCase()));
			}
			break;
		case "color":
			if (specification == null) {
				Color color = Color.valueOf(myList[1].toUpperCase());
				specification = Specification.where(myList[0] == null ? null : CarSpecification.colorContains(color));
			}
			break;
		case "brand":
			if (specification == null) {
				specification = Specification
						.where(myList[0] == null ? null : CarSpecification.brandContains(myList[1].toUpperCase()));
			}
			break;
		default:
			break;
		}

		return specification;

	}

	@Override
	public byte[] exportExcel() throws CarServiceException {
		List<Car> cars = carRepository.findAll();
		if (null != cars && !cars.isEmpty()) {
			CarExcelGenerator ceg = new CarExcelGenerator(cars);
			try {
				return ceg.export();

			} catch (IOException e) {
				throw new CarServiceException(e.getMessage());
			}

		} else {
			throw new EntityNotFoundException(ErrorConstants.NOT_FOUND_CARS);
		}

	}

}
