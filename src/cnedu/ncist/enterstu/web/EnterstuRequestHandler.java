package cnedu.ncist.enterstu.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class EnterstuRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String) map.get("ACTIONTYPE");
		
		if("enterstu_Select1".equals(actionType)){
			return enterstu_Select1(map);
		}else if("enterstu_Select2".equals(actionType)){
			return enterstu_select2(map);
		}else if("enterstu_Select1_1".equals(actionType)){
			return enterstu_select1_1(map);
		}else if("enterstu_Select2_2".equals(actionType)){
			return enterstu_select2_2(map);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private Map enterstu_Select1(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		return conditionMap;
	}

	private Map enterstu_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String family = request.getParameter("family");
		String choose = request.getParameter("choose");
		
		conditionMap.put("FAMILY", family);
		conditionMap.put("CHOOSE", choose);		
		return conditionMap;
	}
	
	private Map enterstu_select1_1(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String choose = request.getParameter("choose");

		conditionMap.put("MAJOR", major);
		conditionMap.put("CHOOSE", choose);
		
		return conditionMap;
	}
	
	private Map enterstu_select2_2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String family = request.getParameter("family");
		String choose = request.getParameter("choose");
		
		conditionMap.put("FAMILY", family);
		conditionMap.put("CHOOSE", choose);	
		//System.out.println(family+"------------s");
		return conditionMap;
	}
	
}
