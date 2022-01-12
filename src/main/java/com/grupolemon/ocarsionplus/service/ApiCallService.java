package com.grupolemon.ocarsionplus.service;

import com.grupolemon.ocarsionplus.model.ServiceEnum;

public interface ApiCallService {
	
	public void logCall(ServiceEnum service, String ip);

}
