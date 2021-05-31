package com.microservices.limitsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "limits-service")
@Data
@NoArgsConstructor
public class Configuration {
	private int minimum;
	private int maximum;

}
