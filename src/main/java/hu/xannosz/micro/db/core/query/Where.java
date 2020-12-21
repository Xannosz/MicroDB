package hu.xannosz.micro.db.core.query;

import hu.xannosz.micro.db.core.Select;
import hu.xannosz.micro.db.core.query.expression.Expression;
import hu.xannosz.micro.db.core.query.expression.LogicalExpression;

import java.util.List;

public class Where extends Select {

    Where(List<Expression> select, List<TableIdentifier> from) {
        this.select = select;
        this.from = from;
    }

    public GroupBy where(LogicalExpression where) {
       return new GroupBy(select, from, where);
    }
}
