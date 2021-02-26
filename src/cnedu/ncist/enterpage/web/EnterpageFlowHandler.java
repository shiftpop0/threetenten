package cnedu.ncist.enterpage.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class EnterpageFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map map) {
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
		}else if ("enterpage_Select1Family".equals(actionType)) {
			return enterpage_select1Family(map);
		}else if ("enterpage_Select2Major".equals(actionType)) {
			return enterpage_select2Major(map); 
		}else if ("enterpage_Select3Family".equals(actionType)) {
			return enterpage_select3Family(map);
		}else if ("enterpage_Select4Major".equals(actionType)) {
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

	private String enterpage_select1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("HOTLIST", map.get("HOTLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("HOTLIST", map.get("HOTLIST"));
		
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("HOTLIST", map.get("HOTLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select1Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select2Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("HOTLIST", map.get("HOTLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select3Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("HOTLIST", map.get("HOTLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select4Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("HOTLIST", map.get("HOTLIST"));
		return (String)map.get("RESULT");
	}
	private String enterpage_select5(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select6(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select7(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String enterpage_select8(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	private String enterpage_selectAll(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("MAJOR", map.get("MAJOR"));
		request.setAttribute("YEAR", map.get("YEAR"));
		return (String)map.get("RESULT");
	}
	private String enterpage_selectAll1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		//request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		return (String)map.get("RESULT");
	}
	private String enterpage_selectEnroll(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		//request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		return (String)map.get("RESULT");
	}

}
