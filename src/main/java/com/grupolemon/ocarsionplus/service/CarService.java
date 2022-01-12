package com.grupolemon.ocarsionplus.service;

import java.time.LocalDate;

import com.grupolemon.ocarsionplus.dto.CarDTO;
import com.grupolemon.ocarsionplus.dto.FilteredCarsDTO;
import com.grupolemon.ocarsionplus.service.exceptions.CarServiceException;

public interface CarService {

	public CarDTO getCar(Long idCar, LocalDate date);

	public FilteredCarsDTO get(String filtersParam);

	public byte[] exportExcel() throws CarServiceException;
}
