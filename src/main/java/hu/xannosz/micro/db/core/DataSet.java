package hu.xannosz.micro.db.core;

import hu.xannosz.micro.db.core.query.Column;
import hu.xannosz.micro.db.core.query.TableIdentifier;

import java.util.ArrayList;
import java.util.List;

public class DataSet extends TableIdentifier {

    private List<Column> columns = new ArrayList<>();
    private List<DataSetRow> table = new ArrayList<>();

    public DataSet(String tableName) {
        super(tableName);
    }
}
