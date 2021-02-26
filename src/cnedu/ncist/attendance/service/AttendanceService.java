package cnedu.ncist.attendance.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class AttendanceService extends AbstractService {

	public Map doServiceProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("basictime_SelectInit".equals(actionType)){
			return basictime_selectInit(map);
		}
		else if("basictime_Set".equals(actionType)){
			return authuser_set(map);
		}
		else if("basictime_DetailInit".equals(actionType)){
			return basictime_DetailInit(map);
		}
		else if("worktime_SelectInit".equals(actionType)){
			return worktime_selectInit(map);
		}
		else if("worktime_AddInit".equals(actionType)){
			return worktime_addInit(map);
		}
		else if("worktime_Add".equals(actionType)){
			return worktime_add(map);
		}
		else if("worktime_Delete".equals(actionType)){
			return worktime_delete(map);
		}
		else if("worktime_InspectInit".equals(actionType)){
			return worktime_inspectInit(map);
		}
		else if("worktime_Inspect".equals(actionType)){
			return worktime_inspect(map);
		}
		else if("worktime_ViewInit".equals(actionType)){
			return worktime_viewInit(map);
		}
		else if("worktime_SumInit".equals(actionType)){
			return worktime_sumInit(map);
		}
		else if("worktime_SumDetailInit".equals(actionType)){
			return worktime_sumDetailInit(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, Object> basictime_selectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> userList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select a.userCode, a.deptName, a.userName, b.groupCode " +
	   			"from authuser a, authgroupmember b, authuserrole c " ;
	   	sql = sql + "where a.userCode = b.userCode ";
	   	sql = sql + "and a.userCode = c.userCode and c.roleCode = '222' ";
	   	sql = sql + "and b.groupCode in ";
	   	sql = sql + "(select groupCode from authgroupmember where userCode = '" +
	   			uCode + "')";

		try{
			userList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == userList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("USERINFO", userList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authuser_set(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] userIdList = (String[]) conditionMap.get("USER_CODES");
		
		//获取用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		String startTime = (String)conditionMap.get("START_TIME");
		String endTime = (String)conditionMap.get("END_TIME");
		String basicTime = (String)conditionMap.get("BASIC_TIME");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		String sqls [] = new String[userIdList.length +1];
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");

		for(int i=0; i<userIdList.length; i++){
		   	String sql = "insert into userbasictime " +
		   			"(startDate, endDate, timeLength, studentCode, setterCode, state, createTime, memo) " +
		   			"values('" + startTime + "', '" + endTime + "'," +
					basicTime + ",'" + userIdList[i] + "', '" + uCode + "', '0','" + dateStr + "',"+
					"''" +	")";
		   	sqls[i] = sql;
		}
		try{
			dba.excuteBatchSql(sqls);
			dba.close();
			
			resultMap.put("RESULT", "success");
		}
		catch(DataManipulationException e){
			System.out.println("update user role mapping error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}

		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> basictime_DetailInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> timeList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select a.startDate, a.endDate, a.timeLength, b.userName studentName, c.userName techerName, a.state, a.createTime " +
	   			"from userbasictime a, authuser b, authuser c " ;
	   	sql = sql + "where a.studentCode = b.userCode and a.setterCode = c.userCode " ;
	   	sql = sql + " order by startDate desc";
	   	
	   	String createTime = (String)map.get("CREATE_TIME");
	   	if((createTime != null) && (!"".equals(createTime))){
	   		sql = sql + " and a.createTime like '%" + createTime + "%'";
	   	}
	   	
	   	String studentName = (String)map.get("STUDENT_NAME");
	   	if((studentName != null) && (!"".equals(studentName))){
	   		sql = sql + " and b.userName like '%" + studentName + "%'";
	   	}
	   	
	   	String teacherName = (String)map.get("TEACHER_NAME");
	   	if((teacherName != null) && (!"".equals(teacherName))){
	   		sql = sql + " and c.userName like '%" + teacherName + "%'";
	   	}
	   	
	   	sql = sql + " and a.setterCode in ";
	   	sql = sql + "(select a.userCode from authgroupmember a, authgroupmember b " +
	   			" where a.groupCode = b.groupCode and b.userCode = '" +
	   			uCode + "') ";
	   	sql = sql + " order by a.createTime desc ";
//System.out.println(sql);
	   	try{
			timeList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == timeList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("TIMEINFO", timeList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> worktime_selectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> timeList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select theOrder, inputDate, morningBeginTime, morningEndTime " +
	   			" ,noonBeginTime, noonEndTime, nightBeginTime, nightEndTime, status " +
	   			"from userworktime " ;
	   	sql = sql + "where inputStudentCode = '" + uCode + "' ";
	   	
	   	String startTime = (String)map.get("START_TIME");
	   	String endTime = (String)map.get("END_TIME");
	   	if((null != startTime)&&(!"".equals(startTime))&&(null != endTime)&& (!"".equals(endTime))){
	   		sql = sql + " and inputDate >= '" + startTime + "' ";
	   		sql = sql + " and inputDate <= '" + endTime + "' ";
	   	}
	   	
	   	String status = (String)map.get("TIME_STATUS");
	   	if(!"".equals(status)){
	   		sql = sql + " and status = '" + status + "' ";
	   	}
	   	
	   	sql = sql + " order by inputDate desc";
//System.out.println(sql);

		try{
			timeList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == timeList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("TIMEINFO", timeList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> worktime_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> timeList = null;
		Map<String, String> timeInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String orderCode = (String)conditionMap.get("TIME_CODE");
		if("update".equals(flag)){
			//获取修改的用户信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select theOrder" +
				", inputDate, morningBeginTime, morningEndTime" +
				", noonBeginTime, noonEndTime, nightBeginTime" +
				", nightEndTime, status, memo " +
				"from userworktime " +
				"where theOrder = " ;
		   	sql = sql + orderCode;
			sql = sql + "" ;
//System.out.println(sql);
			try{
				timeList = dba.getMultiRecord(sql);
				dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get user info access database error!");
				e.printStackTrace();
			}
			catch(DBConnectionException e){
				System.out.println("database close error!");
				e.printStackTrace();
			}
			
			if((null == timeList)||(timeList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				timeInfo = timeList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("TIMEINFO", timeInfo);
		
		return resultMap;
	}

	public Map<String, Object> worktime_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = (String)map.get("FLAG");
		String theOrder = (String)map.get("TIME_CODE");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	if("add".equals(flag)){
	   		
			//获取用户编号
			HttpSession session = request.getSession(true);
			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
			String uCode = (String)userInfo.get("userCode");
			
		   	sql = "insert into userworktime(" +
   			"inputDate, morningBeginTime, morningEndTime, noonBeginTime, noonEndTime" +
			", nightBeginTime, nightEndTime, inputTime, inputStudentCode, status, memo" +
			") " +
			"values(" ;
		
			sql = sql + "'" + (String)map.get("INPUT_TIME") + "'";
			sql = sql + ",'" + (String)map.get("MORNING_BEGIN") + "'";
			sql = sql + ",'" + (String)map.get("MORNING_END") + "'";
			sql = sql + ",'" + (String)map.get("NOON_BEGIN") + "'";
			sql = sql + ",'" + (String)map.get("NOON_END") + "'";
			sql = sql + ",'" + (String)map.get("NIGHT_BEGIN") + "'";
			sql = sql + ",'" + (String)map.get("NIGHT_END") + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",'" + uCode + "'";
			sql = sql + ",'" + "0" + "'";
			sql = sql + ",'" + (String)map.get("TIME_MEMO") + "'";
		   	sql = sql + ")";
		   	
	   	}
	   	else{
	   		sql = "update userworktime set ";
	   		sql = sql + "inputDate = '" + (String)map.get("INPUT_TIME") + "' ";
	   		sql = sql + ",morningBeginTime = '" + (String)map.get("MORNING_BEGIN") + "' ";
	   		sql = sql + ",morningEndTime = '" + (String)map.get("MORNING_END") + "' ";
	   		sql = sql + ",noonBeginTime = '" + (String)map.get("NOON_BEGIN") + "' ";
	   		sql = sql + ",noonEndTime = '" + (String)map.get("NOON_END") + "' ";
	   		sql = sql + ",nightBeginTime = '" + (String)map.get("NIGHT_BEGIN") + "' ";
	   		sql = sql + ",nightEndTime = '" + (String)map.get("NIGHT_END") + "' ";
	   		sql = sql + ",memo = '" + (String)map.get("TIME_MEMO") + "' ";
	   		sql = sql + "where ";
	   		sql = sql + "theOrder = " + map.get("TIME_CODE") + "";
	   	}
//System.out.println(sql);
		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update user info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> worktime_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获取时间自动序号
		String timeCode = (String)conditionMap.get("TIME_CODE");
		
		//删除时间记录
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");

		String sql = "delete from userworktime " +
			"where theOrder = " ;
	   	sql = sql + timeCode;
		sql = sql + "" ;
//System.out.println(sql);
		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update user info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> worktime_inspectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> timeList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select a.theOrder, a.inputDate, a.morningBeginTime, a.morningEndTime, a.noonBeginTime, a.noonEndTime, " +
	   			"a.nightBeginTime, a.nightEndTime, a.status, b.userName " +
			"from userworktime a, authuser b " +
			"where a.inputStudentCode = b.userCode and a.status = '0' and a.inputStudentCode in " ;
	   	sql = sql + " (select userCode from authgroupmember where groupCode in(" +
	   			" select groupCode from authgroupmember where userCode like '" +
	   			uCode + "'))";
	   	sql = sql + " order by a.inputDate desc";
//System.out.println(sql);

		try{
			timeList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get user info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == timeList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("TIMEINFO", timeList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> worktime_inspect(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, String> timeInfo = null;
		List<Map<String,String>> timeList = null;
		
		String[] timeIdList = (String[]) conditionMap.get("TIME_CODE");
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		conditionMap.put("USER_CODE", uCode);
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");

		//计算各种时间
		int morningTime [] = new int[timeIdList.length];
		int noonTime [] = new int[timeIdList.length];
		int nightTime [] = new int[timeIdList.length];
		int basicTime [] = new int[timeIdList.length];
		int totalTime [] = new int[timeIdList.length];
		for(int i=0; i<timeIdList.length; i++){
			String sql = "select morningBeginTime, morningEndTime, noonBeginTime, noonEndTime" +
					", nightBeginTime, nightEndTime, inputDate, inputStudentCode " +
					" from userworktime " +
					"where theOrder = " + timeIdList[i];
			
			timeInfo = null;
			try{
				timeInfo = dba.getSingleRecord(sql);
			}
			catch(DataManipulationException e){
				System.out.println("get user info access database error!");
				e.printStackTrace();
			}
			
			morningTime[i] = 0;
			noonTime[i] = 0;
			nightTime[i] = 0;
			
			String inputDate = "";
			String studentCode = "";
			if(null != timeInfo){
				String mTime = (String)timeInfo.get("morningBeginTime");
				int mbTime = Integer.parseInt(mTime.substring(0, 2));
				mbTime = mbTime * 60 + Integer.parseInt(mTime.substring(3));

				mTime = (String)timeInfo.get("morningEndTime");
				int meTime = Integer.parseInt(mTime.substring(0, 2));
				meTime = meTime * 60 + Integer.parseInt(mTime.substring(3));
				if(meTime > mbTime){
					morningTime[i] = meTime - mbTime;
				}
				
				String nTime = (String)timeInfo.get("noonBeginTime");
				int nbTime = Integer.parseInt(nTime.substring(0, 2));
				nbTime = nbTime * 60 + Integer.parseInt(nTime.substring(3));

				nTime = (String)timeInfo.get("noonEndTime");
				int neTime = Integer.parseInt(nTime.substring(0, 2));
				neTime = neTime * 60 + Integer.parseInt(nTime.substring(3));
				if(neTime > nbTime){
					noonTime[i] = neTime - nbTime;
				}
				
				String gTime = (String)timeInfo.get("nightBeginTime");
				int gbTime = Integer.parseInt(gTime.substring(0, 2));
				gbTime = gbTime * 60 + Integer.parseInt(gTime.substring(3));

				gTime = (String)timeInfo.get("nightEndTime");
				int geTime = Integer.parseInt(gTime.substring(0, 2));
				geTime = geTime * 60 + Integer.parseInt(gTime.substring(3));
				if(geTime > gbTime){
					nightTime[i] = geTime - gbTime;
				}
				inputDate = (String)timeInfo.get("inputDate");
				studentCode = (String)timeInfo.get("inputStudentCode");
			}
			
			//计算基本时间
			basicTime[i] = 0;
			if(!"".equals(inputDate)){
				sql = "select timeLength from userbasictime " +
						"where startDate <= '" + inputDate + "' " +
						"and endDate >= '" + inputDate + "' " +
						" and studentCode = '" + studentCode + "' ";
				try{
					timeList = dba.getMultiRecord(sql);
				}
				catch(DataManipulationException e){
					System.out.println("get user info access database error!");
					e.printStackTrace();
				}
				if((null != timeList) && (timeList.size() > 0)){
					Map m = timeList.get(0);
					basicTime[i] = Integer.parseInt((String)(m.get("timeLength")));
				}
			}
			//计算总时间
			totalTime[i] = morningTime[i] + noonTime[i] + nightTime[i] + basicTime[i];
			if(totalTime[i] > 840){
				totalTime[i] = 840;
			}
		}
		
		String sqls [] = new String[timeIdList.length];
		for(int i=0; i<timeIdList.length; i++){
		   	String sql = "update userworktime set " +
		   			"morningTime = " + morningTime[i] + ", " +
		   			"noonTime = " + noonTime[i] + ", " +
		   			"nightTime = " + nightTime[i] + ", " +
		   			"basicTime = " + basicTime[i] + ", " +
		   			"totalTime = " + totalTime[i] + ", " +
		   			"inspectTime = '" + dateStr + "', " +
		   			"inspectTeacherCode = '" + uCode + "', " +
		   			"status = '1' ";
		   	sql = sql + " where theOrder = '" + timeIdList[i] + "'";
		   	sqls[i] = sql;
		}
		try{
			dba.excuteBatchSql(sqls);
			dba.close();
			
			resultMap.put("RESULT", "success");
		}
		catch(DataManipulationException e){
			System.out.println("update user role mapping error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}

		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> worktime_viewInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> timeList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String studentName = (String)map.get("STUDENT_NAME");
		String inputDate = (String)map.get("INPUT_DATE");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select b.userName, a.inputDate, a.morningTime, a.noonTime, " +
	   			" a.nightTime, a.basicTime, a.totalTime " +
	   			"from userworktime a, authuser b " ;
	   	sql = sql + "where b.userName like '%" + studentName + "%' " +
	   			" and a.inputDate like '%" + inputDate + "%' " +
	   			" and a.inputStudentCode = b.userCode and a.inputStudentCode in ";
	   	sql = sql + "(select userCode from authgroupmember where groupCode in " +
	   			"(select groupCode from authgroupmember where userCode like '" + uCode + "')" +
	   			") ";
	   	sql = sql + " order by a.inputDate desc, a.inputStudentCode";
//System.out.println(sql);

		try{
			timeList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == timeList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("TIMEINFO", timeList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> worktime_sumInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> timeList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String sumYear = (String)map.get("SUM_YEAR");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select a.inputStudentCode, b.userName, sum(a.morningTime)+sum(a.noonTime)+sum(a.nightTime) as workTime ," +
	   			"sum(a.basicTime) as bsTime" +
	   			" , sum(a.totalTime)/420 as ttTime " +
	   			"from userworktime a, authuser b " ;
	   	sql = sql + "where a.inputStudentCode = b.userCode and inputDate like '%" + sumYear + "%' " +
	   			" and inputStudentCode in ";
	   	sql = sql + "(select userCode from authgroupmember where groupCode in " +
	   			"(select groupCode from authgroupmember where userCode like '" + uCode + "')" +
	   			")  ";
	   	sql = sql + " group by inputStudentCode ";
//System.out.println(sql);

		try{
			timeList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == timeList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("TIMEINFO", timeList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}
	
	public Map<String, Object> worktime_sumDetailInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> timeList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String sumYear = (String)map.get("SUM_YEAR");
		
		String studentCode = (String)map.get("STUDENT_CODE");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select a.inputStudentCode, left(a.inputDate, 7) as inputMonth, b.userName, " +
	   			"sum(a.morningTime)+sum(a.noonTime)+sum(a.nightTime) as workTime ," +
	   			"sum(a.basicTime) as bsTime" +
	   			" , sum(a.totalTime)/420 as ttTime " +
	   			"from userworktime a, authuser b " ;
	   	sql = sql + "where a.inputStudentCode = b.userCode and inputDate like '%" + sumYear + "%' " +
	   			" and inputStudentCode like '" + studentCode + "' ";
	   	sql = sql + " group by inputStudentCode, left(a.inputDate, 7) ";
	   	sql = sql + " order by left(a.inputDate, 7) ";
//System.out.println(sql);

		try{
			timeList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == timeList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("TIMEINFO", timeList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}
	
}
