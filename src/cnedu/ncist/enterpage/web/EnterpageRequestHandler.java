package cnedu.ncist.enterpage.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class EnterpageRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String) map.get("ACTIONTYPE");
		if("enterpage_Select1".equals(actionType)){
			return enterpage_select1(map);
		}else if("enterpage_Select2".equals(actionType)){
			return enterpage_select2(map);
		}else if("enterpage_Select3".equals(actionType)){
			return enterpage_select3(map);
		}else if("enterpage_Select4".equals(actionType)){
			return enterpage_select4(map);
		}else if("enterpage_Select1Family".equals(actionType)) {
			return enterpage_select1Family(map);
		}else if("enterpage_Select2Major".equals(actionType)) {
			return enterpage_select2Major(map);
		}else if("enterpage_Select3Family".equals(actionType)) {
			return enterpage_select3Family(map);
		}else if("enterpage_Select4Major".equals(actionType)) {
			return enterpage_select4Major(map);
		}else if ("enterpage_Select5".equals(actionType)) {
			return enterpage_select5(map);
		}else if ("enterpage_Select6".equals(actionType)) {
			return enterpage_select6(map);
		}else if ("enterpage_Select7".equals(actionType)) {
			return enterpage_select7(map);
		}else if ("enterpage_Select8".equals(actionType)) {
			return enterpage_select8(map);
		}else if ("enterpage_SelectAll".equals(actionType)) {
			return enterpage_selectAll(map);
		}else if ("enterpage_SelectAll1".equals(actionType)) {
			return enterpage_selectAll1(map);
		}else if ("enterpage_SelectEnroll".equals(actionType)) {
			return enterpage_selectEnroll(map);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private Map enterpage_select1(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	private Map enterpage_select2(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		return conditionMap;
	}
	private Map enterpage_select3(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	private Map enterpage_select4(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	
	private Map enterpage_select1Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String type = request.getParameter("type");
		String major = request.getParameter("major");
		
		System.out.println(type+"--------------");
		System.out.println(major+"--------------");
		conditionMap.put("TYPE", type);
		conditionMap.put("MAJOR", major);	

		
		return conditionMap;
	}
	
	private Map enterpage_select2Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String type = request.getParameter("type");
		String hot = request.getParameter("hot");
		
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("TYPE", type);	
		conditionMap.put("HOT", hot);	
		
		return conditionMap;
	}
	
	private Map enterpage_select3Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String type = request.getParameter("type");
		String hot = request.getParameter("hot");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("TYPE", type);	
		conditionMap.put("HOT", hot);	
		
		
		return conditionMap;
	}
	
	private Map enterpage_select4Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("major");
		String type = request.getParameter("type");
		String hot = request.getParameter("hot");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("TYPE", type);	
		conditionMap.put("HOT", hot);	
		return conditionMap;
	}
	
	private Map enterpage_select5(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	private Map enterpage_select6(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		return conditionMap;
	}
	private Map enterpage_select7(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	private Map enterpage_select8(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	private Map enterpage_selectAll(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String major = request.getParameter("MAJOR");
		String year = request.getParameter("YEAR");
		String family = request.getParameter("family");

		conditionMap.put("MAJOR", major);
		conditionMap.put("YEAR", year);
		conditionMap.put("FAMILY", family);
		
		System.out.println(major+"---------------------------");
		System.out.println(year+"---------------------------");
		System.out.println(family+"---------------------------");
		return conditionMap;
	}
	private Map enterpage_selectAll1(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String major = request.getParameter("MAJOR");
		String year = request.getParameter("YEAR");
		String type = request.getParameter("TYPE");
		
		conditionMap.put("MAJOR", major);
		conditionMap.put("YEAR", year);
		
		System.out.println(major+"---------------------------");
		System.out.println(year+"---------------------------");
		return conditionMap;
	}
	private Map enterpage_selectEnroll(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String name = request.getParameter("NAME");
		
		conditionMap.put("NAME", name);
		
		System.out.println(name+"-----------------------");

		return conditionMap;
	}
}
