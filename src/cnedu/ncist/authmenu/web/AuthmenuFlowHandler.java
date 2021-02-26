package cnedu.ncist.authmenu.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class AuthmenuFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map paraMap) {
		// TODO Auto-generated method stub

		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("authmenu_SelectInit".equals(actionType)){
			return authmenu_SelectInit(paraMap);
		}
		if("authmenu_Select".equals(actionType)){
			return authmenu_Select(paraMap);
		}
		if("authmenu_AddInit".equals(actionType)){
			return authmenu_AddInit(paraMap);
		}
		else if("authmenu_Add".equals(actionType)){
			return authmenu_add(paraMap);
		}
		else if("authmenu_StateChange".equals(actionType)){
			return authmenu_stateChange(paraMap);
		}
		else if("authmenu_CheckDup".equals(actionType)){
			return authmenu_checkDup(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	public String authmenu_SelectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("MENUINFO", map.get("MENUINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authmenu_Select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("MENUINFO", map.get("MENUINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authmenu_AddInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("MENUINFO", map.get("MENUINFO"));
		request.setAttribute("PARRENTINFO", map.get("PARRENTINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authmenu_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authmenu_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authmenu_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

}
