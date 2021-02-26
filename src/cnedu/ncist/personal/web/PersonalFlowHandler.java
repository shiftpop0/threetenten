package cnedu.ncist.personal.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class PersonalFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map map) {
		// TODO Auto-generated method stub

		String actionType = (String)map.get("ACTIONTYPE");
		
		if("personal_ChangeInit".equals(actionType)){
			return personal_changeInit(map);
		}
		else if("personal_Change".equals(actionType)){
			return personal_change(map);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	public String personal_changeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String personal_change(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

}
