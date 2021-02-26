package cnedu.ncist.authrole.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class AuthroleFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map paraMap) {
		// TODO Auto-generated method stub

		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("authrole_SelectInit".equals(actionType)){
			return authrole_SelectInit(paraMap);
		}
		if("authrole_Select".equals(actionType)){
			return authrole_Select(paraMap);
		}
		if("authrole_AddInit".equals(actionType)){
			return authrole_AddInit(paraMap);
		}
		else if("authrole_Add".equals(actionType)){
			return authrole_add(paraMap);
		}
		else if("authrole_StateChange".equals(actionType)){
			return authrole_stateChange(paraMap);
		}
		else if("authrole_AuthrizeInit".equals(actionType)){
			return authrole_authrizeInit(paraMap);
		}
		else if("authrole_Authrize".equals(actionType)){
			return authrole_authrize(paraMap);
		}
		else if("authrole_CheckDup".equals(actionType)){
			return authrole_checkDup(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;

	}

	public String authrole_SelectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("ROLEINFO", map.get("ROLEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authrole_Select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("ROLEINFO", map.get("ROLEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authrole_AddInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("ROLEINFO", map.get("ROLEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authrole_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authrole_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authrole_authrizeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("ROLEMENU", map.get("ROLEMENU"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authrole_authrize(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");

		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authrole_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("ROLEINFO", map.get("ROLEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

}
