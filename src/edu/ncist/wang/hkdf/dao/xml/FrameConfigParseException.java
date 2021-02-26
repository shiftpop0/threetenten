package edu.ncist.wang.hkdf.dao.xml;

public class FrameConfigParseException extends Exception {

	private static final long serialVersionUID = 20151222113443L;
	
	public FrameConfigParseException(){
		super("DemoFrameConfigParseException");
	}
	public FrameConfigParseException(String message){
		super(message);
	}
}
