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
	//详细交流信息
	function viewrecord(theOrder){
	    formPost(window.SearchState, '','lecture?ActionType=lecture_Detail&LECT_CODE='+theOrder, this);
	};
	
	//修改交流信息记录
	function change(theOrder){
	    formPost(window.SearchState, '','lecture?ActionType=lecture_AddInit&FLAG=update&LECT_CODE='+theOrder, this);
	};
	
	//删除交流信息记录
	function deleterecord(theOrder){
//	    formPost(window.SearchState, '','lecture?ActionType=lecture_Delete&LECT_CODE='+theOrder, this);
        $.ajax({
            type : "post",
            dataType : "text",
            url : "lecture?ActionType=lecture_Delete&LECT_CODE="+theOrder,
            data : {},
            success : function(massage) {
                if (massage == 'success') {
                	goTo("lecture?ActionType=lecture_SelectInit");
                }
            }
        });
	};

	$(function() {
		//类型框回显
	    $("select[name='LECT_TYPE']").val('<c:out value="${CONDITIONMAP.LECT_TYPE}" />');
	});

    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','lecture?ActionType=lecture_SelectInit', this);
            });
        //新建交流记录
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','lecture?ActionType=lecture_AddInit&FLAG=add', this);
            });
    });


</script>

<body>
<div id="current">当前位置：学术交流 &gt; 交流信息</div>
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
      <td width="12%" class="search_td1">类型：</td>
      <td width="15%" class="search_td2">
        <select name = "LECT_TYPE" id = "USER_STATUS">
         	<option value = "">全部</option>
        	<option value = "0">讲座</option>
        	<option value = "1">论文</option>
        	<option value = "2" >图书</option>
        </select>
      </td>
      <td class="search_td2">
          <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
          <input id="btnadd" name="btnadd" type="button" class="btn_4" value="录入" />
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
<ec:table items="LECTINFO" var="row"
    action="lecture?ActionType=lecture_SelectInit"
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
        <ec:column property="EDIT" style="text-align:center;" title="类型">
 		     <c:choose>
			    <c:when test="${row.type == '0'}">讲座
			    </c:when>
			    <c:when test="${row.type == '1'}">论文
			    </c:when>
			    <c:otherwise>图书
			    </c:otherwise>
		    </c:choose>
        </ec:column>
       <ec:column property="memo" style="text-align:center;" title="说明" />
        <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
<!----> 
        	<a href="#" onClick="viewrecord('<c:out value="${row.theOrder}"/>')">详细信息</a> 
 
        	|<a href="#" onClick="deleterecord('<c:out value="${row.theOrder}"/>')">删除</a>
        	|<a href="#" onClick="change('<c:out value="${row.theOrder}"/>')">修改</a>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>