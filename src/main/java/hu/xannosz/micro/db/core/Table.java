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

	public Table(Table table) {
		this.name = new String(table.name);
		this.table = new ArrayList<>(table.table);
		this.columns = new HashMap<>(table.columns);
	}

	public Table select(String... cols) {
		List<Integer> indexs = new ArrayList<>();
		List<List<String>> filteredTable = new ArrayList<>();
		Map<String, Integer> newCols = new HashMap<>();
		for (int i = 0; i < cols.length; i++) {
			if (columns.containsKey(cols[i])) {
				indexs.add(columns.get(cols[i]));
				newCols.put(cols[i], i);
			} else {
				throw new WrongColumnIdentifierException(name, cols[i]);
			}
		}
		for (List<String> row : table) {
			List<String> newRow = new ArrayList<>();
			for (int i : indexs) {
				newRow.add(row.get(i));
			}
			filteredTable.add(newRow);
		}
		table = filteredTable;
		columns = newCols;
		return this;
	}

	public Table where(Filter filter) {
		List<List<String>> filteredTable = new ArrayList<>();
		for (List<String> row : table) {
			if (filter.match(getRowMap(row))) {
				filteredTable.add(row);
			}
		}
		table = filteredTable;
		return this;
	}

	private Map<String, String> getRowMap(List<String> row) {
		Map<String, String> tmpMap = new HashMap<>();
		for (Entry<String, Integer> col : columns.entrySet()) {
			tmpMap.put(col.getKey(), row.get(col.getValue()));
		}
		return tmpMap;
	}

	public Table join(Table... others) {
		distinct();
		List<List<String>> newTable = new ArrayList<>();
		Map<String, Integer> newColumns = new HashMap<>();
		String newName = name;

		for (List<String> row : table) {
			newTable.add(row);
		}
		for (Entry<String, Integer> col : columns.entrySet()) {
			newColumns.put(name + "." + col.getKey(), col.getValue());
		}

		for (Table other : others) {
			other.distinct();
			newName += ("&" + other.name);

			List<List<String>> tmpTable = new ArrayList<>();
			for (List<String> row : newTable) {
				List<String> tmpRow = new ArrayList<>(row);
				for (List<String> row2 : other.table) {
					tmpRow.addAll(row2);
				}
				tmpTable.add(tmpRow);
			}
			newTable = tmpTable;

			Map<String, Integer> tmpColumns = new HashMap<>(newColumns);
			for (Entry<String, Integer> col : other.columns.entrySet()) {
				tmpColumns.put(other.name + "." + col.getKey(), newColumns.size() - 1 + col.getValue());
			}
			newColumns = tmpColumns;
		}

		columns = newColumns;
		table = newTable;
		name = newName;
		distinct();
		return this;
	}

	public Table renameColumn(String col, String newCol) {
		if (columns.containsKey(col)) {
			int value = columns.get(col);
			columns.remove(col);
			columns.put(newCol, value);
		} else {
			throw new WrongColumnIdentifierException(name, col);
		}
		return this;
	}

	public Table createColumn(String name, ColumnCreator creator) {
		List<List<String>> filteredTable = new ArrayList<>();
		for (List<String> row : table) {
			List<String> newRow = new ArrayList<>(row);
			newRow.add(creator.create(getRowMap(row)));
			filteredTable.add(newRow);
		}
		columns.put(name, columns.size());
		table = filteredTable;
		return this;
	}

	public Table distinct() {
		table = new ArrayList<>(new HashSet<>(table));
		return this;
	}

	public Table aggregate(Aggregator... aggregators) {
		List<List<String>> newTable = new ArrayList<>();
		Map<String, Integer> newColumns = new HashMap<>();

		Map<String, List<String>> columnMap = new HashMap<>();
		for (Entry<String, Integer> col : columns.entrySet()) {
			columnMap.put(col.getKey(), new ArrayList<>());
		}

		for (List<String> row : table) {
			for (Entry<String, String> field : getRowMap(row).entrySet()) {
				columnMap.get(field.getKey()).add(field.getValue());
			}
		}

		for (Aggregator agg : aggregators) {
			newTable.add(agg.aggregate(columnMap.get(agg.getResourceColumnName())));
			newColumns.put(agg.getResultColumnName(), newColumns.size());
		}

		table = newTable;
		columns = newColumns;
		return this;
	}

	public Table renameTable(String name) {
		this.name = name;
		return this;
	}

	public List<List<String>> list() {
		return new ArrayList<>(table);
	}
}
