package cnedu.ncist.authuser.service;

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

public class AuthuserService extends AbstractService {

	public Map<String, Object> doServiceProcess(Map paraMap){
		// TODO Auto-generated method stub
		
		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("authuser_SelectInit".equals(actionType)){
			return authuser_selectInit(paraMap);
		}
		else if("authuser_Select".equals(actionType)){
			return authuser_select(paraMap);
		}
		else if("authuser_AddInit".equals(actionType)){
			return authuser_addInit(paraMap);
		}
		else if("authuser_Add".equals(actionType)){
			return authuser_add(paraMap);
		}
		else if("authuser_StateChange".equals(actionType)){
			return authuser_stateChange(paraMap);
		}
		else if("authuser_PasswordChange".equals(actionType)){
			return authuser_passwordChange(paraMap);
		}
		else if("authuser_PasswordChanged".equals(actionType)){
			return authuser_passwordChanged(paraMap);
		}
		else if("authuser_AuthrizeInit".equals(actionType)){
			return authuser_authrizeInit(paraMap);
		}
		else if("authuser_Authrize".equals(actionType)){
			return authuser_authrize(paraMap);
		}
		else if("authuser_CheckDup".equals(actionType)){
			return authuser_checkDup(paraMap);
		}
		else if("authorgan_SelectInit".equals(actionType)){
			return authorgan_select(paraMap);
		}
		else if("authorgan_Select".equals(actionType)){
			return authorgan_select(paraMap);
		}
		else if("authorgan_AddInit".equals(actionType)){
			return authorgan_addInit(paraMap);
		}
		else if("authorgan_Add".equals(actionType)){
			return authorgan_add(paraMap);
		}
		else if("authorgan_StateChange".equals(actionType)){
			return authorgan_stateChange(paraMap);
		}
		else if("authorgan_CheckDup".equals(actionType)){
			return authorgan_checkDup(paraMap);
		}
		else if("authgroup_SelectInit".equals(actionType)){
			return authgroup_select(paraMap);
		}
		else if("authgroup_Select".equals(actionType)){
			return authgroup_select(paraMap);
		}
		else if("authgroup_AddInit".equals(actionType)){
			return authgroup_addInit(paraMap);
		}
		else if("authgroup_Add".equals(actionType)){
			return authgroup_add(paraMap);
		}
		else if("authgroup_StateChange".equals(actionType)){
			return authgroup_stateChange(paraMap);
		}
		else if("authgroup_CheckDup".equals(actionType)){
			return authgroup_checkDup(paraMap);
		}
		else if("authgroup_SetmemberInit".equals(actionType)){
			return authgroup_setmemberInit(paraMap);
		}
		else if("authgroup_Setmember".equals(actionType)){
			return authgroup_setmember(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;

	}
	
	public Map<String, Object> authuser_selectInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> userList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"userCode, deptName, orgCode" +
			", userName, gender, isSysManager" +
			", userCodeUpdate, updTime, telphone" +
			", phone, email, isUse " +
			"from authuser " ;

/*	   	String sql = "select a.name, b.userName from student.student a, authmanagement.authuser b" ;
*/
		try{
			userList = dba.getMultiRecord(sql);
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

	public Map<String, Object> authuser_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> userList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"userCode, deptName, orgCode" +
			", userName, gender, isSysManager" +
			", userCodeUpdate, updTime, telphone" +
			", phone, email, isUse " +
			"from authuser " +
			"where 1 = 1 ";
	   	
	   	String str = (String)map.get("ORG_NAME");
	   	if("" != str){
	   		sql = sql + " and deptName like '%" + str + "%'";
	   	}
	   	str = (String)map.get("PERSON_LOGINNAME");
	   	if("" != str){
	   		sql = sql + " and userCode like '%" + str + "%'";
	   	}
	   	str = (String)map.get("PERSON_NAME");
	   	if("" != str){
	   		sql = sql + " and userName like '%" + str + "%'";
	   	}
	   	str = (String)map.get("USER_STATUS");
	   	if("" != str){
	   		sql = sql + " and isUse = '" + str + "'";
	   	}

		try{
			userList = dba.getMultiRecord(sql);
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

	public Map<String, Object> authuser_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> userList = null;
		Map<String, String> userInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String userCode = (String)conditionMap.get("USER_CODE");
		if("update".equals(flag)){
			//获取修改的用户信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select " +
				"userCode, deptName, orgCode" +
				", userName, gender, isSysManager" +
				", userCodeUpdate, updTime, telphone" +
				", phone, email, isUse " +
				"from authuser " +
				"where userCode = '" ;
		   	sql = sql + userCode;
			sql = sql + "'" ;

			try{
				userList = dba.getMultiRecord(sql);
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
			
			if((null == userList)||(userList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				userInfo = userList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("USERINFO", userInfo);
		
		return resultMap;
	}

	public Map<String, Object> authuser_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = (String)map.get("FLAG");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String orgCode = "";
		String deptName = "";
		
	   	String sql = "";
	   	if("add".equals(flag)){
	   		
			//获取用户编号
			HttpSession session = request.getSession(true);
			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
			String uCode = (String)userInfo.get("userCode");
			
	   		sql = "select orgCode, deptName from authuser ";
	   		sql = sql + " where userCode = '" + uCode + "'";
	   		
			List<Map<String, String>> uList = null;
			Map<String, String> uInfo = null;

			try{
				uList = dba.getMultiRecord(sql);
				//dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get user info access database error!");
				e.printStackTrace();
			}
			
			if((null != uList)||(uList.size() > 0)) {
				uInfo = uList.get(0);
				orgCode = (String)uInfo.get("orgCode");
				deptName = (String)uInfo.get("deptName");
			}
	   		
	   		
		   	sql = "insert into authuser(" +
   			"userCode, deptName, orgCode" +
			", userName, password, gender, isSysManager" +
			", userCodeUpdate, updTime, telphone" +
			",phone, email, isUse) " +
			"values(" ;
		
			sql = sql + "'" + (String)map.get("USER_CODE") + "'";
			sql = sql + ",'" + deptName + "'";
			sql = sql + ",'" + orgCode + "'";
			sql = sql + ",'" + (String)map.get("USER_NAME") + "'";
			sql = sql + ",'" + (String)map.get("PASSWORD") + "'";
			sql = sql + ",'" + (String)map.get("USER_SEX") + "'";
			sql = sql + ",'" + "0" + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",'" + (String)map.get("TELPHONE") + "'";
			sql = sql + ",'" + (String)map.get("PHONE") + "'";
			sql = sql + ",'" + (String)map.get("EMAIL") + "'";
			sql = sql + ",'" + "0" + "'";
		   	sql = sql + ")";
	   	}
	   	else{
	   		sql = "update authuser set ";
	   		sql = sql + "deptName = '" + (String)map.get("ORG_NAME") + "' ";
	   		sql = sql + ",userName = '" + (String)map.get("USER_NAME") + "' ";
	   		sql = sql + ",gender = '" + (String)map.get("USER_SEX") + "' ";
	   		sql = sql + ",telphone = '" + (String)map.get("TELPHONE") + "' ";
	   		sql = sql + ",phone = '" + (String)map.get("PHONE") + "' ";
	   		sql = sql + ",email = '" + (String)map.get("EMAIL") + "' ";
	   		sql = sql + "where ";
	   		sql = sql + "userCode ='" + map.get("USER_CODE") + "'";
	   	}

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

	public Map<String, Object> authuser_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> userList = null;
		Map<String, String> userInfo = null;
		
		//获取用户代码
		String userCode = (String)conditionMap.get("USER_CODE");
		
		//修改状态
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "update authuser " +
			"set isUse = (1-isUse)" +
			"where userCode = '" ;
	   	sql = sql + userCode;
		sql = sql + "'" ;

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

	public Map<String, Object> authuser_passwordChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> authuser_passwordChanged(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
		List<Map<String, String>> userList = null;

		//获取用户代码
		String userCode = (String)conditionMap.get("USER_CODE");
		String password = (String)conditionMap.get("PASSWORDOLD");
		String passwordNew = (String)conditionMap.get("PASSWORDNEW");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"userCode,isUse " +
			"from authuser " +
			"where 1 = 1 " ;
	   	sql = sql + "and userCode = '" + userCode + "' ";
	   	sql = sql + "and password = '" + password + "' ";

		resultMap.put("RESULT", "success");
		try{
			userList = dba.getMultiRecord(sql);
			if((userList != null)&&(userList.size() == 1)){
				sql = "update authuser set password = '";
				sql = sql + passwordNew + "' ";
				sql = sql + "where userCode = '" + userCode + "' ";
				
				dba.executeUpdate(sql);
				resultMap.put("RESULT", "success");
			}
			else{
				resultMap.put("RESULT", "fail");
			}
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

	public Map<String, Object> authuser_authrizeInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> roleList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String userCode = (String)map.get("USER_CODE");
		
	   	String sql = "select " +
			"a.roleCode as rc, a.roleName, a.roleMemo " +
			",b.userCode " +
			"from authrole a " +
			"left join  ( " +
				"select rolecode, userCode from authuserrole where userCode = '" +
				userCode + "'" +
			") b" +
			" on a.roleCode = b.roleCode " +
			"where a.isUse = '1' " ;

		try{
			roleList = dba.getMultiRecord(sql);
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
		
		if(null == roleList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("USERROLE", roleList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authuser_authrize(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] roleIdList = (String[]) conditionMap.get("ROLE_CODE");
		
		String userCode = (String)conditionMap.get("USER_CODE");
		
		String sqls [] = new String[roleIdList.length +1];
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");

		String delSql = "delete from authuserrole " +
				"where userCode = '" + userCode + "'";
		sqls[0] = delSql;
		
		for(int i=0; i<roleIdList.length; i++){
		   	String sql = "insert into authuserrole(userCode, roleCode) " +
				"values('" + userCode + "', '" + roleIdList[i] + "')";
		   	sqls[i+1] = sql;
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

	public Map<String, Object> authuser_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> userList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"userCode " +
			"from authuser " +
			"where userCode = '";
	   	sql = sql + map.get("USER_CODE") + "'";

		try{
			userList = dba.getMultiRecord(sql);
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
		
		if((null != userList) && (userList.size() == 1)){
			resultMap.put("RESULT", "success");
		}
		else{
			resultMap.put("RESULT", "fail");
		}
		resultMap.put("CONDITIONMAP", map);
		resultMap.put("USERINFO", userList);
		
		return resultMap;
	}

	public Map<String, Object> authorgan_select(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		String organCode = (String)map.get("ORGAN_CODE");
		String organName = (String)map.get("ORGAN_NAME");
		String status = (String)map.get("ORGAN_STATUS");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> userList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"organCode, organName" +
			", isUse " +
			"from authorgan " +
	   		"where 1 = 1 " ;
	   	if(!"".equals(organCode)){
	   		sql = sql + " and organCode like '%" + organCode + "%'";
	   	}
	   	if(!"".equals(organName)){
	   		sql = sql + " and organName like '%" + organName + "%'";
	   	}
	   	if(!"".equals(status)){
	   		sql = sql + " and isUse = '" + status + "'";
	   	}

		try{
			userList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get organization info access database error!");
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
		
		resultMap.put("ORGANINFO", userList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authorgan_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> organList = null;
		Map<String, String> organInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String organCode = (String)conditionMap.get("ORGAN_CODE");
		if("update".equals(flag)){
			//获取修改的用户信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select " +
			"organCode, organName" +
			", isUse, organMemo " +
			"from authorgan " +
			"where organCode = '" ;
		   	sql = sql + organCode;
			sql = sql + "'" ;

			try{
				organList = dba.getMultiRecord(sql);
				dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get role info access database error!");
				e.printStackTrace();
			}
			catch(DBConnectionException e){
				System.out.println("database close error!");
				e.printStackTrace();
			}
			
			if((null == organList)||(organList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				organInfo = organList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("ORGANINFO", organInfo);
		
		return resultMap;
	}

	public Map<String, Object> authorgan_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = (String)conditionMap.get("FLAG");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		//获取用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String userCode = (String)userInfo.get("userCode");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	if("add".equals(flag)){
		   	sql = "insert into authorgan(" +
   			"organCode, organName, isUse, userCodeCreate " +
			", createTime, organMemo) " +
			"values(" ;
		
			sql = sql + "'" + (String)conditionMap.get("ORGAN_CODE") + "'";
			sql = sql + ",'" + (String)conditionMap.get("ORGAN_NAME") + "'";
			sql = sql + ",'" + "0" + "'";
			sql = sql + ",'" + userCode + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",'" + (String)conditionMap.get("ORGAN_MEMO") + "'";
		   	sql = sql + ")";
	   	}
	   	else{
	   		sql = "update authorgan set ";
	   		sql = sql + "organName = '" + (String)conditionMap.get("ORGAN_NAME") + "' ";
	   		sql = sql + ",organMemo = '" + (String)conditionMap.get("ORGAN_MEMO") + "' ";
	   		sql = sql + "where ";
	   		sql = sql + "organCode ='" + conditionMap.get("ORGAN_CODE") + "'";
	   	}

		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update organization info access database error!");
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

	public Map<String, Object> authorgan_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> organList = null;
		Map<String, String> organInfo = null;
		
		//获取用户代码
		String organCode = (String)conditionMap.get("ORGAN_CODE");
		
		//修改状态
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "update authorgan " +
			"set isUse = (1-isUse)" +
			"where organCode = '" ;
	   	sql = sql + organCode;
		sql = sql + "'" ;

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

	public Map<String, Object> authorgan_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> organList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"organCode " +
			"from authorgan " +
			"where organCode = '";
	   	sql = sql + map.get("ORGAN_CODE") + "'";

		try{
			organList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get organization info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if((null != organList) && (organList.size() == 1)){
			resultMap.put("RESULT", "success");
		}
		else{
			resultMap.put("RESULT", "fail");
		}
		resultMap.put("CONDITIONMAP", map);
		resultMap.put("USERINFO", organList);
		
		return resultMap;
	}

	public Map<String, Object> authgroup_select(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String groupCode = (String)map.get("GROUP_CODE");
		String groupName = (String)map.get("GROUP_NAME");
		String status = (String)map.get("GROUP_STATUS");
		String organCode = (String)map.get("ORGAN_CODE");
		String organName = (String)map.get("ORGAN_NAME");
		
		//第一次显示小组列表的时候，单位信息可能为空，需要单独读入
		if("".equals(organCode)){
			
			//获取用户编号
			HttpSession session = request.getSession(true);
			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
			String userCode = (String)userInfo.get("userCode");
			
			//获取单位信息
			List<Map<String, String>> organList = null;
			Map<String, String> organInfo = null;

			String sql = "select " +
			"organCode, organName " +
			"from authorgan, authuser " +
			"where authuser.orgCode = authorgan.organCode "  +
			"and authuser.userCode = '"  ;
		   	sql = sql + userCode;
			sql = sql + "'" ;
			try{
				organList = dba.getMultiRecord(sql);
			}
			catch(DataManipulationException e){
				System.out.println("get organization info access database error!");
				e.printStackTrace();
			}
			
			if((null != organList)&&(organList.size() > 0)) {
				organInfo = organList.get(0);
				map.put("ORGAN_CODE", organInfo.get("organCode"));
				map.put("ORGAN_NAME", organInfo.get("organName"));
				organCode = (String)map.get("ORGAN_CODE");
				organName = (String)map.get("ORGAN_NAME");
			}
		}


		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> groupList = null;
		
	   	String sql = "select " +
			"authgroup.groupCode, authgroup.groupName" +
			", authorgan.organName, authgroup.isUse " +
			"from authgroup, authorgan " +
	   		"where authgroup.organCode = authorgan.organCode  " ;
	   	if(!"".equals(groupCode)){
	   		sql = sql + " and authgroup.groupCode like '%" + groupCode + "%'";
	   	}
	   	if(!"".equals(groupName)){
	   		sql = sql + " and authgroup.groupName like '%" + groupName + "%'";
	   	}
	   	if(!"".equals(status)){
	   		sql = sql + " and authgroup.isUse = '" + status + "'";
	   	}
	   	sql = sql + " and authgroup.organCode like '" + organCode + "'";

		try{
			groupList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get group info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if(null == groupList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("GROUPINFO", groupList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authgroup_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> groupList = null;
		Map<String, String> groupInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String groupCode = (String)conditionMap.get("GROUP_CODE");

		String organCode = (String)conditionMap.get("ORGAN_CODE");
		String organName = (String)conditionMap.get("ORGAN_NAME");
		if("update".equals(flag)){
			//获取修改的用户信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select " +
			"groupCode, groupName" +
			", isUse, groupMemo " +
			"from authgroup " +
			"where groupCode = '" ;
		   	sql = sql + groupCode;
			sql = sql + "'" ;

			try{
				groupList = dba.getMultiRecord(sql);
				dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get role info access database error!");
				e.printStackTrace();
			}
			catch(DBConnectionException e){
				System.out.println("database close error!");
				e.printStackTrace();
			}
			
			if((null == groupList)||(groupList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				groupInfo = groupList.get(0);
			}
		}
		else{
			//获取用户编号
			HttpSession session = request.getSession(true);
			Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
			String userCode = (String)userInfo.get("userCode");
			
			//获取单位信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select " +
			"organCode, organName " +
			"from authorgan, authuser " +
			"where authuser.orgCode = authorgan.organCode "  +
			"and authuser.userCode = '"  ;
		   	sql = sql + userCode;
			sql = sql + "'" ;
			try{
				groupList = dba.getMultiRecord(sql);
				dba.close();
			}
			catch(DataManipulationException e){
				System.out.println("get role info access database error!");
				e.printStackTrace();
			}
			catch(DBConnectionException e){
				System.out.println("database close error!");
				e.printStackTrace();
			}
			
			if((null != groupList)&&(groupList.size() > 0)) {
				groupInfo = groupList.get(0);
				conditionMap.put("ORGAN_CODE", groupInfo.get("organCode"));
				conditionMap.put("ORGAN_NAME", groupInfo.get("organName"));
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("GROUPINFO", groupInfo);

		return resultMap;
	}

	public Map<String, Object> authgroup_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = (String)conditionMap.get("FLAG");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		//获取用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String userCode = (String)userInfo.get("userCode");
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	if("add".equals(flag)){
		   	sql = "insert into authgroup(" +
   			"groupCode, groupName, organCode, isUse, userCodeCreate " +
			", createTime, groupMemo) " +
			"values(" ;
		
			sql = sql + "'" + (String)conditionMap.get("GROUP_CODE") + "'";
			sql = sql + ",'" + (String)conditionMap.get("GROUP_NAME") + "'";
			sql = sql + ",'" + (String)conditionMap.get("ORGAN_CODE") + "'";
			sql = sql + ",'" + "0" + "'";
			sql = sql + ",'" + userCode + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",'" + (String)conditionMap.get("GROUP_MEMO") + "'";
		   	sql = sql + ")";
	   	}
	   	else{
	   		sql = "update authgroup set ";
	   		sql = sql + "groupName = '" + (String)conditionMap.get("GROUP_NAME") + "' ";
	   		sql = sql + ",groupMemo = '" + (String)conditionMap.get("GROUP_MEMO") + "' ";
	   		sql = sql + "where ";
	   		sql = sql + "groupCode ='" + conditionMap.get("GROUP_CODE") + "'";
	   	}

		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update group info access database error!");
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

	public Map<String, Object> authgroup_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> groupList = null;
		Map<String, String> groupInfo = null;
		
		//获取用户代码
		String groupCode = (String)conditionMap.get("GROUP_CODE");
		
		//修改状态
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "update authgroup " +
			"set isUse = (1-isUse)" +
			"where groupCode = '" ;
	   	sql = sql + groupCode;
		sql = sql + "'" ;

		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update group info access database error!");
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

	public Map<String, Object> authgroup_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> groupList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"groupCode " +
			"from authgroup " +
			"where groupCode = '";
	   	sql = sql + map.get("GROUP_CODE") + "'";

		try{
			groupList = dba.getMultiRecord(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("get group info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		if((null != groupList) && (groupList.size() == 1)){
			resultMap.put("RESULT", "success");
		}
		else{
			resultMap.put("RESULT", "fail");
		}
		resultMap.put("CONDITIONMAP", map);
		resultMap.put("GROUPINFO", groupList);
		
		return resultMap;
	}

	public Map<String, Object> authgroup_setmemberInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> memberList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String groupCode = (String)map.get("GROUP_CODE");
		
	   	String sql = "select " +
			"a.userCode , a.userName, a.deptName " +
			",b.groupCode gc, c.groupCode " +
			"from authuser a " +
			"left join  ( " +
				"select groupCode , organcode from authgroup where groupCode = '" +
				groupCode + "'" +
			") b" +
			" on a.orgCode = b.organCode " +
			"left join  ( " +
				"select userCode, groupCode from authgroupmember where groupCode = '" +
				groupCode + "'" +
			") c" +
			" on a.userCode = c.userCode " +
			"where a.isUse = '1' " ;

		try{
			memberList = dba.getMultiRecord(sql);
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
		
		if(null == memberList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("GROUPMEMBER", memberList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authgroup_setmember(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] memberIdList = (String[]) conditionMap.get("MEMBER_CODE");
		
		String groupCode = (String)conditionMap.get("GROUP_CODE");
		
		String sqls [] = new String[memberIdList.length +1];
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");

		String delSql = "delete from authgroupmember " +
				"where groupCode = '" + groupCode + "'";
		sqls[0] = delSql;
		
		for(int i=0; i<memberIdList.length; i++){
		   	String sql = "insert into authgroupmember(groupCode, userCode) " +
				"values('" + groupCode + "', '" + memberIdList[i] + "')";
		   	sqls[i+1] = sql;
		}
		try{
			dba.excuteBatchSql(sqls);
			dba.close();
			
			resultMap.put("RESULT", "success");
		}
		catch(DataManipulationException e){
			System.out.println("update group member mapping error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}

		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

}
