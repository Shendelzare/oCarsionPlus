package com.grupolemon.ocarsionplus.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@XmlRootElement(name = "error")
public class ErrorResponseDTO {
	@Getter
	private int statusCode;
	@Getter
	private Date timestamp;
	@Getter
	private String message;
	@Getter
	private String description;

	public ErrorResponseDTO(int statusCode, Date timestamp, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

}