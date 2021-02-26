package cnedu.ncist.mainpage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cnedu.ncist.util.GetExpect;
import cnedu.ncist.util.JsonPsrseUtil;
import cnedu.ncist.util.MysqlDemo;
import com.sun.javaws.IconUtil;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;
import net.sf.json.JSONArray;

public class MainpageService extends AbstractService {
	public Map doServiceProcess(Map paraMap) {

		String actionType = (String) paraMap.get("ACTIONTYPE");
		
		if ("mainpage_Select1".equals(actionType)) {
			return mainpage_select1(paraMap);
		} else if ("mainpage_Select2".equals(actionType)) {
			return mainpage_select2(paraMap);
		} else if ("mainpage_Select3".equals(actionType)) {
			return mainpage_select3(paraMap);
		} else if ("mainpage_Select4".equals(actionType)) {
			return mainpage_select4(paraMap);
		} else if ("mainpage_Select1Family".equals(actionType)) {
			return mainpage_select1Family(paraMap);
		} else if ("mainpage_Select2Major".equals(actionType)) {
			return mainpage_select2Major(paraMap);
		}else if ("mainpage_Select3Family".equals(actionType)) {
			return mainpage_select3Family(paraMap);
		} else if ("mainpage_Select4Major".equals(actionType)) {
			return mainpage_select4Major(paraMap);
		}  else if ("mainpage_Select5".equals(actionType)) {
//			最热专业方法
			return mainpage_select5(paraMap);
		} else if ("mainpage_Select5ByMajor".equals(actionType)) {
			return mainpage_select5ByMajor(paraMap);
		}else if ("mainpage_Select6".equals(actionType)) {
			return mainpage_select6(paraMap);
		} else if ("mainpage_Select7".equals(actionType)) {
			return mainpage_select7(paraMap);
		} else if ("mainpage_Select8".equals(actionType)) {
			return mainpage_select8(paraMap);
		} else if ("mainpage_SelectAll".equals(actionType)) {
			return mainpage_selectAll(paraMap);
		} else if ("mainpage_SelectAll1".equals(actionType)) {
			return mainpage_selectAll1(paraMap);
		}
		else {
			ActionTypeError();
		}

		return null;

	}

	public Map mainpage_select1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");
		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;


		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String major = (String) map.get("MAJOR");
		String year = (String) map.get("YEAR");

		String sql1 = "SELECT enter_student.DQDM,DQMC,ZYDH,KLMC,majortype.KLDM,ZYMC, "
				+ "COUNT(KSH)as FAMILYSUM,round(AVG(CJ), 2) AS CJ,YEAR1 FROM "
				+ "enter_student,province,major,majortype "
				+ "WHERE enter_student.DQDM = province.DQDM AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "GROUP BY DQDM,YEAR1 ";
		String sql2 = "SELECT enter_student.DQDM,DQMC,ZYDH,KLMC,majortype.KLDM,ZYMC, "
				+ "COUNT(KSH)as FAMILYSUM,round(AVG(CJ), 2) AS CJ,YEAR1 FROM "
				+ "enter_student,province,major,majortype "
				+ "WHERE enter_student.DQDM = province.DQDM AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH GROUP BY enter_student.DQDM ";
		System.out.println("mainpage_select1sql1="+sql1);
		System.out.println("mainpage_select1sql2="+sql2);
		try {
			userList = dba.getMultiRecord(sql1);
			familyList = dba.getMultiRecord(sql2);

			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJOR", major);
		resultMap.put("YEAR", year);

		return resultMap;
	}

	public Map mainpage_select2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		//List<Map<String, String>> familyList = null;
		List<Map<String, String>> yearList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE"); 
		String flag = (String) map.get("FLAG");
		//String family = (String) map.get("FAMILY");
		String year = (String) map.get("YEAR");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//		String sql1 = "SELECT a.KLMC,a.ZYMC,a.LQZY,a.sum,CONCAT(ROUND(a.sum/a.allsum*100,3),'','%') AS Luqu_Rate,a.YEAR1 "
//				+ "FROM(SELECT KLMC,ZYMC,LQZY,major.KLDM,Count(LQZY) AS sum,all_sum.allsum,YEAR1 FROM major,majortype,enter_student,(SELECT COUNT(*) AS allsum FROM enter_student WHERE  "
//				+ "enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = major.ZYDH "
//				+ "AND major.KLDM = majortype.KLDM  AND enter_student.LQFS != 13 AND enter_student.LQFS != 12 "
//				+ "AND enter_student.LQFS != 23 GROUP BY ZYDM,major.KLDM,KLMC,ZYMC,YEAR1) AS a WHERE a.LQZY=a.LQZY ";
		String sql1 = "SELECT *,COALESCE(CONCAT(ROUND(tb_rate.BKZY_allsum / tb_rate.LQZY_sum * 100),'','%'),0) AS Luqu_Rate FROM tb_rate WHERE tb_rate.ZYDH = tb_rate.ZYDH";
		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		String sql4 = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ";
		
		if(flag==null){
			flag = "a";
		}
		if (major == null) {
			major = "all";
		}
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			if("all".equals(year)){
				year = null;
			}

			
			if(major != null){
				sql1 = sql1 + " and tb_rate.ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and tb_rate.KLDM = '"+type+"'";
			}
			if(year != null){
				sql1 = sql1 + " and tb_rate.YEAR1 = '"+year+"'";
			}
		}
		
//		sql1 = sql1 + " Group by ZYDM,KLMC,ZYMC,YEAR1 Order by vorate desc ";
		System.out.println("mainpage_select2_sql1="+sql1);
		System.out.println("mainpage_select2_sql2="+sql2);
		System.out.println("mainpage_select2_sql3="+sql3);
		try {
			majorList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			userList = dba.getMultiRecord(sql1);
			//familyList = dba.getMultiRecord(sql4);
			yearList = dba.getMultiRecord(sql4);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		resultMap.put("YEARLIST", yearList);
		
		System.out.println("-----++++++---"+jsonMajor);
		System.out.println("-----++++++---"+jsonMajorType);
		
		return resultMap;
	}

	public Map mainpage_select3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		//List<Map<String, String>> familyList = null;
		List<Map<String, String>> yearList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE"); 
		String flag = (String) map.get("FLAG");
		//String family = (String) map.get("FAMILY");
		String year = (String) map.get("YEAR");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//		String sql1 = "SELECT a.ZYDH,a.ZYMC,a.KLDM,a.KLMC,a.YEAR1,a.TiaoJisum,CONCAT(ROUND(a.TiaoJisum/a.allsum*100,3),'','%') AS TiaoJi_Rate "
//				+ "FROM (SELECT major.ZYDH,major.ZYMC,majortype.KLDM,majortype.KLMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS TiaoJisum,all_sum.allsum "
//				+ "FROM enter_student,major,(SELECT COUNT(*) AS allsum FROM enter_student WHERE  enter_student.LQFS != 12 "
//				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum,majortype "
//				+ "WHERE enter_student.LQZY = major.ZYDH AND LQZY != ZYDH1 AND LQZY != ZYDH2 "
//				+ "AND LQZY != ZYDH3 AND LQZY != ZYDH4 AND LQZY != ZYDH5 AND LQZY != ZYDH6 AND enter_student.LQFS != 12 "
//				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 AND major.KLDM = majortype.KLDM "
//				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS a WHERE a.ZYDH = a.ZYDH  ";
		
		String sql1 ="SELECT * FROM(SELECT Tiaoji_sum.ZYDH,Tiaoji_sum.ZYMC,Tiaoji_sum.KLDM,Tiaoji_sum.KLMC, "
				+ "Tiaoji_sum.YEAR1,Tiaoji_sum.TiaoJisum,LQ_sum.LQsum,CONCAT(ROUND(Tiaoji_sum.TiaoJisum/LQ_sum.LQsum*100,2),'','%') AS TiaoJi_Rate "
				+ "FROM(SELECT major.ZYDH,major.ZYMC,majortype.KLDM,majortype.KLMC,enter_student.YEAR1, "
				+ "COUNT(enter_student.LQZY) AS TiaoJisum FROM enter_student,major,majortype WHERE "
				+ "enter_student.LQZY = major.ZYDH AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 "
				+ "AND LQZY != ZYDH4 AND LQZY != ZYDH5 AND LQZY != ZYDH6 AND enter_student.LQFS != 12 "
				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 AND major.KLDM = majortype.KLDM "
				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS Tiaoji_sum LEFT JOIN "
				+ "(SELECT LQZY,COUNT(enter_student.LQZY) AS LQsum,YEAR1 FROM enter_student "
				+ "GROUP BY YEAR1,LQZY) AS LQ_sum ON LQ_sum.LQZY = Tiaoji_sum.ZYDH AND LQ_sum.YEAR1 = Tiaoji_sum.YEAR1) AS a WHERE a.ZYDH = a.ZYDH ";

		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		String sql4 = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ";
		
		if(flag==null){
			flag = "a";
		}
		if (major == null) {
			major = "all";
		}
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			if("all".equals(year)){
				year = null;
			}

			
			if(major != null){
				sql1 = sql1 + " and a.ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and a.KLDM = '"+type+"'";
			}
			if(year != null){
				sql1 = sql1 + " and a.YEAR1 = '"+year+"'";
			}
		}
		
		sql1 = sql1 + " ORDER BY a.YEAR1 DESC,a.TiaoJisum DESC";
		System.out.println(sql1);
		try {
			majorList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			userList = dba.getMultiRecord(sql1);
			//familyList = dba.getMultiRecord(sql4);
			yearList = dba.getMultiRecord(sql4);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		resultMap.put("YEARLIST", yearList);
		
		System.out.println("-----++++++---"+yearList);
		System.out.println("-----++++++---"+jsonMajorType);
		return resultMap;
	}

	public Map mainpage_select4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		//List<Map<String, String>> familyList = null;
		List<Map<String, String>> yearList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE"); 
		String flag = (String) map.get("FLAG");
		//String family = (String) map.get("FAMILY");
		String year = (String) map.get("YEAR");
		System.out.println("major-----"+major);
		System.out.println("type-----"+type);
		System.out.println("flag-----"+flag);
		System.out.println("year-----"+year);
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//		String sql1 = "Select LQZY,KLMC,ZYMC,Count(LQZY) as sum,ZYQZ,YEAR1,AVG(CJ) as Score"
//						+" From major,majortype,enter_student"
//						+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM "
//						+ "AND enter_student.LQFS !=13 AND enter_student.LQFS !=12 AND enter_student.LQFS !=23 ";
//		String sql1 = "SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,COALESCE(LQ_sum.LQZY_sum,0) LQZY_sum,COALESCE(BKZY1.BKZY1_sum,0) BKZY1_sum,COALESCE(BKZY2.BKZY2_sum,0) BKZY2_sum, "
//				+ "COALESCE(BKZY3.BKZY3_sum,0) BKZY3_sum,COALESCE(BKZY4.BKZY4_sum,0) BKZY4_sum,COALESCE(BKZY5.BKZY5_sum,0) BKZY5_sum,COALESCE(BKZY6.BKZY6_sum,0) BKZY6_sum,COALESCE(BKRSALLSUM.BKZY_allsum,0) BKZY_allsum,COALESCE(BKZY1_Rate.BKZY1Rate,0) BKZY1Rate, "
//				+ "COALESCE(LQZY1_Rate.LQZY1sum,0) LQZY1sum,COALESCE(LQZY1_Rate.LQZY1_Rate,0) LQZY1_Rate, "
//				+ "(COALESCE(BKZY1.BKZY1_sum,0)*10+COALESCE(BKZY2.BKZY2_sum,0)*5+COALESCE(BKZY3.BKZY3_sum,0)*4+COALESCE(BKZY4.BKZY4_sum,0)*3+COALESCE(BKZY5.BKZY5_sum,0)*2+COALESCE(BKZY6.BKZY6_sum,0)*1) AS qz "
//				+ "FROM (SELECT * FROM sheet2 WHERE sheet2.ZYDH=sheet2.ZYDH ";
		String sql1 ="SELECT * FROM tb_rate WHERE tb_rate.ZYDH = tb_rate.ZYDH ";
		

		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		String sql4 = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ";
		
		if(flag==null){
			flag = "a";
		}
		if(major == null) {
			major = "all";
		}
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			if("all".equals(year)){
				year = null;
			}

			
			if(major != null){
				sql1 = sql1 + " and ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and KLDM = '"+type+"'";
			}
			if(year != null){
				sql1 = sql1 + " and YEAR1 = '"+year+"'";
			}
		}
		
//		sql1 = sql1+") AS sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS LQZY_sum "
//				+ "FROM enter_student,major WHERE enter_student.LQZY = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS LQ_sum ON sheet2.ZYDH = LQ_sum.ZYDH AND sheet2.YEAR1 = LQ_sum.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH2) AS BKZY2_sum  FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH2 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY2 ON sheet2.ZYDH = BKZY2.ZYDH AND sheet2.YEAR1 = BKZY2.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH3) AS BKZY3_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH3 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY3 ON sheet2.ZYDH = BKZY3.ZYDH AND sheet2.YEAR1 = BKZY3.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH4) AS BKZY4_sum  FROM enter_student,major WHERE enter_student.ZYDH4 = major.ZYDH "
//				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY4 ON sheet2.ZYDH = BKZY4.ZYDH AND sheet2.YEAR1 = BKZY4.YEAR1 LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH5) AS BKZY5_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH5 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY5 ON sheet2.ZYDH = BKZY5.ZYDH AND sheet2.YEAR1 = BKZY5.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH6) AS BKZY6_sum  FROM enter_student,major WHERE enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY6 ON sheet2.ZYDH = BKZY6.ZYDH AND sheet2.YEAR1 = BKZY6.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.KSH) AS BKZY_allsum FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH  OR enter_student.ZYDH2 = major.ZYDH "
//				+ "OR enter_student.ZYDH3 = major.ZYDH OR enter_student.ZYDH4 = major.ZYDH OR enter_student.ZYDH5 = major.ZYDH OR enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKRSALLSUM ON sheet2.ZYDH = BKRSALLSUM.ZYDH AND sheet2.YEAR1 = BKRSALLSUM.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BKZY1.BKZY1_sum,CONCAT(ROUND(BKZY1.BKZY1_sum/BKZY1.allsum*100,1),'','%') AS BKZY1Rate FROM sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum,allsum FROM enter_student,major, "
//				+ "(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.ZYDH1 = major.ZYDH "
//				+ "GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1) AS BKZY1_Rate ON sheet2.ZYDH = BKZY1_Rate.ZYDH AND sheet2.YEAR1 = BKZY1_Rate.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQZY1.LQZY1sum,CONCAT(ROUND(LQZY1.LQZY1sum/LQZY1.allsum*100,1),'','%') AS LQZY1_Rate FROM sheet2 "
//				+ "LEFT JOIN (SELECT enter_student.LQZY,major.KLDM,major.ZYMC,majortype.KLMC,count(*) AS LQZY1sum,all_sum.allsum,enter_student.YEAR1  "
//				+ "FROM enter_student,major,majortype,(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 "
//				+ "AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = enter_student.ZYDH1 AND major.KLDM = majortype.KLDM "
//				+ "AND enter_student.LQZY = major.ZYDH AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 "
//				+ "AND enter_student.LQFS != 23 GROUP BY enter_student.LQZY,enter_student.YEAR1) AS LQZY1 ON sheet2.ZYDH = LQZY1.LQZY AND sheet2.YEAR1 = LQZY1.YEAR1) AS LQZY1_Rate ON sheet2.ZYDH = LQZY1_Rate.ZYDH AND sheet2.YEAR1 = LQZY1_Rate.YEAR1";
		System.out.println("sql1======"+sql1);
		try {
			majorList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			userList = dba.getMultiRecord(sql1);
			//familyList = dba.getMultiRecord(sql4);
			yearList = dba.getMultiRecord(sql4);
			
			
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		for(int i=0;i<userList.size();i++){
			int x = Integer.parseInt(userList.get(i).get("qz"));
			if(x >=5000){
				userList.get(i).put("hot", "火爆");
			}else if(x >=3000&&x <5000){
				userList.get(i).put("hot", "热门");
			}else if(x >=1000&&x <3000){
				userList.get(i).put("hot", "普通");
			}
			else if(x<1000){
				userList.get(i).put("hot", "冷门");
			}
		}
		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		resultMap.put("YEARLIST", yearList);
		
		System.out.println("-----++++++---"+jsonMajor);
		System.out.println("-----++++++---"+jsonMajorType);
		return resultMap;
	}

	public Map mainpage_select1Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;

		

		
		String major = (String) map.get("MAJOR");
		String year = (String) map.get("YEAR");
		String family = (String) map.get("FAMILY");
		
		String sql = null;
		String sql1 = "SELECT enter_student.DQDM,DQMC,ZYDH,KLMC,majortype.KLDM,ZYMC, "
				+ "COUNT(KSH)as FAMILYSUM,round(AVG(CJ), 2) AS CJ,YEAR1,rate FROM "
				+ "enter_student,province,major,majortype "
				+ "WHERE enter_student.DQDM = province.DQDM AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
						+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 ";
		String sql2 = "SELECT enter_student.DQDM,DQMC,ZYDH,KLMC,majortype.KLDM,ZYMC, "
				+ "COUNT(KSH)as FAMILYSUM,round(AVG(CJ), 2) AS CJ,YEAR1,rate FROM "
				+ "enter_student,province,major,majortype "
				+ "WHERE enter_student.DQDM = province.DQDM AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH GROUP BY DQDM ";


		if (family.equals("all")) {
			sql = sql1;
		} else if (family != "0") {
			sql = sql1 + "AND DQMC = '"+family+"' ";
		}
		sql = sql+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH GROUP BY DQDM,YEAR1 ";
		System.out.println("1111111111"+sql);
		try {
			userList = dba.getMultiRecord(sql);
			familyList = dba.getMultiRecord(sql2);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("USERLIST", userList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJOR", major);
		resultMap.put("YEAR", year);
		
		System.out.println("-----------"+userList);
		return resultMap;
	}
	public Map mainpage_select2Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> yearList = null;

		String year = (String) map.get("YEAR");
		String family = (String) map.get("FAMILY");
		String major = (String) map.get("MAJOR");
		//String sql = null;
		String sql1 = "Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,vorate,YEAR1,ROUND(AVG(CJ)) as Score"
						+" From major,majortype,enter_student,province"
						+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM "
						+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
						+ " and enter_student.DQDM = province.DQDM";
		String sql2 = "Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,vorate,YEAR1,AVG(CJ) as Score"
				+" From major,majortype,enter_student,province"
				+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "and enter_student.DQDM = province.DQDM "
				+ "and LQZY = '"+major+"' and YEAR1 = '"+year+"' "
				+ "Group by DQMC,ZYMC,YEAR1 Order by YEAR1,vorate desc";
		
		System.out.println("year="+year);
		System.out.println("family="+family);
		System.out.println("major="+major);
		if(family == null){
			family = "all";
		}
		
		if("all".equals(family)){
			family = null;
		}

		if(major != null){
			sql1 = sql1 + " and LQZY = '"+major+"'";
		}
		if(family != null){
			sql1 = sql1 + " and province.DQDM = '"+family+"'";
		}
		if(year != null){
			sql1 = sql1 + " and YEAR1 = '"+year+"'";
		}
		
		sql1 = sql1 + "	Group by DQMC,ZYMC,YEAR1 Order by YEAR1,vorate desc";
		System.out.println("mainpage_select2Major_sql1="+sql1);
		System.out.println("mainpage_select2Major_sql2="+sql2);
		try {
			familyList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql1);
			//yearList = dba.getMultiRecord(sql3);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("USERLIST", userList);
		resultMap.put("YEAR", year);
		resultMap.put("MAJOR", major);
		//resultMap.put("YEARLIST", yearList);
		
		System.out.println("+++++++++++++++"+userList);
		return resultMap;
	}
	
	public Map mainpage_select3Family(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> yearList = null;
		
		String year = (String) map.get("YEAR");
		String family = (String) map.get("FAMILY");
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE");
		//String sql = null;
//		String sql1 = "Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,rlrate,YEAR1,AVG(CJ) as Score"
//						+" From major,majortype,enter_student,province"
//						+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM and ZYZYTJ = 1 and enter_student.DQDM = province.DQDM";
		String sql1 = "SELECT a.LQZY,a.DQDM,a.DQMC,a.KLMC,a.ZYMC,a.sum,a.allsum,a.YEAR1,a.Score,CONCAT(ROUND(a.sum/a.allsum*100,1),'','%') AS TiaoJi_Rate "
				+ "FROM (Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,all_sum.allsum,YEAR1,AVG(CJ) as Score "
				+ "From major,majortype,enter_student,province,(SELECT COUNT(*) AS allsum FROM enter_student WHERE  enter_student.LQFS != 12 "
				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum "
				+ "Where LQZY = ZYDH and major.KLDM = majortype.KLDM AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 AND LQZY != ZYDH4 "
				+ "AND LQZY != ZYDH5 AND LQZY != ZYDH6 and enter_student.DQDM = province.DQDM "
				+ "Group by DQMC,ZYMC,YEAR1) AS a WHERE a.LQZY=a.LQZY ";
		String sql2 = "SELECT a.LQZY,a.DQDM,a.DQMC,a.KLMC,a.ZYMC,a.sum,a.allsum,a.YEAR1,a.Score,CONCAT(ROUND(a.sum/a.allsum*100,1),'','%') AS TiaoJi_Rate "
				+ "FROM (Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,all_sum.allsum,YEAR1,AVG(CJ) as Score "
				+ "From major,majortype,enter_student,province,(SELECT COUNT(*) AS allsum FROM enter_student WHERE  enter_student.LQFS != 12 "
				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum "
				+ "Where LQZY = ZYDH and major.KLDM = majortype.KLDM AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 AND LQZY != ZYDH4 "
				+ "AND LQZY != ZYDH5 AND LQZY != ZYDH6 and enter_student.DQDM = province.DQDM "
				+ "Group by DQMC,ZYMC,YEAR1) AS a WHERE a.LQZY = '"+major+"' and a.YEAR1 = '"+year+"' ";
		
		if(family == null){
			family = "all";
		}
		
		if(family.equals("all")){
			family = null;
		}

		if(major != null){
			sql1 = sql1 + " and a.LQZY = '"+major+"'";
		}
		if(family != null){
			sql1 = sql1 + " and a.DQDM = '"+family+"'";
		}
		if(year != null){
			sql1 = sql1 + " and a.YEAR1 = '"+year+"'";
		}
		
//		sql1 = sql1 + "	Group by DQMC,ZYMC,YEAR1 Order by YEAR1,rlrate desc";
		System.out.println(sql1);
		System.out.println(sql2);
		try {
			familyList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql1);
			//yearList = dba.getMultiRecord(sql3);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("USERLIST", userList);
		resultMap.put("YEAR", year);
		resultMap.put("MAJOR", major);
		resultMap.put("MAJORTYPE", type);
		//resultMap.put("YEARLIST", yearList);
		
		System.out.println("+++++++++++++++"+userList);
		return resultMap;
	}
	
	public Map mainpage_select4Major(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		//List<Map<String, String>> yearList = null;

		String year = (String) map.get("YEAR");
		String family = (String) map.get("FAMILY");
		String major = (String) map.get("MAJOR");
		System.out.println(year);
		System.out.println(major);
		//String sql = null;
		String sql1 = "Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,ZYQZ,YEAR1,ROUND(AVG(CJ),0) as Score"
						+" From major,majortype,enter_student,province"
						+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM and enter_student.DQDM = province.DQDM";

		String sql2 = "Select LQZY,province.DQDM,DQMC,KLMC,ZYMC,Count(LQZY) as sum,ZYQZ,YEAR1,AVG(CJ) as Score"
				+" From major,majortype,enter_student,province"
				+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM and enter_student.DQDM = province.DQDM "
				+ "and LQZY = '"+major+"' and YEAR1 = '"+year+"' Group by DQMC,ZYMC,YEAR1 Order by YEAR1,sum desc ";
		System.out.println("sql"+sql2);
		if(family == null){
			family = "all";
		}
		
		if("all".equals(family)){
			family = null;
		}

		if(major != null){
			sql1 = sql1 + " and LQZY = '"+major+"'";
		}
		if(family != null){
			sql1 = sql1 + " and province.DQDM = '"+family+"'";
		}
		if(year != null){
			sql1 = sql1 + " and YEAR1 = '"+year+"'";
		}
		
		sql1 = sql1 + "	Group by DQMC,ZYMC,YEAR1 Order by YEAR1,sum desc";
		try {
			familyList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql1);
			
			/*for(int i=0;i<userList.size();i++){
				int x = Integer.parseInt(userList.get(i).get("qz"));
				if(x >=3000){
					userList.get(i).put("hot", "火爆");
				}else if(x >=1000&&x <3000){
					userList.get(i).put("hot", "热门");
				}else if(x<1000){
					userList.get(i).put("hot", "冷门");
				}
			}*/
			//yearList = dba.getMultiRecord(sql3);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("USERLIST", userList);
		resultMap.put("YEAR", year);
		resultMap.put("MAJOR", major);
		//resultMap.put("YEARLIST", yearList);
		
		System.out.println("+++++++++++++++"+userList);
		return resultMap;
	}
	//	显示各专业一志愿率信息首页点击跳转到这，查询后跳mainpage_select5Bymajor
	public Map mainpage_select5(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> majorTypeList = null;
		List<Map<String, String>> yearList = null;
		
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//		String sql1 = "SELECT LQZY,ZYMC,ZYDH,major.KLDM,KLMC,COUNT(LQZY) as LQZYRS,YEAR1,rate,round(AVG(CJ),2) as CJ "+
//				"FROM enter_student,major,majortype "+
//				"WHERE LQZY = ZYDH AND majortype.KLDM = major.KLDM "
//				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
//				+"GROUP BY LQZY,YEAR1 ";
		
//		String sql1 = "SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQ_sum.LQZY_sum,BKZY1.BKZY1_sum,BKZY2.BKZY2_sum, "
//				+ "BKZY3.BKZY3_sum,BKZY4.BKZY4_sum,BKZY5.BKZY5_sum,BKZY6.BKZY6_sum,BKRSALLSUM.BKZY_allsum,BKZY1_Rate.BKZY1Rate, "
//				+ "LQZY1_Rate.LQZY1sum,LQZY1_Rate.LQZY1_Rate FROM sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS LQZY_sum "
//				+ "FROM enter_student,major WHERE enter_student.LQZY = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS LQ_sum ON sheet2.ZYDH = LQ_sum.ZYDH AND sheet2.YEAR1 = LQ_sum.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH2) AS BKZY2_sum  FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH2 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY2 ON sheet2.ZYDH = BKZY2.ZYDH AND sheet2.YEAR1 = BKZY2.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH3) AS BKZY3_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH3 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY3 ON sheet2.ZYDH = BKZY3.ZYDH AND sheet2.YEAR1 = BKZY3.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH4) AS BKZY4_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH4 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY4 ON sheet2.ZYDH = BKZY4.ZYDH AND sheet2.YEAR1 = BKZY4.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH5) AS BKZY5_sum "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH5 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY5 ON sheet2.ZYDH = BKZY5.ZYDH AND sheet2.YEAR1 = BKZY5.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH6) AS BKZY6_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY6 ON sheet2.ZYDH = BKZY6.ZYDH AND sheet2.YEAR1 = BKZY6.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.KSH) AS BKZY_allsum "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH  OR enter_student.ZYDH2 = major.ZYDH "
//				+ "OR enter_student.ZYDH3 = major.ZYDH OR enter_student.ZYDH4 = major.ZYDH OR enter_student.ZYDH5 = major.ZYDH "
//				+ "OR enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKRSALLSUM ON sheet2.ZYDH = BKRSALLSUM.ZYDH AND sheet2.YEAR1 = BKRSALLSUM.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BKZY1.BKZY1_sum,CONCAT(ROUND(BKZY1.BKZY1_sum/BKZY1.allsum*100,1),'','%') AS BKZY1Rate FROM sheet2 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum,allsum FROM enter_student,major, "
//				+ "(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum "
//				+ "WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1) AS BKZY1_Rate ON sheet2.ZYDH = BKZY1_Rate.ZYDH AND sheet2.YEAR1 = BKZY1_Rate.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQZY1.LQZY1sum,CONCAT(ROUND(LQZY1.LQZY1sum/LQZY1.allsum*100,1),'','%') AS LQZY1_Rate "
//				+ "FROM sheet2 LEFT JOIN (SELECT enter_student.LQZY,major.KLDM,major.ZYMC,majortype.KLMC,count(*) AS LQZY1sum,all_sum.allsum,enter_student.YEAR1  "
//				+ "FROM enter_student,major,majortype,(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 "
//				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = enter_student.ZYDH1 AND major.KLDM = majortype.KLDM "
//				+ "AND enter_student.LQZY = major.ZYDH AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 "
//				+ "GROUP BY enter_student.LQZY,enter_student.YEAR1) AS LQZY1 ON sheet2.ZYDH = LQZY1.LQZY AND sheet2.YEAR1 = LQZY1.YEAR1) "
//				+ "AS LQZY1_Rate ON sheet2.ZYDH = LQZY1_Rate.ZYDH AND sheet2.YEAR1 = LQZY1_Rate.YEAR1";
		
		String sql1 = "SELECT * FROM tb_rate ";
		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		String sql4 = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ";
		
//		System.out.println("mainpage_select5_sql1=="+sql1);
//		System.out.println("mainpage_select5_sql2=="+sql2);
//		System.out.println("mainpage_select5_sql3=="+sql3);
//		System.out.println("mainpage_select5_sql4=="+sql4);
		try {
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			majorTypeList = dba.getMultiRecord(sql3);
			yearList = dba.getMultiRecord(sql4);
			
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(majorTypeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		resultMap.put("YEARLIST", yearList);
		System.out.println(yearList);
		return resultMap;
	}
	//二级界面带查询
	public Map mainpage_select5ByMajor(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> TypeList = null;
		List<Map<String, String>> yearList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		String sql = null;
//		String sql1 = "SELECT LQZY,ZYMC,ZYDH,major.KLDM,KLMC,COUNT(LQZY) as LQZYRS,YEAR1,rate,round(AVG(CJ),2) as CJ "+
//				"FROM enter_student,major,majortype "+
//				"WHERE LQZY = ZYDH AND majortype.KLDM = major.KLDM "
//				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 ";
		
		
//		String sql1 = "SELECT * FROM (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQ_sum.LQZY_sum,BKZY1.BKZY1_sum,BKZY2.BKZY2_sum, "
//				+ "BKZY3.BKZY3_sum,BKZY4.BKZY4_sum,BKZY5.BKZY5_sum,BKZY6.BKZY6_sum,BKRSALLSUM.BKZY_allsum,BKZY1_Rate.BKZY1Rate, "
//				+ "LQZY1_Rate.LQZY1sum,LQZY1_Rate.LQZY1_Rate FROM sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS LQZY_sum "
//				+ "FROM enter_student,major WHERE enter_student.LQZY = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS LQ_sum ON sheet2.ZYDH = LQ_sum.ZYDH AND sheet2.YEAR1 = LQ_sum.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH2) AS BKZY2_sum  FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH2 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY2 ON sheet2.ZYDH = BKZY2.ZYDH AND sheet2.YEAR1 = BKZY2.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH3) AS BKZY3_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH3 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY3 ON sheet2.ZYDH = BKZY3.ZYDH AND sheet2.YEAR1 = BKZY3.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH4) AS BKZY4_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH4 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY4 ON sheet2.ZYDH = BKZY4.ZYDH AND sheet2.YEAR1 = BKZY4.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH5) AS BKZY5_sum "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH5 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY5 ON sheet2.ZYDH = BKZY5.ZYDH AND sheet2.YEAR1 = BKZY5.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH6) AS BKZY6_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY6 ON sheet2.ZYDH = BKZY6.ZYDH AND sheet2.YEAR1 = BKZY6.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.KSH) AS BKZY_allsum "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH  OR enter_student.ZYDH2 = major.ZYDH "
//				+ "OR enter_student.ZYDH3 = major.ZYDH OR enter_student.ZYDH4 = major.ZYDH OR enter_student.ZYDH5 = major.ZYDH "
//				+ "OR enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKRSALLSUM ON sheet2.ZYDH = BKRSALLSUM.ZYDH AND sheet2.YEAR1 = BKRSALLSUM.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BKZY1.BKZY1_sum,CONCAT(ROUND(BKZY1.BKZY1_sum/BKZY1.allsum*100,1),'','%') AS BKZY1Rate FROM sheet2 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum,allsum FROM enter_student,major, "
//				+ "(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum "
//				+ "WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1) AS BKZY1_Rate ON sheet2.ZYDH = BKZY1_Rate.ZYDH AND sheet2.YEAR1 = BKZY1_Rate.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQZY1.LQZY1sum,CONCAT(ROUND(LQZY1.LQZY1sum/LQZY1.allsum*100,1),'','%') AS LQZY1_Rate "
//				+ "FROM sheet2 LEFT JOIN (SELECT enter_student.LQZY,major.KLDM,major.ZYMC,majortype.KLMC,count(*) AS LQZY1sum,all_sum.allsum,enter_student.YEAR1  "
//				+ "FROM enter_student,major,majortype,(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 "
//				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = enter_student.ZYDH1 AND major.KLDM = majortype.KLDM "
//				+ "AND enter_student.LQZY = major.ZYDH AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 "
//				+ "GROUP BY enter_student.LQZY,enter_student.YEAR1) AS LQZY1 ON sheet2.ZYDH = LQZY1.LQZY AND sheet2.YEAR1 = LQZY1.YEAR1) "
//				+ "AS LQZY1_Rate ON sheet2.ZYDH = LQZY1_Rate.ZYDH AND sheet2.YEAR1 = LQZY1_Rate.YEAR1) AS a WHERE a.ZYDH = a.ZYDH ";
		
		String sql1 = "SELECT * FROM tb_rate WHERE tb_rate.ZYDH = tb_rate.ZYDH ";
		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		String sql4 = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ";
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("TYPELIST"); 
		String year = (String) map.get("YEARLIST");
		String flag = (String) map.get("FLAG");
		System.out.println("major---" + major);
		System.out.println("MAJORTYPE---" + type);
		System.out.println("YEAR---" + year);
		System.out.println("flag---" + flag);
//		if (major.equals("all") && majortype.equals("all") && year.equals("all")) {
//			sql = sql1;
//		} else if (major != null && major.equals("all")==false) {
//			sql = sql1 + "AND ZYMC = '"+major+"' ";
//		}else if (majortype != null && majortype.equals("all")==false) {
//			sql = sql1 +"AND KLMC = '"+majortype+"' ";
//		}else if (year != null && year.equals("all")==false) {
//			sql = sql1 +"AND YEAR1 = '"+year+"' ";
//		}
		if(flag==null){
			flag = "a";
		}
		if(major == null) {
			major = "all";
		}
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			if("all".equals(year)){
				year = null;
			}

			
			if(major != null){
				sql1 = sql1 + " and ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and KLDM = '"+type+"'";
			}
			if(year != null){
				sql1 = sql1 + " and YEAR1 = '"+year+"'";
			}
		}
		System.out.println("sql--"+sql1);
		try {
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			TypeList = dba.getMultiRecord(sql3);
			yearList = dba.getMultiRecord(sql4);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(TypeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		resultMap.put("YEARLIST", yearList);

//		System.err.println("------"+userList);
		return resultMap;
	}

	public Map mainpage_select6(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;

		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE"); 
		String flag = (String) map.get("FLAG");
		System.out.println("major="+major);
		System.out.println("type="+type);
		System.out.println("flag="+flag);
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		//获取最新年份
//		MysqlDemo mDemo = new MysqlDemo();
//		List<Integer> yearList = mDemo.yearList();
//		Integer newYear = yearList.get(0);
		Integer newYear = 2018;
//		String sql1 = "SELECT a.KLMC,a.ZYMC,a.LQZY,a.sum,CONCAT(ROUND(a.sum/a.allsum*100,3),'','%') AS Luqu_Rate,a.YEAR1 "
//				+ "FROM(SELECT KLMC,ZYMC,LQZY,major.KLDM,Count(LQZY) AS sum,all_sum.allsum,YEAR1 FROM major,majortype,enter_student,(SELECT COUNT(*) AS allsum FROM enter_student WHERE  "
//				+ "enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = major.ZYDH "
//				+ "AND major.KLDM = majortype.KLDM  AND YEAR1 = 2018 AND enter_student.LQFS != 13 AND enter_student.LQFS != 12 "
//				+ "AND enter_student.LQFS != 23 GROUP BY ZYDM,major.KLDM,KLMC,ZYMC,YEAR1) AS a WHERE a.LQZY=a.LQZY ";
		String sql1 = "SELECT *,COALESCE(CONCAT(ROUND(tb_rate.BKZY_allsum / tb_rate.LQZY_sum * 100),'','%'),0) AS Luqu_Rate FROM tb_rate WHERE YEAR1='"+newYear+"'";
		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		if(flag == null){
			flag = "a";
		}
		if(major == null){
			major = "all";
		}
		
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			
			if(major != null){
				sql1 = sql1 + " AND tb_rate.ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and tb_rate.KLDM = '"+type+"'";
			}
		}
//		sql1 = sql1+" Group by ZYDM,KLMC,ZYMC Order by vorate desc";
		System.out.println("mainpage_select6_sql1="+sql1);
		System.out.println("mainpage_select6_sql2="+sql2);
		System.out.println("mainpage_select6_sql3="+sql3);
		try {
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
//		System.out.println("-----++++++---"+jsonMajor);
//		System.out.println("-----++++++---"+jsonMajorType);
		return resultMap;
	}

	public Map mainpage_select7(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE"); 
		String flag = (String) map.get("FLAG");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		//获取最新年份
//		MysqlDemo mDemo = new MysqlDemo();
//		List<Integer> yearList = mDemo.yearList();
//		Integer newYear = yearList.get(0);
		Integer newYear = 2018;
		System.out.println("----------"+newYear);
//		String sql1 = "SELECT a.ZYDH,a.ZYMC,a.KLDM,a.KLMC,a.YEAR1,a.TiaoJisum,CONCAT(ROUND(a.TiaoJisum/a.allsum*100,3),'','%') AS TiaoJi_Rate "
//					+ "FROM (SELECT major.ZYDH,major.ZYMC,majortype.KLDM,majortype.KLMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS TiaoJisum,all_sum.allsum "
//					+ "FROM enter_student,major,(SELECT COUNT(*) AS allsum FROM enter_student WHERE  enter_student.LQFS != 12 "
//					+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum,majortype "
//					+ "WHERE enter_student.LQZY = major.ZYDH AND LQZY != ZYDH1 AND LQZY != ZYDH2 "
//					+ "AND LQZY != ZYDH3 AND LQZY != ZYDH4 AND LQZY != ZYDH5 AND LQZY != ZYDH6 AND enter_student.LQFS != 12 "
//					+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 AND major.KLDM = majortype.KLDM "
//					+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS a WHERE a.YEAR1 = '2018'  ";
		String sql1 ="SELECT * FROM(SELECT Tiaoji_sum.ZYDH,Tiaoji_sum.ZYMC,Tiaoji_sum.KLDM,Tiaoji_sum.KLMC, "
				+ "Tiaoji_sum.YEAR1,Tiaoji_sum.TiaoJisum,LQ_sum.LQsum,CONCAT(ROUND(Tiaoji_sum.TiaoJisum/LQ_sum.LQsum*100,2),'','%') AS TiaoJi_Rate "
				+ "FROM(SELECT major.ZYDH,major.ZYMC,majortype.KLDM,majortype.KLMC,enter_student.YEAR1, "
				+ "COUNT(enter_student.LQZY) AS TiaoJisum FROM enter_student,major,majortype WHERE "
				+ "enter_student.LQZY = major.ZYDH AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 "
				+ "AND LQZY != ZYDH4 AND LQZY != ZYDH5 AND LQZY != ZYDH6 AND enter_student.LQFS != 12 "
				+ "AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 AND major.KLDM = majortype.KLDM "
				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS Tiaoji_sum LEFT JOIN "
				+ "(SELECT LQZY,COUNT(enter_student.LQZY) AS LQsum,YEAR1 FROM enter_student "
				+ "GROUP BY YEAR1,LQZY) AS LQ_sum ON LQ_sum.LQZY = Tiaoji_sum.ZYDH AND LQ_sum.YEAR1 = Tiaoji_sum.YEAR1) AS a WHERE a.YEAR1 = '"+newYear+"' ";

		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		
		
		if(flag == null){
			flag = "a";
		}
		if (major == null) {
			major = "all";
		}
		
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			
			if(major != null){
				sql1 = sql1 + " and a.ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and a.KLDM = '"+type+"'";
			}
		}
		sql1 = sql1+"ORDER BY a.YEAR1 DESC,a.TiaoJisum DESC";
		System.out.println("mainpage_select7_sql1="+sql1);
		System.out.println("mainpage_select7_sql2="+sql2);
		System.out.println("mainpage_select7_sql3="+sql3);
		try {
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		System.out.println("-----++++++---"+jsonMajor);
		System.out.println("-----++++++---"+jsonMajorType);
		return resultMap;
	}

	public Map mainpage_select8(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> typeList = null;
		
		String major = (String) map.get("MAJOR");
		String type = (String) map.get("MAJORTYPE"); 
		String flag = (String) map.get("FLAG");

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		//获取最新年份
//		MysqlDemo mDemo = new MysqlDemo();
//		List<Integer> yearList = mDemo.yearList();
//		Integer newYear = yearList.get(0);
		Integer newYear = 2018;
//		String sql1 = "Select KLMC,ZYMC,Count(LQZY) as sum,ZYQZ"
//						+" From major,majortype,enter_student"
//						+" Where LQZY = ZYDH and major.KLDM = majortype.KLDM and YEAR1 = 2019 "
//						+ "AND enter_student.LQFS !=13 AND enter_student.LQFS !=12 AND enter_student.LQFS !=23 ";
//		String sql1 = "SELECT major.ZYDH,major.ZYMC,major_type.KLMC, "
//				+ "(majorSum.sum)AS LQ_sum,major1.ZYDH1_sum,major2.ZYDH2_sum, "
//				+ "major3.ZYDH3_sum,major4.ZYDH4_sum,major5.ZYDH5_sum, "
//				+ "major6.ZYDH6_sum FROM major LEFT JOIN (SELECT major.ZYMC,COUNT(ZYDH1) AS "
//				+ "ZYDH1_sum,major.ZYDH FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH "
//				+ "GROUP BY ZYDH) as major1 ON major.ZYDH = major1.ZYDH LEFT JOIN "
//				+ "(SELECT major.ZYMC,COUNT(ZYDH2) AS ZYDH2_sum,major.ZYDH FROM "
//				+ "enter_student,major WHERE enter_student.ZYDH2 = major.ZYDH GROUP BY "
//				+ "ZYDH) as major2 ON major.ZYDH = major2.ZYDH LEFT JOIN "
//				+ "(SELECT major.ZYMC,COUNT(ZYDH3) AS ZYDH3_sum,major.ZYDH "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH3 = major.ZYDH "
//				+ "GROUP BY ZYDH) as major3 ON major.ZYDH = major3.ZYDH LEFT JOIN "
//				+ "(SELECT major.ZYMC,COUNT(ZYDH4) AS ZYDH4_sum,major.ZYDH FROM "
//				+ "enter_student,major WHERE enter_student.ZYDH4 = major.ZYDH GROUP BY "
//				+ "ZYDH) as major4 ON major.ZYDH = major4.ZYDH "
//				+ "LEFT JOIN(SELECT major.ZYMC,major.ZYDH,majortype.KLDM,majortype.KLMC "
//				+ "FROM major,majortype WHERE major.KLDM = majortype.KLDM ) AS "
//				+ "major_type ON major.ZYDH = major_type.ZYDH "
//				+ "LEFT JOIN(SELECT major.ZYMC,COUNT(ZYDH5) AS ZYDH5_sum,major.ZYDH FROM "
//				+ "enter_student,major WHERE enter_student.ZYDH5 = major.ZYDH GROUP BY ZYDH) as "
//				+ "major5 ON major.ZYDH = major5.ZYDH LEFT JOIN (SELECT major.ZYMC,COUNT(ZYDH6) AS ZYDH6_sum, "
//				+ "major.ZYDH FROM enter_student,major WHERE enter_student.ZYDH6 = major.ZYDH "
//				+ "GROUP BY ZYDH) as major6 ON major.ZYDH = major6.ZYDH LEFT JOIN(Select KLMC,major.ZYDH,ZYMC,Count(LQZY) "
//				+ "as sum,ZYQZ From major,majortype,enter_student Where LQZY = ZYDH "
//				+ "and major.KLDM = majortype.KLDM and YEAR1 = 2019 AND enter_student.LQFS !=13 AND enter_student.LQFS !=12 AND "
//				+ "enter_student.LQFS !=23 Group by ZYDM,KLMC,ZYMC Order by ZYDH) AS majorSum ON major.ZYDH = majorSum.ZYDH "
//				+ "WHERE major.KLDM = major.KLDM ";
//		String sql1 = "SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,COALESCE(LQ_sum.LQZY_sum,0) LQZY_sum,COALESCE(BKZY1.BKZY1_sum,0) BKZY1_sum,COALESCE(BKZY2.BKZY2_sum,0) BKZY2_sum, "
//				+ "COALESCE(BKZY3.BKZY3_sum,0) BKZY3_sum,COALESCE(BKZY4.BKZY4_sum,0) BKZY4_sum,COALESCE(BKZY5.BKZY5_sum,0) BKZY5_sum,COALESCE(BKZY6.BKZY6_sum,0) BKZY6_sum,COALESCE(BKRSALLSUM.BKZY_allsum,0) BKZY_allsum,COALESCE(BKZY1_Rate.BKZY1Rate,0) BKZY1Rate, "
//				+ "COALESCE(LQZY1_Rate.LQZY1sum,0) LQZY1sum,COALESCE(LQZY1_Rate.LQZY1_Rate,0) LQZY1_Rate, "
//				+ "(COALESCE(BKZY1.BKZY1_sum,0)*10+COALESCE(BKZY2.BKZY2_sum,0)*5+COALESCE(BKZY3.BKZY3_sum,0)*4+COALESCE(BKZY4.BKZY4_sum,0)*3+COALESCE(BKZY5.BKZY5_sum,0)*2+COALESCE(BKZY6.BKZY6_sum,0)*1) AS qz "
//				+ "FROM (SELECT * FROM sheet2 WHERE YEAR1=2018 ";
		String sql1 ="SELECT * FROM tb_rate WHERE YEAR1='"+newYear+"' ";
				

//		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
//		String sql3 = "SELECT KLMC,major.KLDM FROM enter_student,major,majortype WHERE "
//				+ "LQZY = ZYDH AND majortype.KLDM = major.KLDM "
//				+ "AND enter_student.LQFS !=13 AND enter_student.LQFS !=12 AND enter_student.LQFS !=23 "
//				+ "GROUP BY KLMC ";
		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		if(flag == null){
			flag = "a";
		}
		if (major == null) {
			major ="all";
		}
		
		if("select".equals(flag)){
			if("all".equals(major)){
				major = null;
			}
			if("all".equals(type)){
				type = null;
			}
			
			if(major != null){
				sql1 = sql1 + " and ZYMC = '"+major+"'";
			}
			if(type != null){
				sql1 = sql1 + " and KLDM = '"+type+"'";
			}
		}
//		sql1 = sql1+") AS sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS LQZY_sum "
//				+ "FROM enter_student,major WHERE enter_student.LQZY = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS LQ_sum ON sheet2.ZYDH = LQ_sum.ZYDH AND sheet2.YEAR1 = LQ_sum.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH2) AS BKZY2_sum  FROM enter_student,major "
//				+ "WHERE enter_student.ZYDH2 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY2 ON sheet2.ZYDH = BKZY2.ZYDH AND sheet2.YEAR1 = BKZY2.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH3) AS BKZY3_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH3 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY3 ON sheet2.ZYDH = BKZY3.ZYDH AND sheet2.YEAR1 = BKZY3.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH4) AS BKZY4_sum  FROM enter_student,major WHERE enter_student.ZYDH4 = major.ZYDH "
//				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY4 ON sheet2.ZYDH = BKZY4.ZYDH AND sheet2.YEAR1 = BKZY4.YEAR1 LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH5) AS BKZY5_sum  "
//				+ "FROM enter_student,major WHERE enter_student.ZYDH5 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY5 ON sheet2.ZYDH = BKZY5.ZYDH AND sheet2.YEAR1 = BKZY5.YEAR1 "
//				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH6) AS BKZY6_sum  FROM enter_student,major WHERE enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY6 ON sheet2.ZYDH = BKZY6.ZYDH AND sheet2.YEAR1 = BKZY6.YEAR1 "
//				+ "LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.KSH) AS BKZY_allsum FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH  OR enter_student.ZYDH2 = major.ZYDH "
//				+ "OR enter_student.ZYDH3 = major.ZYDH OR enter_student.ZYDH4 = major.ZYDH OR enter_student.ZYDH5 = major.ZYDH OR enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKRSALLSUM ON sheet2.ZYDH = BKRSALLSUM.ZYDH AND sheet2.YEAR1 = BKRSALLSUM.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BKZY1.BKZY1_sum,CONCAT(ROUND(BKZY1.BKZY1_sum/BKZY1.allsum*100,1),'','%') AS BKZY1Rate FROM sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum,allsum FROM enter_student,major, "
//				+ "(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.ZYDH1 = major.ZYDH "
//				+ "GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1) AS BKZY1_Rate ON sheet2.ZYDH = BKZY1_Rate.ZYDH AND sheet2.YEAR1 = BKZY1_Rate.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQZY1.LQZY1sum,CONCAT(ROUND(LQZY1.LQZY1sum/LQZY1.allsum*100,1),'','%') AS LQZY1_Rate FROM sheet2 "
//				+ "LEFT JOIN (SELECT enter_student.LQZY,major.KLDM,major.ZYMC,majortype.KLMC,count(*) AS LQZY1sum,all_sum.allsum,enter_student.YEAR1  "
//				+ "FROM enter_student,major,majortype,(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 "
//				+ "AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = enter_student.ZYDH1 AND major.KLDM = majortype.KLDM "
//				+ "AND enter_student.LQZY = major.ZYDH AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 "
//				+ "AND enter_student.LQFS != 23 GROUP BY enter_student.LQZY,enter_student.YEAR1) AS LQZY1 ON sheet2.ZYDH = LQZY1.LQZY AND sheet2.YEAR1 = LQZY1.YEAR1) AS LQZY1_Rate ON sheet2.ZYDH = LQZY1_Rate.ZYDH AND sheet2.YEAR1 = LQZY1_Rate.YEAR1";
		System.out.println("mainpage_select8="+sql1);
		try {
			userList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			
			for(int i=0;i<userList.size();i++){
				int x = Integer.parseInt(userList.get(i).get("qz"));
				if(x >=5000){
					userList.get(i).put("hot", "火爆");
				}else if(x >=3000&&x <5000){
					userList.get(i).put("hot", "热门");
				}else if(x >=1000&&x <3000){
					userList.get(i).put("hot", "普通");
				}
				else if(x<1000){
					userList.get(i).put("hot", "冷门");
				}
			}
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);
		
		System.out.println("-----++++++---"+jsonMajor);
		System.out.println("-----++++++---"+jsonMajorType);
		return resultMap;
	}
	public Map mainpage_selectAll(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");
		List<Map<String, String>> userList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String family = (String) map.get("FAMILY");
		String major = (String) map.get("ZYDH");
		String year = (String) map.get("YEAR");
		String flag = (String) map.get("Flag");
		
		
		System.out.println("--------------"+flag+"-----------------------");
//		System.out.println(fam1ily+"---------------------------");

		String sql1 = "SELECT KSH,XM,ZXMC,enter_student.DQDM,DQMC,LQZY,ZYMC,KLMC,CJ,YEAR1,LQFSMC "
				+ "FROM enter_student,province,major,majortype,td_lqfs "
				+ "WHERE enter_student.DQDM = province.DQDM "
				+ "AND enter_student.LQFS = td_lqfs.LQFSDM "
				+ "AND enter_student.DQDM = '"+family+"' AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 ";
//		String sql1 = "SELECT * FROM enter_student WHERE LQZY = '"+major+"' "
//				+ "AND YEAR1 = '"+year+"' AND enter_student.DQDM = '"+family+"' ";
		
		if("TJ".equals(flag)){
			sql1 = "SELECT KSH,XM,ZXMC,enter_student.DQDM,DQMC,LQZY,ZYMC,KLMC,CJ,YEAR1,LQFSMC "
				+ "FROM enter_student,province,major,majortype,td_lqfs "
				+ "WHERE enter_student.DQDM = province.DQDM  AND enter_student.DQDM = '"+family+"' AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND enter_student.LQFS = td_lqfs.LQFSDM "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 AND LQZY != ZYDH4 "
				+ "AND LQZY != ZYDH5 AND LQZY != ZYDH6 "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH ";
			System.out.println(1);
//			sql1 = "SELECT * FROM enter_student WHERE LQZY = '"+major+"' "
//					+ "AND YEAR1 = '"+year+"' AND enter_student.DQDM = '"+family+"' ";
		}
		if("ZY".equals(flag)){
			sql1 = "SELECT KSH,XM,ZXMC,enter_student.DQDM,DQMC,LQZY,ZYMC,KLMC,CJ,YEAR1,LQFSMC "
				+ "FROM enter_student,province,major,majortype,td_lqfs "
				+ "WHERE enter_student.DQDM = province.DQDM   AND enter_student.DQDM = '"+family+"' AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND enter_student.LQFS = td_lqfs.LQFSDM "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH ";
//			sql1 = "SELECT * FROM enter_student WHERE LQZY = '"+major+"' "
//					+ "AND YEAR1 = '"+year+"' AND enter_student.DQDM = '"+family+"' ";
		}
		if("SC".equals(flag)){
			sql1 = "SELECT KSH,XM,ZXMC,enter_student.DQDM,DQMC,LQZY,ZYMC,KLMC,CJ,YEAR1,LQFSMC "
				+ "FROM enter_student,province,major,majortype,td_lqfs "
				+ "WHERE enter_student.DQDM = province.DQDM   AND enter_student.DQDM = '"+family+"' AND ZYDH = '"+major+"' AND YEAR1 = '"+year+"' "
				+ "AND enter_student.LQFS = td_lqfs.LQFSDM "
				+ "AND LQFS != 13 AND LQFS != 23 AND LQFS != 12 "
				+ "AND majortype.KLDM = major.KLDM AND LQZY = ZYDH ";
		}
		System.out.println("22"+sql1);
		try {
			userList = dba.getMultiRecord(sql1);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		return resultMap;
	}
	public Map mainpage_selectAll1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map) paraMap.get("CONDITIONMAP");
		List<Map<String, String>> userList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String flag = (String) map.get("FLAG");
		String major = (String) map.get("MAJOR");
		String year = (String) map.get("YEAR");

		

		String sql2 = "Select stuName,stuScoure,majorName,stuSchool,stuyear " +
		"From major,student_year,year Where stuMajor = majorID and stuyear = yearId";

		
		if(major != null && year == null){
			sql2 = sql2+" and stuMajor = '"+major+"' ";
		}else if(major != null && year != null){
			sql2 = sql2+" and stuMajor = '"+major+"' ";
			sql2 = sql2+ " and stuyear = '"+year+"' ";
		}
		
		try {
			userList = dba.getMultiRecord(sql2);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == userList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("USERLIST", userList);
		return resultMap;
	}
}
