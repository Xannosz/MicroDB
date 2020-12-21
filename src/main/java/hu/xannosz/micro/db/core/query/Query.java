package hu.xannosz.micro.db.core.query;

import hu.xannosz.micro.db.core.query.expression.Expression;

import java.util.Arrays;

public class Query {
    public static From select(Expression... select) {
       return new From(Arrays.asList(select));
    }
}
