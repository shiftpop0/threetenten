package cnedu.ncist.authmenu.web;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class AuthmenuRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub

		String actionType = (String)map.get("ACTIONTYPE");
		
		if("authmenu_SelectInit".equals(actionType)){
			return authmenu_selectInit(map);
		}
		if("authmenu_Select".equals(actionType)){
			return authmenu_select(map);
		}
		if("authmenu_AddInit".equals(actionType)){
			return authmenu_addInit(map);
		}
		if("authmenu_Add".equals(actionType)){
			return authmenu_add(map);
		}
		else if("authmenu_StateChange".equals(actionType)){
			return authmenu_stateChange(map);
		}
		else if("authmenu_CheckDup".equals(actionType)){
			return authmenu_checkDup(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, String> authmenu_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}

	public Map<String, String> authmenu_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("MENU_CODE", getParameters(request, "MENU_CODE"));
		conditionMap.put("MENU_NAME", getParameters(request, "MENU_NAME"));
		conditionMap.put("USER_STATUS", getParameters(request, "USER_STATUS"));
		
		return conditionMap;
	}

	public Map<String, String> authmenu_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("FLAG", getParameters(request, "FLAG"));
		conditionMap.put("MENU_CODE", getParameters(request, "MENU_CODE"));
		
		return conditionMap;
	}

	public Map<String, String> authmenu_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		
		//获取页面查询参数
		String menuCode = getParameters(request, "MENU_CODE");
		String menuName = getParameters(request, "MENU_NAME");
		String linkPage = getParameters(request, "LINK_PAGE");
		String menuParrent = getParameters(request, "MENU_PARRENT");
		String menuOrder = getParameters(request, "MENU_ORDER");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("MENU_CODE", menuCode);
		conditionMap.put("MENU_NAME", menuName);
		conditionMap.put("LINK_PAGE", linkPage);
		conditionMap.put("MENU_PARRENT", menuParrent);
		conditionMap.put("MENU_ORDER", menuOrder);

		return conditionMap;
	}

	public Map<String, String> authmenu_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String menuCode = getParameters(request, "MENU_CODE");
		conditionMap.put("MENU_CODE", menuCode);
		
		return conditionMap;
	}

	public Map<String, String> authmenu_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String menuCode = getParameters(request, "MENU_CODE");
		conditionMap.put("MENU_CODE", menuCode);

		return conditionMap;
	}

}
