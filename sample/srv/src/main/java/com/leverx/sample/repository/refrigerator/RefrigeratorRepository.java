package com.leverx.sample.repository.refrigerator;

import java.util.List;

import com.leverx.sample.Shelf;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.repository.Repository;

public interface RefrigeratorRepository extends Repository<Refrigerator, String> {
	
	public List<Shelf> getShelfByRefrigeratorId(String id);

	public void setSingleAttrById(String id, String prop, Object value);
	
	public Refrigerator createRefrigerator(Refrigerator refrigarator);
	
}
