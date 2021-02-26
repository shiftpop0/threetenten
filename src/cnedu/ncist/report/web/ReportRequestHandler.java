package cnedu.ncist.report.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;

public class ReportRequestHandler extends AbstractRequestHandler {

	public Map doRequestProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("report_SelectInit".equals(actionType)){
			return report_selectInit(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, String> report_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String inputYear =(String)getParameters(request, "INPUT_YEAR");
System.out.println("=" + inputYear + "-");		
		if((null == inputYear) || ("".equals(inputYear))){
			Date tempDate = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
			Calendar cal = Calendar.getInstance();  
			cal.setTime(tempDate);  
			inputYear =String.valueOf(cal.get(Calendar.YEAR));  
System.out.println("==" + inputYear);		
		}

		conditionMap.put("INPUT_YEAR", inputYear);
		
		return conditionMap;
	}

}
