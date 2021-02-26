<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%--    <script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>--%>
<%--    <script src = "../js/common.js" type = "text/javascript"></script>--%>
    <script src="../../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../js/dark.js"></script>
    <script type="text/javascript" src="../../js/xadmin.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
    <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../../css/font.css">
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

    function state_change(menuCode){
        $.ajax({
            type : "post",
            dataType : "text",
            url : "authmenu?ActionType=authmenu_StateChange&MENU_CODE="+menuCode,
            data : {},
            success : function(massage) {
                if (massage == 'success') {
                    goTo("authmenu?ActionType=authmenu_SelectInit");
                }
            }
        });
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
                formPost(window.SearchState, '','authmenu?ActionType=authmenu_Select', this);
            });
        //新建立角色
        $("#btnadd").click(
            function() {
                formPost(window.SearchState, '','authmenu?ActionType=authmenu_AddInit&FLAG=add', this);
            });
    });

</script>
<body>
<%--<div id="current">当前位置：权限管理 &gt; 菜单管理</div>--%>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">菜单管理</a>
        <a><cite>菜单信息</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<div class="layui-card-body ">
<form name="SearchState" method="post">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
<%--        <tr class="search_title_tr">--%>
<%--            <td colspan="6" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>--%>
<%--            <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>--%>
<%--        </tr>--%>

 <%--       <tr>
            <td width="12%" class="search_td1">菜单号：</td>
            <td class="search_td2">
                <input name="MENU_CODE" type="text" size="16" value="<c:out value='${CONDITIONMAP.MENU_CODE}' />" />
            </td>
            <td width="12%" class="search_td1">菜单名：</td>
            <td width="15%" class="search_td2">
                <input name="MENU_NAME" type="text" size="16" value="<c:out value='${CONDITIONMAP.MENU_NAME}' />" />
            </td>
            <td width="12%" class="search_td1">状态：</td>
            <td width="8%" class="search_td2">
                <select name = "USER_STATUS" id = "USER_STATUS">
                    <option value = "">全部</option>
                    <option value = "0">未使用</option>
                    <option value = "1" >使用中</option>
                </select>
            </td>
            <td class="search_td2">
                <input id="btnsearch" name="btnsearch" type="button" class="btn_2" value="查询" />
                <input id="btnadd" name="btnadd" type="button" class="btn_4" value="新建菜单" /></td>
        </tr>
        --%>
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
<ec:table items="MENUINFO" var="row"
          action="authmenu?ActionType=authmenu_SelectInit"
          view="html"
          autoIncludeParameters="false"
>

    <ec:exportXls fileName="roleList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount"  cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="menuCode" style="text-align:center;" title="菜单号" />
        <ec:column property="menuName" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="菜单名" />
        <ec:column property="EDIT" style="text-align:center;" title="状态">
            <c:choose>
                <c:when test="${row.isUse == '1'}">使用中
                </c:when>
                <c:otherwise>未使用
                </c:otherwise>
            </c:choose>
        </ec:column>
        <ec:column property="EDIT" style="text-align:center;" title="操作(更改操作后请刷新)" viewsDenied="xls">
            <a href="#" onClick="formPost(window.SearchState,'','authmenu?ActionType=authmenu_AddInit&FLAG=update&MENU_CODE=<c:out value="${row.menuCode}"/>',this);">修改</a>

            <c:choose>
                <c:when test="${row.isUse == '1'}">
                    |<a href="#" onClick="state_change('<c:out value="${row.menuCode}"/>')">停用</a>
                </c:when>
                <c:otherwise>
                    |<a href="#" onClick="state_change('<c:out value="${row.menuCode}"/>')">启用</a>
                </c:otherwise>
            </c:choose>
        </ec:column>
    </ec:row>
</ec:table>
</div>
</body>
</html>