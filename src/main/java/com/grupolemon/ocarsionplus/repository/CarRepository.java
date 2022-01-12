package com.grupolemon.ocarsionplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.grupolemon.ocarsionplus.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>,JpaSpecificationExecutor<Car> {

	
}
