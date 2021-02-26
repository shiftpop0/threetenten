package cnedu.ncist.stuusers.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.NullLiteral;

import cnedu.ncist.util.JsonPsrseUtil;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StuusersService extends AbstractService {

	public Map doServiceProcess(Map paraMap) {
		// TODO Auto-generated method stub
		String actionType = (String) paraMap.get("ACTIONTYPE");
		System.out.println(actionType);
		if ("stuusers_MiddleSchool_Init".equals(actionType)) {
			return stuusers_middleSchool_init(paraMap);
		} else if ("stuusers_MiddleSchool_Province".equals(actionType)) {
			return stuusers_MiddleSchool_Province(paraMap);
		}else if ("stuusers_MiddleSchool_Distribution".equals(actionType)) {
			return stuusers_MiddleSchool_Distribution(paraMap);
		} else {
			ActionTypeError();
		}

		return null;
	}

	// 中学信息初始
	private Map stuusers_middleSchool_init(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> schoolList = null;
		List<Map<String, String>> provinceList = null;

		String flag = (String) map.get("FLAG");
		String family = (String) map.get("FAMILY");
		System.out.println(family);
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String sql = null;
		if (family == null) {
			sql = "SELECT enter_student.DQDM,DQMC,ZXDM,ZXMC,COUNT(ZXDM) AS stusum,YZBM "
					+ "FROM enter_student,province WHERE enter_student.DQDM=province.DQDM "
					+ "GROUP BY DQMC,ZXDM,YZBM  ";
		}else {
			sql = "SELECT enter_student.DQDM,DQMC,ZXDM,ZXMC,COUNT(ZXDM) AS stusum,YZBM "
					+ "FROM enter_student,province WHERE enter_student.DQDM=province.DQDM "
					+ "AND province.DQMC = '"+family+"' "
					+ "GROUP BY DQMC,ZXDM,YZBM  ";
		}
		String sql1 = "SELECT enter_student.DQDM,DQMC FROM enter_student,province "
				+ "WHERE enter_student.DQDM=province.DQDM GROUP BY DQDM ";
		System.out.println("----"+sql);
		System.out.println("----"+sql1);
		try {
			schoolList = dba.getMultiRecord(sql);
			provinceList = dba.getMultiRecord(sql1);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == schoolList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("SCHOOLLIST", schoolList);
		resultMap.put("PROVINCELIST", provinceList);
		resultMap.put("FLAG", flag);
		return resultMap;

	}

	// 中学信息--省查询
	private Map stuusers_MiddleSchool_Province(Map paraMap) {

		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map map = (Map) paraMap.get("CONDITIONMAP");

		List<Map<String, String>> schoolList = null;
		List<Map<String, String>> provinceList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String province = (String) map.get("PROVINCE");
		
		String sql = null;
		String sql1 = "SELECT enter_student.DQDM,DQMC FROM enter_student,province "
				+ "WHERE enter_student.DQDM=province.DQDM GROUP BY DQDM ";
		
		if (province.equals("all")) {
			sql = "SELECT enter_student.DQDM,DQMC,ZXDM,ZXMC,COUNT(ZXDM) AS stusum,YZBM "
					+ "FROM enter_student,province WHERE enter_student.DQDM=province.DQDM ";
		} else if (province != "0") {
			sql = "SELECT enter_student.DQDM,DQMC,ZXDM,ZXMC,COUNT(ZXDM) AS stusum,YZBM "
					+ "FROM enter_student,province WHERE enter_student.DQDM=province.DQDM "
					+"AND province.DQMC='"+ province +"' ";
		}
		sql = sql+ "GROUP BY DQMC,ZXDM,YZBM ORDER BY stusum DESC ";
		
		System.out.println("-"+sql);
		
		try {
			schoolList = dba.getMultiRecord(sql);
			provinceList = dba.getMultiRecord(sql1);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == schoolList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("SCHOOLLIST", schoolList);
		resultMap.put("PROVINCELIST", provinceList);
		return resultMap;
	}

	private Map stuusers_MiddleSchool_Distribution(Map paraMap) {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> schoolList = null;
		List<Map<String, String>> provinceList = null;
		
		
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String sql = "SELECT province.DQMC AS name,IFNULL(bb.SchoolSum, 0) AS value,IFNULL(school1.ZXMC1,0) AS ZXMC1, "
				+ "IFNULL(school1.stusum1,0) AS stusum1,IFNULL(school1.rank1,0) AS rank1, "
				+ "IFNULL(school2.ZXMC2,0) AS ZXMC2,IFNULL(school2.stusum2,0) AS stusum2, "
				+ "IFNULL(school2.rank2,0) AS rank2,IFNULL(school3.ZXMC3,0) AS ZXMC3, "
				+ "IFNULL(school3.stusum3,0) AS stusum3,IFNULL(school3.rank3,0) AS rank3 "
				+ "FROM province LEFT JOIN (SELECT province.DQMC,COUNT(ZXMC) AS SchoolSum FROM "
				+ "(SELECT province.DQMC,ZXMC FROM enter_student,province WHERE enter_student.DQDM = province.DQDM "
				+ "GROUP BY ZXMC,province.DQMC ) AS aa,province WHERE province.DQMC = aa.DQMC "
				+ "GROUP BY province.DQMC) AS bb ON province.DQMC = bb.DQMC LEFT JOIN "
				+ "(SELECT result.DQMC,result.ZXMC as ZXMC1,result.stusum AS stusum1,result.rank AS rank1 "
				+ "FROM (SELECT temp.DQMC,temp.ZXMC,temp.stusum,@rownum:=@rownum+1 , if(@MC=temp.DQMC,@rank:=@rank+1,@rank:=1) as rank, "
				+ "@MC:=temp.DQMC FROM(SELECT DQMC,ZXMC,COUNT(ZXDM) AS stusum FROM enter_student, province  "
				+ "WHERE enter_student.DQDM = province.DQDM GROUP BY DQMC,ZXDM,YZBM ORDER BY DQMC,stusum DESC) AS temp,(select @rownum :=0 , @MC := null ,@rank:=0) a ) AS result "
				+ "WHERE rank=1) as school1 ON province.DQMC = school1.DQMC LEFT JOIN (SELECT result.DQMC,result.ZXMC as ZXMC2,result.stusum AS stusum2,result.rank AS rank2 "
				+ "FROM(SELECT temp.DQMC,temp.ZXMC,temp.stusum,@rownum:=@rownum+1 , if(@MC=temp.DQMC,@rank:=@rank+1,@rank:=1) as rank, "
				+ "@MC:=temp.DQMC FROM(SELECT DQMC,ZXMC,COUNT(ZXDM) AS stusum FROM enter_student,"
				+ "province WHERE enter_student.DQDM = province.DQDM GROUP BY DQMC,ZXDM,YZBM ORDER BY DQMC, "
				+ "stusum DESC) AS temp,(select @rownum :=0 , @MC := null ,@rank:=0) a ) AS result "
				+ "WHERE rank=2) AS school2 ON province.DQMC = school2.DQMC LEFT JOIN "
				+ "(SELECT result.DQMC,result.ZXMC as ZXMC3,result.stusum AS stusum3,result.rank AS rank3 "
				+ "FROM (SELECT temp.DQMC,temp.ZXMC,temp.stusum,@rownum:=@rownum+1 , "
				+ "if(@MC=temp.DQMC,@rank:=@rank+1,@rank:=1) as rank, @MC:=temp.DQMC FROM(SELECT "
				+ "DQMC,ZXMC,COUNT(ZXDM) AS stusum FROM enter_student,province WHERE enter_student.DQDM = province.DQDM "
				+ "GROUP BY DQMC,ZXDM,YZBM ORDER BY DQMC,stusum DESC) AS temp,(select @rownum :=0 , @MC := null ,@rank:=0) a ) AS result "
				+ "WHERE rank=3)AS school3 ON province.DQMC = school3.DQMC";
		System.out.println(sql);
		try {
			schoolList = dba.getMultiRecord(sql);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == schoolList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		
		System.out.println(schoolList);
		resultMap.put("SCHOOLLIST", schoolList);
		HttpSession session = request.getSession(true);
//		session.setAttribute("SCHOOLLIST", schoolList);
		
		JSONArray jsonArray = JsonPsrseUtil.toJsonArray(schoolList);
		System.out.println("----"+jsonArray);
		JSONObject json = new JSONObject();
		json.accumulate("name", "0");
		json.accumulate("value", "100");
		jsonArray.add(json);
		session.setAttribute("SCHOOLLIST", jsonArray);
		String aa = jsonArray.toString();
		String json1 = aa; 
		String t = json1.replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2"); 
		System.out.println("////"+t);
		session.setAttribute("SCHOOLLIST1", t);
	
		return resultMap;
	}
	
	
	
	
	
//------------------------以后删除----------------------------------------------	
	
	
	
	
//	public Map stuusers_selectMajor(Map paraMap) {
//	HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
//
//	Map<String, Object> resultMap = new HashMap<String, Object>();
//
//	List<Map<String, String>> schoolList = null;
//	List<Map<String, String>> provinceList = null;
//
//	DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//
//	String sql = "SELECT enter_student.DQDM,DQMC,ZXDM,ZXMC,COUNT(ZXDM) AS stusum,YZBM "
//			+ "FROM enter_student,province WHERE enter_student.DQDM=province.DQDM "
//			+ "GROUP BY DQMC,ZXDM,YZBM ORDER BY stusum DESC ";
//	String sql1 = "SELECT enter_student.DQDM,DQMC FROM enter_student,province "
//			+ "WHERE enter_student.DQDM=province.DQDM GROUP BY DQDM ";
//	try {
//		schoolList = dba.getMultiRecord(sql);
//		provinceList = dba.getMultiRecord(sql1);
//		dba.close();
//	} catch (DataManipulationException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (DBConnectionException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	if (null == schoolList) {
//		resultMap.put("RESULT", "fail");
//	} else {
//		resultMap.put("RESULT", "success");
//	}
//	resultMap.put("SCHOOLLIST", schoolList);
//	resultMap.put("PROVINCELIST", provinceList);
//	return resultMap;
//
//}

//public Map stuusers_selectFamily(Map paraMap) {
//	HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
//	Map<String, Object> resultMap = new HashMap<String, Object>();
//	Map map = (Map) paraMap.get("CONDITIONMAP");
//
//	List<Map<String, String>> userList = null;
//	List<Map<String, String>> familyList = null;
//	List<Map<String, String>> majorList = null;
//
//	DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//
//	String sql = "Select familyName from family";
//	String sql1 = "Select majorName from major";
//	String sql2 = "select SUM(familySum) as familySum,majorName,familyName from "
//			+ "(select * from (Select familyName,majorName,majorSum,familySum from ("
//			+ "Select familyName,majorName,COUNT(majorName) as majorSum,COUNT(stuFamily) as familySum "
//			+ "From major,student_year,family " + "Where familyID = stuFamily and majorID = stuMajor "
//			+ "Group by familyName,majorName "
//			+ "Order by majorSum DESC ) TEMP Group by majorName,familyName Order by majorSum DESC) bbb ORDER BY majorSum desc) "
//			+ "temp GROUP BY familyName " + "ORDER BY familySum desc ";
//
//	try {
//		familyList = dba.getMultiRecord(sql);
//		majorList = dba.getMultiRecord(sql1);
//		userList = dba.getMultiRecord(sql2);
//		dba.close();
//	} catch (DataManipulationException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (DBConnectionException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	if (null == familyList) {
//		resultMap.put("RESULT", "fail");
//	} else {
//		resultMap.put("RESULT", "success");
//	}
//
//	resultMap.put("FAMILYLIST", familyList);
//	resultMap.put("MAJORLIST", majorList);
//	resultMap.put("USERLIST", userList);
//	return resultMap;
//}

//public Map stuusers_selectByFamily(Map paraMap) {
//	HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
//	Map<String, Object> resultMap = new HashMap<String, Object>();
//	Map map = (Map) paraMap.get("CONDITIONMAP");
//
//	List<Map<String, String>> userList = null;
//	List<Map<String, String>> familyList = null;
//
//	DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
//
//	String sql = null;
//	String sql2 = "Select familyName from family";
//
//	String family = (String) map.get("FAMILY");
//	String choose = (String) map.get("CHOOSE");
//
//	if (family.equals("all")) {
//		sql = "select SUM(familySum) as familySum,majorName,familyName from "
//				+ "(select * from (Select familyName,majorName,majorSum,familySum from ("
//				+ "Select familyName,majorName,COUNT(majorName) as majorSum,COUNT(stuFamily) as familySum "
//				+ "From major,student_year,family " + "Where familyID = stuFamily and majorID = stuMajor "
//				+ "Group by familyName,majorName "
//				+ "Order by majorSum DESC ) TEMP Group by majorName,familyName Order by majorSum DESC) bbb ORDER BY majorSum desc) "
//				+ "temp GROUP BY familyName " + "ORDER BY familySum desc ";
//	} else if (family != "0") {
//		sql = "select SUM(familySum) as familySum,majorName,familyName from "
//				+ "(select * from (Select familyName,majorName,majorSum,familySum from ("
//				+ "Select familyName,majorName,COUNT(majorName) as majorSum,COUNT(stuFamily) as familySum "
//				+ "From major,student_year,family " + "Where familyID = stuFamily and majorID = stuMajor "
//				+ "Group by familyName,majorName "
//				+ "Order by majorSum DESC ) TEMP Group by majorName,familyName Order by majorSum DESC) bbb ORDER BY majorSum desc) "
//				+ " temp Where familyName = '" + family + "' GROUP BY familyName " + "ORDER BY majorSum desc";
//	}
//	try {
//		familyList = dba.getMultiRecord(sql2);
//		userList = dba.getMultiRecord(sql);
//	} catch (DataManipulationException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	if (null == userList) {
//		resultMap.put("RESULT", "fail");
//	} else {
//		resultMap.put("RESULT", "success");
//	}
//
//	resultMap.put("FAMILYLIST", familyList);
//	resultMap.put("USERLIST", userList);
//	System.out.println(familyList + "---------");
//	return resultMap;
//}

}
