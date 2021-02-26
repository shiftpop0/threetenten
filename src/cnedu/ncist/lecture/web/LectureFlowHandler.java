package cnedu.ncist.lecture.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class LectureFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map map) {
		// TODO Auto-generated method stub
		
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("lecture_SelectInit".equals(actionType)){
			return lecture_selectInit(map);
		}
		else if("lecture_AddInit".equals(actionType)){
			return lecture_addInit(map);
		}
		else if("lecture_Add".equals(actionType)){
			return lecture_add(map);
		}
		else if("lecture_Delete".equals(actionType)){
			return lecture_delete(map);
		}
		else if("lecture_Detail".equals(actionType)){
			return lecture_detail(map);
		}
		else if("lecture_DetailAdd".equals(actionType)){
			return lecture_detailAdd(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public String lecture_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("LECTINFO", map.get("LECTINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String lecture_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("LECTINFO", map.get("LECTINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String lecture_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String lecture_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String lecture_detail(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("LECTINFO", map.get("LECTINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String lecture_detailAdd(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

}
