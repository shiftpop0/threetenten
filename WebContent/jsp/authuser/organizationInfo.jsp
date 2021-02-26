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
//修改单位状态：使用->停用；停用->使用
function state_change(organCode){
    $.ajax({
        type : "post",
        dataType : "text",
        url : "authuser?ActionType=authorgan_StateChange&ORGAN_CODE="+organCode,
        data : {},
        success : function(massage) {
            if (massage == 'success') {
            	goTo("authuser?ActionType=authorgan_SelectInit");
            }
        }
    });
};

	//状态框回显
	$(function() {
	$("select[name='ORGAN_STATUS']")
	.val(
	'<c:out value="${CONDITIONMAP.ORGAN_STATUS}" />');
	});
	
    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','authuser?ActionType=authorgan_Select', this);
            });
        //新建立单位
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','authuser?ActionType=authorgan_AddInit&FLAG=add', this);
            });
    });

</script>
<body>
<div id="current">当前位置：用户管理 &gt; 单位管理</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="6" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="12%" class="search_td1">单位码：</td>
      <td class="search_td2">
        <input name="ORGAN_CODE" type="text" size="16" value="<c:out value='${CONDITIONMAP.ORGAN_CODE}' />" />
      </td>
      <td width="12%" class="search_td1">单位号：</td>
      <td width="15%" class="search_td2">
        <input name="ORGAN_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.ORGAN_NAME}' />" />
      </td>
      <td width="12%" class="search_td1">状态：</td>
      <td width="8%" class="search_td2">
        <select name = "ORGAN_STATUS" id = "ORGAN_STATUS">
         	<option value = "">全部</option>
        	<option value = "0">未使用</option>
        	<option value = "1" >使用中</option>
        </select>
      </td>
      <td class="search_td2">
      <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
      <input id="btnadd" name="btnadd" type="button" class="btn_4" value="新建单位" /></td>
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
<ec:table items="ORGANINFO" var="row"
    action="authorgan?ActionType=authorgan_SelectInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="roleList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="organCode" style="text-align:center;" title="单位号" />
        <ec:column property="organName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="单位名" />
        <ec:column property="EDIT" style="text-align:center;" title="状态">
 		     <c:choose>
			    <c:when test="${row.isUse == '1'}">使用中
			    </c:when>
			    <c:otherwise>未使用
			    </c:otherwise>
		    </c:choose>
        </ec:column>
        <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        	<a href="#" onClick="formPost(window.SearchState,'','authuser?ActionType=authorgan_AddInit&FLAG=update&ORGAN_CODE=<c:out value="${row.organCode}"/>',this);">修改</a> 
 		     <c:choose>
			    <c:when test="${row.isUse == '1'}">
		   			|<a href="#" onClick="state_change('<c:out value="${row.organCode}"/>')">停用</a>
			    </c:when>
			    <c:otherwise>
		   			|<a href="#" onClick="state_change('<c:out value="${row.organCode}"/>')">启用</a>
			    </c:otherwise>
		    </c:choose>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>