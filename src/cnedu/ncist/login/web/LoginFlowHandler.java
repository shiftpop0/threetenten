package cnedu.ncist.login.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.bli.FlowHandlable;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class LoginFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map paraMap) {
		// TODO Auto-generated method stub

		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("login_Init".equals(actionType)){
			return login_login(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	public String login_login(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

//		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
}
