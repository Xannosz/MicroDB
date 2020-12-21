package hu.xannosz.micro.db.core.query;

import lombok.Getter;

public class Column {

    @Getter
    private String tableIdentifier;
    @Getter
    private String name;
    @Getter
    private String as;

    public Column(String name) {
        this(null, name);
    }

    public Column(String tableIdentifier, String name ) {
        this.tableIdentifier = tableIdentifier;
        this.name = name;
    }

    public void as(String as) {
        this.as = as;
    }
}
