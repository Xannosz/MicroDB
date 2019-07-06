package hu.xannosz.micro.db.core.exception;

public class DBException extends RuntimeException{

	private static final long serialVersionUID = 3862726673611623756L;

	public DBException(String message){
		super(message);
	}
}
