package hu.xannosz.micro.db.core;

import hu.xannosz.micro.db.core.query.Column;
import hu.xannosz.micro.db.core.query.expression.Expression;
import hu.xannosz.micro.db.core.query.expression.LogicalExpression;
import hu.xannosz.micro.db.core.query.TableIdentifier;

import java.util.List;

public class Select extends QueryExpression {

    protected List<Expression> select;
    protected List<TableIdentifier> from;
    protected LogicalExpression where;
    protected List<Column> groupBy;
    protected LogicalExpression having;

    public Select() {

    }

    public Select(List<Expression> select, List<TableIdentifier> from, LogicalExpression where, List<Column> groupBy, LogicalExpression having) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
        this.having = having;
    }

    DataSet run(DB db) {
        
    }
}
