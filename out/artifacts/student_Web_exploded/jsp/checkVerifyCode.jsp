<%@ page pageEncoding="utf-8"%>
<%
	String str = request.getParameter("cCode");
	if(str.equals(session.getAttribute("certCode").toString())){
		out.print("ok");
	}else{
		out.print("error");
	}
%>
