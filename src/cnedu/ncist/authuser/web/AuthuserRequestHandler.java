package cnedu.ncist.authuser.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.ncist.wang.hkdf.bussiness.bli.RequestHandlable;
import edu.ncist.wang.hkdf.bussiness.blo.AbstractRequestHandler;
import edu.ncist.wang.hkdf.dao.database.DBConnectionException;
import edu.ncist.wang.hkdf.dao.database.DataBaseAccess;
import edu.ncist.wang.hkdf.dao.database.DataManipulationException;

public class AuthuserRequestHandler extends AbstractRequestHandler{

	public Map doRequestProcess(Map map){
		// TODO Auto-generated method stub

		String actionType = (String)map.get("ACTIONTYPE");
		
		if("authuser_SelectInit".equals(actionType)){
			return authuser_selectInit(map);
		}
		else if("authuser_Select".equals(actionType)){
			return authuser_select(map);
		}
		else if("authuser_AddInit".equals(actionType)){
			return authuser_addInit(map);
		}
		else if("authuser_Add".equals(actionType)){
			return authuser_add(map);
		}
		else if("authuser_StateChange".equals(actionType)){
			return authuser_stateChange(map);
		}
		else if("authuser_PasswordChange".equals(actionType)){
			return authuser_passwordChange(map);
		}
		else if("authuser_PasswordChanged".equals(actionType)){
			return authuser_passwordChanged(map);
		}
		else if("authuser_AuthrizeInit".equals(actionType)){
			return authuser_authrizeInit(map);
		}
		else if("authuser_Authrize".equals(actionType)){
			return authuser_authrize(map);
		}
		else if("authuser_CheckDup".equals(actionType)){
			return authuser_checkDup(map);
		}
		else if("authorgan_SelectInit".equals(actionType)){
			return authorgan_selectInit(map);
		}
		else if("authorgan_Select".equals(actionType)){
			return authorgan_select(map);
		}
		else if("authorgan_AddInit".equals(actionType)){
			return authorgan_addInit(map);
		}
		else if("authorgan_Add".equals(actionType)){
			return authorgan_add(map);
		}
		else if("authorgan_StateChange".equals(actionType)){
			return authorgan_stateChange(map);
		}
		else if("authorgan_CheckDup".equals(actionType)){
			return authorgan_checkDup(map);
		}
		else if("authgroup_SelectInit".equals(actionType)){
			return authgroup_selectInit(map);
		}
		else if("authgroup_Select".equals(actionType)){
			return authgroup_select(map);
		}
		else if("authgroup_AddInit".equals(actionType)){
			return authgroup_addInit(map);
		}
		else if("authgroup_Add".equals(actionType)){
			return authgroup_add(map);
		}
		else if("authgroup_StateChange".equals(actionType)){
			return authgroup_stateChange(map);
		}
		else if("authgroup_CheckDup".equals(actionType)){
			return authgroup_checkDup(map);
		}
		else if("authgroup_SetmemberInit".equals(actionType)){
			return authgroup_setmemberInit(map);
		}
		else if("authgroup_Setmember".equals(actionType)){
			return authgroup_setmember(map);
		}
		else{
			ActionTypeError();
		}

		return null;
	}

	public Map<String, String> authuser_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		return conditionMap;
	}

	public Map<String, String> authuser_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取页面查询参数
		String unitName = request.getParameter("ORG_NAME");
		String loginName = request.getParameter("PERSON_LOGINNAME");
		String userName = request.getParameter("PERSON_NAME");
		String userStatus = request.getParameter("USER_STATUS");
		
		if(null == unitName){
			unitName = "";
		}
		if(null == loginName){
			loginName = "";
		}
		if(null == userName){
			userName = "";
		}
		
		conditionMap.put("ORG_NAME", unitName);
		conditionMap.put("PERSON_LOGINNAME", loginName);
		conditionMap.put("PERSON_NAME", userName);
		conditionMap.put("USER_STATUS", userStatus);
		
		return conditionMap;
	}

	public Map<String, String> authuser_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);
		
		//获取用户登录代码
		String userCode = getParameters(request, "USER_CODE");
		conditionMap.put("USER_CODE", userCode);
		
		return conditionMap;
	}

	public Map<String, String> authuser_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		
		//获取页面查询参数
		String userName = getParameters(request, "USER_NAME");
		String gender = "男";
		if("1".equals(request.getParameter("USER_SEX"))){
			gender = "女";
		}
		String userCode = getParameters(request, "USER_CODE");
		String password = getParameters(request, "PASSWORD");
		String unitName = getParameters(request, "ORG_NAME");
		
		String telphone = getParameters(request, "TELPHONE");
		String phone = getParameters(request, "PHONE");
		String email = getParameters(request, "EMAIL");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("USER_NAME", userName);
		conditionMap.put("USER_SEX", gender);
		conditionMap.put("USER_CODE", userCode);
		conditionMap.put("PASSWORD", password);
		conditionMap.put("ORG_NAME", unitName);
		conditionMap.put("TELPHONE", telphone);
		conditionMap.put("PHONE", phone);
		conditionMap.put("EMAIL", email);

		return conditionMap;
	}

	public Map<String, String> authuser_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String userCode = getParameters(request, "USER_CODE");
		conditionMap.put("USER_CODE", userCode);
		
		return conditionMap;
	}

	public Map<String, String> authuser_passwordChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String userCode = getParameters(request, "USER_CODE");
		conditionMap.put("USER_CODE", userCode);
		
		return conditionMap;
	}

	public Map<String, String> authuser_passwordChanged(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String userCode = getParameters(request, "USER_CODE");
		
		conditionMap.put("USER_CODE", userCode);
		conditionMap.put("PASSWORDOLD", getParameters(request, "PASSWORDOLD"));
		conditionMap.put("PASSWORDNEW", getParameters(request, "PASSWORDNEW"));
		conditionMap.put("PASSWORDCFM", getParameters(request, "PASSWORDCFM"));

		return conditionMap;
	}

	public Map<String, String> authuser_authrizeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String userCode = getParameters(request, "USER_CODE");
		conditionMap.put("USER_CODE", userCode);
		
		return conditionMap;
	}

	public Map<String, String> authuser_authrize(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map conditionMap = new HashMap();
		
		//获取用户代码
		String userCode = getParameters(request, "USER_CODE");
		conditionMap.put("USER_CODE", userCode);

		conditionMap.put("ROLE_CODE", getParameterValues(request, "RCODE"));
		
		return conditionMap;
	}

	public Map<String, String> authuser_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String userCode = request.getParameter("USER_CODE");
		if(null == userCode){
			userCode = "";
		}
		conditionMap.put("USER_CODE", userCode);

		return conditionMap;
	}

	
	public Map<String, String> authorgan_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取页面查询参数
		String unitCode = "";
		String unitName = "";
		String organStatus = "";
		
		conditionMap.put("ORGAN_CODE", unitCode);
		conditionMap.put("ORGAN_NAME", unitName);
		conditionMap.put("ORGAN_STATUS", organStatus);
		
		return conditionMap;
	}

	public Map<String, String> authorgan_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取页面查询参数
		String unitCode = request.getParameter("ORGAN_CODE");
		String unitName = request.getParameter("ORGAN_NAME");
		String organStatus = request.getParameter("ORGAN_STATUS");
		
		if(null == unitCode){
			unitCode = "";
		}
		if(null == unitName){
			unitName = "";
		}
		
		conditionMap.put("ORGAN_CODE", unitCode);
		conditionMap.put("ORGAN_NAME", unitName);
		conditionMap.put("ORGAN_STATUS", organStatus);
		
		return conditionMap;
	}

	public Map<String, String> authorgan_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);
		
		conditionMap.put("ORGAN_CODE", getParameters(request, "ORGAN_CODE"));
		
		return conditionMap;
	}

	public Map<String, String> authorgan_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		
		//获取页面查询参数
		String organCode = getParameters(request, "ORGAN_CODE");
		String organName = getParameters(request, "ORGAN_NAME");
		
		String organMemo = getParameters(request, "ORGAN_MEMO");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("ORGAN_CODE", organCode);
		conditionMap.put("ORGAN_NAME", organName);
		conditionMap.put("ORGAN_MEMO", organMemo);

		return conditionMap;
	}

	public Map<String, String> authorgan_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String organCode = getParameters(request, "ORGAN_CODE");
		conditionMap.put("ORGAN_CODE", organCode);
		
		return conditionMap;
	}

	public Map<String, String> authorgan_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String organCode = request.getParameter("ORGAN_CODE");
		if(null == organCode){
			organCode = "";
		}
		conditionMap.put("ORGAN_CODE", organCode);

		return conditionMap;
	}

	public Map<String, String> authgroup_selectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取页面查询参数
		String groupCode = "";
		String groupName = "";
		String organCode = "";
		String organName = "";
		String groupStatus = "";
		
		conditionMap.put("GROUP_CODE", groupCode);
		conditionMap.put("GROUP_NAME", groupName);
		conditionMap.put("ORGAN_CODE", organCode);
		conditionMap.put("ORGAN_NAME", organName);
		conditionMap.put("GROUP_STATUS", groupStatus);
		
		
		return conditionMap;
	}

	public Map<String, String> authgroup_select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取页面查询参数
		String groupCode = request.getParameter("GROUP_CODE");
		String groupName = request.getParameter("GROUP_NAME");
		String organCode = request.getParameter("ORGAN_CODE");
		String organName = request.getParameter("ORGAN_NAME");
		String groupStatus = request.getParameter("GROUP_STATUS");
		
		if(null == groupCode){
			groupCode = "";
		}
		if(null == groupName){
			groupName = "";
		}
		if(null == organCode){
			organCode = "";
		}
		if(null == organName){
			organName = "";
		}
		
		conditionMap.put("GROUP_CODE", groupCode);
		conditionMap.put("GROUP_NAME", groupName);
		conditionMap.put("ORGAN_CODE", organCode);
		conditionMap.put("ORGAN_NAME", organName);
		conditionMap.put("GROUP_STATUS", groupStatus);
		
		return conditionMap;
	}

	public Map<String, String> authgroup_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		conditionMap.put("FLAG", flag);
		
		conditionMap.put("GROUP_CODE", getParameters(request, "GROUP_CODE"));
		conditionMap.put("ORGAN_CODE", getParameters(request, "ORGAN_CODE"));
		conditionMap.put("ORGAN_NAME", getParameters(request, "ORGAN_NAME"));
		
		return conditionMap;
	}

	public Map<String, String> authgroup_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取标志
		String flag = getParameters(request, "FLAG");
		
		//获取页面查询参数
		String organCode = getParameters(request, "ORGAN_CODE");
		String organName = getParameters(request, "ORGAN_NAME");
		
		String groupCode = getParameters(request, "GROUP_CODE");
		String groupName = getParameters(request, "GROUP_NAME");
		String groupMemo = getParameters(request, "GROUP_MEMO");
		
		conditionMap.put("FLAG", flag);
		conditionMap.put("ORGAN_CODE", organCode);
		conditionMap.put("ORGAN_NAME", organName);
		conditionMap.put("GROUP_CODE", groupCode);
		conditionMap.put("GROUP_NAME", groupName);
		conditionMap.put("GROUP_MEMO", groupMemo);

		return conditionMap;
	}

	public Map<String, String> authgroup_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取小组代码
		String groupCode = getParameters(request, "GROUP_CODE");
		conditionMap.put("GROUP_CODE", groupCode);
		
		return conditionMap;
	}

	public Map<String, String> authgroup_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		String groupCode = request.getParameter("GROUP_CODE");
		if(null == groupCode){
			groupCode = "";
		}
		conditionMap.put("GROUP_CODE", groupCode);

		return conditionMap;
	}

	public Map<String, String> authgroup_setmemberInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//获取用户代码
		String groupCode = getParameters(request, "GROUP_CODE");
		conditionMap.put("GROUP_CODE", groupCode);
		return conditionMap;
	}

	public Map<String, String> authgroup_setmember(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		
		Map conditionMap = new HashMap();
		
		//获取用户代码
		String groupCode = getParameters(request, "GROUP_CODE");
		conditionMap.put("GROUP_CODE", groupCode);

		conditionMap.put("MEMBER_CODE", getParameterValues(request, "MCODE"));
		
		return conditionMap;
	}

}
