package hu.xannosz.micro.db.core.exception;

import hu.xannosz.micro.db.core.query.expression.LogicalOperator;

public class WrongLogicalOperatorException extends DBException {
    public WrongLogicalOperatorException(LogicalOperator operator, String type1, String type2) {
        super(operator.toString() + " not applicable with " + type1 + " and " + type2);
    }
}
