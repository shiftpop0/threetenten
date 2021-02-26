<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="image/jpeg" %>
<jsp:useBean id="image" scope="page" class="edu.ncist.wang.util.VerifyCode" />
<%
	String str=image.imageOut(response.getOutputStream(), 60, 22);
	// 将认证码存入SESSION
	session.setAttribute("VerifyCode", str);
	out.clear();
	out = pageContext.pushBody();
%>