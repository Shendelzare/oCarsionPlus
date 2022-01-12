package com.grupolemon.ocarsionplus.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import com.grupolemon.ocarsionplus.model.Brand;
import com.grupolemon.ocarsionplus.model.Car;
import com.grupolemon.ocarsionplus.model.Color;
import com.grupolemon.ocarsionplus.model.Price;
import com.grupolemon.ocarsionplus.repository.CarRepository;
import com.grupolemon.ocarsionplus.repository.PriceRepository;
import com.grupolemon.ocarsionplus.service.exceptions.CarServiceException;
import com.grupolemon.ocarsionplus.service.exceptions.EntityNotFoundException;
import com.grupolemon.ocarsionplus.utils.CarExcelGenerator;

@SpringBootTest
class CarServiceImplTest {
	@Mock
	private CarRepository carRepository;

	@Mock
	private PriceRepository priceRepository;
	@InjectMocks
	private CarServiceImpl carServiceImpl;

	@Test
	void testGetCar() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);

		Optional<Car> optiCar = Optional.of(car);
		Optional<Price> optiPrice = Optional.of(price);

		Mockito.when(carRepository.findById(1L)).thenReturn(optiCar);
		Mockito.when(priceRepository.findByCarAndEndEffectiveDateAfterAndEffectiveDateBefore(Mockito.any(Car.class),
				Mockito.any(LocalDate.class))).thenReturn(optiPrice);
//LocalDate.of(2022, 1, 14)
		EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			carServiceImpl.getCar(3L, LocalDate.of(2021, 1, 13));
		});

		Assertions.assertEquals("Car not found", thrown.getMessage());

	}

	@Test
	void testGetCar1() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);
		
		Optional<Car> optiCar = Optional.of(car);
		Mockito.when(carRepository.findById(1L)).thenReturn(optiCar);

		EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			carServiceImpl.getCar(1L, null);
		});

		Assertions.assertEquals("Car not found", thrown.getMessage());

	}
	@Test
	void testGetCar2() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);

		Optional<Car> optiCar = Optional.of(car);
		Optional<Price> optiPrice = Optional.of(price);

		Mockito.when(carRepository.findById(Mockito.anyLong())).thenReturn(optiCar);
		Mockito.when(priceRepository.findByCarAndEndEffectiveDateAfterAndEffectiveDateBefore(Mockito.any(Car.class),
				Mockito.any(LocalDate.class))).thenReturn(optiPrice);
		Assertions.assertNotNull(carServiceImpl.getCar(3L, LocalDate.of(2022, 1, 13)));
	
	}
	@Test
	void testGet() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);
		
		List<Car> carList = new ArrayList<>();
		Mockito.when(carRepository.findAll()).thenReturn(carList);
		Assertions.assertNotNull(carServiceImpl.get(null));
		
	}

	@Test
	void testGet1() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);
		
		List<Car> carList = new ArrayList<>();
		carList.add(car);
		Mockito.when(carRepository.findAll()).thenReturn(carList);
		Assertions.assertNotNull(carServiceImpl.get("name eq Mustang"));
		
	}
	
	@Test
	void testGet2() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		price.setEndEffectiveDate(null);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);
		
		List<Car> carList = new ArrayList<>();
		carList.add(car);
		Mockito.when(carRepository.findAll()).thenReturn(carList);
		Assertions.assertNotNull(carServiceImpl.get("brand eq Ford"));
		
	}
	
	@Test
	void testGet3() {
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(null);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);
		
		List<Car> carList = new ArrayList<>();
		carList.add(car);
		Optional<Price> optiPrice = Optional.of(price);

		Mockito.when(carRepository.findAll(Mockito.any(Specification.class))).thenReturn(carList);
		Mockito.when(priceRepository.findByCarAndEndEffectiveDateAfterAndEffectiveDateBefore(Mockito.any(Car.class),
				Mockito.any(LocalDate.class))).thenReturn(optiPrice);
 		Assertions.assertEquals(1, carServiceImpl.get("color eq black").getCars().size());
		
	}
	
	@Test
	void testExportExcel() {
		
		List<Car> carList = new ArrayList<>();

		Mockito.when(carRepository.findAll()).thenReturn(carList);
		
		EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			carServiceImpl.exportExcel();
		});

		Assertions.assertEquals("Cannot find any car", thrown.getMessage());
	}
	
	@Test
	void testExportExcel1() {
		
		List<Car> carList = null;

		Mockito.when(carRepository.findAll()).thenReturn(carList);
		
		EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
			carServiceImpl.exportExcel();
		});

		Assertions.assertEquals("Cannot find any car", thrown.getMessage());
	}
	
	@Test
	void testExportExcel2() {
		
		Brand brand = initializeBrand();
		Price price = initializePrice();
		Car car = initializeCar();
		
		List<Price> listPrice = new ArrayList<>();
		listPrice.add(price);
		List<Car> listCar = new ArrayList<>();
		price.setCar(car);
		car.setPrice(listPrice);
		car.setBrand(brand);
		listCar.add(car);
		brand.setCar(listCar);		
		
		List<Car> carListRepo = new ArrayList<>();

		CarExcelGenerator ceg = Mockito.mock(CarExcelGenerator.class);
		carListRepo.add(car);
		byte[] arrayBytes = new byte[] {0};
		Mockito.when(carRepository.findAll()).thenReturn(carListRepo);
		try {
			Mockito.when(ceg.export()).thenReturn(arrayBytes);
		} catch (IOException e) {
			Assertions.fail(toString(e));
		}
		try {
			Assertions.assertNotNull(carServiceImpl.exportExcel());
		} catch (CarServiceException e) {
			Assertions.fail(toString(e));
		}

	}


	private Car initializeCar() {
		Car car = new Car();
		car.setColor(Color.BLACK);
		car.setId(1L);
		car.setModelName("Mustang");
		return car;
		
	}
	
	private Brand initializeBrand() {
		Brand brand = new Brand();
		brand.setBrandName("Ford");
		brand.setId(1L);
		List<Car> cars = new ArrayList<>();
		brand.setCar(cars);
		
		return brand;
		
	}
	
	private Price initializePrice() {
		Price price = new Price();
		price.setEffectiveDate(LocalDate.of(1998, 06, 01));
		price.setId(1L);
		price.setEndEffectiveDate(LocalDate.of(2023, 06, 01));
		price.setValue(21.000);
		return price;
		
	}
	
	private static String toString(Throwable e) {
		StringBuffer sb = new StringBuffer();
		sb.append(e.toString()).append("\n");
		for(StackTraceElement st : e.getStackTrace()) {
			sb.append(" at ").append(st.toString()).append("\n");
		}
		return sb.toString();
	}
}
