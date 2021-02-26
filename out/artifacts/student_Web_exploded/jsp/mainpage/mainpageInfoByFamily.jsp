	<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	
	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<title>Insert title here</title>
</head>
	<body>
	<div class="page-container">
	<form name="SearchState" method="post"
			Action="mainpage?ActionType=mainpage_SelectInfoByFamily&FNAME=<c:out value='${requestScope.FNAME }'/>&YEAR=<c:out value='${requestScope.YEAR }'/>">
			<div class="text-c">
			
				省份: <span class="select-box inline"> <select class="select"
					name="family">
						<option value="all">全部</option>
						<c:forEach items="${FAMILYLIST}" var="family">
							<option value="${family.familyName }">${family.familyName }</option>
						</c:forEach>
				</select>
				</span> <input type="submit" class="btn btn-primary radius" name="button"
					value="查询" />
			</div>
		</form>
		<div class="mt-20">
			<!--数据列表-START-->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			    <tr>
			        <td class="search_shadow"></td>
			    </tr>
			    <tr>
			        <td class="main_blank"></td>
			    </tr>
			</table>
			
			<ec:table items="USERLIST" var="row"
			    action="mainpage?ActionType=mainpage_SelectInfoByFamily"
			    view="html" 
			     >
			   <ec:row>
			    <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        		<ec:column property="majorName" style="text-align:center;" title="专业" />
        		<ec:column property="familyName" style="text-align:center;" title="省份" />	
        		<ec:column property="stuyear" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="年份" />
        		<ec:column property="famsum" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="报考人数" />
        		<ec:column property="regRate" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="报考率" />
        		<ec:column property="avgScore" style="text-align:center;" title="平均分" />
				<ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        			<a href="#" onClick="formPost(window.SearchState,'','mainpage?ActionType=mainpage_SelectAll1&FLAG=major&FNAME=<c:out value='${row.stuMajor }'/>&YEAR=<c:out value='${row.stuyear }'/>',this);">详情</a> 
        		</ec:column>
				</ec:row>
			</ec:table>
		</div>
	</div>
	</body>
</html>
