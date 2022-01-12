package com.grupolemon.ocarsionplus.repository;

import java.text.MessageFormat;

import org.springframework.data.jpa.domain.Specification;

import com.grupolemon.ocarsionplus.model.Car;
import com.grupolemon.ocarsionplus.model.Color;

public final class CarSpecification {

	private CarSpecification() {
	}

	public static Specification<Car> modelNameContains(String expression) {
		return (root, query, builder) -> builder.like(builder.upper(root.get("modelName")), contains(expression));
	}

	public static Specification<Car> colorContains(Color expression) {
		return (root, query, builder) -> builder.equal(root.get("color"), expression);
	}
	
	public static Specification<Car> brandContains(String expression) {
		return (root, query, builder) -> builder.like(builder.upper(root.get("brand").get("brandName")), contains(expression));
	}

	private static String contains(String expression) {
		return MessageFormat.format("%{0}%", expression);
	}
}
