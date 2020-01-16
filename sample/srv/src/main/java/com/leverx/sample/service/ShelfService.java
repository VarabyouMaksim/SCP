package com.leverx.sample.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverx.sample.Shelf;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.repository.shelf.ShelfRepository;
import com.leverx.sample.repository.refrigerator.RefrigeratorRepository;

@Service
public class ShelfService {
	
	@Autowired
	RefrigeratorRepository refrigeratorRepository;
	
	@Autowired
	ShelfRepository shelfRepository;
	
}
