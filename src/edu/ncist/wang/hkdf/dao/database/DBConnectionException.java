package edu.ncist.wang.hkdf.dao.database;

public class DBConnectionException extends Exception {

	public DBConnectionException(){
		super("DBConnectionErrorException");
	}
	public DBConnectionException(String message){
		super(message);
	}
}
