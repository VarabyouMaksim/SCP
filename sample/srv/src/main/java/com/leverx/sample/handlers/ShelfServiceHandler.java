package com.leverx.sample.handlers;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;

import com.leverx.sample.context.ShelfOwnedEventContext;
import com.leverx.sample.context.HasUninspectedShelfEventContext;

import com.leverx.sample.Shelf;
import com.leverx.sample.service.ShelfService;

import leverx.com.sample.shelfservice.ShelfService_;
import leverx.com.sample.shelfservice.Shelf_;
import leverx.com.sample.shelfservice.Refrigerator_;

@Component
@ServiceName(ShelfService_.CDS_NAME)
public class ShelfServiceHandler implements EventHandler {
	
	@Autowired
	ShelfService shelfService;


	@After(event = CdsService.EVENT_CREATE, entity = Shelf_.CDS_NAME)
	public void afterMethod(CdsCreateEventContext context) {
		
	}
	
	@Before(event = CdsService.EVENT_CREATE, entity = Shelf_.CDS_NAME)
	public void beforeMethod(Stream<Shelf> shelf) {
		
	}

	@On(event = "inspect", entity = Shelf_.CDS_NAME)
	public void setInspected(EventContext context) {
		shelfService.setShelfInspected((CqnSelect)context.get("cqn"), (boolean)context.get("inspected"));
		context.setCompleted();
	}
	
	@On(event = "shelfOwned", entity = Refrigerator_.CDS_NAME)
	public void shelfOwned(ShelfOwnedEventContext context) {
		int numberOfShelf = shelfService.getNumberOfShelf((CqnSelect)context.getCqn());
		context.setResult(numberOfShelf);
		context.setCompleted();
	}
	
	@On(event = "hasUninspectedShelf")
	public void hasUninspectedShelf(HasUninspectedShelfEventContext context) {
		boolean hasUninspectedShelf = shelfService.hasUninspectedShelf(context.getRefrigeratorId());
		context.setResult(hasUninspectedShelf);
		context.setCompleted();
	}


}
