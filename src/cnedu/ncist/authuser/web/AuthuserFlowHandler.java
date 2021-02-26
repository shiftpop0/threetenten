package cnedu.ncist.authuser.web;

import java.util.Map;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ncist.wang.hkdf.bussiness.blo.AbstractFlowHandler;
import edu.ncist.wang.util.AjaxOperation;

public class AuthuserFlowHandler extends AbstractFlowHandler {

	public String doFlowProcess(Map paraMap) {
		// TODO Auto-generated method stub

		String actionType = (String)paraMap.get("ACTIONTYPE");
		
		if("authuser_SelectInit".equals(actionType)){
			return authuser_SelectInit(paraMap);
		}
		else if("authuser_Select".equals(actionType)){
			return authuser_Select(paraMap);
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
			return authuser_PasswordChange(paraMap);
		}
		else if("authuser_PasswordChanged".equals(actionType)){
			return authuser_PasswordChanged(paraMap);
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
			return authorgan_Select(paraMap);
		}
		else if("authorgan_Select".equals(actionType)){
			return authorgan_Select(paraMap);
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
			return authgroup_Select(paraMap);
		}
		else if("authgroup_Select".equals(actionType)){
			return authgroup_Select(paraMap);
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

	public String authuser_SelectInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authuser_Select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}
	
	public String authuser_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO1", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authuser_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authuser_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authuser_PasswordChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authuser_PasswordChanged(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authuser_authrizeInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("USERROLE", map.get("USERROLE"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authuser_authrize(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");

		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authuser_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("USERINFO", map.get("USERINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authorgan_Select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("ORGANINFO", map.get("ORGANINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}
	
	public String authorgan_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("ORGANINFO", map.get("ORGANINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authorgan_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authorgan_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("ORGANINFO", map.get("ORGANINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authorgan_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("ORGANINFO", map.get("ORGANINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authgroup_Select(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("GROUPINFO", map.get("GROUPINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}
	
	public String authgroup_addInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("GROUPINFO", map.get("GROUPINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authgroup_add(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authgroup_stateChange(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("GROUPINFO", map.get("GROUPINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authgroup_checkDup(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");
		Map map = (Map)paraMap.get("RESULTMAP");

		request.setAttribute("GROUPINFO", map.get("GROUPINFO"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authuser_setmemberInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("GROUPMEMBER", map.get("GROUPMEMBER"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authuser_setmember(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");

		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

	public String authgroup_setmemberInit(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("GROUPMEMBER", map.get("GROUPMEMBER"));
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		
		return (String)map.get("RESULT");
	}

	public String authgroup_setmember(Map paraMap){
		
		HttpServletRequest request = (HttpServletRequest)paraMap.get("REQUEST");
		HttpServletResponse response = (HttpServletResponse)paraMap.get("RESPONSE");

		Map map = (Map)paraMap.get("RESULTMAP");

		
		request.setAttribute("CONDITIONMAP", map.get("CONDITIONMAP"));
		AjaxOperation.ajaxToHtml(response, (String)map.get("RESULT"));

		return "ajax";
	}

}
