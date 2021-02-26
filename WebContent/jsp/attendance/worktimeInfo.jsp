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

	//状态框回显
	$(function() {
	$("select[name='TIME_STATUS']")
	.val(
	'<c:out value="${CONDITIONMAP.TIME_STATUS}" />');
	});
	
    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','attendance?ActionType=worktime_SelectInit', this);
            });
        //新建立用户
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','attendance?ActionType=worktime_AddInit&FLAG=add', this);
            });
    });

	//删除时间记录
	function deletetime(timeCode){
        $.ajax({
            type : "post",
            dataType : "text",
            url : "attendance?ActionType=worktime_Delete&TIME_CODE="+timeCode,
            data : {},
            success : function(massage) {
                if (massage == 'success') {
                	goTo("attendance?ActionType=worktime_SelectInit");
                }
            }
        });
	};


</script>

<body>
<div id="current">当前位置：学生考勤 &gt; 工作时间录入</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="6" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="12%" class="search_td1">开始日期：</td>
      <td width="15%" class="search_td2">
        <input name="START_TIME" id="START_TIME" type="text" size="16" value="<c:out value='${CONDITIONMAP.START_TIME}' />" />
      </td>
      <td width="12%" class="search_td1">结束日期：</td>
      <td width="15%" class="search_td2">
        <input name="END_TIME" id="END_TIME" type="text" size="16" value="<c:out value='${CONDITIONMAP.END_TIME}' />" />
      </td>
      <td width="12%" class="search_td1">状态：</td>
      <td width="15%" class="search_td2">
        <select name = "TIME_STATUS" id = "TIME_STATUS">
         	<option value = "">全部</option>
        	<option value = "0">未审核</option>
        	<option value = "1" >已审核</option>
        </select>
      </td>
      <td class="search_td2">
          <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
          <input id="btnadd" name="btnadd" type="button" class="btn_4" value="时间录入" />
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
    action="attendance?ActionType=worktime_SelectInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="userList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="inputDate" style="text-align:center;" title="录入日期" />
        <ec:column property="morningBeginTime" style="text-align:center;" title="上午开始" />
       <ec:column property="morningEndTime" style="text-align:center;" title="上午结束" />
       <ec:column property="noonBeginTime" style="text-align:center;" title="下午开始" />
       <ec:column property="noonEndTime" style="text-align:center;" title="下午结束" />
       <ec:column property="nightBeginTime" style="text-align:center;" title="晚上开始" />
       <ec:column property="nightEndTime" style="text-align:center;" title="晚上结束" />
         <ec:column property="EDIT" style="text-align:center;" title="状态">
 		     <c:choose>
			    <c:when test="${row.status == '0'}">未审核
			    </c:when>
			    <c:otherwise>已审核
			    </c:otherwise>
		    </c:choose>
        </ec:column>
        <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        	<a href="#" onClick="formPost(window.SearchState,'','attendance?ActionType=worktime_AddInit&FLAG=update&TIME_CODE=<c:out value="${row.theOrder}"/>',this);">修改</a> 
        	|<a href="#" onClick="deletetime('<c:out value="${row.theOrder}"/>')">删除</a>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>