package cnedu.ncist.enterstu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class EnterstuService extends AbstractService{

	public Map doServiceProcess(Map paraMap) {
		// TODO Auto-generated method stub
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("enterstu_Select1".equals(actionType)){
			return enterstu_select1(paraMap);
		}else if("enterstu_Select2".equals(actionType)){
			return enterstu_select2(paraMap);
		}else if("enterstu_Select1_1".equals(actionType)){
			return enterstu_select1_1(paraMap);
		}else if("enterstu_Select2_2".equals(actionType)){
			return enterstu_select2_2(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	private Map enterstu_select1(Map paraMap) {

		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> userList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String sql = "Select familyName from family";
		String sql1 = "Select majorName from major";
		String sql2 = "select SUM(majorSum) as majorSum,majorName,familyName from "+
		"(select * from (Select majorName,familyName,majorSum,familySum from ("+
		"Select majorName,familyName,COUNT(majorName) as majorSum,COUNT(stuFamily1) as familySum "+
		"From major,enter_student_year,family "+
		"Where majorID = stuMajor1 and familyID = stuFamily1 "+
		"Group by majorName,familyName "+
		"Order by majorSum DESC ) TEMP Group by majorName,familyName Order by familySum DESC) bbb ORDER BY majorSum desc) "+
		" temp GROUP BY majorName "+
		"ORDER BY majorSum desc";
		
		try {
			familyList = dba.getMultiRecord(sql);
			majorList = dba.getMultiRecord(sql1);
			userList = dba.getMultiRecord(sql2);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null == familyList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("USERLIST", userList);
		return resultMap;
	}
	
	public Map enterstu_select1_1(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		List<Map<String, String>> userList = null;
		List<Map<String, String>> majorList = null;
		
		String sql = null;
		String sql2 = "Select majorName from major";
		
		String major = (String) map.get("MAJOR");
		String choose = (String) map.get("CHOOSE");
		
		if(major.equals("all")){
			sql = "select SUM(majorSum) as majorSum,majorName,familyName from "+
			"(select * from (Select majorName,familyName,majorSum,familySum from ("+
			"Select majorName,familyName,COUNT(majorName) as majorSum,COUNT(stuFamily1) as familySum "+
			"From major,enter_student_year,family "+
			"Where majorID = stuMajor1 and familyID = stuFamily1 "+
			"Group by majorName,familyName "+
			"Order by majorSum DESC ) TEMP Group by majorName,familyName Order by familySum DESC) bbb ORDER BY majorSum desc) "+
			" temp GROUP BY majorName "+
			"ORDER BY majorSum desc";
		}
		else if(major!="0"){
			sql = "select SUM(majorSum) as majorSum,majorName,familyName from "+
			"(select * from (Select majorName,familyName,majorSum,familySum from ("+
			"Select majorName,familyName,COUNT(majorName) as majorSum,COUNT(stuFamily1) as familySum "+
			"From major,enter_student_year,family "+
			"Where majorID = stuMajor1 and familyID = stuFamily1 "+
			"Group by majorName,familyName "+
			"Order by majorSum DESC ) TEMP Group by majorName,familyName Order by familySum DESC) bbb ORDER BY majorSum desc) "+
			" temp Where majorName = '"+major+"' GROUP BY majorName "+
			"ORDER BY majorSum desc";
		}
		
		try {
			userList = dba.getMultiRecord(sql);
			majorList = dba.getMultiRecord(sql2);
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null == userList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("USERLIST", userList);
		resultMap.put("MAJORLIST", majorList);

		return resultMap;
	}
	
	public Map enterstu_select2(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> majorList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String sql = "Select familyName from family";
		String sql1 = "Select majorName from major";
		String sql2 = "select SUM(familySum) as familySum,majorName,familyName from "+
				"(select * from (Select familyName,majorName,majorSum,familySum from ("+
				"Select familyName,majorName,COUNT(majorName) as majorSum,COUNT(stuFamily1) as familySum "+
				"From major,enter_student_year,family "+
				"Where familyID = stuFamily1 and majorID = stuMajor1 "+
				"Group by familyName,majorName "+
				"Order by majorSum DESC ) TEMP Group by majorName,familyName Order by majorSum DESC) bbb ORDER BY majorSum desc) "+
				"temp GROUP BY familyName "+
				"ORDER BY familySum desc ";
		
		try {
			familyList = dba.getMultiRecord(sql);
			majorList = dba.getMultiRecord(sql1);
			userList = dba.getMultiRecord(sql2);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null == familyList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("USERLIST", userList);
		System.out.println(familyList);
		return resultMap;
	}
	
	public Map enterstu_select2_2 (Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");

		String sql = null;
		String sql2 = "Select familyName from family";
		
		String family = (String) map.get("FAMILY");
		String choose = (String) map.get("CHOOSE");
		
		if(family.equals("all")) {
			sql = "select SUM(familySum) as familySum,majorName,familyName from "+
					"(select * from (Select familyName,majorName,majorSum,familySum from ("+
					"Select familyName,majorName,COUNT(majorName) as majorSum,COUNT(stuFamily1) as familySum "+
					"From major,enter_student_year,family "+
					"Where familyID = stuFamily1 and majorID = stuMajor1 "+
					"Group by familyName,majorName "+
					"Order by majorSum DESC ) TEMP Group by majorName,familyName Order by majorSum DESC) bbb ORDER BY majorSum desc) "+
					"temp GROUP BY familyName "+
					"ORDER BY familySum desc ";
		}
		else if(family != "0") {
			sql = "select SUM(familySum) as familySum,majorName,familyName from "+
					"(select * from (Select familyName,majorName,majorSum,familySum from ("+
					"Select familyName,majorName,COUNT(majorName) as majorSum,COUNT(stuFamily1) as familySum "+
					"From major,enter_student_year,family "+
					"Where familyID = stuFamily1 and majorID = stuMajor1 "+
					"Group by familyName,majorName "+
					"Order by majorSum DESC ) TEMP Group by majorName,familyName Order by majorSum DESC) bbb ORDER BY majorSum desc) "+
					" temp Where familyName = '"+family+"' GROUP BY familyName "+
					"ORDER BY majorSum desc";
		}
		try {
			familyList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql);
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null == userList) {
			resultMap.put("RESULT", "fail");
		}
		else {
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("USERLIST", userList);
		//System.out.println(familyList+"---------");
		return resultMap;
	}
	
	public Map enterstu_selectByFamily1 (Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		List<Map<String, String>> userList = null;
		List<Map<String, String>> familyList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");

		String sql = null;
		String sql2 = "Select familyName from family";
		
		String family = (String) map.get("FAMILY");
		String choose = (String) map.get("CHOOSE");
		
		if(family.equals("all")) {
			sql = "Select familyName,COUNT(familyName) as sum,AVG(stuScoure) as avgScore " +
			"From family,enter_student_year " +
			"Where familyID = stuFamily1 " +
			"Group by stuFamily1 Order by sum";	
		}
		else if(family != "0") {
			sql = "Select familyName,COUNT(familyName) as sum,AVG(stuScoure) as avgScore " +
			"From family,enter_student_year " +
			"Where familyID = stuFamily1 and familyName = '" +family+ "' "+
			"Group by stuFamily1 Order by sum";	
		}
		try {
			familyList = dba.getMultiRecord(sql2);
			userList = dba.getMultiRecord(sql);
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(null == userList) {
			resultMap.put("RESULT", "fail");
		}
		else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("USERLIST", userList);
		resultMap.put("FAMILYLIST", familyList);
		
		return resultMap;
	}
}
