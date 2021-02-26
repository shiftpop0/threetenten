package cnedu.ncist.enterstu.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class EnterstuFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map paraMap) {
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("enterstu_Select1".equals(actionType)){
			return enterstu_select1(paraMap);
		}else if("enterstu_Select2".equals(actionType)){
			return enterstu_select2(paraMap);
		}else if("enterstu_Select1_1".equals(actionType)){
			return enterstu_select1_1(paraMap);
		}else if("enterstu_Select2_2".equals(actionType)){
			return enterstu_select2_2(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private String enterstu_select1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("MAJORLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterstu_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterstu_select1_1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		return (String)map.get("RESULT");
	}
	private String enterstu_select2_2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		//System.out.println(map.get("FAMILYLIST")+"----------------");
		return (String)map.get("RESULT");
	}
}
