package com.leverx.sample.context;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;

@EventName("shelfOwned")
public interface ShelfOwnedEventContext extends EventContext {
	
    CqnSelect getCqn();
    void setCqn(CqnSelect select);
    
    void setResult(Integer review);
    Integer getResult();
	
}
