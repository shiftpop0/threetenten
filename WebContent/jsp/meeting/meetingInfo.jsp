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
	//修改会议纪要
	function change(theOrder){
	    formPost(window.SearchState, '','meeting?ActionType=meet_AddInit&FLAG=update&MEET_CODE='+theOrder, this);
	};
	
	//删除会议纪要
	function deleterecord(theOrder){
//	alert(theOrder);		
	    formPost(window.SearchState, '','meeting?ActionType=meet_Delete&MEET_CODE='+theOrder, this);
	};


    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','meeting?ActionType=meet_SelectInit', this);
            });
        //新建立用户
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','meeting?ActionType=meet_AddInit&FLAG=add', this);
            });
    });


</script>

<body>
<div id="current">当前位置：周例会 &gt; 会议纪要</div>
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
      <td class="search_td2">
          <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
          <input id="btnadd" name="btnadd" type="button" class="btn_4" value="会议录入" />
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
<ec:table items="MEETINFO" var="row"
    action="meeting?ActionType=meet_SelectInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="userList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="inputDate" style="text-align:center;" title="录入日期" />
        <ec:column property="location" style="text-align:center;" title="会议地点" />
       <ec:column property="topic" style="text-align:center;" title="会议主题" />
       <ec:column property="attendees" style="text-align:center;" title="参会人" />
       <ec:column property="memo" style="text-align:center;" title="说明" />
        <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        	<a href='<c:out value="${row.recordurl}"/>' >查看会议纪要</a> 
        	|<a href="#" onClick="deleterecord('<c:out value="${row.theOrder}"/>')">删除纪要</a>
        	|<a href="#" onClick="change('<c:out value="${row.theOrder}"/>')">修改纪要</a>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>