package hu.xannosz.micro.db.core;

import java.util.HashSet;
import java.util.Set;

import hu.xannosz.micro.db.core.exception.TableNotFoundException;

public class DB {

	private Set<Table> tables = new HashSet<>();

	public Table getTable(String name) {
		for (Table table : tables) {
			if (table.getName().equals(name)) {
				return new Table(table);
			}
		}
		throw new TableNotFoundException(name);
	}
}
