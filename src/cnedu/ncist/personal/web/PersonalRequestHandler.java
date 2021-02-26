package cnedu.ncist.personal.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class PersonalRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("personal_ChangeInit".equals(actionType)){
			return personal_changeInit(map);
		}
		else if("personal_Change".equals(actionType)){
			return personal_Change(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, String> personal_changeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}

	public Map<String, String> personal_Change(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String userCode = getParameters(request, "USER_CODE");
		
		conditionMap.put("USER_CODE", userCode);
		conditionMap.put("PASSWORDOLD", getParameters(request, "PASSWORDOLD"));
		conditionMap.put("PASSWORDNEW", getParameters(request, "PASSWORDNEW"));
		conditionMap.put("PASSWORDCFM", getParameters(request, "PASSWORDCFM"));

		return conditionMap;
	}

}
