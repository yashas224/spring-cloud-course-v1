package com.microservices.currencyexchangeservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.currencyexchangeservice.entity.ExchangeValue;

public interface ExchangeValueRepositry extends JpaRepository<ExchangeValue, Long> {
	public ExchangeValue findByFromAndTo(String from, String to);

}
