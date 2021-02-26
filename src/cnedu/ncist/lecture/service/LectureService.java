package cnedu.ncist.lecture.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class LectureService extends AbstractService {

	public Map doServiceProcess(Map map) {
		// TODO Auto-generated method stub
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("lecture_SelectInit".equals(actionType)){
			return lecture_selectInit(map);
		}
		else if("lecture_AddInit".equals(actionType)){
			return lecture_addInit(map);
		}
		else if("lecture_Add".equals(actionType)){
			return lecture_add(map);
		}
		else if("lecture_Delete".equals(actionType)){
			return lecture_delete(map);
		}
		else if("lecture_Detail".equals(actionType)){
			return lecture_detail(map);
		}
		else if("lecture_DetailAdd".equals(actionType)){
			return lecture_detailAdd(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, Object> lecture_selectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> lectList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String startTime = (String)map.get("START_TIME");
		String endTime = (String)map.get("END_TIME");
		String type = (String)map.get("LECT_TYPE");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select theOrder, inputDate, location, topic, " +
	   			"attendees, inputStudentcode, inputTime, recordurl, type, memo " +
	   			"from userlecture " ;
	   	sql = sql + "where 1 = 1 " ;
	   	if((!"".equals(startTime))&&(!"".equals(endTime))){
	   		sql = sql +" and inputDate <= '" + endTime + "' " +
	   			" and inputDate >= '" + startTime + "' ";
	   	}
	   	if((null != type)&&(!"".equals(type))){
	   		sql = sql +" and type = '" + type + "' " ;
	   	}
	   	sql = sql + " and inputStudentCode in ";
	   	sql = sql + "(select userCode from authgroupmember where groupCode in ";
	   	sql = sql + "(select groupCode from authgroupmember where userCode = '" +
			uCode + "'))";
	   	sql = sql + " order by inputDate desc";
//System.out.println(sql);

		try{
			lectList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get member lecture access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == lectList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("LECTINFO", lectList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> lecture_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> lectList = null;
		Map<String, String> lectInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String orderCode = (String)conditionMap.get("LECT_CODE");
		if("update".equals(flag)){
			//获取修改的会议纪要信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select theOrder" +
				", inputDate, location, topic, attendees, recordurl, type, memo " +
				"from userlecture " +
				"where theOrder = " ;
		   	sql = sql + orderCode;
			sql = sql + "" ;
//System.out.println(sql);
			try{
				lectList = dba.getMultiRecord(sql);
				dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get lecture info access database error!");
				e.printStackTrace();
			}
			catch(DBConnectionException e){
				System.out.println("database close error!");
				e.printStackTrace();
			}
			
			if((null == lectList)||(lectList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				lectInfo = lectList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("LECTINFO", lectInfo);
		
		return resultMap;
	}

	public Map<String, Object> lecture_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = (String)map.get("FLAG");
		String theOrder = (String)map.get("LECT_CODE");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	resultMap.put("RESULT", "fail");
	   	
	   	if("add".equals(flag)){
	   		
			//获取用户编号
			HttpSession session = request.getSession(true);
			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
			String uCode = (String)userInfo.get("userCode");
			
		   	sql = "insert into userlecture(" +
   			"inputDate, location, topic, attendees, inputStudentCode, inputTime, recordurl, type, memo" +
			") " +
			"values(" ;
		
			sql = sql + "'" + (String)map.get("INPUT_TIME") + "'";
			sql = sql + ",'" + (String)map.get("LECT_TOPIC") + "'";
			sql = sql + ",'" + (String)map.get("LECT_LOCATION") + "'";
			sql = sql + ",'" + (String)map.get("LECT_ATTENDEES") + "'";
			sql = sql + ",'" + uCode + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",''";
			sql = sql + ",'" + (String)map.get("LECT_TYPE") + "'";
			sql = sql + ",'" + (String)map.get("LECT_MEMO") + "'";
		   	sql = sql + ")";
		   	
	   	}
	   	else{
	   		sql = "update userlecture set ";
	   		sql = sql + "inputDate = '" + (String)map.get("INPUT_TIME") + "' ";
	   		sql = sql + ",location = '" + (String)map.get("LECT_LOCATION") + "' ";
	   		sql = sql + ",topic = '" + (String)map.get("LECT_TOPIC") + "' ";
	   		sql = sql + ",attendees = '" + (String)map.get("LECT_ATTENDEES") + "' ";
	   		sql = sql + ",type = '" + (String)map.get("LECT_TYPE") + "' ";
	   		sql = sql + ",memo = '" + (String)map.get("LECT_MEMO") + "' ";
	   		sql = sql + " where ";
	   		sql = sql + " theOrder = " + theOrder + "";
	   	}
System.out.println(sql);
		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update meeting info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
/*		
		//获取会议记录列表开始
		List<Map<String, String>> meetList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String startTime = (String)map.get("START_TIME");
		String endTime = (String)map.get("END_TIME");
		
	   	sql = "select theOrder, inputDate, location, topic, " +
	   			"attendees, inputStudentcode, inputTime, memo " +
	   			"from usermeeting " ;
	   	sql = sql + "where 1 = 1 " ;
	   	if((!"".equals(startTime))&&(!"".equals(endTime))&& (null != startTime) && (null != endTime)){
	   	sql = sql +" and inputDate <= '" + endTime + "' " +
	   			" and inputDate >= '" + startTime + "' ";
	   	}
	   	sql = sql + " and inputStudentCode in ";
	   	sql = sql + "(select userCode from authgroupmember where groupCode in ";
	   	sql = sql + "(select groupCode from authgroupmember where userCode = '" +
			uCode + "'))";
//System.out.println(sql);

		try{
			meetList = dba.getMultiRecord(sql);
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
		
		if(null == meetList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("MEETINFO", meetList);

		//获取会议列表结束，返回显示列表
*/		
		resultMap.put("RESULT", "success");
		
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> lecture_delete(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String theOrder = (String)map.get("LECT_CODE");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");
		
	   	String sql = "delete from  userlecture " ;
	   	sql = sql + "where theOrder =" + theOrder ;
//System.out.println(sql);
		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get meeting info access database error!");
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

	public Map<String, Object> lecture_detail(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> lectList = null;
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		map.put("USER_CODE", uCode);
		
		String theOrder = (String)map.get("LECT_CODE");

		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "(select userName, inputTime, topic as content " +
	   			"from userlecture, authuser " ;
	   	sql = sql + "where userlecture.inputStudentcode = authuser.userCode and theOrder = " + theOrder + ")";
	   	
	   	String sql1 = "(select userName, inputTime, content " +
			"from userlecturedetail, authuser " ;
	   	sql1 = sql1 + "where userlecturedetail.userCode = authuser.userCode and parentOrder = " + theOrder + ")";
	   	sql1 = sql1 + " order by inputTime desc ";
	
	   	sql = sql + " union " + sql1;
//System.out.println(sql);

		try{
			lectList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get  lecture access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == lectList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("LECTINFO", lectList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> lecture_detailAdd(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String theOrder = (String)map.get("LECT_CODE");
		String memo = (String)map.get("LECT_MEMO");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	resultMap.put("RESULT", "fail");
	   	
		//获取用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
	   	sql = "insert into userlecturedetail(" +
		"userCode, content, inputTime, parentOrder, memo" +
		") " +
		"values(" ;
	
		sql = sql + "'" + uCode + "'";
		sql = sql + ",'" + memo + "'";
		sql = sql + ",'" + dateStr + "'";
		sql = sql + "," + theOrder + "";
		sql = sql + ",''";
	   	sql = sql + ")";
	   	
System.out.println(sql);
		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update lecture detail info access database error!");
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

}
