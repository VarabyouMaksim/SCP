package com.leverx.sample.handlers;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.ErrorStatuses;
import com.sap.cds.services.ServiceException;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsService;

import com.leverx.sample.Shelf;
import com.leverx.sample.service.ShelfService;

import leverx.com.sample.shelfservice.ShelfService_;
import leverx.com.sample.shelfservice.Shelf_;

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


}
