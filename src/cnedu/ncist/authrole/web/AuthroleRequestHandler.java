package cnedu.ncist.authrole.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class AuthroleRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub

		String actionType = (String)map.get("ACTIONTYPE");
		
		if("authrole_SelectInit".equals(actionType)){
			return authrole_selectInit(map);
		}
		if("authrole_Select".equals(actionType)){
			return authrole_select(map);
		}
		if("authrole_AddInit".equals(actionType)){
			return authrole_addInit(map);
		}
		if("authrole_Add".equals(actionType)){
			return authrole_add(map);
		}
		if("authrole_StateChange".equals(actionType)){
			return authrole_stateChange(map);
		}
		else if("authrole_AuthrizeInit".equals(actionType)){
			return authrole_authrizeInit(map);
		}
		else if("authrole_Authrize".equals(actionType)){
			return authrole_authrize(map);
		}
		else if("authrole_CheckDup".equals(actionType)){
			return authrole_checkDup(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, String> authrole_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}

	public Map<String, String> authrole_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("ROLE_CODE", getParameters(request, "ROLE_CODE"));
		conditionMap.put("ROLE_NAME", getParameters(request, "ROLE_NAME"));
		conditionMap.put("USER_STATUS", getParameters(request, "USER_STATUS"));
		
		return conditionMap;
	}

	public Map<String, String> authrole_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("FLAG", getParameters(request, "FLAG"));
		conditionMap.put("ROLE_CODE", getParameters(request, "ROLE_CODE"));
		
		return conditionMap;
	}

	public Map<String, String> authrole_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		
		//获取页面查询参数
		String roleCode = getParameters(request, "ROLE_CODE");
		String roleName = getParameters(request, "ROLE_NAME");
		
		String roleMemo = getParameters(request, "ROLE_MEMO");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("ROLE_CODE", roleCode);
		conditionMap.put("ROLE_NAME", roleName);
		conditionMap.put("ROLE_MEMO", roleMemo);

		return conditionMap;
	}

	public Map<String, String> authrole_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String userCode = getParameters(request, "ROLE_CODE");
		conditionMap.put("ROLE_CODE", userCode);
		
		return conditionMap;
	}

	public Map<String, String> authrole_authrizeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String roleCode = getParameters(request, "ROLE_CODE");
		conditionMap.put("ROLE_CODE", roleCode);
		
		return conditionMap;
	}

	public Map<String, String> authrole_authrize(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map conditionMap = new HashMap();
		
		//获取用户代码
		String roleCode = getParameters(request, "ROLE_CODE");
		conditionMap.put("ROLE_CODE", roleCode);

		conditionMap.put("MENU_CODE", getParameterValues(request, "MCODE"));
		
		return conditionMap;
	}

	public Map<String, String> authrole_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String roleCode = getParameters(request, "ROLE_CODE");
		conditionMap.put("ROLE_CODE", roleCode);

		return conditionMap;
	}

}
