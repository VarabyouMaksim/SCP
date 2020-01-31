package com.leverx.sample.repository.refrigerator;

import java.util.List;

import com.leverx.sample.Shelf;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.repository.Repository;
import com.sap.cds.ql.cqn.CqnSelect;

public interface RefrigeratorRepository extends Repository<Refrigerator, String> {
	
	public List<Shelf> getShelfByRefrigeratorId(String id);

	public void setSingleAttrById(String id, String prop, Object value);
	
	public Refrigerator createRefrigerator(Refrigerator refrigarator);

	public Refrigerator runSelectSingle(CqnSelect query);
	
	public List<Refrigerator> runSelect(CqnSelect query);

	public List<Shelf> getUninspectedShelf(String id);
	
}
