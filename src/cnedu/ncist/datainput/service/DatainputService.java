package cnedu.ncist.datainput.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cnedu.ncist.util.Calculation;
import cnedu.ncist.util.ExcelUtil;
import cnedu.ncist.util.MysqlDemo;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class DatainputService extends AbstractService {

	public Map<String, Object> doServiceProcess(Map paraMap) {
		// TODO Auto-generated method stub
		String actionType = (String) paraMap.get("ACTIONTYPE");
		// System.out.println("***********"+actionType);
		// cnedu.ncist.upload.service
		if ("fileup".equals(actionType)) {
			return fileup(paraMap);
		} else if ("delete".equals(actionType)) {
			return delete(paraMap);
		} else if ("put".equals(actionType)) {
			return put(paraMap);
		} else if ("calculate".equals(actionType)) {
			return calculate(paraMap);
		}else if ("cratetable".equals(actionType)) {
			return cratetable(paraMap);
		}else if ("Excle".equals(actionType)) {
			return excle(paraMap);
		} else {
			ActionTypeError();
		}
		return null;
	}

	

	private Map<String, Object> excle(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> yearList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String year = (String) map.get("YEAR");
		
		ExcelUtil eu = new ExcelUtil();
		String a = eu.uploadFile(request);
		
		System.out.println(a+"==-------------");

		List<Map<String, String>> majorList = eu.loadExcel(a);

		//解决报错
		if(majorList==null){
			resultMap.put("YEARLIST", yearList);
			resultMap.put("RESULT", "success");
			resultMap.put("CONDITIONMAP", map);
			return resultMap;
		}

		String sql = "Select * From year";
		String sql2 = null;
		try {
			yearList = dba.getMultiRecord(sql);
			for(int i=0;i<majorList.size();i++){
				sql2 = "Update enter_student Set BD = 1 Where YEAR1 = "+year+" and SFZH = '"+majorList.get(i).get("���֤��")+"'";
				dba.executeUpdate(sql2);
			}

		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resultMap.put("YEARLIST", yearList);
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		return resultMap;
	}



	private Map<String, Object> calculate(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> calculateList = null;

		List<Map<String, String>> yearList = null;
		List<Map<String, String>> majorList = null;
		List<Map<String, String>> majorList2 = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

//		String insert_tb_rate_Sql = "SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,COALESCE(LQ_sum.LQZY_sum,0) LQZY_sum,COALESCE(BKZY1.BKZY1_sum,0) BKZY1_sum,COALESCE(BKZY2.BKZY2_sum,0) BKZY2_sum, "
//				+ "COALESCE(BKZY3.BKZY3_sum,0) BKZY3_sum,COALESCE(BKZY4.BKZY4_sum,0) BKZY4_sum,COALESCE(BKZY5.BKZY5_sum,0) BKZY5_sum,COALESCE(BKZY6.BKZY6_sum,0) BKZY6_sum,COALESCE(BKRSALLSUM.BKZY_allsum,0) BKZY_allsum,COALESCE(BKZY1_Rate.BKZY1Rate,0) BKZY1Rate, "
//				+ "COALESCE(LQZY1_Rate.LQZY1sum,0) LQZY1sum,COALESCE(LQZY1_Rate.LQZY1_Rate,0) LQZY1_Rate, "
//				+ "(COALESCE(BKZY1.BKZY1_sum,0)*10+COALESCE(BKZY2.BKZY2_sum,0)*5+COALESCE(BKZY3.BKZY3_sum,0)*4+COALESCE(BKZY4.BKZY4_sum,0)*3+COALESCE(BKZY5.BKZY5_sum,0)*2+COALESCE(BKZY6.BKZY6_sum,0)*1) AS qz "
//				+ "FROM (SELECT * FROM sheet2 WHERE sheet2.ZYDH=sheet2.ZYDH ) AS sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS LQZY_sum "
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
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BKZY1.BKZY1_sum,CONCAT(ROUND(BKZY1.BKZY1_sum/BKZY1.allsum*100,3),'','%') AS BKZY1Rate FROM sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum,allsum FROM enter_student,major, "
//				+ "(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.ZYDH1 = major.ZYDH "
//				+ "GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1) AS BKZY1_Rate ON sheet2.ZYDH = BKZY1_Rate.ZYDH AND sheet2.YEAR1 = BKZY1_Rate.YEAR1 "
//				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQZY1.LQZY1sum,CONCAT(ROUND(LQZY1.LQZY1sum/LQZY1.allsum*100,3),'','%') AS LQZY1_Rate FROM sheet2 "
//				+ "LEFT JOIN (SELECT enter_student.LQZY,major.KLDM,major.ZYMC,majortype.KLMC,count(*) AS LQZY1sum,all_sum.allsum,enter_student.YEAR1  "
//				+ "FROM enter_student,major,majortype,(SELECT count(*) AS allsum FROM enter_student WHERE enter_student.LQFS != 12 AND enter_student.LQFS != 13 "
//				+ "AND enter_student.LQFS != 23) AS all_sum WHERE enter_student.LQZY = enter_student.ZYDH1 AND major.KLDM = majortype.KLDM "
//				+ "AND enter_student.LQZY = major.ZYDH AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 "
//				+ "AND enter_student.LQFS != 23 GROUP BY enter_student.LQZY,enter_student.YEAR1) AS LQZY1 ON sheet2.ZYDH = LQZY1.LQZY AND sheet2.YEAR1 = LQZY1.YEAR1) AS LQZY1_Rate ON sheet2.ZYDH = LQZY1_Rate.ZYDH AND sheet2.YEAR1 = LQZY1_Rate.YEAR1 ";

		String insert_tb_rate_Sql = "SELECT * ,COALESCE(CONCAT(ROUND(a.LQZY1_sum / a.LQZY_sum * 100),'','%'),0) AS LQZY1Rate, COALESCE(CONCAT(ROUND(a.BKZY1sum / a.LQZY_sum * 100),'','%'),0) AS BKZY1Rate,COALESCE (CONCAT(ROUND(a.BD_sum / a.LQZY_sum * 100,2),'','%'),0) AS BDRate,COALESCE (CONCAT(ROUND(a.TiaoJi_sum / a.LQZY_sum * 100, 2),'','%'),0) AS TiaoJiRate FROM "
				+ "(SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,COALESCE (LQ_sum.LQZY_sum, 0) LQZY_sum,COALESCE (BKZY1.BKZY1_sum, 0) BKZY1_sum,COALESCE (BKZY2.BKZY2_sum, 0) BKZY2_sum,COALESCE (BKZY3.BKZY3_sum, 0) BKZY3_sum,"
				+ "COALESCE (BKZY4.BKZY4_sum, 0) BKZY4_sum,COALESCE (BKZY5.BKZY5_sum, 0) BKZY5_sum,COALESCE (BKZY6.BKZY6_sum, 0) BKZY6_sum,COALESCE (BKRSALLSUM.BKZY_allsum, 0) BKZY_allsum,COALESCE (LQZY1_sum.LQZY1sum, 0) LQZY1_sum,"
				+ "COALESCE (BKZY1sum.BKZY1_sum, 0) BKZY1sum,(COALESCE (BKZY1.BKZY1_sum, 0) * 10 + COALESCE (BKZY2.BKZY2_sum, 0) * 5 + COALESCE (BKZY3.BKZY3_sum, 0) * 4 + COALESCE (BKZY4.BKZY4_sum, 0) * 3 + COALESCE (BKZY5.BKZY5_sum, 0) * 2 + COALESCE (BKZY6.BKZY6_sum, 0) * 1) AS qz, COALESCE (BD_sum.BD_sum, 0) BD_sum,COALESCE (Tiaoji_sum.TiaoJisum, 0) TiaoJi_sum "
				+ "FROM(SELECT * FROM sheet2 WHERE sheet2.ZYDH = sheet2.ZYDH) AS sheet2 LEFT JOIN ( SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS LQZY_sum FROM enter_student, "
				+ "major WHERE enter_student.LQZY = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS LQ_sum ON sheet2.ZYDH = LQ_sum.ZYDH AND sheet2.YEAR1 = LQ_sum.YEAR1 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum FROM enter_student,major WHERE "
				+ "enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH, YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1 LEFT JOIN ( SELECT "
				+ "major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH2) AS BKZY2_sum FROM enter_student,major WHERE enter_student.ZYDH2 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY2 ON sheet2.ZYDH = BKZY2.ZYDH "
				+ "AND sheet2.YEAR1 = BKZY2.YEAR1 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH3) AS BKZY3_sum FROM enter_student, major WHERE enter_student.ZYDH3 = major.ZYDH GROUP BY "
				+ "major.ZYDH,enter_student.YEAR1) AS BKZY3 ON sheet2.ZYDH = BKZY3.ZYDH AND sheet2.YEAR1 = BKZY3.YEAR1 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH4) AS BKZY4_sum FROM enter_student,major WHERE enter_student.ZYDH4 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY4 ON sheet2.ZYDH = BKZY4.ZYDH "
				+ "AND sheet2.YEAR1 = BKZY4.YEAR1 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH5) AS BKZY5_sum FROM enter_student,major WHERE enter_student.ZYDH5 = major.ZYDH GROUP BY major.ZYDH, enter_student.YEAR1) AS BKZY5 ON sheet2.ZYDH = BKZY5.ZYDH "
				+ "AND sheet2.YEAR1 = BKZY5.YEAR1 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.ZYDH6) AS BKZY6_sum FROM enter_student,major WHERE enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKZY6 ON sheet2.ZYDH = BKZY6.ZYDH "
				+ "AND sheet2.YEAR1 = BKZY6.YEAR1 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,enter_student.YEAR1,COUNT(enter_student.KSH) AS BKZY_allsum FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH OR enter_student.ZYDH2 = major.ZYDH OR enter_student.ZYDH3 = major.ZYDH OR enter_student.ZYDH4 = major.ZYDH "
				+ "OR enter_student.ZYDH5 = major.ZYDH OR enter_student.ZYDH6 = major.ZYDH GROUP BY major.ZYDH,enter_student.YEAR1) AS BKRSALLSUM ON sheet2.ZYDH = BKRSALLSUM.ZYDH AND sheet2.YEAR1 = BKRSALLSUM.YEAR1 "
				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,LQZY1.LQZY1sum FROM sheet2 LEFT JOIN (SELECT enter_student.LQZY,major.KLDM,major.ZYMC,majortype.KLMC,count(*) AS LQZY1sum,enter_student.YEAR1 "
				+ "FROM enter_student,major,majortype WHERE enter_student.LQZY = enter_student.ZYDH1 AND major.KLDM = majortype.KLDM AND enter_student.LQZY = major.ZYDH AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 GROUP BY enter_student.LQZY,enter_student.YEAR1) AS LQZY1 ON sheet2.ZYDH = LQZY1.LQZY "
				+ "AND sheet2.YEAR1 = LQZY1.YEAR1) AS LQZY1_sum ON sheet2.ZYDH = LQZY1_sum.ZYDH AND sheet2.YEAR1 = LQZY1_sum.YEAR1 LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BKZY1.BKZY1_sum "
				+ "FROM sheet2 LEFT JOIN (SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(enter_student.ZYDH1) AS BKZY1_sum "
				+ "FROM enter_student,major WHERE enter_student.ZYDH1 = major.ZYDH GROUP BY major.ZYDH,YEAR1) AS BKZY1 ON sheet2.ZYDH = BKZY1.ZYDH AND sheet2.YEAR1 = BKZY1.YEAR1) AS BKZY1sum ON sheet2.ZYDH = BKZY1sum.ZYDH AND sheet2.YEAR1 = BKZY1sum.YEAR1 "
				+ "LEFT JOIN (SELECT sheet2.ZYDH,sheet2.KLDM,sheet2.KLMC,sheet2.ZYMC,sheet2.YEAR1,BDsum.BD_sum FROM sheet2 LEFT JOIN ( SELECT major.ZYDH,major.ZYMC,YEAR1,COUNT(*) AS BD_sum FROM enter_student,major WHERE enter_student.LQZY = major.ZYDH "
				+ "AND enter_student.BD = 1 GROUP BY major.ZYDH,YEAR1) AS BDsum ON sheet2.ZYDH = BDsum.ZYDH AND sheet2.YEAR1 = BDsum.YEAR1) AS BD_sum ON sheet2.ZYDH = BD_sum.ZYDH AND sheet2.YEAR1 = BD_sum.YEAR1 "
				+ "LEFT JOIN(SELECT major.ZYDH,major.ZYMC,majortype.KLDM,majortype.KLMC,enter_student.YEAR1,COUNT(enter_student.LQZY) AS TiaoJisum FROM enter_student,major,majortype "
				+ "WHERE enter_student.LQZY = major.ZYDH AND LQZY != ZYDH1 AND LQZY != ZYDH2 AND LQZY != ZYDH3 AND LQZY != ZYDH4 AND LQZY != ZYDH5 AND LQZY != ZYDH6 AND enter_student.LQFS != 12 AND enter_student.LQFS != 13 AND enter_student.LQFS != 23 AND major.KLDM = majortype.KLDM "
				+ "GROUP BY major.ZYDH,enter_student.YEAR1) AS Tiaoji_sum ON sheet2.ZYDH = Tiaoji_sum.ZYDH AND sheet2.YEAR1 = Tiaoji_sum.YEAR1) AS a ";
		
		System.out.println("==================="+insert_tb_rate_Sql);
		String create_tb_rate_Sql1 = "drop table if exists tb_rate ";
		String create_tb_rate_Sql2 = "create table if not exists tb_rate (ZYDH VARCHAR(20),ZYMC VARCHAR(20),KLDM VARCHAR(20), "
				+ "KLMC VARCHAR(20),YEAR1 INT(20),LQZY_sum INT(20),BKZY1_sum INT(20), "
				+ "BKZY2_sum INT(20),BKZY3_sum INT(20),BKZY4_sum INT(20),BKZY5_sum INT(20), "
				+ "BKZY6_sum INT(20),BKZY_allsum INT(20),LQZY1_sum INT(20), "
				+ "qz INT(20),LQZY1_rate VARCHAR(20),BKZY1_rate VARCHAR(20),BD_rate VARCHAR(20),BD_sum INT(20),TiaoJi_rate VARCHAR(20),TiaoJi_sum INT(20),hot_qz INT(20))charset=utf8";
		String create_sheet2_Sql1 = "drop table if exists sheet2 ";

		String create_sheet2_Sql2 = "create table if not exists sheet2 (ZYDH VARCHAR(20),ZYMC VARCHAR(20),YEAR1 INT(20), "
				+ "KLDM VARCHAR(20),KLMC VARCHAR(20))charset=utf8";

		String year = "SELECT YEAR1 FROM enter_student GROUP BY YEAR1 ORDER BY YEAR1 DESC";
		String major = "SELECT ZYDH,ZYMC,major.KLDM,KLMC FROM major,majortype WHERE major.KLDM = majortype.KLDM ORDER BY ZYDH";
		
		String sql4 = "Select ZYMC,LQZY_sum,qz,YEAR1 From tb_rate";

		String sql5 =null;
		
		Calculation calculation = new Calculation();
		Boolean createTable = calculation.createTable(create_sheet2_Sql1, create_sheet2_Sql2);
		try {
			

			yearList = dba.getMultiRecord(year);
			majorList = dba.getMultiRecord(major);
			calculation.insert(majorList, yearList);
			//sheet2�����˲�ִ�����д���
			if (createTable == true) {
				calculateList = dba.getMultiRecord(insert_tb_rate_Sql);
				calculation.createTable(create_tb_rate_Sql1, create_tb_rate_Sql2);
				calculation.save(calculateList);
			}
			//�����ȶ�ֵ
			majorList2 = dba.getMultiRecord(sql4);
			System.out.println(majorList2);
			for(int i=0;i<majorList2.size();i++){
				int a = Integer.parseInt(majorList2.get(i).get("LQZY_sum"));
				int b = Integer.parseInt(majorList2.get(i).get("qz"));
				System.out.println(a+"         "+b);
				if(a>0){
					int c = b/a;
					sql5 = "Update tb_rate Set hot_qz = "+c+" Where ZYMC = '"+majorList2.get(i).get("ZYMC")+"' and YEAR1 = '"+majorList2.get(i).get("YEAR1")+"'";
				}else{
					int c = 0;
					sql5 = "Update tb_rate Set hot_qz = "+c+" Where ZYMC = '"+majorList2.get(i).get("ZYMC")+"' and YEAR1 = '"+majorList2.get(i).get("YEAR1")+"'";
				}
				dba.executeUpdate(sql5);
			}
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultMap.put("LIST", calculateList);
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		System.out.println(majorList);
		return resultMap;
	}

	private Map<String, Object> put(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> yearList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String year = (String) map.get("YEAR");

		String sql = "Select * From year";

		try {
			yearList = dba.getMultiRecord(sql);
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resultMap.put("YEARLIST", yearList);
		resultMap.put("YEAR", year);
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		return resultMap;
	}

	private Map<String, Object> delete(Map paraMap) {
		Map map = (Map) paraMap.get("CONDITIONMAP");

		MysqlDemo md = new MysqlDemo();
		md.truncateTable();

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		return resultMap;
	}

	public Map<String, Object> fileup(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Map<String, String>> yearList = null;

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");

		String sql = "Select * From year";

		try {
			yearList = dba.getMultiRecord(sql);
		} catch (DataManipulationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resultMap.put("YEARLIST", yearList);
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		return resultMap;
	}
	//�������ݱ�
	private Map<String, Object> cratetable(Map paraMap) {
		HttpServletRequest request = (HttpServletRequest) paraMap.get("REQUEST");
		Map map = (Map) paraMap.get("CONDITIONMAP");
		List<Map<String, String>> lq_bd1 = null;
		List<Map<String, String>> lq_bd2 = null;
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();

		DataBaseAccess dba = (DataBaseAccess) request.getAttribute("AUTHDATACONNECTION");
		
		String crateTabe_lq_bd1 = "drop table if exists lq_bd ";
		String crateTabe_lq_bd2 = "CREATE TABLE `lq_bd` ( `XYMC` varchar(30) DEFAULT NULL COMMENT 'ѧԺ����', "
				+ "`ZYMC` varchar(30) DEFAULT NULL COMMENT 'רҵ����',`LQRS` int(10) DEFAULT NULL COMMENT '¼ȡ����', "
				+ "`BDRS` int(10) DEFAULT NULL COMMENT '��������',`BDrate` varchar(30) DEFAULT NULL COMMENT '������', "
				+ "`ZY1_sum` int(20) DEFAULT NULL COMMENT '־Ը1����',`ZY2_sum` int(20) DEFAULT NULL COMMENT '־Ը2����',`TJ_sum` int(20) DEFAULT NULL COMMENT '��������', "
				+ "`TJrate` varchar(30) DEFAULT NULL COMMENT '������') ENGINE=InnoDB DEFAULT CHARSET=utf8; ";
		
		String crateTabe_lq_201 = "drop table if exists lq_20 ";
		String crateTabe_lq_202 = "CREATE TABLE `lq_20` (`ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '���',`ZYMC` varchar(30) DEFAULT NULL COMMENT 'רҵ����', "
				+ "`LQRS` int(10) DEFAULT NULL COMMENT '¼ȡ����',`BDRS` int(10) DEFAULT NULL COMMENT '��������',`BDrate` varchar(30) DEFAULT NULL COMMENT '������', "
				+ "`ZY1_sum` int(20) DEFAULT NULL COMMENT '־Ը1����',`ZY2_sum` int(20) DEFAULT NULL COMMENT '־Ը2����',`TJ_sum` int(20) DEFAULT NULL COMMENT '��������', "
				+ "`TJrate` varchar(30) DEFAULT NULL COMMENT '������', PRIMARY KEY (`ID`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		
		String crateTable_lq1 = "drop table if exists lq ";
		String crateTable_lq2 = "CREATE TABLE `lq` ( "
			  +"`XYMC` varchar(30) DEFAULT NULL COMMENT 'ѧԺ����', "
			  +"`ZYMC` varchar(30) DEFAULT NULL COMMENT 'רҵ����', "
			  +"`LQRS` int(10) DEFAULT NULL COMMENT '¼ȡ����', "
			  +"`ZY1_sum` int(20) DEFAULT NULL COMMENT '־Ը1����', "
			  +"`ZY2_sum` int(20) DEFAULT NULL COMMENT '־Ը2����', "
			  +"`TJ_sum` int(20) DEFAULT NULL COMMENT '��������', "
			  +"`TJrate` varchar(30) DEFAULT NULL COMMENT '������') " 
			  + "ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		String crateTable_newstudent1 = "drop table if exists bd_newstudent";
		String crateTable_newstudent2 = "CREATE TABLE `bd_newstudent` ( "
				+ "`TZSH` varchar(30) DEFAULT NULL COMMENT '֪ͨ���', "
  			  +"`XH` varchar(30) DEFAULT NULL COMMENT 'ѧ��', "
  			  +"`XM` varchar(30) DEFAULT NULL COMMENT '����', "
  			  +"`XB` varchar(20) DEFAULT NULL COMMENT '�Ա�', "
  			  +"`CSRQ` datetime DEFAULT NULL COMMENT '��������', "
  			  +"`MZ` varchar(30) DEFAULT NULL COMMENT '����', "
  			  +"`SYD` varchar(30) DEFAULT NULL COMMENT '��Դ��', "
  			  +"`XYMC` varchar(30) DEFAULT NULL COMMENT 'Ժϵ', "
  			  +"`ZYMC` varchar(30) DEFAULT NULL COMMENT 'רҵ', "
  			  +"`BJ` varchar(30) DEFAULT NULL COMMENT '�༶', "
  			  +"`SFZH` varchar(30) DEFAULT NULL COMMENT '���֤����', "
  			  +"`ZSXX` varchar(30) DEFAULT NULL COMMENT 'ס����Ϣ', "
  			  +"`LXDH` varchar(30) DEFAULT NULL COMMENT '��ϵ�绰', "
  			  +"`XSJF` varchar(30) DEFAULT NULL COMMENT 'ѧ�޽ɷ�', "
  			  +"`JCJF` varchar(30) DEFAULT NULL COMMENT '�̲Ľɷ�', "
  			  +"`HQJF` varchar(30) DEFAULT NULL COMMENT '���ڽɷ�', "
 			  +" `RXSX` varchar(30) DEFAULT NULL COMMENT '��ѧ����', "
  			  +"`XYBD` varchar(30) DEFAULT NULL COMMENT 'ѧԺ����', "
  			  +"`XSF` varchar(30) DEFAULT NULL COMMENT 'ѧ�޷���', "
 			  +" `WJF` varchar(30) DEFAULT NULL COMMENT '�Ծ߷�', "
 			  +"`JCF` varchar(30) DEFAULT NULL COMMENT '�̲ķ�', "
 			  +" `LSTD` varchar(30) DEFAULT NULL COMMENT '��ɫͨ��', "
  			  +"`LQJXF` varchar(30) DEFAULT NULL COMMENT '��ȡ��ѵ��', "
 			  +" `LQSSYS` varchar(30) DEFAULT NULL COMMENT '��ȡ����Կ��', "
 			  +" `LQBFYS` varchar(30) DEFAULT NULL COMMENT '��ȡ����Կ��', "
 			  +" `WBDYY` varchar(255) DEFAULT NULL COMMENT 'δ����ԭ��' "
			  +") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		
		Calculation calculation = new Calculation();
		calculation.createTable(crateTabe_lq_bd1, crateTabe_lq_bd2);
		calculation.createTable(crateTabe_lq_201, crateTabe_lq_202);
		calculation.createTable(crateTable_lq1, crateTable_lq2);
		calculation.createTable(crateTable_newstudent1, crateTable_newstudent2);
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		return resultMap;
	}

}
