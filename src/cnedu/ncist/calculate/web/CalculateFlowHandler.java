package cnedu.ncist.calculate.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class CalculateFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map map) {
		String actionType = (String) map.get("ACTIONTYPE");
//		System.out.println("--"+actionType);
		if ("calculate_Score".equals(actionType)) {
			return calculate_Score(map);
		} 
		else {
			ActionTypeError();
		}

		return null;
	}

	private String calculate_Score(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("SCORELIST", map.get("SCORELIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
		return (String)map.get("RESULT");
	}



}
