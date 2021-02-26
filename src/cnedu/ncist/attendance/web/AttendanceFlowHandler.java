package cnedu.ncist.attendance.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class AttendanceFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map map) {
		// TODO Auto-generated method stub

		String actionType = (String)map.get("ACTIONTYPE");
		
		if("basictime_SelectInit".equals(actionType)){
			return basictime_selectInit(map);
		}
		else if("basictime_Set".equals(actionType)){
			return authuser_set(map);
		}
		else if("basictime_DetailInit".equals(actionType)){
			return basictime_DetailInit(map);
		}
		else if("worktime_SelectInit".equals(actionType)){
			return worktime_selectInit(map);
		}
		else if("worktime_AddInit".equals(actionType)){
			return worktime_addInit(map);
		}
		else if("worktime_Add".equals(actionType)){
			return worktime_add(map);
		}
		else if("worktime_Delete".equals(actionType)){
			return worktime_delete(map);
		}
		else if("worktime_InspectInit".equals(actionType)){
			return worktime_inspectInit(map);
		}
		else if("worktime_Inspect".equals(actionType)){
			return worktime_inspect(map);
		}
		else if("worktime_ViewInit".equals(actionType)){
			return worktime_viewInit(map);
		}
		else if("worktime_SumInit".equals(actionType)){
			return worktime_sumInit(map);
		}
		else if("worktime_SumDetailInit".equals(actionType)){
			return worktime_sumDetailInit(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public String basictime_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authuser_set(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");

		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String basictime_DetailInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String worktime_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String worktime_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String worktime_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String worktime_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));

		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String worktime_inspectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String worktime_inspect(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");

		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String worktime_viewInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String worktime_sumInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String worktime_sumDetailInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("TIMEINFO", map.get("TIMEINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

}
