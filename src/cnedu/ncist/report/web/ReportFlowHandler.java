package cnedu.ncist.report.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class ReportFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map map) {
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

	public String report_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("REPTINFO", map.get("REPTINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

}
