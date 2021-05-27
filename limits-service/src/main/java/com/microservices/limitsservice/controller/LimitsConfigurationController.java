package com.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limitsservice.Configuration;
import com.microservices.limitsservice.dto.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	// @Value("${limits-service.maximum}")
	// private int maxVal;
	//
	// @Value("${limits-service.minimum}")
	// private int minVal;

	@Autowired
	Configuration limitConfig;

	@GetMapping("/limits")
	public LimitConfiguration retriveLimitsFromComfigurations() {
		return new LimitConfiguration(limitConfig.getMaximum(), limitConfig.getMinimum());

	}
}
