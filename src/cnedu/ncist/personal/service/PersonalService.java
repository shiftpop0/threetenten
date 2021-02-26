package cnedu.ncist.personal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractService;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class PersonalService extends AbstractService {

	public Map doServiceProcess(Map map) {
		// TODO Auto-generated method stub
		
		String actionType = (String)map.get("ACTIONTYPE");
		
		if("personal_ChangeInit".equals(actionType)){
			return personal_changeInit(map);
		}
		else if("personal_Change".equals(actionType)){
			return personal_change(map);
		}
		else{
			ActionTypeError();
		}
		
		return null;
	}

	public Map<String, Object> personal_changeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");

		Map conditionMap = (Map)paraMap.get("CONDITIONMAP");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获取登录用户编号
		HttpSession session = request.getSession(true);
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute("USERINFO");
		String uCode = (String)userInfo.get("userCode");
		
		conditionMap.put("USER_CODE", uCode);
		
		
		resultMap.put("RESULT", "success");
		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

	public Map<String, Object> personal_change(Map paraMap){
		
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
		
		resultMap.put("CONDITIONMAP", conditionMap);
		
		return resultMap;
	}

}
