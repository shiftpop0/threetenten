package cnedu.ncist.authrole.service;

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

public class AuthroleService extends AbstractService {

	public Map doServiceProcess(Map paraMap) {
		// TODO Auto-generated method stub

		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("authrole_SelectInit".equals(actionType)){
			return authrole_selectInit(paraMap);
		}
		if("authrole_Select".equals(actionType)){
			return authrole_select(paraMap);
		}
		if("authrole_AddInit".equals(actionType)){
			return authrole_addInit(paraMap);
		}
		else if("authrole_Add".equals(actionType)){
			return authrole_add(paraMap);
		}
		else if("authrole_StateChange".equals(actionType)){
			return authrole_stateChange(paraMap);
		}
		else if("authrole_AuthrizeInit".equals(actionType)){
			return authrole_authrizeInit(paraMap);
		}
		else if("authrole_Authrize".equals(actionType)){
			return authrole_authrize(paraMap);
		}
		else if("authrole_CheckDup".equals(actionType)){
			return authrole_checkDup(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	
	public Map<String, Object> authrole_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> roleList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"roleCode, roleName" +
			", createTime, isUse " +
			"from authrole " ;
	
		try{
			roleList = dba.getMultiRecord(sql);
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
		
		if(null == roleList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("ROLEINFO", roleList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authrole_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> roleList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String roleCode = (String)conditionMap.get("ROLE_CODE");
		String roleName = (String)conditionMap.get("ROLE_NAME");
		String status = (String)conditionMap.get("USER_STATUS");
		
	   	String sql = "select " +
			"roleCode, roleName" +
			", createTime, isUse " +
			"from authrole " +
			"where 1 = 1 " ;
	   	if(!"".equals(roleCode)){
	   		sql = sql + " and roleCode like '%" + roleCode + "%'";
	   	}
	   	if(!"".equals(roleName)){
	   		sql = sql + " and roleName like '%" + roleName + "%'";
	   	}
	   	if(!"".equals(status)){
	   		sql = sql + " and isUse = '" + status + "'";
	   	}
	
		try{
			roleList = dba.getMultiRecord(sql);
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
		
		if(null == roleList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("ROLEINFO", roleList);
		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> authrole_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> roleList = null;
		Map<String, String> roleInfo = null;
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String roleCode = (String)conditionMap.get("ROLE_CODE");
		if("update".equals(flag)){
			//获取修改的用户信息
			DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
			
		   	String sql = "select " +
			"roleCode, roleName" +
			", createTime, isUse, roleMemo " +
			"from authrole " +
			"where roleCode = '" ;
		   	sql = sql + roleCode;
			sql = sql + "'" ;

			try{
				roleList = dba.getMultiRecord(sql);
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
			
			if((null == roleList)||(roleList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				roleInfo = roleList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("ROLEINFO", roleInfo);
		
		return resultMap;
	}

	public Map<String, Object> authrole_add(Map paraMap){
		
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
		   	sql = "insert into authrole(" +
   			"roleCode, roleName, isUse, userCodeCreate " +
			", createTime, roleMemo) " +
			"values(" ;
		
			sql = sql + "'" + (String)conditionMap.get("ROLE_CODE") + "'";
			sql = sql + ",'" + (String)conditionMap.get("ROLE_NAME") + "'";
			sql = sql + ",'" + "0" + "'";
			sql = sql + ",'" + (String)conditionMap.get("USER_CODE") + "'";
			sql = sql + ",'" + dateStr + "'";
			sql = sql + ",'" + (String)conditionMap.get("ROLE_MEMO") + "'";
		   	sql = sql + ")";
	   	}
	   	else{
	   		sql = "update authrole set ";
	   		sql = sql + "roleName = '" + (String)conditionMap.get("ROLE_NAME") + "' ";
	   		sql = sql + ",roleMemo = '" + (String)conditionMap.get("ROLE_MEMO") + "' ";
	   		sql = sql + "where ";
	   		sql = sql + "roleCode ='" + conditionMap.get("ROLE_CODE") + "'";
	   	}

		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update role info access database error!");
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

	public Map<String, Object> authrole_stateChange(Map paraMap){
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> roleList = null;
		Map<String, String> roleInfo = null;
		
		//获取用户代码
		String roleCode = (String)conditionMap.get("ROLE_CODE");
		//修改状态
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "update authrole " +
			"set isUse = (1-isUse)" +
			"where roleCode = '" ;
	   	sql = sql + roleCode;
		sql = sql + "'" ;

		try{
			dba.executeUpdate(sql);
			dba.close();
		}
		catch(DataManipulationException e){
			System.out.println("update role state access database error!");
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

	public Map<String, Object> authrole_authrizeInit(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> menuList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String roleCode = (String)map.get("ROLE_CODE");
		
	   	String sql = "select " +
			"a.menuCode as mc, a.menuName, a.linkPage " +
			",b.roleCode " +
			"from authmenu a " +
			"left join  ( " +
				"select rolecode, menuCode from authrolemenu where roleCode = '" +
				roleCode + "'" +
			") b" +
			" on a.menuCode = b.menuCode " +
			"where a.isUse = '1' and parrentCode <> '-1'" ;

		try{
			menuList = dba.getMultiRecord(sql);
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
		
		if(null == menuList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("ROLEMENU", menuList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authrole_authrize(Map paraMap){
		
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String[] menuIdList = (String[]) conditionMap.get("MENU_CODE");
		
		String roleCode = (String)conditionMap.get("ROLE_CODE");
		
		String sqls [] = new String[menuIdList.length +1];
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		resultMap.put("RESULT", "fail");

		String delSql = "delete from authrolemenu " +
				"where roleCode = '" + roleCode + "'";
		sqls[0] = delSql;
		
		for(int i=0; i<menuIdList.length; i++){
		   	String sql = "insert into authrolemenu(roleCode, menuCode) " +
				"values('" + roleCode + "', '" + menuIdList[i] + "')";
		   	sqls[i+1] = sql;
		}
		try{
			dba.excuteBatchSql(sqls);
			dba.close();
			
			resultMap.put("RESULT", "success");
		}
		catch(DataManipulationException e){
			System.out.println("update role menu mapping error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}

		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> authrole_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> roleList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"roleCode " +
			"from authrole " +
			"where roleCode = '";
	   	sql = sql + map.get("ROLE_CODE") + "'";

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
		
		if((null != roleList) && (roleList.size() == 1)){
			resultMap.put("RESULT", "success");
		}
		else{
			resultMap.put("RESULT", "fail");
		}
		resultMap.put("CONDITIONMAP", map);
		resultMap.put("ROLEINFO", roleList);
		
		return resultMap;
	}

}
