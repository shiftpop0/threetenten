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

	//修改用户状态：使用->停用；停用->使用
	function state_change(userCode){
        $.ajax({
            type : "post",
            dataType : "text",
            url : "authuser?ActionType=authuser_StateChange&USER_CODE="+userCode,
            data : {},
            success : function(massage) {
                if (massage == 'success') {
                	goTo("authuser?ActionType=authuser_SelectInit");
                }
            }
        });
	};

	//修改密码
	function password_changed(userCode){
        formPost(window.SearchState, '','authuser?ActionType=authuser_PasswordChange&USER_CODE='+userCode, this);
	};

	//授权
	function authorize(userCode){
        formPost(window.SearchState, '','authuser?ActionType=authuser_AuthrizeInit&USER_CODE='+userCode, this);
	};

	//状态框回显
	$(function() {
	$("select[name='USER_STATUS']")
	.val(
	'<c:out value="${CONDITIONMAP.USER_STATUS}" />');
	});
	
    $(function() {
        //查询
        $("#btnsearch").click(
            function() {
                formPost(window.SearchState, '','authuser?ActionType=authuser_Select', this);
            });
        //新建立用户
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','authuser?ActionType=authuser_AddInit&FLAG=add', this);
            });
    });
</script>
<body>
<div id="current">当前位置：权限管理 &gt; 用户管理</div>
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="4" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>

    <tr>
      <td width="12%" class="search_td1">单位：</td>
      <td width="25%" class="search_td2">
        <input name="ORG_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.ORG_NAME}' />" />
      </td>
      <td width="12%" class="search_td1">登录名：</td>
      <td colspan="2" class="search_td2">
        <input name="PERSON_LOGINNAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.PERSON_LOGINNAME}' />" />
      </td>
    </tr>
    <tr>
      <td width="12%" class="search_td1">用户姓名：</td>
      <td width="25%" class="search_td2">
        <input name="PERSON_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.PERSON_NAME}' />" />
      </td>
      <td width="12%" class="search_td1">状态：</td>
      <td width="26%" class="search_td2">
        <select name = "USER_STATUS" id = "USER_STATUS">
         	<option value = "">全部</option>
        	<option value = "0">未使用</option>
        	<option value = "1" >使用中</option>
        </select>
      </td>
      <td class="search_td2">
      <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
      <input id="btnadd" name="btnadd" type="button" class="btn_4" value="新建用户" /></td>
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
<ec:table items="USERINFO" var="row"
    action="authuser?ActionType=authuser_SelectInit"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="userList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="deptName" style="text-align:center;" title="单位" />
        <ec:column property="userCode" style="text-align:center;" title="登录名称" />
        <ec:column property="userName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="用户姓名" />
        <ec:column property="EDIT" style="text-align:center;" title="状态">
 		     <c:choose>
			    <c:when test="${row.isUse == '1'}">使用中
			    </c:when>
			    <c:otherwise>未使用
			    </c:otherwise>
		    </c:choose>
        </ec:column>
        <ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
        	<a href="#" onClick="formPost(window.SearchState,'','authuser?ActionType=authuser_AddInit&FLAG=update&USER_CODE=<c:out value="${row.userCode}"/>',this);">修改</a> 
        	|<a href="#" onClick="password_changed('<c:out value="${row.userCode}"/>')">修改密码</a>
        	|<a href="#" onClick="authorize('<c:out value="${row.userCode}"/>')">授权</a>
		     
		     <c:choose>
			    <c:when test="${row.isUse == '1'}">
		   			|<a href="#" onClick="state_change('<c:out value="${row.userCode}"/>')">停用</a>
			    </c:when>
			    <c:otherwise>
		   			|<a href="#" onClick="state_change('<c:out value="${row.userCode}"/>')">启用</a>
			    </c:otherwise>
		    </c:choose>
        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>