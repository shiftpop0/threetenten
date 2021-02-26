package cnedu.ncist.mainpage.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class MainpageRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String) map.get("ACTIONTYPE");
		if ("mainpage_Select1".equals(actionType)) {
			return mainpage_select1(map);
		} else if ("mainpage_Select2".equals(actionType)) {
			return mainpage_select2(map);
		} else if ("mainpage_Select3".equals(actionType)) {
			return mainpage_select3(map);
		} else if ("mainpage_Select4".equals(actionType)) {
			return mainpage_select4(map);
		} else if ("mainpage_Select1Family".equals(actionType)) {
			return mainpage_select1Family(map);
		} else if ("mainpage_Select2Major".equals(actionType)) {
			return mainpage_select2Major(map);
		} else if ("mainpage_Select3Family".equals(actionType)) {
			return mainpage_select3Family(map);
		} else if ("mainpage_Select4Major".equals(actionType)) {
			return mainpage_select4Major(map);
		} else if ("mainpage_Select5".equals(actionType)) {
			return mainpage_select5(map);
		} else if ("mainpage_Select5ByMajor".equals(actionType)) {
			return mainpage_select5ByMajor(map);
		} else if ("mainpage_Select6".equals(actionType)) {
			return mainpage_select6(map);
		} else if ("mainpage_Select7".equals(actionType)) {
			return mainpage_select7(map);
		} else if ("mainpage_Select8".equals(actionType)) {
			return mainpage_select8(map);
		} else if ("mainpage_SelectAll".equals(actionType)) {
			return mainpage_selectAll(map);
		} else if ("mainpage_SelectAll1".equals(actionType)) {
			return mainpage_selectAll1(map);
		} else {
			ActionTypeError();
		}

		return null;
	}

	private Map mainpage_select1(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		String major = request.getParameter("MAJOR");
		String year = request.getParameter("YEAR");
		String majorType = request.getParameter("MAJORTYPE");

		conditionMap.put("MAJOR", major);
		conditionMap.put("YEAR", year);
		conditionMap.put("MAJORTYPE", majorType);

		System.out.println(major + "------------------------");
		System.out.println(year + "------------------------");
		System.out.println(majorType + "------------------------");
		return conditionMap;
	}

	private Map mainpage_select2(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String year = request.getParameter("year");
		String family = request.getParameter("family");
		String major = request.getParameter("major");
		String majortype = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", majortype);
		conditionMap.put("FLAG", flag);
		conditionMap.put("YEAR", year);
		conditionMap.put("FAMILY", family);
		return conditionMap;
	}

	private Map mainpage_select3(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String year = request.getParameter("year");
		String family = request.getParameter("family");
		String major = request.getParameter("major");
		String majortype = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", majortype);
		conditionMap.put("FLAG", flag);
		conditionMap.put("YEAR", year);
		conditionMap.put("FAMILY", family);

		return conditionMap;
	}

	private Map mainpage_select4(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String year = request.getParameter("year");
		String family = request.getParameter("family");
		String major = request.getParameter("major");
		String majortype = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", majortype);
		conditionMap.put("FLAG", flag);
		conditionMap.put("YEAR", year);
		conditionMap.put("FAMILY", family);
		return conditionMap;
	}

	private Map mainpage_select1Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String family = request.getParameter("family");
		String major = request.getParameter("MAJOR");
		String year = request.getParameter("YEAR");

		conditionMap.put("MAJOR", major);
		conditionMap.put("YEAR", year);
		conditionMap.put("FAMILY", family);

		System.out.println(major + "------------------------");
		System.out.println(year + "------------------------");
		System.out.println(family + "------------------------");

		return conditionMap;
	}

	private Map mainpage_select2Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String family = request.getParameter("family");
		String major = request.getParameter("MAJOR");
		String year = request.getParameter("YEAR");
		System.out.println("---"+major);
		conditionMap.put("FAMILY", family);
		conditionMap.put("MAJOR", major);
		conditionMap.put("YEAR", year);

		return conditionMap;
	}

	private Map mainpage_select3Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String family = request.getParameter("family");
		String major = request.getParameter("MAJOR");
		String type = request.getParameter("MAJORTYPE");
		String year = request.getParameter("YEAR");

		conditionMap.put("FAMILY", family);
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", type);
		conditionMap.put("YEAR", year);
		
		System.out.println(major + "------------------------");
		System.out.println(year + "------------------------");
		System.out.println(family + "------------------------");
		System.out.println(type + "------------------------");

		return conditionMap;
	}

	private Map mainpage_select4Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String family = request.getParameter("family");
		String major = request.getParameter("MAJOR");
		String year = request.getParameter("YEAR");

		conditionMap.put("FAMILY", family);
		conditionMap.put("MAJOR", major);
		conditionMap.put("YEAR", year);

		return conditionMap;
	}

	private Map mainpage_select5(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		return conditionMap;
	}

	private Map mainpage_select5ByMajor(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String type = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		String year = request.getParameter("year");

		conditionMap.put("MAJOR", major);
		conditionMap.put("FLAG", flag);
		conditionMap.put("TYPELIST", type);
		conditionMap.put("YEARLIST", year);
//		System.out.println("major---" + major);
//		System.out.println("MAJORTYPE---" + type);
//		System.out.println("YEAR---" + year);

		return conditionMap;
	}	

	private Map mainpage_select6(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String majortype = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", majortype);
		conditionMap.put("FLAG", flag);
		return conditionMap;
	}

	private Map mainpage_select7(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String majortype = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", majortype);
		conditionMap.put("FLAG", flag);
		
		System.out.println("major---" + major);
		System.out.println("MAJORTYPE---" + majortype);
		System.out.println("YEAR---" + flag);
		return conditionMap;
	}

	private Map mainpage_select8(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String majortype = request.getParameter("type");
		String flag = request.getParameter("FLAG");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("MAJORTYPE", majortype);
		conditionMap.put("FLAG", flag);
		return conditionMap;
	}

	private Map mainpage_selectAll(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		String major = request.getParameter("ZYDH");
		String family = request.getParameter("FAMILY");
		String year = request.getParameter("YEAR");
		String flag = request.getParameter("Flag");

		conditionMap.put("ZYDH", major);
		conditionMap.put("FAMILY", family);
		conditionMap.put("YEAR", year);
		conditionMap.put("Flag", flag);

		System.out.println(major + "-----------------------");
		System.out.println(family + "---------------------------");
		System.out.println(year + "---------------------------");
		return conditionMap;
	}

	private Map mainpage_selectAll1(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		String flag = request.getParameter("FLAG");
		String major = request.getParameter("FNAME");
		String flag1 = request.getParameter("FLAG1");
		String year = request.getParameter("YEAR");

		conditionMap.put("FLAG", flag);
		conditionMap.put("MAJOR", major);
		conditionMap.put("FLAG1", flag1);
		conditionMap.put("YEAR", year);

		System.out.println(flag + "-----------------------");
		System.out.println(major + "---------------------------");
		System.out.println(flag1 + "---------------------------");
		System.out.println(year + "---------------------------");
		return conditionMap;
	}
}
