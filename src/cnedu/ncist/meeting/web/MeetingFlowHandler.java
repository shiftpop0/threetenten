package cnedu.ncist.meeting.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class MeetingFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map map) {
		// TODO Auto-generated method stub
		
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("meet_SelectInit".equals(actionType)){
			return meet_selectInit(map);
		}
		else if("meet_AddInit".equals(actionType)){
			return meet_addInit(map);
		}
		else if("meet_Add".equals(actionType)){
			return meet_add(map);
		}
		else if("meet_Delete".equals(actionType)){
			return meet_delete(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public String meet_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("MEETINFO", map.get("MEETINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String meet_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("MEETINFO", map.get("MEETINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String meet_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("MEETINFO", map.get("MEETINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String meet_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("MEETINFO", map.get("MEETINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

}
