package hu.xannosz.micro.db.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hu.xannosz.micro.db.core.exception.WrongColumnIdentifierException;
import lombok.Getter;

public class Table {

	@Getter
	private String name;
	private List<List<String>> table = new ArrayList<>();
	private Map<String, Integer> columns = new HashMap<>();

	public Table(String name) {
		this.name = name;
	}
}
