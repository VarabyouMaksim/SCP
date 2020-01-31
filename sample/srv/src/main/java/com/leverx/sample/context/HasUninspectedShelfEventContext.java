package com.leverx.sample.context;

import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.EventName;

@EventName("hasUninspectedShelf")
public interface HasUninspectedShelfEventContext extends EventContext {

    CqnSelect getCqn();
    void setCqn(CqnSelect select);
    
    void setResult(Boolean result);
    Boolean getResult(); 
    
    String getRefrigeratorId();
    void setRefrigeratorId(String refrigeratorId);
	
}
