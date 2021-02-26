<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/jquery-1.12.1.js" type="text/javascript"></script>
<script src="../js/common.js" type="text/javascript"></script>

<link href="<%=request.getContextPath()%>/css/extremecomponents.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/global.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<title>Insert title here</title>
</head>
<body>
	<div class="page-container">

	<div class="mt-20">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			    <tr>
			        <td class="search_shadow"></td>
			    </tr>
			    <tr>
			        <td class="main_blank"></td>
			    </tr>
			</table>
			
			<ec:table items="USERLIST" var="row"
				action="enterpage?ActionType=enterpage_SelectEnroll"
			    view="html" >
			   <ec:row>
			    <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        		<ec:column property="stuName" style="text-align:center;" title="名字" />
        		<ec:column property="wish1" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="第一志愿" />
        		<ec:column property="wish2" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="第二志愿" />
        		<ec:column property="wish3" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="第三志愿" />
        		<ec:column property="wish4" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="第四志愿" />
        		<ec:column property="wish5" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="第五志愿" />
        		<ec:column property="wish6" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="第六志愿" />		
				</ec:row>
			</ec:table>
		</div>
		</div>
</body>
</html>