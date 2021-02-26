<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	
	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<script>


    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','attendance?ActionType=basictime_DetailInit', this);
            });
        //返回
        $("#btnreturn").click(
            function() {
                formPost(window.SearchState, '','attendance?ActionType=basictime_SelectInit', this);
            });
    });
</script>
<body>
<div id="current">当前位置：学生考勤 &gt; 设置基础时间&gt; 查看基础时间</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="6" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="12%" class="search_td1">设置时间：</td>
      <td width="15%" class="search_td2">
        <input name="CREATE_TIME" type="text" size="16" value="<c:out value='${CONDITIONMAP.CREATE_TIME}' />" />
      </td>
      <td width="12%" class="search_td1">学生姓名：</td>
      <td width="15%" class="search_td2">
        <input name="STUDENT_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.STUDENT_NAME}' />" />
      </td>
      <td width="12%" class="search_td1">教师姓名：</td>
      <td width="15%" class="search_td2">
        <input name="TEACHER_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.TEACHER_NAME}' />" />
      </td>
      <td class="search_td2">
        <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
        <input id="btnreturn" name="btnadd" type="button" class="btn_4" value="返回" />
      </td>
    </tr>
  </table>
  </form>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="search_shadow"></td>
    </tr>
    <tr>
        <td class="main_blank"></td>
    </tr>
</table>
<!--数据列表-START-->
<ec:table items="TIMEINFO" var="row"
    action="attendance?ActionType=basictime_DetailInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="timeList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="startDate" style="text-align:center;" title="开始时间" />
        <ec:column property="endDate" style="text-align:center;" title="结束时间" />
        <ec:column property="timeLength" style="text-align:center;" title="时间长度" />
        <ec:column property="studentName" style="text-align:center;" title="学生姓名" />
        <ec:column property="techerName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="教师姓名" />
        <ec:column property="createTime" style="text-align:center;" title="设置时间" />
    </ec:row>
</ec:table>
</body>
</html>