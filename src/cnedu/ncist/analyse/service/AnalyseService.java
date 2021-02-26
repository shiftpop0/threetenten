package cnedu.ncist.analyse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import cnedu.ncist.util.JsonPsrseUtil;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class AnalyseService extends AbstractService{

	public Map doServiceProcess(Map map) {
		String actionType = (String)map.get("ACTIONTYPE");
		if("analyse_School".equals(actionType)){
			return analyse_School(map);
		}else if("analyse_School1".equals(actionType)){
			return analyse_School1(map);
		}else if("analyse_School2".equals(actionType)){
			return analyse_School2(map);
		}else if("analyse_School3".equals(actionType)){
			return analyse_School3(map);
		}else if("analyse_School4".equals(actionType)){
			return analyse_School4(map);
		}
		return null;
	}

	private Map analyse_School4(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> majorList1 = null;
		List<Map<String, String>> majorList2 = null;
		List<Map<String, String>> typeList = null;
		
		String major = (String) map.get("MAJOR");
		String flag = (String) map.get("FLAG");
		
		String sql = "Select ZYDH,ZYMC,YEAR1,hot_qz From tb_rate Group by YEAR1";
		String sql2 = "SELECT ZYMC,ZYDH,KLDM FROM major";
		String sql3 = "SELECT KLMC,major.KLDM FROM major,majortype WHERE majortype.KLDM = major.KLDM "
				+ "AND major.KLDM !='L' AND major.KLDM !='4' GROUP BY KLMC";
		String sql4 = "Select ZYMC,LQZY_sum,qz,YEAR1 From tb_rate";
		if(flag == null){
			flag = "all";
		}
		
		if(flag.equals("select")){
			sql = "Select ZYDH,ZYMC,YEAR1,hot_qz From tb_rate Where ZYMC = '"+major+"' Group by YEAR1";
		}
		String sql5 = null;
		try {
			//计算热度值
//			majorList2 = dba.getMultiRecord(sql4);
//			System.out.println(majorList2);
//			for(int i=0;i<majorList2.size();i++){
//				int a = Integer.parseInt(majorList2.get(i).get("LQZY_sum"));
//				int b = Integer.parseInt(majorList2.get(i).get("qz"));
//				System.out.println(a+"         "+b);
//				if(a>0){
//					int c = b/a;
//					sql5 = "Update tb_rate Set hot_qz = "+c+" Where ZYMC = '"+majorList2.get(i).get("ZYMC")+"' and YEAR1 = '"+majorList2.get(i).get("YEAR1")+"'";
//				}else{
//					int c = 0;
//					sql5 = "Update tb_rate Set hot_qz = "+c+" Where ZYMC = '"+majorList2.get(i).get("ZYMC")+"' and YEAR1 = '"+majorList2.get(i).get("YEAR1")+"'";
//				}
//				dba.executeUpdate(sql5);
//			}
			
			majorList = dba.getMultiRecord(sql);
			
			System.out.println(sql);
			System.out.println(majorList);
			majorList1 = dba.getMultiRecord(sql2);
			typeList = dba.getMultiRecord(sql3);
			
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == majorList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		JSONArray jsonMajor = JsonPsrseUtil.toJsonArray(majorList1);
		JSONArray jsonMajorType = JsonPsrseUtil.toJsonArray(typeList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("MAJORLIST1",jsonMajor);
		resultMap.put("TYPELIST", jsonMajorType);

		return resultMap;
	}

	private Map analyse_School3(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> majorList1 = null;
		List<Map<String, String>> majorList2 = null;
		List<Map<String, String>> majorList3 = null;
		List<Map<String, String>> majorList4 = null;
		List<Map<String, String>> majorList5 = null;
		List<Map<String, String>> majorList6 = null;
		List<Map<String, String>> yearList = null;
		
		String year = (String) map.get("YEAR");
		String flag = (String) map.get("FLAG");
		
		
		if(flag == null){
			flag = "all";
		}
		
		String sql = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '华北' and "+"YEAR1 = 2018 "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql2 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '华南' and "+"YEAR1 = 2018 "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql3 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '华东' and "+"YEAR1 = 2018 "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql4 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '西南' and "+"YEAR1 = 2018 "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql5 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '西北' and "+"YEAR1 = 2018 "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql6 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '东北' and "+"YEAR1 = 2018 "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql7 = "Select * From year";
		String sql8 = "Select ZYMC,ZYDH From major ORDER by ZYDH";
		if(flag.equals("select")){
			sql = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '华北' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql2 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '华南' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql3 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '华东' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql4 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '西南' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql5 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '西北' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql6 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.FWMC1 = '东北' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
		}
		try {
			majorList = dba.getMultiRecord(sql);
			majorList1 = dba.getMultiRecord(sql2);
			majorList2 = dba.getMultiRecord(sql3);
			majorList3 = dba.getMultiRecord(sql4);
			majorList4 = dba.getMultiRecord(sql5);
			majorList5 = dba.getMultiRecord(sql6);
			majorList6 = dba.getMultiRecord(sql8);
			yearList = dba.getMultiRecord(sql7);
				for(int i=0;i<majorList6.size();i++){
					for(int j=0;j<majorList.size();j++){
						if(majorList.get(j).get("ZYMC").equals(majorList6.get(i).get("ZYMC"))){
							majorList6.get(i).put("sum1", majorList.get(j).get("sum"));
							break;
						}else{
							majorList6.get(i).put("sum1", "0");
						}
					}
				}
				for(int i=0;i<majorList6.size();i++){
					for(int j=0;j<majorList1.size();j++){
						if(majorList1.get(j).get("ZYMC").equals(majorList6.get(i).get("ZYMC"))){
							majorList6.get(i).put("sum2", majorList1.get(j).get("sum"));
							break;
						}else{
							majorList6.get(i).put("sum2", "0");
						}
					}
				}
				for(int i=0;i<majorList6.size();i++){
					for(int j=0;j<majorList2.size();j++){
						if(majorList2.get(j).get("ZYMC").equals(majorList6.get(i).get("ZYMC"))){
							majorList6.get(i).put("sum3", majorList2.get(j).get("sum"));
							break;
						}else{
							majorList6.get(i).put("sum3", "0");
						}
					}
				}
				for(int i=0;i<majorList6.size();i++){
					for(int j=0;j<majorList3.size();j++){
						if(majorList3.get(j).get("ZYMC").equals(majorList6.get(i).get("ZYMC"))){
							majorList6.get(i).put("sum4", majorList3.get(j).get("sum"));
							break;
						}else{
							majorList6.get(i).put("sum4", "0");
						}
					}
				}
				for(int i=0;i<majorList6.size();i++){
					for(int j=0;j<majorList4.size();j++){
						if(majorList4.get(j).get("ZYMC").equals(majorList6.get(i).get("ZYMC"))){
							majorList6.get(i).put("sum5", majorList4.get(j).get("sum"));
							break;
						}else{
							majorList6.get(i).put("sum5", "0");
						}
					}
				}
				for(int i=0;i<majorList6.size();i++){
					for(int j=0;j<majorList5.size();j++){
						if(majorList5.get(j).get("ZYMC").equals(majorList6.get(i).get("ZYMC"))){
							majorList6.get(i).put("sum6", majorList5.get(j).get("sum"));
							break;
						}else{
							majorList6.get(i).put("sum6", "0");
						}
					}
				}
				
			
			System.out.println(majorList6);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == majorList6) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("MAJORLIST1", majorList1);
		resultMap.put("MAJORLIST2", majorList2);
		resultMap.put("MAJORLIST3", majorList3);
		resultMap.put("MAJORLIST4", majorList4);
		resultMap.put("MAJORLIST5", majorList5);
		resultMap.put("MAJORLIST6", majorList6);
		resultMap.put("YEARLIST", yearList);
		
		return resultMap;
	}

	private Map analyse_School2(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> schoolList = null;
		List<Map<String, String>> schoolList1 = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> yearList = null;
		
		String flag = (String) map.get("FLAG");
		String family = (String) map.get("FAMILY");
		String family1 = (String) map.get("FAMILY1");
		String year = (String) map.get("YEAR");
		String sql = null;
		String sql1 = null;
		
		System.out.println(family);
		System.out.println(family1);
		
		if(flag == null){
			flag = "all";
		}
		
		if(flag.equals("select")){
		
			sql = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.DQDM = '"+family+"' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql1 = "Select Count(ZYDH1) AS sum,ZYMC,DQMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and province.DQDM = '"+family1+"' and "+"YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
		}
		String sql2 = "Select DQDM,DQMC From province";
		String sql3 = "Select ZYMC,ZYDH From major ORDER by ZYDH";
		String sql4 = "Select * From year";
		try {
			familyList = dba.getMultiRecord(sql2);
			majorList = dba.getMultiRecord(sql3);
			yearList = dba.getMultiRecord(sql4);
			if(flag.equals("select")){
				schoolList = dba.getMultiRecord(sql1);
				schoolList1 = dba.getMultiRecord(sql);
			
				for(int i=0;i<majorList.size();i++){
					for(int j=0;j<schoolList.size();j++){
						if(schoolList.get(j).get("ZYMC").equals(majorList.get(i).get("ZYMC"))){
							majorList.get(i).put("sum1", schoolList.get(j).get("sum"));
							break;
						}else{
							majorList.get(i).put("sum1", "0");
						}
					}
				}
				
				for(int i=0;i<majorList.size();i++){
					for(int j=0;j<schoolList1.size();j++){
						if(schoolList1.get(j).get("ZYMC").equals(majorList.get(i).get("ZYMC"))){
							majorList.get(i).put("sum2", schoolList1.get(j).get("sum"));
							break;
						}else{
							majorList.get(i).put("sum2", "0");
						}
					}
				}
			}
			
			System.out.println(majorList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == familyList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("SCHOOLLIST", schoolList);
		resultMap.put("SCHOOLLIST1", schoolList1);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("YEARLIST", yearList);
		return resultMap;
	}

	private Map analyse_School1(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> yearList = null;
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> schoolList = null;
		List<Map<String, String>> majorList = null;
		
		String flag = (String) map.get("FLAG");
		String year = (String) map.get("YEAR");
		

		
		String sql = "Select Count(ZYDH1) as sum,XBMC,ZYMC "+
					" From enter_student,major,gender "+
					" Where enter_student.XBDM = gender.XBDM and ZYDH1 = ZYDH and XBMC = '男' "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql1 = "Select Count(ZYDH1) as sum,XBMC,ZYMC "+
						" From enter_student,major,gender "+
						" Where enter_student.XBDM = gender.XBDM and ZYDH1 = ZYDH and XBMC = '女' "+
						" Group by ZYMC "+
						" Order by sum desc";
		
		if(flag == null){
			flag = "all";
		}
		if(flag.equals("select")){
			sql = "Select Count(ZYDH1) as sum,XBMC,ZYMC "+
								" From enter_student,major,gender "+
								" Where enter_student.XBDM = gender.XBDM and ZYDH1 = ZYDH and XBMC = '男' and YEAR1 = "+year+" "+
								" Group by ZYMC "+
								" Order by sum desc";
			sql1 = "Select Count(ZYDH1) as sum,XBMC,ZYMC "+
								" From enter_student,major,gender "+
								" Where enter_student.XBDM = gender.XBDM and ZYDH1 = ZYDH and XBMC = '女' and YEAR1 = "+year+" "+
								" Group by ZYMC "+
								" Order by sum desc";
		}
		String sql2 = "Select ZYMC,ZYDH From major ORDER by ZYDH";
		String sql3 = "Select * From year";
		try {
			familyList = dba.getMultiRecord(sql);
			schoolList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			yearList = dba.getMultiRecord(sql3);
			
			for(int i=0;i<majorList.size();i++){
				for(int j=0;j<schoolList.size();j++){
					if(schoolList.get(j).get("ZYMC").equals(majorList.get(i).get("ZYMC"))){
						majorList.get(i).put("sum1", schoolList.get(j).get("sum"));
						break;
					}else{
						majorList.get(i).put("sum1", "0");
					}
				}
			}
			
			for(int i=0;i<majorList.size();i++){
				for(int j=0;j<familyList.size();j++){
					if(familyList.get(j).get("ZYMC").equals(majorList.get(i).get("ZYMC"))){
						majorList.get(i).put("sum2", familyList.get(j).get("sum"));
						break;
					}else{
						majorList.get(i).put("sum2", "0");
					}
				}
			}
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == familyList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}

		resultMap.put("SCHOOLLIST", schoolList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJORLIST", majorList);
		resultMap.put("YEARLIST", yearList);
		return resultMap;
	}

	private Map analyse_School(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> familyList = null;
		List<Map<String, String>> schoolList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> yearList = null;
		
		String flag = (String) map.get("FLAG");
		String year = (String) map.get("YEAR");
		
		
		if(flag == null){
			flag = "all";
		}
		String sql = "Select Count(ZYDH1) AS sum,ZYMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and FWMC = '南' "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql1 = "Select Count(ZYDH1) AS sum,ZYMC From enter_student,major,province "+
					" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and FWMC = '北' "+
					" Group by ZYMC "+
					" Order by sum desc";
		String sql2 = "Select ZYMC,ZYDH From major ORDER by ZYDH";
		String sql3 = "Select * From year";
		
		if(flag.equals("select")){
			sql = "Select Count(ZYDH1) AS sum,ZYMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and FWMC = '南' and YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
			sql1 = "Select Count(ZYDH1) AS sum,ZYMC From enter_student,major,province "+
						" Where ZYDH1 = major.ZYDH and enter_student.DQDM = province.DQDM and FWMC = '北' and YEAR1 = "+year+" "+
						" Group by ZYMC "+
						" Order by sum desc";
		}
		try {
			yearList = dba.getMultiRecord(sql3);
			familyList = dba.getMultiRecord(sql);
			schoolList = dba.getMultiRecord(sql1);
			majorList = dba.getMultiRecord(sql2);
			for(int i=0;i<majorList.size();i++){
				for(int j=0;j<schoolList.size();j++){
					if(schoolList.get(j).get("ZYMC").equals(majorList.get(i).get("ZYMC"))){
						majorList.get(i).put("sum1", schoolList.get(j).get("sum"));
						break;
					}else{
						majorList.get(i).put("sum1", "0");
					}
				}
			}
			
			for(int i=0;i<majorList.size();i++){
				for(int j=0;j<familyList.size();j++){
					if(familyList.get(j).get("ZYMC").equals(majorList.get(i).get("ZYMC"))){
						majorList.get(i).put("sum2", familyList.get(j).get("sum"));
						break;
					}else{
						majorList.get(i).put("sum2", "0");
					}
				}
			}
//			System.out.println(userList);
			dba.close();
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == familyList) {
			resultMap.put("RESULT", "fail");
		} else {
			resultMap.put("RESULT", "success");
		}
		resultMap.put("SCHOOLLIST", schoolList);
		resultMap.put("FAMILYLIST", familyList);
		resultMap.put("MAJORLIST",majorList);
		resultMap.put("YEARLIST", yearList);
		return resultMap;
	}

}
