package cnedu.ncist.stuusers.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class StuusersRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String) map.get("ACTIONTYPE");
		if("stuusers_MiddleSchool_Init".equals(actionType)){
			return stuusers_middleSchool_init(map);
		}else if("stuusers_MiddleSchool_Province".equals(actionType)){
			return stuusers_MiddleSchool_Province(map);
		}else if("stuusers_SelectInit".equals(actionType)){
			return stuusers_selectMajor(map);
		}else if("stuusers_SelectByFamily".equals(actionType)) {
			return stuusers_selectByFamily(map);
		}else if ("stuusers_MiddleSchool_Distribution".equals(actionType)) {
			return stuusers_MiddleSchool_Distribution(map);
		}
		else{
			ActionTypeError();
		}
		return null;
	}
	//中学信息初始
	private Map stuusers_middleSchool_init(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		String flag = request.getParameter("FLAG");
		String family = request.getParameter("family");
		conditionMap.put("FLAG", flag);
		conditionMap.put("FAMILY", family);
		return conditionMap;
	}
	//中学信息--省份查找
	private Map stuusers_MiddleSchool_Province(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String province = request.getParameter("PROVINCE");
//		System.out.println(province+"------------");
		
		conditionMap.put("PROVINCE", province);		
		return conditionMap;
	}
	

	private Map stuusers_selectFamily(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String family = request.getParameter("family");
		String choose = request.getParameter("choose");
		
		conditionMap.put("FAMILY", family);
		conditionMap.put("CHOOSE", choose);		
		return conditionMap;
	}
	
	private Map stuusers_selectMajor(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		return conditionMap;
	}
	
	private Map stuusers_selectByFamily(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		String family = request.getParameter("family");
		String choose = request.getParameter("choose");
		
		conditionMap.put("FAMILY", family);
		conditionMap.put("CHOOSE", choose);	
		//System.out.println(family+"------------s");
		return conditionMap;
	}
	private Map stuusers_MiddleSchool_Distribution(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();

		return conditionMap;
	}
}
