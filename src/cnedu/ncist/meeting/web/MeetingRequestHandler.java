package cnedu.ncist.meeting.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class MeetingRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
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

	public Map<String, String> meet_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String startTime =(String)getParameters(request, "START_TIME");
		String endTime =(String)getParameters(request, "END_TIME");

		conditionMap.put("START_TIME", startTime);
		conditionMap.put("END_TIME", endTime);
		
		return conditionMap;
	}

	public Map<String, String> meet_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);

		//获取会议纪要自动顺序号
		String orderCode = getParameters(request, "MEET_CODE");
		conditionMap.put("MEET_CODE", orderCode);
		
//System.out.println(flag + orderCode);
		return conditionMap;
	}

	public Map<String, String> meet_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
/*		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);
		
		//获取输入时间自动顺序号
		String meetCode = getParameters(request, "MEET_CODE");
		conditionMap.put("MEET_CODE", meetCode);
		
		
		//获取页面查询参数
		String inputTime = getParameters(request, "INPUT_TIME");
		String meetLocation = getParameters(request, "MEET_LOCATION");
		String meetTopic = getParameters(request, "MEET_TOPIC");
		String meetAttendees = getParameters(request, "MEET_ATTENDEES");
		String meetMemo = getParameters(request, "MEET_MEMO");

		if(null == inputTime){
			inputTime = "";
		}
		if(null == meetLocation){
			meetLocation = "";
		}
		if(null == meetTopic){
			meetTopic = "";
		}
		if(null == meetAttendees){
			meetAttendees = "";
		}
		if(null == meetMemo){
			meetMemo = "";
		}
		
		conditionMap.put("INPUT_TIME", inputTime);
		conditionMap.put("MEET_LOCATION", meetLocation);
		conditionMap.put("MEET_TOPIC", meetTopic);
		conditionMap.put("MEET_ATTENDEES", meetAttendees);
		conditionMap.put("MEET_MEMO", meetMemo);
*/
		return conditionMap;
	}

	public Map<String, String> meet_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String theOrder =(String)getParameters(request, "MEET_CODE");

		conditionMap.put("MEET_CODE", theOrder);
		
		return conditionMap;
	}

}
