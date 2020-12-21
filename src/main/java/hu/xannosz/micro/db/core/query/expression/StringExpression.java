package hu.xannosz.micro.db.core.query.expression;

import hu.xannosz.micro.db.core.DataSet;
import hu.xannosz.micro.db.core.DataSetRow;
import hu.xannosz.micro.db.core.query.Column;

import java.util.function.Function;

public class StringExpression implements Expression{

    private Function<DataSetRow, String> run;

    public StringExpression(String exp) {
        run = dataSetRow -> exp;
    }

    public StringExpression(Column column) {
        run = dataSetRow -> dataSetRow.getData(column);
    }

    public StringExpression(StringExpression... exps) {
        run = dataSetRow -> {
            StringBuilder builder = new StringBuilder();
            for (StringExpression exp : exps) {
                builder.append(exp.runExpression(dataSetRow));
            }
            return builder.toString();
        };
    }

    public String runExpression(DataSetRow row){
        return run.apply(row);
    }
}
