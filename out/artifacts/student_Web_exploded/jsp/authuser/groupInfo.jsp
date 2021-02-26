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
//修改小组状态：使用->停用；停用->使用
function state_change(groupCode){
    $.ajax({
        type : "post",
        dataType : "text",
        url : "authuser?ActionType=authgroup_StateChange&GROUP_CODE="+groupCode,
        data : {},
        success : function(massage) {
            if (massage == 'success') {
            	goTo("authuser?ActionType=authgroup_SelectInit");
            }
        }
    });
};

//小组成员管理
function setmember(groupCode){
    formPost(window.SearchState, '','authuser?ActionType=authgroup_SetmemberInit&GROUP_CODE='+groupCode, this);
};


	//状态框回显
	$(function() {
	$("select[name='GROUP_STATUS']")
	.val(
	'<c:out value="${CONDITIONMAP.GROUP_STATUS}" />');
	});
	
    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','authuser?ActionType=authgroup_Select', this);
            });
        //新建立角色
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','authuser?ActionType=authgroup_AddInit&FLAG=add', this);
            });
    });

</script>
<body>
<div id="current">当前位置：用户管理 &gt; 小组管理</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="6" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="12%" class="search_td1">小组编号：</td>
      <td class="search_td2">
        <input name="GROUP_CODE" type="text" size="16" value="<c:out value='${CONDITIONMAP.GROUP_CODE}' />" />
      </td>
      <td width="12%" class="search_td1">小组名称：</td>
      <td width="15%" class="search_td2">
        <input name="GROUP_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.GROUP_NAME}' />" />
         <input name="ORGAN_NAME" id="ORGAN_NAME" type="hidden"  value="<c:out value='${CONDITIONMAP.ORGAN_NAME}' />" />
         <input type="hidden" name="ORGAN_CODE" id="ORGAN_CODE" value="<c:out value="${CONDITIONMAP.ORGAN_CODE}"/>" />
      </td>
      <td width="12%" class="search_td1">状态：</td>
      <td width="8%" class="search_td2">
        <select name = "GROUP_STATUS" id = "GROUP_STATUS">
         	<option value = "">全部</option>
        	<option value = "0">未使用</option>
        	<option value = "1" >使用中</option>
        </select>
      </td>
      <td class="search_td2">
      <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
      <input id="btnadd" name="btnadd" type="button" class="btn_4" value="新建小组" /></td>
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
<ec:table items="GROUPINFO" var="row"
    action="authuser?ActionType=authgroup_SelectInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="groupList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="groupCode" style="text-align:center;" title="小组号" />
        <ec:column property="groupName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="小组名" />
        <ec:column property="organName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="所在单位" />
        <ec:column property="EDIT" style="text-align:center;" title="状态">
 		     <c:choose>
			    <c:when test="${row.isUse == '1'}">使用中
			    </c:when>
			    <c:otherwise>未使用
			    </c:otherwise>
		    </c:choose>
        </ec:column>
        <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        	<a href="#" onClick="formPost(window.SearchState,'','authuser?ActionType=authgroup_AddInit&FLAG=update&GROUP_CODE=<c:out value="${row.groupCode}"/>',this);">修改</a> 
        	|<a href="#" onClick="setmember('<c:out value="${row.groupCode}"/>')">成员管理</a>
		     <c:choose>
			    <c:when test="${row.isUse == '1'}">
		   			|<a href="#" onClick="state_change('<c:out value="${row.groupCode}"/>')">停用</a>
			    </c:when>
			    <c:otherwise>
		   			|<a href="#" onClick="state_change('<c:out value="${row.groupCode}"/>')">启用</a>
			    </c:otherwise>
		    </c:choose>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>