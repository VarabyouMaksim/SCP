package com.leverx.sample.repository.shelf;

import java.util.List;

import com.leverx.sample.Shelf;
import com.leverx.sample.repository.Repository;

public interface ShelfRepository extends Repository<Shelf, String> {
	
	public void setSingleAttrById(String id, String prop, Object value);
	
	
}
