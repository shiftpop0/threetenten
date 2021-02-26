package cnedu.ncist.analyse.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class AnalyseFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map map) {
		String actionType = (String)map.get("ACTIONTYPE");
		if("analyse_School".equals(actionType)){
			return analyse_School(map);
		}
		else if("analyse_School1".equals(actionType)){
			return analyse_School1(map);
		}else if("analyse_School2".equals(actionType)){
			return analyse_School2(map);
		}else if("analyse_School3".equals(actionType)){
			return analyse_School3(map);
		}else if("analyse_School4".equals(actionType)){
			return analyse_School4(map);
		}
		return null;
	}

	private String analyse_School4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("MAJORLIST1", map.get("MAJORLIST1"));
		request.setAttribute("TYPELIST", map.get("TYPELIST"));
		return (String)map.get("RESULT");
	}

	private String analyse_School3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("MAJORLIST1", map.get("MAJORLIST1"));
		request.setAttribute("MAJORLIST2", map.get("MAJORLIST2"));
		request.setAttribute("MAJORLIST3", map.get("MAJORLIST3"));
		request.setAttribute("MAJORLIST4", map.get("MAJORLIST4"));
		request.setAttribute("MAJORLIST5", map.get("MAJORLIST5"));
		request.setAttribute("MAJORLIST6", map.get("MAJORLIST6"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		return (String)map.get("RESULT");
	}

	private String analyse_School2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("SCHOOLLIST1", map.get("SCHOOLLIST1"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		return (String)map.get("RESULT");
	}

	private String analyse_School1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		return (String)map.get("RESULT");
	}

	private String analyse_School(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("MAJORLIST", map.get("MAJORLIST"));
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		return (String)map.get("RESULT");
	}

}
