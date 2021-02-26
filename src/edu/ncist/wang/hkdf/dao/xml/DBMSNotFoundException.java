package edu.ncist.wang.hkdf.dao.xml;

public class DBMSNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20151219060541L;
	
	public DBMSNotFoundException(){
		super("DBMSNameNotFoundException");
	}
	public DBMSNotFoundException(String message){
		super(message);
	}
}
