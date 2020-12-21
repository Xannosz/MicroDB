package hu.xannosz.micro.db.core.query;

import hu.xannosz.micro.db.core.query.expression.Expression;

import java.util.Arrays;
import java.util.List;

public class From {

    private List<Expression> select;

    From(List<Expression> select) {
        this.select = select;
    }

    public Where from(TableIdentifier... from){
        return new Where(select, Arrays.asList(from));
    }
}
