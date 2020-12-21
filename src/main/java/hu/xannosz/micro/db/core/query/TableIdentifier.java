package hu.xannosz.micro.db.core.query;

import hu.xannosz.micro.db.core.DataSet;
import lombok.Getter;

public class TableIdentifier {

    @Getter
    private String tableName;
    @Getter
    private String identifier;

    public TableIdentifier(String tableName) {
        this(tableName, tableName);
    }

    public TableIdentifier(String tableName, String identifier) {
        this.tableName = tableName;
        this.identifier = identifier;
    }
}
