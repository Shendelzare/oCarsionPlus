package com.grupolemon.ocarsionplus.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;

	@OneToMany(mappedBy = "brand")
	private List<Car> car;

	@Column(name = "NAME")
	private String brandName;
}
