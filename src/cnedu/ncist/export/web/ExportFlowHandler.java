package cnedu.ncist.export.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cnedu.ncist.util.ExcelExport;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;

public class ExportFlowHandler extends AbstractFlowHandler{

	public String doFlowProcess(Map paraMap) {
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("bd_new".equals(actionType)){
			return enterstu_select1(paraMap);
		}else if("lq".equals(actionType)){
			return enterstu_select2(paraMap);
		}else if("lq_20".equals(actionType)){
			return enterstu_select1_1(paraMap);
		}else if("lz_bd".equals(actionType)){
			return enterstu_select2_2(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private String enterstu_select1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		List familyList = (List) map.get("FAMILYLIST");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));

		String []title = new String []{"通知书号","学号","姓名","性别	","出生日期","民族","生源地","院系","专业","班级","身份证号","住宿信息","考生联系电话","学宿费缴费情况","教材费缴费情况","后勤缴费情况	","学院报到环节","学宿费"	,"卧具费","教材费","绿色通道(贷款)","领取军训服","领取宿舍钥匙","领取被服钥匙","未报到原因"};
		String[] key = new String[]{"TZSH","XH","SH","XB","CSRQ","MZ","SYD","XYMC","ZYMC","BJ","SFZH","ZSXX","LXDH","XSJF","JCJF","HQJF","RXSX","XYBD","XSF","WJF","JCF","LSTD","LQJXF","LQSSYS","LQBFYS","WBDYY"};
		try {
			ExcelExport.createEmploy("新生报到名单.xls", title, key, familyList, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)map.get("RESULT");
	}
	
	private String enterstu_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		List familyList = (List) map.get("FAMILYLIST");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));

		String []title = new String []{"学院名称","专业名称","录取人数","志愿1","志愿2","调剂人数","调剂率"};
		String[] key = new String[]{"XYMC","ZYMC","LQRS","ZY1_sum","ZY2_sum","TJ_sum","TJrate"};
		try {
			ExcelExport.createExpert("录取志愿率情况.xls", title, key, familyList, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)map.get("RESULT");
	}
	
	private String enterstu_select1_1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		List familyList = (List) map.get("FAMILYLIST");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));

		String []title = new String []{"序号","专业名称","录取人数","报到人数","报到率","志愿1","志愿2","调剂人数","调剂率"};
		String[] key = new String[]{"ID","ZYMC","LQRS","BDRS","BDrate","ZY1_sum","ZY2_sum","TJ_sum","TJrate"};
		try {
			ExcelExport.resAnanlyse("调剂率高于20%专业录取报到情况.xls", title, key, familyList, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String)map.get("RESULT");
	}
	private String enterstu_select2_2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		List familyList = (List) map.get("FAMILYLIST");
		
//		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));

		String []title = new String []{"专业名称","录取人数","报到人数","报到率","志愿1","志愿2","调剂人数","调剂率"};
		String[] key = new String[]{"ZYMC","LQZY_sum","BD_sum","BD_rate","BKZY1_sum","BKZY2_sum","TiaoJi_sum","TiaoJi_rate"};
		try {
			ExcelExport.createExercise("录取志愿率及报到情况.xls", title, key, familyList, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(map.get("FAMILYLIST")+"----------------");
		return (String)map.get("RESULT");
	}
}
