package com.leverx.sample.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.sample.service.FeatureService;
import com.sap.cds.feature.platform.ServiceBinding;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class FeatureController {
	
	@Autowired
	private FeatureService featureService;
	
	@GetMapping("/services")
	public Map<String, ServiceBinding> getServiceBindings() {
		return featureService.getServices();
	}
	
	@GetMapping("/appName")
	public String getApplicaitonProperties() {
		return featureService.getAppName();
	}
	
	@GetMapping("/token")
	public Map<String, String> getTokenInfo() {
		return featureService.getTokenInfo();
	}
	
	@GetMapping("/fail")
	@PreAuthorize("hasAuthority('fake')")
	public String authFail() {
		return featureService.getAuthFailed();
	}
	
	@GetMapping("/success")
	public String authSuccess() {
		return featureService.getAuthSuccess();
	}
	
}
