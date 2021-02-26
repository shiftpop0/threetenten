package cnedu.ncist.stuusers.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import cnedu.ncist.util.ExcelExport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class StuusersFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map paraMap) {
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("stuusers_MiddleSchool_Init".equals(actionType)){
			return stuusers_middleSchool_init(paraMap);
		}else if("stuusers_MiddleSchool_Province".equals(actionType)){
			return stuusers_MiddleSchool_Province(paraMap);
		}else if("stuusers_SelectInit".equals(actionType)){
			return stuusers_selectMajor(paraMap);
		}else if("stuusers_SelectByFamily".equals(actionType)) {
			return stuusers_selectByFamily(paraMap);
		}else if ("stuusers_MiddleSchool_Distribution".equals(actionType)) {
			return stuusers_MiddleSchool_Distribution(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}
	
	//中学信息初始
	private String stuusers_middleSchool_init(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");
		String flag = (String) map.get("FLAG");
		List schoolList = (List) map.get("SCHOOLLIST");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
		if(flag == null){
			flag = "all";
		}
		
		if(flag.equals("Export")){
			String []title = new String []{"省份","中学名称","录取人数","邮政编码"};
			String[] key = new String[]{"DQMC","ZXMC","stusum","YZBM"};
			try {
				ExcelExport.createDept("学生中学信息.xls", title, key, schoolList, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (String) map.get("RESULT");
		
	}
	//中学信息——省份查找
	private String stuusers_MiddleSchool_Province(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
		
		return (String) map.get("RESULT");
	}
		
	private String stuusers_selectFamily(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		return (String)map.get("RESULT");
	}
	
	private String stuusers_selectMajor(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
		
		return (String) map.get("RESULT");
	}
	private String stuusers_selectByFamily(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("USERLIST", map.get("USERLIST"));
		request.setAttribute("FAMILYLIST", map.get("FAMILYLIST"));
		//System.out.println(map.get("FAMILYLIST")+"----------------");
		return (String)map.get("RESULT");
	}
	private String stuusers_MiddleSchool_Distribution(Map paraMap) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");
		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		request.setAttribute("SCHOOLLIST", map.get("SCHOOLLIST"));
		request.setAttribute("PROVINCELIST", map.get("PROVINCELIST"));
		
		return (String) map.get("RESULT");
	}

}
