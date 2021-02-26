package cnedu.ncist.calculate.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class CalculateRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map) {
		String actionType = (String) map.get("ACTIONTYPE");
		System.out.println("--"+actionType);

		if ("calculate_Score".equals(actionType)) {
			return calculate_Score(map);
		} 
		else {
			ActionTypeError();
		}

		return null;
	}

	private Map calculate_Score(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		String flag = request.getParameter("FLAG");
		String major = request.getParameter("major");
		String type = request.getParameter("type");
		String province = request.getParameter("province");
		
//		System.out.println("province=="+province);
//		System.out.println("major=="+major);
//		System.out.println("type=="+type);
//		System.out.println(flag+"----------------------------");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("MAJOR", major);
		conditionMap.put("TYPE", type);
		conditionMap.put("PROVINCE", province);
		return conditionMap;
	}

}
