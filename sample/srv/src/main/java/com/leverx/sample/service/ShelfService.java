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
import com.sap.cds.ql.cqn.CqnSelect;

@Service
public class ShelfService {
	
	private static final int BIG_SHELF_COUNT = 3;
	
	@Autowired
	RefrigeratorRepository refrigeratorRepository;
	
	@Autowired
	ShelfRepository shelfRepository;
	
	public void setBigRefrigerator(Shelf shelf) {
		Optional<Refrigerator> shelfOwner = refrigeratorRepository.findById(shelf.getRefrigeratorId());
		if (shelfOwner.isPresent()) {
			Refrigerator refrigerator = shelfOwner.get();
			List<Shelf> shelfList = refrigeratorRepository.getShelfByRefrigeratorId(refrigerator.getId());
			if (shelfList.size() >= BIG_SHELF_COUNT && !refrigerator.getBig()) {
				refrigeratorRepository.setSingleAttrById(refrigerator.getId(), "big", true);
			}
		}
	}
	
	public void setShelfInspected(CqnSelect query, boolean inspected) {
		List<Shelf> shelfList = shelfRepository.runSelect(query);
		if (!shelfList.isEmpty()) {
			Shelf shelf = shelfList.get(0);
			shelfRepository.setSingleAttrById(shelf.getId(), "inspected", inspected);
		}
	}
	
	public boolean isValidType(Stream<Shelf> shelf) {
		List<String> typeList = shelf.map(Shelf::getType).collect(Collectors.toList());
		List<Shelf> shelfList = shelfRepository.getShelfByType(typeList);
		return shelfList.isEmpty();
	}
	
	public int getNumberOfShelf(CqnSelect query) {
		Refrigerator refrigerator = refrigeratorRepository.runSelectSingle(query);
		List<Shelf> refrigeratorShelf = refrigeratorRepository.getShelfByRefrigeratorId(refrigerator.getId());
		return refrigeratorShelf.size();
	}
	
	public boolean hasUninspectedShelf(String refrigeratorId) {
		return !refrigeratorRepository.getUninspectedShelf(refrigeratorId).isEmpty();
	}
	
}
