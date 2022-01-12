package com.grupolemon.ocarsionplus.dto;

import com.grupolemon.ocarsionplus.model.Color;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDTO {

	private Long idCar;

	private Long idBrand;

	private PriceDTO price;

	private Color color;

}
