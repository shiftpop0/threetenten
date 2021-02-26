<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/jquery.validate.js" type = "text/javascript"></script>
	<script src = "../js/messages_zh.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	
	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
	<title></title>
	<style>
		#AddTimeForm label.error {
			margin-left: 10px;
			width: auto;
			color:red;
			display: inline;
		}
	</style>

<script>
    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','report?ActionType=report_SelectInit', this);
            });
    });
</script>

</head>
<body>
   	<div id="current">当前位置：年度报告 &gt; 汇总报告</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="2" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="12%" class="search_td1">汇总年度：</td>
      <td width="15%" class="search_td2">
        <input name="INPUT_YEAR" id="INPUT_YEAR" type="text" size="16" value="<c:out value='${CONDITIONMAP.INPUT_YEAR}' />" />
      </td>
      <td class="search_td2">
          <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
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
<ec:table items="REPTINFO" var="row"
    action="report?ActionType=report_SelectInit"
    view="html" 
    autoIncludeParameters="false"
     >
     <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="userName" style="text-align:center;" title="学生姓名" />
        <ec:column property="workDays" style="text-align:center;" title="工作标准天数" />
       <ec:column property="paperTimes" style="text-align:center;" title="论文点评次数" />
       <ec:column property="lectTimes" style="text-align:center;" title="讲座点评次数" />
       <ec:column property="bookTimes" style="text-align:center;" title="图书点评次数" />
    </ec:row>
</ec:table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="search_shadow">说明：</td>
    </tr>
    <tr>
        <td class="search_shadow">1. 工作标准天数--工作时间7个小时折合成一个标准天</td>
    </tr>
    <tr>
        <td class="search_shadow">2. 论文点评次数--参与论文点评的次数，讲座点评次数和图书点评次数是指分别参与点评的次数</td>
    </tr>
</table>
</body>
</html>