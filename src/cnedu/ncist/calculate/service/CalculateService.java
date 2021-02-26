package cnedu.ncist.calculate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.IStabilityClassifier;

import cnedu.ncist.util.ChangeList;
import cnedu.ncist.util.GetExpect;
import cnedu.ncist.util.JsonPsrseUtil;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;
import net.sf.json.JSONArray;

public class CalculateService extends AbstractService {
	public Map doServiceProcess(Map paraMap) {

		String actionType = (String) paraMap.get("ACTIONTYPE");
		System.out.println("--"+actionType);

		if ("calculate_Score".equals(actionType)) {
			return calculate_Score(paraMap);
		} 
		else {
			ActionTypeError();
		}

		return null;
	}
	
	public Map calculate_Score(Map paraMap){
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> List = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		List<Map<String, String>> yearList = null;
		List<Map<String, String>> provinceList = null;
		List<Map<String, String>> changeListMap = null;
		Map<String, String> changeMap = null;
		
		String major = (String) map.get("MAJOR");
		String flag = (String) map.get("FLAG");
		String type = (String) map.get("TYPE");
		String province = (String) map.get("PROVINCE");
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String sql1 = "SELECT ZYDH,ZYMC,KLDM,KLMC FROM sheet2 WHERE sheet2.ZYDH !=13 AND sheet2.ZYDH !=14 AND sheet2.ZYDH !=55 GROUP BY ZYDH";
		String sql2 = "SELECT KLMC,KLDM FROM sheet2 WHERE sheet2.ZYDH !=13 AND sheet2.ZYDH !=14 AND sheet2.ZYDH !=55 GROUP BY KLMC";
		
		String sql3 = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ";
		String sql4 = "SELECT * FROM province";
		String sql = "SELECT enter_student.LQZY,enter_student.DQDM,province.DQMC,ROUND(AVG(enter_student.CJ)) AS avg_cj, "
		 		+ "major.ZYMC,YEAR1 FROM enter_student,major,province WHERE major.ZYDH = enter_student.LQZY "
		 		+ "AND province.DQDM = enter_student.DQDM "
		 		+ "AND KLDM = '0' "
		 		+ "AND ZYMC = '会计学' "
		 		+ "AND enter_student.DQDM = 110000 "
		 		+ "GROUP BY DQDM, LQZY, YEAR1";
		if(flag == null){
			flag = "all";
		}
		if("select".equals(flag)){
			sql = "SELECT enter_student.LQZY,enter_student.DQDM,province.DQMC,ROUND(AVG(enter_student.CJ)) AS avg_cj, "
			 		+ "major.ZYMC,YEAR1 FROM enter_student,major,province WHERE major.ZYDH = enter_student.LQZY "
			 		+ "AND province.DQDM = enter_student.DQDM "
			 		+ "AND KLDM = '"+type+"' "
			 		+ "AND ZYMC = '"+major+"' "
			 		+ "AND enter_student.DQDM = '"+province+"' "
			 		+ "GROUP BY DQDM, LQZY, YEAR1";
		}
		System.out.println("sql-----="+sql);
		try {
			majorList = dba.getMultiRecord(sql1);
			typeList = dba.getMultiRecord(sql2);
			yearList = dba.getMultiRecord(sql3);
			provinceList = dba.getMultiRecord(sql4);
//			if(flag == null){
//				flag = "all";
//			}
//			GetExpect ge = new GetExpect();
//			//处理拼接字符串，改sql语句
//			String sql = ge.appendString(yearList);
//			if(flag.equals("select")){
//				 sql = ge.appendStringWithMajor(yearList, major, type);
//			}
//			System.out.println(sql);
			List = dba.getMultiRecord(sql);
//			List不为空才能执行
			if (List.size()!=0) {
				ChangeList change = new ChangeList();
				List = change.change(List,yearList);
			}
			System.out.println("List="+List);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == List) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		GetExpect gExpect = new GetExpect();

		for (int i = 0; i < List.size();i++) {
			gExpect.getExpect(List.get(i),yearList, 1, 0.7);
		}
//		System.out.println("changeMap1==="+changeMap);
//		gExpect.getExpect(changeMap,yearList, 1, 0.6);
//		System.out.println("changeMap2==="+changeMap);
		JSONArray jsonList = JsonPsrseUtil.toJsonArray(List);
		JSONArray jsonList1 = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonList2 = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("SCORELIST", jsonList);
		resultMap.put("MAJORLIST",jsonList1);
		resultMap.put("TYPELIST",jsonList2);
		resultMap.put("PROVINCELIST",provinceList);
		System.out.println("------"+jsonList);
		return resultMap;
	}
	
}
