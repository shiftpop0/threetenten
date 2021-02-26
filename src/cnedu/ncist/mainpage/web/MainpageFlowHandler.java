package cnedu.ncist.mainpage.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class MainpageFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String) map.get("ACTIONTYPE");

		if("mainpage_Select1".equals(actionType)){
			return mainpage_select1(map);
		}else if("mainpage_Select2".equals(actionType)){
			return mainpage_select2(map);
		}else if("mainpage_Select3".equals(actionType)){
			return mainpage_select3(map);
		}else if("mainpage_Select4".equals(actionType)){
			return mainpage_select4(map);
		}else if ("mainpage_Select1Family".equals(actionType)) {
			return mainpage_select1Family(map);
		}else if ("mainpage_Select2Major".equals(actionType)) {
			return mainpage_select2Major(map); 
		}else if ("mainpage_Select3Family".equals(actionType)) {
			return mainpage_select3Family(map);
		}else if ("mainpage_Select4Major".equals(actionType)) {
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
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private String mainpage_select1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJOR", map.get("MAJOR"));
		request.setAttribute("YEAR", map.get("YEAR"));

		return (String)map.get("RESULT");
	}
	
	private String mainpage_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		//request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select1Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("MAJOR", map.get("MAJOR"));
		request.setAttribute("YEAR", map.get("YEAR"));

		return (String)map.get("RESULT");
	}
	
	private String mainpage_select2Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("YEAR", map.get("YEAR"));
		request.setAttribute("MAJOR", map.get("MAJOR"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select3Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("YEAR", map.get("YEAR"));
		request.setAttribute("MAJOR", map.get("MAJOR"));
		request.setAttribute("MAJORTYPE", map.get("MAJORTYPE"));
		
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select4Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("YEAR", map.get("YEAR"));
		request.setAttribute("MAJOR", map.get("MAJOR"));

		return (String)map.get("RESULT");
	}
	private String mainpage_select5(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

//		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		return (String)map.get("RESULT");
	}
	private String mainpage_select5ByMajor(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select6(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select7(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_select8(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	
	private String mainpage_selectAll(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
	private String mainpage_selectAll1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		System.out.println(map.get("USERLIST"));
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		return (String)map.get("RESULT");
	}
}
