package com.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.currencyconversionservice.dto.CurrencyConversionBean;
import com.microservices.currencyconversionservice.restClient.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class.getName());

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@GetMapping("/currency-converter/from/{fromVal}/to/{toVal}/quantity/{quantity}")
	public ResponseEntity<CurrencyConversionBean> convertCurrency(@PathVariable String fromVal,
			@PathVariable String toVal, @PathVariable BigDecimal quantity) {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("from", fromVal);
			map.put("to", toVal);
			CurrencyConversionBean obj = restTemplate
					.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
							CurrencyConversionBean.class, map)
					.getBody();
			obj.setQuantity(quantity);
			obj.setTotalCalculatedAmount(quantity.multiply(obj.getConversionMultiple()));
			return new ResponseEntity<CurrencyConversionBean>(obj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/currency-converter-feign/from/{fromVal}/to/{toVal}/quantity/{quantity}")
	public ResponseEntity<CurrencyConversionBean> convertCurrencyFeign(@PathVariable String fromVal,
			@PathVariable String toVal, @PathVariable BigDecimal quantity) {
		try {
			CurrencyConversionBean obj = currencyExchangeServiceProxy.retriveExchangeValue(fromVal, toVal);
			obj.setQuantity(quantity);
			obj.setTotalCalculatedAmount(quantity.multiply(obj.getConversionMultiple()));
			LOGGER.info("{}", obj);
			return new ResponseEntity<CurrencyConversionBean>(obj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
