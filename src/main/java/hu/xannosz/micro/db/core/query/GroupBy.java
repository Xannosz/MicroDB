package hu.xannosz.micro.db.core.query;

import hu.xannosz.micro.db.core.Select;
import hu.xannosz.micro.db.core.query.expression.Expression;
import hu.xannosz.micro.db.core.query.expression.LogicalExpression;

import java.util.Arrays;
import java.util.List;

public class GroupBy extends Select {

    GroupBy(List<Expression> select, List<TableIdentifier> from, LogicalExpression expression){
        this.select = select;
        this.from = from;
        this.where = expression;
    }

    public Having groupBy(Column... groupBy){
        return new Having(select, from, where, Arrays.asList(groupBy));
    }
}
