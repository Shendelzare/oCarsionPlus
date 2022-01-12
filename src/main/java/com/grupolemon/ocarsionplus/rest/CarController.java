package com.grupolemon.ocarsionplus.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupolemon.ocarsionplus.dto.CarDTO;
import com.grupolemon.ocarsionplus.dto.FilteredCarsDTO;
import com.grupolemon.ocarsionplus.model.ServiceEnum;
import com.grupolemon.ocarsionplus.service.ApiCallService;
import com.grupolemon.ocarsionplus.service.CarService;
import com.grupolemon.ocarsionplus.service.exceptions.CarServiceException;
import com.grupolemon.ocarsionplus.utils.Constants;

@RestController
@RequestMapping
public class CarController {

	@Autowired
	private CarService carService;
	@Autowired
	private ApiCallService apiCallService;

	@GetMapping("/cars/{carId}")
	public ResponseEntity<CarDTO> getCar(
			@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
			@PathVariable(name = "carId", required = true) Long idCar,
			 HttpServletRequest request) {
		apiCallService.logCall(ServiceEnum.CAR_DETAIL, request.getRemoteAddr());
		CarDTO car = carService.getCar(idCar, date);
		
		return new ResponseEntity<>(car, HttpStatus.OK);

	}

	@GetMapping("/cars")
	public ResponseEntity<FilteredCarsDTO> getCar(@RequestParam(name="filter") String filtersParam, HttpServletRequest request) {
		apiCallService.logCall(ServiceEnum.LIST_CAR_FILTERED, request.getRemoteAddr());
		FilteredCarsDTO filteredCars = carService.get(filtersParam);
		
		return new ResponseEntity<>(filteredCars, HttpStatus.OK);

	}

	@GetMapping("/cars/export/excel")
	public ResponseEntity<ByteArrayResource> exportToExcel( HttpServletRequest request) throws CarServiceException {
		apiCallService.logCall(ServiceEnum.EXPORT_CARS, request.getRemoteAddr());

		DateFormat dateFormatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		String currentDateTime = dateFormatter.format(new Date());

		byte[] excelContent = carService.exportExcel();

		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "force-download"));
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cars_" + currentDateTime + ".xlsx");
		header.set(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return new ResponseEntity<>(new ByteArrayResource(excelContent), header, HttpStatus.OK);
	}
	
	
	

}
