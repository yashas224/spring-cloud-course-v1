package com.microservices.currencyconversionservice.restClient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.currencyconversionservice.dto.CurrencyConversionBean;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")

// if this service is calling the currency-exchange-service directly with euraka
/*@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/currency-exchange/from/{fromVal}/to/{toVal}")
	public CurrencyConversionBean retriveExchangeValue(@PathVariable(name = "fromVal") String from,
			@PathVariable(name = "toVal") String to);
}
*/

//if this service is calling the currency-exchange-service via the AUUL Gateway with euraka

@FeignClient(name = "NETFLIX-ZUUL-API-GATEWAY-SERVER")
@RibbonClient(name = "NETFLIX-ZUUL-API-GATEWAY-SERVER")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/currency-exchange-service/currency-exchange/from/{fromVal}/to/{toVal}")
	public CurrencyConversionBean retriveExchangeValue(@PathVariable(name = "fromVal") String from,
			@PathVariable(name = "toVal") String to);
}
