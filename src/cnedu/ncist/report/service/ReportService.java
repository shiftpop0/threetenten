package cnedu.ncist.report.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class ReportService extends AbstractService {

	public Map doServiceProcess(Map map) {
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

	public Map<String, Object> report_selectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> reportList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String inputYear = (String)map.get("INPUT_YEAR");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sqltime = "(select inputStudentCode as userCode, format(sum(totalTime)/60/7,2) as workDays ";
	   	sqltime = sqltime + "from userworktime ";
	   	sqltime = sqltime + "where inputDate like '" + inputYear + "%' ";
	   	sqltime = sqltime + " and inputStudentCode in ";
	   	sqltime = sqltime + "(select userCode from authgroupmember where groupCode in ";
	   	sqltime = sqltime + "(select groupCode from authgroupmember where userCode = '" + uCode + "'))";
	   	sqltime = sqltime + " group by inputStudentCode ";
	   	sqltime = sqltime + ") a ";
	   	
	   	String sqllect = "(select ud.userCode, count(userCode) as lectTimes ";
	   	sqllect = sqllect + "from userlecturedetail ud, userlecture ";
	   	sqllect = sqllect + "where ud.parentOrder = userLecture.theOrder and userlecture.type = '0' ";
	   	sqllect = sqllect + " and ud.inputTime like '" + inputYear + "%' ";
	   	sqllect = sqllect + " and userCode in ";
	   	sqllect = sqllect + "(select userCode from authgroupmember where groupCode in ";
	   	sqllect = sqllect + "(select groupCode from authgroupmember where userCode = '" + uCode + "'))";
	   	sqllect = sqllect + " group by userCode ";
	   	sqllect = sqllect + ") b ";
	   	
	   	String sqlpaper = "(select ud.userCode, count(userCode) as paperTimes ";
	   	sqlpaper = sqlpaper + "from userlecturedetail ud, userlecture ";
	   	sqlpaper = sqlpaper + "where ud.parentOrder = userLecture.theOrder and userlecture.type = '1' ";
	   	sqlpaper = sqlpaper + " and ud.inputTime like '" + inputYear + "%' ";
	   	sqlpaper = sqlpaper + " and userCode in ";
	   	sqlpaper = sqlpaper + "(select userCode from authgroupmember where groupCode in ";
	   	sqlpaper = sqlpaper + "(select groupCode from authgroupmember where userCode = '" + uCode + "'))";
	   	sqlpaper = sqlpaper + " group by userCode ";
	   	sqlpaper = sqlpaper + ") c ";
	   	
	   	String sqlbook = "(select ud.userCode, count(userCode) as bookTimes ";
	   	sqlbook = sqlbook + "from userlecturedetail ud, userlecture ";
	   	sqlbook = sqlbook + "where ud.parentOrder = userLecture.theOrder and userlecture.type = '2' ";
	   	sqlbook = sqlbook + " and ud.inputTime like '" + inputYear + "%' ";
	   	sqlbook = sqlbook + " and userCode in ";
	   	sqlbook = sqlbook + "(select userCode from authgroupmember where groupCode in ";
	   	sqlbook = sqlbook + "(select groupCode from authgroupmember where userCode = '" + uCode + "'))";
	   	sqlbook = sqlbook + " group by userCode ";
	   	sqlbook = sqlbook + ") d ";
	   	
	   	String sql = "select a.userCode, u.userName, a.workDays, b.lectTimes, c.paperTimes, d.bookTimes " ;
	   	sql = sql + "from " + sqltime ;
	   	sql = sql + " left join authuser u on a.userCode = u.userCode ";
	   	sql = sql + " left join " + sqllect + " on a.userCode = b.userCode ";
	   	sql = sql + " left join " + sqlpaper + " on a.userCode = c.userCode ";
	   	sql = sql + " left join " + sqlbook + " on a.userCode = d.userCode";
System.out.println(sql);

		try{
			reportList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get annual total message access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == reportList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("REPTINFO", reportList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

}
