<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <script src="../js/jquery-1.12.1.js" type="text/javascript"></script>
    <script src="../js/common.js" type="text/javascript"></script>

    <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css"/>
    <title>Insert title here</title>
</head>

<body>
<div class="page-container">
    <form name="SearchState" method="post" Action="enterstu?ActionType=enterstu_Select1_1">
        <div class="text-c">

            基本情况:
            <span class="select-box inline">
			<select class="select" name="choose">
				<option value="录取">录取</option>
				<option value="报考">报考</option>
			</select>
		</span>

            专业:
            <span class="select-box inline">
			<select class="select" name="major">
				<option value="all">全部</option>
				<c:forEach items="${MAJORLIST}" var="major">
                    <option value="${major.majorName }">${major.majorName }</option>
                </c:forEach>
			</select>
		</span>
            户口:
            <span class="select-box inline">
		<select class="select" id="" name="">
		<option value="0">户口</option>
		</select>
		</span>

            调剂:
            <span class="select-box inline">
			<select class="select" id="" name="">
				<option value="0">调剂</option>
				<option value="AccountInfo">AccountInfo</option>
			</select>
		</span>
            报道:
            <span class="select-box inline">
			<select class="select" id="" name="">
				<option value="0">报道</option>
				<option value="AccountInfo">AccountInfo</option>
			</select>
		</span>
            顺序:
            <span class="select-box inline">
			<select class="select" id="" name="">
				<option value="0">升序</option>
				<option value="AccountInfo">降序</option>
			</select>
		</span>
            <input type="submit" class="btn btn-primary radius" name="submit" value="查询"/>
        </div>
    </form>

    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
            <tr class="text-c">
                <th>报名此专业最多省份</th>
                <th>专业</th>
                <th>人数</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${USERLIST}" var="user">
                <tr class="text-c">
                    <td>${user.familyName }</td>
                    <td>${user.majorName }</td>
                    <td>${user.majorSum }</td>
                    <td class="f-14"><a style="text-decoration:none"
                                        onclick="system_data_edit('角色编辑','system-data-edit.html','0001','400','310')"
                                        href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
                        <a title="删除" href="javascript:;" onclick="system_data_del(this,'10001')" class="ml-5"
                           style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>