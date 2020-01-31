package com.leverx.sample.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.leverx.sample.Shelf;
import com.leverx.sample.Refrigerator;
import com.leverx.sample.util.ValueGenerator;
import com.sap.cds.Result;
import com.sap.cds.Row;
import com.sap.cds.Struct;
import com.sap.cds.impl.RowImpl;
import com.sap.cds.impl.ResultImpl;

public class Creator {
	
	private static final int STRING_LENGTH = 10;
	private static final int VIN_LENGTH = 17;
	private static final int MIN_SHELF_COUNT = 1;
	private static final int MAX_SHELF_COUNT = 5;
	
	private Creator() {}
	
	public static Refrigerator createRefrigerator() {
		return Struct.create(Refrigerator.class);
	}
	
	public static Shelf createShelf() {
		return Struct.create(Shelf.class);
	}

	public static Refrigerator createRandomRefrigerator() {
		Refrigerator refrigerator = createRefrigerator();
		refrigerator.setId(ValueGenerator.getRandomUUID());
		refrigerator.setBrand(RandomStringUtils.randomAlphabetic(STRING_LENGTH));
		refrigerator.setVolume(new BigDecimal("100"));
		refrigerator.setUoM(RandomStringUtils.randomAlphabetic(STRING_LENGTH));
		refrigerator.setBig(false);
		return refrigerator;
	}
	
	public static Shelf createRandomShelf() {
		Shelf shelf = createShelf();
		shelf.setId(ValueGenerator.getRandomUUID());
		shelf.setInspected(ValueGenerator.getRandomBoolean());
		shelf.setMaterial(RandomStringUtils.randomAlphabetic(STRING_LENGTH));
		shelf.setType(RandomStringUtils.randomAlphabetic(STRING_LENGTH));
		shelf.setRefrigeratorId(ValueGenerator.getRandomUUID());
		return shelf;
	}
	
	public static Shelf createRandomShelfForRefrigerator(String refrigeratorId) {
		Shelf shelf = createRandomShelf();
		shelf.setRefrigeratorId(refrigeratorId);
		return shelf;
	}
	
	public static List<Shelf> generateShelfsForRefrigerator(String refrigeratorId, int count) {
		List<Shelf> shelfList = new ArrayList<Shelf>();
		for (int i = 0; i < count; i++) {
			shelfList.add(createRandomShelfForRefrigerator(refrigeratorId));
		}
		return shelfList;
	}
	
	public static List<Shelf> generateShelfsForRefrigerator(String refrigeratorId) {
		return generateShelfsForRefrigerator(refrigeratorId, ValueGenerator.getRandomInt(MIN_SHELF_COUNT, MAX_SHELF_COUNT));
	}
	
	public static List<Shelf> generateRandomShelfs(int count) {
		List<Shelf> shelfList = new ArrayList<Shelf>();
		for (int i = 0; i < count; i++) {
			shelfList.add(createRandomShelf());
		}
		return shelfList;
	}
	
	public static List<Shelf> generateRandomShelf() {
		return generateRandomShelf(ValueGenerator.getRandomInt(MIN_SHELF_COUNT, MAX_SHELF_COUNT));
	}
	
	public static Row createRandomShelfRow() {
		Shelf shelf = createRandomShelf();		
		return new RowImpl((Map.ofEntries(
				Map.entry(Shelf.ID, shelf.getId()),
				Map.entry(Shelf.INSPECTED, shelf.getInspected()),
				Map.entry(Shelf.MATERIAL, shelf.getMaterial()),
				Map.entry(Shelf.TYPE, shelf.getType()),
				Map.entry(Shelf.REFRIGERATOR_ID, shelf.getRefrigeratorId())
	    )));
	}
	
	public static Result createRandomShelfResult(int rowCount) {
		List<Row> rows = new ArrayList<Row>();
		for (int i = 0; i < rowCount; i++) {
			rows.add(createRandomShelfRow());
		}
		return ResultImpl.query(rows);
	}
	
	public static Result createRandomShelfResult() {
		return createRandomShelfResult(ValueGenerator.getRandomInt(MIN_SHELF_COUNT, MAX_SHELF_COUNT));
	}
	
}
