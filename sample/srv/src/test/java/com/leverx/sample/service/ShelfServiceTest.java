package com.leverx.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leverx.sample.Shelf;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.Refrigerator_;
import com.leverx.sample.creator.Creator;
import com.leverx.sample.repository.shelf.ShelfRepository;
import com.leverx.sample.repository.refrigerator.RefrigeratorRepository;
import com.leverx.sample.service.ShelfService;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.cqn.CqnSelect;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTest {	
	
	@Mock
	private RefrigeratorRepository refrigeratorRepository;
	
	@Mock
	private ShelfRepository shelfRepository;
	
	@InjectMocks
	private ShelfService shelfService;
	
	@Test
	public void getNumberOfShelf_givenRefrigeratorShelfNumber_shouldBeEqual() {
		//given
		Refrigerator refrigerator = Creator.createRandomRefrigerator();
		List<Shelf> expectedList = Creator.generateShelfsForRefrigerator(refrigerator.getId());
		CqnSelect query = Select.from(Refrigerator_.class).byId(refrigerator.getId());
		when(refrigeratorRepository.runSelectSingle(query)).thenReturn(refrigerator); //stub
		when(refrigeratorRepository.getShelfByRefrigeratorId(refrigerator.getId())).thenReturn(expectedList); //stub
		//when
		int numberOfShelf = shelfService.getNumberOfShelf(query);
		//then
		assertEquals(expectedList.size(), numberOfShelf);
		verify(refrigeratorRepository).runSelectSingle(eq(query));
		verify(refrigeratorRepository).getShelfByRefrigeratorId(eq(refrigerator.getId()));
	}
	
	@Test
	public void setBigRefrigerator_givenRefrigeratorWithShelfs_shouldVerifyRepositoryCalls() {
		//given
		Refrigerator refrigerator = Creator.createRandomRefrigerator();
		List<Shelf> shelfList = Creator.generateShelfsForRefrigerator(refrigerator.getId(), 3);
		when(refrigeratorRepository.findById(refrigerator.getId())).thenReturn(Optional.of(refrigerator)); //stub
		when(refrigeratorRepository.getShelfByRefrigeratorId(refrigerator.getId())).thenReturn(shelfList); //stub
		//when
		shelfService.setBigRefrigerator(shelfList.get(shelfList.size() - 1));
		//then
		verify(refrigeratorRepository).findById(eq(refrigerator.getId()));
		verify(refrigeratorRepository).setSingleAttrById(eq(refrigerator.getId()), eq("rich"), eq(true));
	}
	
	@Test
	public void setBigRefrigerator_givenRefrigeratorWithShelfs_shouldNotVerifyRepositoryCalls() {
		//given
		Refrigerator refrigerator = Creator.createRandomRefrigerator();
		List<Shelf> shelfList = Creator.generateShelfsForRefrigerator(refrigerator.getId(), 2);
		when(refrigeratorRepository.findById(refrigerator.getId())).thenReturn(Optional.of(refrigerator)); //stub
		when(refrigeratorRepository.getShelfByRefrigeratorId(refrigerator.getId())).thenReturn(shelfList); //stub
		//when
		shelfService.setBigRefrigerator(shelfList.get(shelfList.size() - 1));
		//then
		verify(refrigeratorRepository).findById(eq(refrigerator.getId()));
		verify(refrigeratorRepository, never()).setSingleAttrById(anyString(), anyString(), any());
	}

}
