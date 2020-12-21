package hu.xannosz.micro.db.core;

public abstract class QueryExpression {
    abstract DataSet run(DB db);
}
