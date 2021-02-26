package edu.ncist.wang.hkdf.bussiness.blo;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.bli.RequestHandlable;

public abstract class AbstractRequestHandler implements RequestHandlable {

	public String getParameters(HttpServletRequest request, String key){
		
		String value = "";
		
		value = request.getParameter(key);
		if(null == value){
			value = "";
		}

		return value;
	}

	public String[] getParameterValues(HttpServletRequest request, String key){
		
		String [] values = null;
		
		values = request.getParameterValues(key);

		return values;
	}

	public void ActionTypeError(){
		System.out.println("ActionType not found in Request process...");
	}
	
}
