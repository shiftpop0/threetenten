package edu.ncist.wang.hkdf.dao.xml;

public class DBConfigParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20151219061943L;
	
	public DBConfigParseException(){
		super("DBConfigXMLFileParseException");
	}
	public DBConfigParseException(String message){
		super(message);
	}
}
