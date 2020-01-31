package com.leverx.sample.repository.refrigerator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leverx.sample.Shelf;
import com.leverx.sample.Shelf_;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.Refrigerator_;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;

@Repository
public class RefrigeratorRepositoryImpl implements RefrigeratorRepository {
	
	@Autowired
	private PersistenceService persistenceService;

	@Override
	public Optional<Refrigerator> findById(String id) {
		CqnSelect query = Select.from(Refrigerator_.class).distinct().byId(id);
		return Optional.of(persistenceService.run(query).single().as(Refrigerator.class));
	}

	@Override
	public List<Refrigerator> findAll() {
		CqnSelect query = Select.from(Refrigerator_.class);
		return persistenceService.run(query).listOf(Refrigerator.class);
	}

	@Override
	public List<Shelf> getShelfByRefrigeratorId(String id) {
		CqnSelect query = Select.from(Shelf_.class).where(shelf -> shelf.refrigerator_ID().eq(id));
		return persistenceService.run(query).listOf(Shelf.class);
	}

	@Override
	public void setSingleAttrById(String id, String prop, Object value) {
		CqnUpdate query = Update.entity(Refrigerator_.class).byId(id).data(prop, value);
		persistenceService.run(query);
	}

	@Override
	public Refrigerator createRefrigerator(Refrigerator refrigerator) {
		CqnInsert query = Insert.into(Refrigerator_.class).entry(refrigerator);
		return persistenceService.run(query).single().as(Refrigerator.class);
	}

	@Override
	public List<Refrigerator> runSelect(CqnSelect query) {
		return persistenceService.run(query).listOf(Refrigerator.class);
	}

	@Override
	public List<Shelf> getUninspectedShelf(String id) {
		CqnSelect query = Select.from(Shelf_.class).byId(id).where(shelf -> shelf.inspected().eq(false));
		return persistenceService.run(query).listOf(Shelf.class);
	}

	@Override
	public Refrigerator runSelectSingle(CqnSelect query) {
		return persistenceService.run(query).single().as(Refrigerator.class);
	}

}
