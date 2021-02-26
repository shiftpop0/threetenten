package cnedu.ncist.export.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class ExportRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String) map.get("ACTIONTYPE");
		
		if("bd_new".equals(actionType)){
			return enterstu_Select1(map);
		}else if("lq".equals(actionType)){
			return enterstu_select2(map);
		}else if("lq_20".equals(actionType)){
			return enterstu_select1_1(map);
		}else if("lz_bd".equals(actionType)){
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
			
		return conditionMap;
	}
	
	private Map enterstu_select1_1(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}
	
	private Map enterstu_select2_2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, String> conditionMap = new HashMap<String, String>();
		//System.out.println(family+"------------s");
		return conditionMap;
	}
	
}
