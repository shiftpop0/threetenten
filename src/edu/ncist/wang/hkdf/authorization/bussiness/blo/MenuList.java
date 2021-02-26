package edu.ncist.wang.hkdf.authorization.bussiness.blo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import edu.ncist.wang.hkdf.authorization.bussiness.bli.MenuListAble;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class MenuList implements MenuListAble {
	
	public List<Map<String, String>> login(HttpServletRequest request, Map map){
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");

		String sql = "select userName, userCode from authuser where userCode ='";
		sql = sql + (String)map.get("LOGINID") + "'";
		sql = sql + " and password = '" + (String)map.get("PASSWORD") + "'";
		
		List<Map<String, String>> list = null;
		try{
			list = dba.getMultiRecord(sql);
		}
		catch(DataManipulationException e){
			System.out.println("login access database error!");
			e.printStackTrace();
		}
		return list;
	}

	public Map<String, String> getUserInfo(HttpServletRequest request, String userCode){
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");

    	String sql = "select " +
    			"userCode, deptName, orgCode" +
    			", userName, gender, isSysManager" +
    			", userCodeUpdate, updTime, telphone" +
    			", phone, email, isUse " +
    			"from authuser where userCode ='";
		sql = sql + userCode + "'";
		
		List<Map<String, String>> list = null;
		try{
			list = dba.getMultiRecord(sql);
		}
		catch(DataManipulationException e){
			System.out.println("login access database error!");
			e.printStackTrace();
		}
		Map map = null;
		if((null != list) && (list.size() > 0)){
			map = list.get(0);
		}
		else{
			System.out.println("登录用户不存在！");
		}
		return map;
	}
	
	public List<Map<String, String>> getMenuList(HttpServletRequest request, String userCode){
		
		DataBaseAccess dba = (DataBaseAccess)request.getAttribute("AUTHDATACONNECTION");
		
		UserRoles userRoles = new UserRoles();

		List<Map<String, String>> list = null;

		List<Map<String, String>> roleList = userRoles.getUserRoles(dba, userCode);
		
		if((null == roleList) || (roleList.size() == 0)){
			System.out.println("用户对应角色为空！");
		}
		else{
			System.out.println("login user role size = " + roleList.size());
		}
		
		RoleMenus roleMenu = new RoleMenus();
		list = roleMenu.getMenuList(dba, roleList);
		
		if((null == list) || (list.size() == 0)){
			System.out.println("用户角色对应功能菜单为空");
		}
		else{
			System.out.println("login user menu size = " + list.size());
		}

		return list;
	}

}
