package cnedu.ncist.lecture.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class LectureRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
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

	public Map<String, String> lecture_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String startTime =(String)getParameters(request, "START_TIME");
		String endTime =(String)getParameters(request, "END_TIME");
		String type =(String)getParameters(request, "LECT_TYPE");

		conditionMap.put("START_TIME", startTime);
		conditionMap.put("END_TIME", endTime);
		conditionMap.put("LECT_TYPE", type);
		
		return conditionMap;
	}

	public Map<String, String> lecture_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);

		//获取学术信息自动顺序号
		String orderCode = getParameters(request, "LECT_CODE");
		conditionMap.put("LECT_CODE", orderCode);
		
		return conditionMap;
	}

	public Map<String, String> lecture_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);
		
		//获取输入时间自动顺序号
		String theOrder = getParameters(request, "LECT_CODE");
		conditionMap.put("LECT_CODE", theOrder);
		
//System.out.println(flag + theOrder);

		//获取页面查询参数
		String inputTime = getParameters(request, "INPUT_TIME");
		String lectLocation = getParameters(request, "LECT_LOCATION");
		String lectTopic = getParameters(request, "LECT_TOPIC");
		String lectAttendees = getParameters(request, "LECT_ATTENDEES");
		String lectTyep = getParameters(request, "LECT_TYPE");
		String lectMemo = getParameters(request, "LECT_MEMO");

		if(null == inputTime){
			inputTime = "";
		}
		if(null == lectLocation){
			lectLocation = "";
		}
		if(null == lectTopic){
			lectTopic = "";
		}
		if(null == lectAttendees){
			lectAttendees = "";
		}
		if(null == lectMemo){
			lectMemo = "";
		}
		
		conditionMap.put("INPUT_TIME", inputTime);
		conditionMap.put("LECT_LOCATION", lectLocation);
		conditionMap.put("LECT_TOPIC", lectTopic);
		conditionMap.put("LECT_ATTENDEES", lectAttendees);
		conditionMap.put("LECT_TYPE", lectTyep);
		conditionMap.put("LECT_MEMO", lectMemo);

		return conditionMap;
	}

	public Map<String, String> lecture_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String theOrder =(String)getParameters(request, "LECT_CODE");

		conditionMap.put("LECT_CODE", theOrder);
		
		return conditionMap;
	}

	public Map<String, String> lecture_detail(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String theOrder =(String)getParameters(request, "LECT_CODE");

		conditionMap.put("LECT_CODE", theOrder);
		
		return conditionMap;
	}

	public Map<String, String> lecture_detailAdd(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String theOrder =(String)getParameters(request, "LECT_CODE");
		String lectMemo =(String)getParameters(request, "LECT_MEMO");

		conditionMap.put("LECT_CODE", theOrder);
		conditionMap.put("LECT_MEMO", lectMemo);
//System.out.println(theOrder + lectMemo);		
		return conditionMap;
	}

}
