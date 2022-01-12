
package com.grupolemon.ocarsionplus.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.grupolemon.ocarsionplus.model.Car;
import com.grupolemon.ocarsionplus.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

	@Query("select p from Price p where p.car = ?1 "
			+ "and ((?2 BETWEEN p.effectiveDate AND p.endEffectiveDate)"
			+ "or(?2> p.effectiveDate AND p.endEffectiveDate is null))")
	public Optional<Price> findByCarAndEndEffectiveDateAfterAndEffectiveDateBefore(Car car, LocalDate date);
	
}
