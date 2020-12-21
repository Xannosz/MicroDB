package hu.xannosz.micro.db.core.query;

import hu.xannosz.micro.db.core.Select;
import hu.xannosz.micro.db.core.query.expression.Expression;
import hu.xannosz.micro.db.core.query.expression.LogicalExpression;

import java.util.List;

public class Having extends Select {

    Having(List<Expression> select, List<TableIdentifier> from, LogicalExpression where, List<Column> groupBy) {
        this.select = select;
        this.from = from;
        this.where = where;
        this.groupBy = groupBy;
    }

    public Select having(LogicalExpression having) {
       return new Select(select, from, where, groupBy, having);
    }
}
