package hu.xannosz.micro.db.core.exception;

public class WrongColumnIdentifierException extends DBException {

	private static final long serialVersionUID = 2327322079877407046L;

	public WrongColumnIdentifierException(String name, String col) {
		super(name + " table don't have column with name: " + col);
	}

}
