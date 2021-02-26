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
<div id="current">当前位置：学生信息&gt; 对应条件的学生信息详情</div>
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
				action="mainpage?ActionType=mainpage_SelectAll"
			    view="html" >
			   <ec:row>
			    <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        		<ec:column property="KSH" style="text-align:center;" title="考生号" />
        		<ec:column property="XM" style="text-align:center;" title="姓名" />
		   		<ec:column property="DQMC" style="text-align:center;" title="省份" />
           		<ec:column property="ZXMC" style="text-align:center;" title="高中学校" />
        		<ec:column property="ZYMC" style="text-align:center;" title="录取专业" />
	        	<ec:column property="KLMC" style="text-align:center;" title="专业类别" />
	        	<ec:column property="LQFSMC" style="text-align:center;" title="录取方式" />
        		<ec:column property="CJ" style="text-align:center;" title="成绩" />
        		<ec:column property="YEAR1" style="text-align:center;" title="入学年份" />
				</ec:row>
			</ec:table>
		</div>
</body>
</html>