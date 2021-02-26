package cnedu.ncist.authmenu.service;

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

public class AuthmenuService extends AbstractService {

	public Map doServiceProcess(Map paraMap) {
		// TODO Auto-generated method stub

		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("authmenu_SelectInit".equals(actionType)){
			return authmenu_selectInit(paraMap);
		}
		if("authmenu_Select".equals(actionType)){
			return authmenu_select(paraMap);
		}
		if("authmenu_AddInit".equals(actionType)){
			return authmenu_addInit(paraMap);
		}
		else if("authmenu_Add".equals(actionType)){
			return authmenu_add(paraMap);
		}
		else if("authmenu_StateChange".equals(actionType)){
			return authmenu_stateChange(paraMap);
		}
		else if("authmenu_CheckDup".equals(actionType)){
			return authmenu_checkDup(paraMap);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	public Map<String, Object> authmenu_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> menuList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"menuCode, menuName" +
			", isUse " +
			"from authmenu " +
			"order by menuOrder " ;
	
		try{
			menuList = dba.getMultiRecord(sql);
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
		
		if(null == menuList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("MENUINFO", menuList);
		resultMap.put("CONDITIONMAP", map);
		
		return resultMap;
	}

	public Map<String, Object> authmenu_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, String>> menuList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		String menuCode = (String)conditionMap.get("MENU_CODE");
		String menuName = (String)conditionMap.get("MENU_NAME");
		String status = (String)conditionMap.get("USER_STATUS");
		
	   	String sql = "select " +
			"menuCode, menuName" +
			", isUse " +
			"from authmenu " +
			"where 1 = 1 " ;
	   	if(!"".equals(menuCode)){
	   		sql = sql + " and menuCode like '%" + menuCode + "%'";
	   	}
	   	if(!"".equals(menuName)){
	   		sql = sql + " and menuName like '%" + menuName + "%'";
	   	}
	   	if(!"".equals(status)){
	   		sql = sql + " and isUse = '" + status + "'";
	   	}
	
		try{
			menuList = dba.getMultiRecord(sql);
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
		
		if(null == menuList){
			resultMap.put("RESULT", "fail");
		}
		else{
			resultMap.put("RESULT", "success");
		}
		
		resultMap.put("MENUINFO", menuList);
		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> authmenu_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> menuList = null;
		Map<String, String> menuInfo = null;
		List<Map<String, String>> parrentInfo = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		//根据标志判断是新建用户还是修改用户
		String flag = (String)conditionMap.get("FLAG");
		String menuCode = (String)conditionMap.get("MENU_CODE");

		String pSql = "select " +
			"menuCode, menuName " +
			"from authmenu " +
			"where parrentCode = '-1'" ;
		
		
		try{
			parrentInfo = dba.getMultiRecord(pSql);
			if("add".equals(flag)){
				dba.close();
			}
		}
		catch(DataManipulationException e){
			System.out.println("get parrent menu info access database error!");
			e.printStackTrace();
		}
		catch(DBConnectionException e){
			System.out.println("database close error!");
			e.printStackTrace();
		}
		
		Map<String, String> defaultOption = new HashMap<String, String>();
		defaultOption.put("menuCode", "-1");
		defaultOption.put("menuName", "一级菜单");
		parrentInfo.add(0, defaultOption);

		if("update".equals(flag)){
			//获取修改的角色信息
		   	String sql = "select " +
			"menuCode, menuName" +
			", linkPage, parrentCode, menuOrder " +
			"from authmenu " +
			"where menuCode = '" ;
		   	sql = sql + menuCode;
			sql = sql + "'" ;

			try{
				menuList = dba.getMultiRecord(sql);
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
			
			if((null == menuList)||(menuList.size() == 0)) {
				resultMap.put("RESULT", "fail");
			}
			else{
				resultMap.put("RESULT", "success");
				menuInfo = menuList.get(0);
			}
		}
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		resultMap.put("PARRENTINFO", parrentInfo);
		resultMap.put("MENUINFO", menuInfo);
		
		return resultMap;
	}

	public Map<String, Object> authmenu_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String flag = (String)conditionMap.get("FLAG");
		
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String dateStr = df.format(now);
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "";
	   	if("add".equals(flag)){
		   	sql = "insert into authmenu(" +
   			"menuCode, menuName, linkPage" +
   			", parrentCode, isUse, menuOrder) " +
			"values(" ;
		
			sql = sql + "'" + (String)conditionMap.get("MENU_CODE") + "'";
			sql = sql + ",'" + (String)conditionMap.get("MENU_NAME") + "'";
			sql = sql + ",'" + (String)conditionMap.get("LINK_PAGE") + "'";
			sql = sql + ",'" + (String)conditionMap.get("MENU_PARRENT") + "'";
			sql = sql + ",'" + "0" + "'";
			sql = sql + ",'" + (String)conditionMap.get("MENU_ORDER") + "'";
		   	sql = sql + ")";
	   	}
	   	else{
	   		sql = "update authmenu set ";
	   		sql = sql + "menuName = '" + (String)conditionMap.get("MENU_NAME") + "' ";
	   		sql = sql + ", linkPage = '" + (String)conditionMap.get("LINK_PAGE") + "' ";
	   		sql = sql + ", parrentCode = '" + (String)conditionMap.get("MENU_PARRENT") + "' ";
	   		sql = sql + ", menuOrder = '" + (String)conditionMap.get("MENU_ORDER") + "' ";
	   		sql = sql + "where ";
	   		sql = sql + "menuCode ='" + conditionMap.get("MENU_CODE") + "'";
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

	public Map<String, Object> authmenu_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获取用户代码
		String menuCode = (String)conditionMap.get("MENU_CODE");
		
		//修改状态
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "update authmenu " +
			"set isUse = (1-isUse)" +
			"where menuCode = '" ;
	   	sql = sql + menuCode;
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

	public Map<String, Object> authmenu_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, String>> menuList = null;
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
	   	String sql = "select " +
			"menuCode " +
			"from authmenu " +
			"where menuCode = '";
	   	sql = sql + map.get("MENU_CODE") + "'";

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
		
		if((null != menuList) && (menuList.size() == 1)){
			resultMap.put("RESULT", "success");
		}
		else{
			resultMap.put("RESULT", "fail");
		}
		resultMap.put("CONDITIONMAP", map);
		resultMap.put("ROLEINFO", menuList);
		
		return resultMap;
	}

}
