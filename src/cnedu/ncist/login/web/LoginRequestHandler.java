package cnedu.ncist.login.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class LoginRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map paraMap){
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

	public Map<String, String> login_login(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String loginId = getParameters(request, "logId");
		conditionMap.put("LOGINID", loginId);
		
		String password = getParameters(request, "password");
		conditionMap.put("PASSWORD", password);
		
		//String verifyCode = getParameters(request, "Verif");
		//conditionMap.put("VERIFYCODE", verifyCode);
		
		return conditionMap;
	}
}
