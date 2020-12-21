package hu.xannosz.micro.db.core.query.expression;

import hu.xannosz.micro.db.core.DataSetRow;
import hu.xannosz.micro.db.core.query.Column;

import java.util.function.Function;

public class NumericalExpression implements Expression {

    private Function<DataSetRow, Double> run;

    public NumericalExpression(Double exp) {
        run = dataSetRow -> exp;
    }

    public NumericalExpression(Column column) {
        run = dataSetRow -> Double.parseDouble(dataSetRow.getData(column));
    }

    public NumericalExpression(NumericalExpression left, NumericalOperator op, NumericalExpression right) {
        switch (op) {
            case PLUS:
                run = dataSetRow -> left.runExpression(dataSetRow) + right.runExpression(dataSetRow);
                break;
            case MINUS:
                run = dataSetRow -> left.runExpression(dataSetRow) - right.runExpression(dataSetRow);
                break;
            case MULTIPLY:
                run = dataSetRow -> left.runExpression(dataSetRow) * right.runExpression(dataSetRow);
                break;
            case MOD:
                run = dataSetRow -> left.runExpression(dataSetRow) % right.runExpression(dataSetRow);
                break;
            case DIVIDE:
                run = dataSetRow -> left.runExpression(dataSetRow) / right.runExpression(dataSetRow);
                break;
            case POWER:
                run = dataSetRow -> Math.pow(left.runExpression(dataSetRow), right.runExpression(dataSetRow));
                break;
        }
    }

    public Double runExpression(DataSetRow row) {
        return run.apply(row);
    }
}
