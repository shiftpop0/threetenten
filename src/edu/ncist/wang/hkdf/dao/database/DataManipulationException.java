package edu.ncist.wang.hkdf.dao.database;

public class DataManipulationException extends Exception {

	public DataManipulationException(){
		super("DataManipulationException");
	}
	public DataManipulationException(String message){
		super(message);
	}
}
