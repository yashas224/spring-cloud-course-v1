package com.microservices.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.dao.ExchangeValueRepositry;
import com.microservices.currencyexchangeservice.entity.ExchangeValue;

@RestController
public class CurrencyExchangeController {
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class.getName());

	@Value("${server.port}")
	private int port;

	@Autowired
	private ExchangeValueRepositry repositry;

	@GetMapping("/currency-exchange/from/{fromVal}/to/{toVal}")
	public ExchangeValue retriveExchangeValue(@PathVariable(name = "fromVal") String from,
			@PathVariable(name = "toVal") String to) {
		ExchangeValue obj = repositry.findByFromAndTo(from, to);
		obj = obj == null ? new ExchangeValue() : obj;
		obj.setPort(port);
		LOGGER.info("{}", obj);
		return obj;
	}
}
