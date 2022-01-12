package com.grupolemon.ocarsionplus.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "api_calls")
public class ApiCall {

	@Id
	private String id;
	private ServiceEnum service;
	private LocalDateTime datetime;	
	private String ip;
	
	public ApiCall(ServiceEnum service, LocalDateTime datetime,String ip) {
		this.service=service;
		this.datetime = datetime;
		this.ip=ip;
	}
}
