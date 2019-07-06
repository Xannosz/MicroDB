package hu.xannosz.micro.db.core.exception;

public class TableNotFoundException extends DBException {

	private static final long serialVersionUID = 590489869375538952L;

	public TableNotFoundException(String name) {
		super(name+" table not fund.");
	}
}
