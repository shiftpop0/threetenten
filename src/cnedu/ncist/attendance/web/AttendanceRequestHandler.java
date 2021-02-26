package cnedu.ncist.attendance.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class AttendanceRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub

		String actionType = (String)map.get("ACTIONTYPE");
		
		if("basictime_SelectInit".equals(actionType)){
			return basictime_selectInit(map);
		}
		else if("basictime_Set".equals(actionType)){
			return basictime_set(map);
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

	public Map<String, String> basictime_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}

	public Map<String, String> basictime_set(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map conditionMap = new HashMap();
		
		String startTime =(String)getParameters(request, "START_TIME");
		String endTime =(String)getParameters(request, "END_TIME");
		String basicTime =(String)getParameters(request, "BASIC_TIME");

		conditionMap.put("START_TIME", startTime);
		conditionMap.put("END_TIME", endTime);
		conditionMap.put("BASIC_TIME", basicTime);
		
		conditionMap.put("USER_CODES", getParameterValues(request, "MCODE"));
		
		return conditionMap;
	}

	public Map<String, String> basictime_DetailInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String createTime =(String)getParameters(request, "CREATE_TIME");
		String studentName =(String)getParameters(request, "STUDENT_NAME");
		String techerName =(String)getParameters(request, "TEACHER_NAME");

		conditionMap.put("CREATE_TIME", createTime);
		conditionMap.put("STUDENT_NAME", studentName);
		conditionMap.put("TEACHER_NAME", techerName);
		
		return conditionMap;
	}

	public Map<String, String> worktime_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		
		String startTime =(String)getParameters(request, "START_TIME");
		String endTime =(String)getParameters(request, "END_TIME");
		String timeStatus =(String)getParameters(request, "TIME_STATUS");

		conditionMap.put("START_TIME", startTime);
		conditionMap.put("END_TIME", endTime);
		conditionMap.put("TIME_STATUS", timeStatus);
		
		return conditionMap;
	}

	public Map<String, String> worktime_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);

		//获取输入时间自动顺序号
		String orderCode = getParameters(request, "TIME_CODE");
		conditionMap.put("TIME_CODE", orderCode);

		return conditionMap;
	}

	public Map<String, String> worktime_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		
		//获取输入时间自动顺序号
		String orderCode = getParameters(request, "TIME_CODE");
		conditionMap.put("TIME_CODE", orderCode);
		
		
		//获取页面查询参数
		String inputTime = getParameters(request, "INPUT_TIME");
		String morningBegin = getParameters(request, "MORNING_BEGIN");
		String morningEnd = getParameters(request, "MORNING_END");
		
		String noonBegin = getParameters(request, "NOON_BEGIN");
		String noonEnd = getParameters(request, "NOON_END");

		String nightBegin = getParameters(request, "NIGHT_BEGIN");
		String nightEnd = getParameters(request, "NIGHT_END");

		String timeMemo = getParameters(request, "TIME_MEMO");

		if(null == inputTime){
			inputTime = "";
		}
		if(null == morningBegin){
			morningBegin = "";
		}
		if(null == morningEnd){
			morningEnd = "";
		}
		if(null == noonBegin){
			noonBegin = "";
		}
		if(null == noonEnd){
			noonEnd = "";
		}
		if(null == nightBegin){
			nightBegin = "";
		}
		if(null == nightEnd){
			nightEnd = "";
		}
		if(null == timeMemo){
			timeMemo = "";
		}
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("INPUT_TIME", inputTime);
		conditionMap.put("MORNING_BEGIN", morningBegin);
		conditionMap.put("MORNING_END", morningEnd);
		conditionMap.put("NOON_BEGIN", noonBegin);
		conditionMap.put("NOON_END", noonEnd);
		conditionMap.put("NIGHT_BEGIN", nightBegin);
		conditionMap.put("NIGHT_END", nightEnd);
		conditionMap.put("TIME_MEMO", timeMemo);

		return conditionMap;
	}

	public Map<String, String> worktime_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String timeCode = getParameters(request, "TIME_CODE");
		conditionMap.put("TIME_CODE", timeCode);
		
		return conditionMap;
	}

	public Map<String, String> worktime_inspectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}

	public Map<String, String> worktime_inspect(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map conditionMap = new HashMap();
		
		//获取用户代码
		conditionMap.put("TIME_CODE", getParameterValues(request, "RCODE"));
		
		return conditionMap;
	}

	public Map<String, String> worktime_viewInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取学生姓名
		String studentName = getParameters(request, "STUDENT_NAME");
		if(null == studentName){
			studentName = "";
		}
		conditionMap.put("STUDENT_NAME", studentName);
		
		//获取输入日期
		String inputDate = getParameters(request, "INPUT_DATE");
		if(null == inputDate){
			inputDate = "";
		}
		if("".equals(inputDate)){
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = df.format(now);
			inputDate = dateStr.substring(0, 7);
		}
		conditionMap.put("INPUT_DATE", inputDate);
		
		return conditionMap;
	}

	public Map<String, String> worktime_sumInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取汇总年度
		String sumYear = getParameters(request, "SUM_YEAR");
		if((null == sumYear) ||("".equals(sumYear))){
			Date now = new Date();
			int year = now.getYear() + 1900;
			sumYear = String.valueOf(year);
		}
		conditionMap.put("SUM_YEAR", sumYear);
		
		return conditionMap;
	}

	public Map<String, String> worktime_sumDetailInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取汇总年度
		String sumYear = getParameters(request, "SUM_YEAR");
		if((null == sumYear) ||("".equals(sumYear))){
			Date now = new Date();
			int year = now.getYear() + 1900;
			sumYear = String.valueOf(year);
		}
		conditionMap.put("SUM_YEAR", sumYear);
		
		//获取学生编号
		String studentCode = getParameters(request, "STUDENT_CODE");
		if(null == studentCode){
			studentCode = "";
		}
		conditionMap.put("STUDENT_CODE", studentCode);
		
		return conditionMap;
	}

}
