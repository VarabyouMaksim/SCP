package com.leverx.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leverx.sample.Shelf;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.repository.refrigerator.RefrigeratorRepositoryImpl;

@RestController
public class RefrigeratorController {
	
	@Autowired
	private RefrigeratorRepositoryImpl refrigeratorRepository;
	
	@PostMapping("/refrigerator")
	public Refrigerator createRefrigerator(@RequestBody Refrigerator refrigerator) {
		return refrigeratorRepository.createRefrigerator(refrigerator);
	}
	
	@GetMapping("/refrigerator/{id}/shelf")
	public List<Shelf> getRefrigeratorShelf(@PathVariable String id) {
		return refrigeratorRepository.getShelfByRefrigeratorId(id);
	}
	
	@GetMapping("/refrigerator")
	public List<Refrigerator> getAllRefrigerators() {
		return refrigeratorRepository.findAll();
	}

}
