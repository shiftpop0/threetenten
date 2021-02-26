package cnedu.ncist.analyse.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class AnalyseRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map) {
		String actionType = (String)map.get("ACTIONTYPE");
		if("analyse_School".equals(actionType)){
			return analyse_School(map);
		}
		else if("analyse_School1".equals(actionType)){
			return analyse_School1(map);
		}
		else if("analyse_School2".equals(actionType)){
			return analyse_School2(map);
		}
		else if("analyse_School3".equals(actionType)){
			return analyse_School3(map);
		}
		else if("analyse_School4".equals(actionType)){
			return analyse_School4(map);
		}
		return null;
	}
	private Map analyse_School4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String flag = request.getParameter("FLAG");
		String major = request.getParameter("major");
		System.out.println(flag);
		conditionMap.put("FLAG", flag);
		conditionMap.put("MAJOR", major);
		return conditionMap;
	}


	private Map analyse_School3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String flag = request.getParameter("FLAG");
		String year = request.getParameter("year");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("YEAR", year);
		return conditionMap;
	}

	private Map analyse_School2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		String flag = request.getParameter("FLAG");
		String family = request.getParameter("type");
		String family1 = request.getParameter("type1");
		String year = request.getParameter("year");
		
		System.out.println(family);
		System.out.println(family1);
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("FAMILY", family);
		conditionMap.put("FAMILY1", family1);
		conditionMap.put("YEAR", year);
		return conditionMap;
	}

	private Map analyse_School1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String flag = request.getParameter("FLAG");
		String year = request.getParameter("year");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("YEAR", year);
		return conditionMap;
	}

	private Map analyse_School(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String flag = request.getParameter("FLAG");
		String year = request.getParameter("year");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("YEAR", year);
		return conditionMap;
	}

}
