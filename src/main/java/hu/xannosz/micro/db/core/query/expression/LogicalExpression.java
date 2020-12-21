package hu.xannosz.micro.db.core.query.expression;

import hu.xannosz.micro.db.core.DataSetRow;
import hu.xannosz.micro.db.core.exception.WrongLogicalOperatorException;

import java.util.Set;
import java.util.function.Function;

public class LogicalExpression implements Expression {

    private Function<DataSetRow, Boolean> run;

    private LogicalExpression() {
    }

    public LogicalExpression(NumericalExpression left, LogicalOperator op, NumericalExpression right) {
        switch (op) {
            case EQ:
                run = dataSetRow -> left.runExpression(dataSetRow).equals(right.runExpression(dataSetRow));
                break;
            case NEQ:
                run = dataSetRow -> !left.runExpression(dataSetRow).equals(right.runExpression(dataSetRow));
                break;
            case LT:
                run = dataSetRow -> left.runExpression(dataSetRow) < right.runExpression(dataSetRow);
                break;
            case NLT:
            case GR_OR_EQ:
                run = dataSetRow -> left.runExpression(dataSetRow) >= right.runExpression(dataSetRow);
                break;
            case GR:
                run = dataSetRow -> left.runExpression(dataSetRow) > right.runExpression(dataSetRow);
                break;
            case NGR:
            case LT_OR_EQ:
                run = dataSetRow -> left.runExpression(dataSetRow) <= right.runExpression(dataSetRow);
                break;
            case LIKE:
            case NOT_LIKE:
            case IN:
            case NOT_IN:
                throw new WrongLogicalOperatorException(op, "Number", "Number");
        }
    }

    public LogicalExpression(StringExpression left, LogicalOperator op, StringExpression right) {
        switch (op) {
            case LIKE:
                run = dataSetRow -> left.runExpression(dataSetRow).matches(right.runExpression(dataSetRow));
            case NOT_LIKE:
                run = dataSetRow -> !left.runExpression(dataSetRow).matches(right.runExpression(dataSetRow));
                break;
            case EQ:
            case NEQ:
            case LT:
            case NLT:
            case GR_OR_EQ:
            case GR:
            case NGR:
            case LT_OR_EQ:
            case IN:
            case NOT_IN:
                throw new WrongLogicalOperatorException(op, "String", "String");
        }
    }

    public LogicalExpression(StringExpression left, LogicalOperator op, Set<String> right) {
        switch (op) {
            case LIKE:
            case NOT_LIKE:
            case EQ:
            case NEQ:
            case LT:
            case NLT:
            case GR_OR_EQ:
            case GR:
            case NGR:
            case LT_OR_EQ:
                throw new WrongLogicalOperatorException(op, "String", "Set<String>");
            case IN:
                run = dataSetRow -> right.contains(left.runExpression(dataSetRow));
                break;
            case NOT_IN:
                run = dataSetRow -> !right.contains(left.runExpression(dataSetRow));
                break;
        }
    }

    public LogicalExpression(NumericalExpression left, LogicalOperator op, Set<Double> right) {
        switch (op) {
            case LIKE:
            case NOT_LIKE:
            case EQ:
            case NEQ:
            case LT:
            case NLT:
            case GR_OR_EQ:
            case GR:
            case NGR:
            case LT_OR_EQ:
                throw new WrongLogicalOperatorException(op, "Double", "Set<Double>");
            case IN:
                run = dataSetRow -> right.contains(left.runExpression(dataSetRow));
                break;
            case NOT_IN:
                run = dataSetRow -> !right.contains(left.runExpression(dataSetRow));
                break;
        }
    }

    public static LogicalExpression and(LogicalExpression... expressions) {
        LogicalExpression exp = new LogicalExpression();
        exp.run = dataSetRow -> {
            boolean result = true;
            for (LogicalExpression expression : expressions) {
                result &= expression.runExpression(dataSetRow);
            }
            return result;
        };
        return exp;
    }

    public static LogicalExpression or(LogicalExpression... expressions) {
        LogicalExpression exp = new LogicalExpression();
        exp.run = dataSetRow -> {
            boolean result = false;
            for (LogicalExpression expression : expressions) {
                result |= expression.runExpression(dataSetRow);
            }
            return result;
        };
        return exp;
    }

    public static LogicalExpression not(LogicalExpression expression) {
        LogicalExpression exp = new LogicalExpression();
        exp.run = dataSetRow -> !expression.runExpression(dataSetRow);
        return exp;
    }

    public Boolean runExpression(DataSetRow row) {
        return run.apply(row);
    }
}
