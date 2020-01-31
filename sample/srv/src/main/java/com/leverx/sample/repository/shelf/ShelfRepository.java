package com.leverx.sample.repository.shelf;

import java.util.List;

import com.leverx.sample.Shelf;
import com.leverx.sample.repository.Repository;
import com.sap.cds.ql.cqn.CqnSelect;

public interface ShelfRepository extends Repository<Shelf, String> {

	public List<Shelf> runSelect(CqnSelect query);
	
	public void setSingleAttrById(String id, String prop, Object value);
	
	public List<Shelf> getShelfByType(List<String> typeList);
	
}
