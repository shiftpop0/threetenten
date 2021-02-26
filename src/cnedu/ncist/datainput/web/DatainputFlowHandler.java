package cnedu.ncist.datainput.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class DatainputFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map paraMap) {
		// TODO Auto-generated method stub
		String actionType = (String)paraMap.get("ACTIONTYPE");
		if("fileup".equals(actionType)){
			return fileup(paraMap);
		}else if("delete".equals(actionType)){
			return delete(paraMap);
		}else if("put".equals(actionType)){
			return put(paraMap);
		}else if ("calculate".equals(actionType)) {
			return calculate(paraMap);
		}else if ("cratetable".equals(actionType)) {
			return cratetable(paraMap);
		}else if ("Excle".equals(actionType)) {
			return excle(paraMap);
		}else{
			ActionTypeError();
		}
		return null;
	}

	private String excle(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		request.setAttribute("YEAR", map.get("YEAR"));
//		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		return (String)map.get("RESULT");
	}

	private String cratetable(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
		return (String)map.get("RESULT");
	}

	private String calculate(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
		return (String)map.get("RESULT");
	}

	private String put(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
		request.setAttribute("YEAR", map.get("YEAR"));
//		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		return (String)map.get("RESULT");
	}

	public String fileup(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
		request.setAttribute("YEARLIST", map.get("YEARLIST"));
//		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		return (String)map.get("RESULT");
	}
	public String delete(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
//		request.setAttribute("UPLOADINFO", map.get("UPLOADINFO"));
//		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		return (String)map.get("RESULT");
	}
}
