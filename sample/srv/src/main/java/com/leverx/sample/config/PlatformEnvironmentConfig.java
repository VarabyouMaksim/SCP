package com.leverx.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sap.cds.feature.platform.PlatformEnvironment;

@Configuration
@Profile(value = { "cloud", "production" })
public class PlatformEnvironmentConfig {
	
	@Bean
	public PlatformEnvironment platformEnv() {
		return PlatformEnvironment.INSTANCE;
	}
	
}
