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
        //汇总
        $("#btnsum").click(
            function() {
                formPost(window.SearchState, '','attendance?ActionType=worktime_SumInit', this);
            });
    });
</script>
<body>
<div id="current">当前位置：学生考勤 &gt; 工作时间汇总</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="2" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="35%" class="search_td1">统计年份：</td>
      <td width="35%" class="search_td2">
        <input name="SUM_YEAR" id="SUM_YEAR" type="text" size="16" value="<c:out value='${CONDITIONMAP.SUM_YEAR}' />" />
      </td>
     <td class="search_td2">
      <input id="btnsum" name="btnsum" type="button" class="btn_2" value="汇总" />
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
    action="attendance?ActionType=worktime_SumInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="timeList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="EDIT" style="text-align:center;" title="学生编号" viewsDenied="xls">
        	<a href="#" onClick="formPost(window.SearchState,'','attendance?ActionType=worktime_SumDetailInit&STUDENT_CODE=<c:out value="${row.inputStudentCode}"/>',this);"><c:out value="${row.inputStudentCode}"/></a> 
        </ec:column>
        <ec:column property="userName" style="text-align:center;" title="学生姓名" />
        <ec:column property="bsTime" style="text-align:center;" title="基础时间(分钟)" />
        <ec:column property="workTime" style="text-align:center;" title="工作时间(分钟)" />
        <ec:column property="ttTime" style="text-align:center;" title="总计时间(标准天)" />
    </ec:row>
</ec:table>
</body>
</html>