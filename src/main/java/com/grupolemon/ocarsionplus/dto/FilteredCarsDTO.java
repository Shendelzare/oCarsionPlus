package com.grupolemon.ocarsionplus.dto;

import java.util.List;

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
public class FilteredCarsDTO {
	@Getter
	@Setter
	private List<CarDTO> cars;

}
