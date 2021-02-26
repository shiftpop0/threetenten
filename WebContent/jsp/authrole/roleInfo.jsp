<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


    <script src="../../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/dark.js"></script>
    <script type="text/javascript" src="../../js/xadmin.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>

    <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../lib/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="../../css/xadmin.css">
    <link rel="stylesheet" href="../../css/echartsHtmlbase.css">
    <style type="text/css">
        body{
            background: #333;
        }
    </style>
<title>Insert title here</title>
</head>
<script>
//修改角色状态：使用->停用；停用->使用
function state_change(roleCode){
    $.ajax({
        type : "post",
        dataType : "text",
        url : "authrole?ActionType=authrole_StateChange&ROLE_CODE="+roleCode,
        data : {},
        success : function(massage) {
            if (massage == 'success') {
            	goTo("authrole?ActionType=authrole_SelectInit");
            }
        }
    });
};

//授权
function authorize(roleCode){
    formPost(window.SearchState, '','authrole?ActionType=authrole_AuthrizeInit&ROLE_CODE='+roleCode, this);
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
                formPost(window.SearchState, '','authrole?ActionType=authrole_Select', this);
            });
        //新建立角色
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','authrole?ActionType=authrole_AddInit&FLAG=add', this);
            });
    });

</script>
<body>
<%--<div id="current">当前位置：权限管理 &gt; 角色管理</div>--%>

<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">角色管理</a>
        <a><cite>角色信息</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<div class="layui-card-body ">
<form name="SearchState" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
<%--    <tr class="search_title_tr">--%>
<%--      <td colspan="6" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>--%>
<%--      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>--%>
<%--    </tr>--%>

<%--    <tr>--%>
<%--      <td width="12%" class="search_td1">角色码：</td>--%>
<%--      <td class="search_td2">--%>
<%--        <input name="ROLE_CODE" type="text" size="16" value="<c:out value='${CONDITIONMAP.ROLE_CODE}' />" />--%>
<%--      </td>--%>
<%--      <td width="12%" class="search_td1">角色名：</td>--%>
<%--      <td width="15%" class="search_td2">--%>
<%--        <input name="ROLE_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.ROLE_NAME}' />" />--%>
<%--      </td>--%>
<%--      <td width="12%" class="search_td1">状态：</td>--%>
<%--      <td width="8%" class="search_td2">--%>
<%--        <select name = "USER_STATUS" id = "USER_STATUS">--%>
<%--         	<option value = "">全部</option>--%>
<%--        	<option value = "0">未使用</option>--%>
<%--        	<option value = "1" >使用中</option>--%>
<%--        </select>--%>
<%--      </td>--%>
<%--      <td class="search_td2">--%>
<%--      <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />--%>
<%--      <input id="btnadd" name="btnadd" type="button" class="btn_4" value="新建角色" /></td>--%>
<%--    </tr>--%>
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

<ec:table items="ROLEINFO" var="row"
    action="authrole?ActionType=authrole_SelectInit"
    view="html"
    autoIncludeParameters="false"
     >

    <ec:exportXls fileName="roleList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="roleCode" style="text-align:center;" title="角色号" />
        <ec:column property="roleName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="角色名" />
        <ec:column property="EDIT" style="text-align:center;" title="状态">
 		     <c:choose>
			    <c:when test="${row.isUse == '1'}">使用中
			    </c:when>
			    <c:otherwise>未使用
			    </c:otherwise>
		    </c:choose>
        </ec:column>
        <ec:column property="EDIT" style="text-align:center;" title="操作(更改操作后请刷新)" viewsDenied="xls">
        	<a href="#" onClick="formPost(window.SearchState,'','authrole?ActionType=authrole_AddInit&FLAG=update&ROLE_CODE=<c:out value="${row.roleCode}"/>',this);">修改</a>
        	|<a href="#" onClick="authorize('<c:out value="${row.roleCode}"/>')">授权</a>
		     <c:choose>
			    <c:when test="${row.isUse == '1'}">
		   			|<a href="#" onClick="state_change('<c:out value="${row.roleCode}"/>')">停用</a>
			    </c:when>
			    <c:otherwise>
		   			|<a href="#" onClick="state_change('<c:out value="${row.roleCode}"/>')">启用</a>
			    </c:otherwise>
		    </c:choose>
        </ec:column>
    </ec:row>
</ec:table>
</div>
</body>
</html>