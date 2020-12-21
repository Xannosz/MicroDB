package hu.xannosz.micro.db.core;

import hu.xannosz.micro.db.core.query.Column;

import java.util.Map;

public class DataSetRow {

    private Map<Column,String> data;

    public String getData(Column column){
        return data.get(column);
    }
}
