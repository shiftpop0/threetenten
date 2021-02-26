	<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div id="current">当前位置：筛选查询 &gt; 录取学生中学信息</div>
	<div class="page-container">
		<form name="SearchState" method="post"
			Action="stuusers?ActionType=stuusers_MiddleSchool_Province">
			<div class="text-c">
				省份: <span class="select-box inline"> 
				<select class="select" name="PROVINCE">
						<option value="all">全部</option>
						<c:forEach items="${PROVINCELIST}" var="PROVINCE">
							<option value="${PROVINCE.DQMC}">${PROVINCE.DQMC}</option>
						</c:forEach>
				</select>
				</span> 
				
				<input type="submit" class="btn btn-primary radius" name="button"
					value="查询" />
			</div>
		</form>
		<form name="SearchState" method="post"
			Action="stuusers?ActionType=stuusers_MiddleSchool_Init&FLAG=Export">
			<div class="text-c">
				<input type="submit" class="btn btn-primary radius" name="button"
					value="导出Excle表" />
			</div>
		</form>
		<div class="mt-20">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			    <tr>
			        <td class="search_shadow"></td>
			    </tr>
			    <tr>
			        <td class="main_blank"></td>
			    </tr>
			</table>
			
			<ec:table items="SCHOOLLIST" var="row"
			    action="stuusers?ActionType=stuusers_MiddleSchool_Init"
			    view="html" 
			     >
			   <ec:row>
			    <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        		<ec:column property="DQMC" style="text-align:center;" title="省份" />
        		<ec:column property="ZXDM" style="text-align:center;" title="中学名称"/>
        		<ec:column property="ZXMC" style="text-align:center;" title="中学名称"/>
        		<ec:column property="stusum" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="录取人数"/>
        		<ec:column property="YZBM" style="text-align:center;" title="邮政编码" />
        		<%-- <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        			<a href="#" onClick="formPost(window.SearchState,'','mainpage?ActionType=mainpage_SelectAll&Flag=SC&YEAR=<c:out value='${row.stuyear }'/>&MAJOR=<c:out value='${row.stuMajor1 }'/>',this);">详情</a> 
        		</ec:column> --%>
				</ec:row>
			</ec:table>
		</div>
	</div>
</body>
</html>