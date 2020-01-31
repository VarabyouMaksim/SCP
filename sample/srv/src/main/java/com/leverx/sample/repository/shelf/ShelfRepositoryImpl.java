package com.leverx.sample.repository.shelf;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leverx.sample.Shelf;
import com.leverx.sample.Shelf_;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;

@Repository
public class ShelfRepositoryImpl implements ShelfRepository {
	
	@Autowired
	private PersistenceService persistenceService;

	@Override
	public Optional<Shelf> findById(String id) {
		CqnSelect query = Select.from(Shelf_.class).distinct().byId(id);
		return Optional.of(persistenceService.run(query).single().as(Shelf.class));
	}

	@Override
	public List<Shelf> findAll() {
		CqnSelect query = Select.from(Shelf_.class);
		return persistenceService.run(query).listOf(Shelf.class);
	}

	@Override
	public void setSingleAttrById(String id, String prop, Object value) {
		CqnUpdate query = Update.entity(Shelf_.class).byId(id).data(prop, value);
		persistenceService.run(query);
	}

	@Override
	public List<Shelf> runSelect(CqnSelect query) {
		System.out.println(query);
		System.out.println(persistenceService);
		return persistenceService.run(query).listOf(Shelf.class);
	}

	@Override
	public List<Shelf> getShelfByType(List<String> typeList) {
		CqnSelect query = Select.from(Shelf_.class).where(shelf -> shelf.type().in(typeList.stream().toArray(String[]::new)));
		return persistenceService.run(query).listOf(Shelf.class);
	}

	
}
