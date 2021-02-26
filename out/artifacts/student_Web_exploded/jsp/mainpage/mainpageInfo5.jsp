<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%--<script src="../js/jquery-1.12.1.js" type="text/javascript"></script>--%>
	<%--<script src="../js/common.js" type="text/javascript"></script>--%>

	<link href="<%=request.getContextPath()%>/css/extremecomponents.css"
		  rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/global.css"
		  rel="stylesheet" type="text/css" />
	<script src="../../js/exporting.js"></script>
	<script src="../../js/export-data.js"></script>
	<%--	<link rel="stylesheet" href="../css/font.css">--%>
	<link rel="stylesheet" href="../../css/xadmin.css">
	<%--	<link rel="stylesheet" href="../css/echartsHtmlbase.css">--%>
	<%--<link rel="stylesheet" type="text/css"--%>
	<%--	href="<%=request.getContextPath()%>/css/H-ui.min.css" />--%>
	<%--<link rel="stylesheet" type="text/css"--%>
	<%--	href="<%=request.getContextPath()%>/css/H-ui.admin.css" />--%>
	<%--<link rel="stylesheet" type="text/css"--%>
	<%--	href="<%=request.getContextPath()%>/css/iconfont.css" />--%>
	<%--<link rel="stylesheet" type="text/css"--%>
	<%--	href="<%=request.getContextPath()%>/css/skin.css" id="skin" />--%>
	<%--<link rel="stylesheet" type="text/css"--%>
	<%--	href="<%=request.getContextPath()%>/css/style.css" />--%>
	<title>Insert title here</title>
	<script>
		var majorTypes = eval('${TYPELIST}');
		var majors = eval('${MAJORLIST}');
		function selectMajor() {
			//得到当前选中省份
			var majorType = document.getElementById("majorType");
			var majorTypeValue = majorType.value;
			var j = 0;
			var majorArray = new Array();
			//从数组中取得对应的专业名称
			for(var i in majors){
				if(majorTypeValue == majors[i].KLDM){
					majorArray[j] = majors[i].ZYMC;
					j++;
				}
			}
			var majorSelect = document.getElementById("major");
			//清空select中的option
			majorSelect.options.length = 0;
			for(var i = 0; i < majorArray.length; i++){
				//动态创建专业元素结点 <option>专业<option>
				var majorText = majorArray[i];
				var option1 = document.createElement("option");
				//创建专业文本结点
				var textNode = document.createTextNode(majorText);
				//将option结点和文本关联
				option1.appendChild(textNode);
				//添加专业到select中
				majorSelect.appendChild(option1);

			}
		}
	</script>
	<style type="text/css">
		body{
			background: #333;
		}
	</style>
</head>
<script src="../../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../../js/xadmin.js"></script>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/dark.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<body>
<div class="x-nav">
		<span class="layui-breadcrumb">
			<a href="javacipt:void(0)">系统首页</a>
			<a><cite>历年一志愿录取、报考详情</cite></a>
		</span>
	<a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
		<i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
	</a>
	<a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right" onclick="history.back()" title="返回">
		<i class="layui-icon layui-icon-prev" style="line-height:30px"></i>
	</a>
</div>

<%--	<div id="current">当前位置：一志愿详情&gt; 历年一志愿录取、报考详情</div>--%>
<div class="layui-card-body ">
	<div class="page-container">
		<form style="float: left"
			  name="SearchState" method="post"
			  Action="mainpage?ActionType=mainpage_Select5ByMajor&FLAG=select">
			<div style="color: white">

				专业类别: <span class="select-box inline">
				<!-- 选择专业类别 -->
				<select class="select" name="type" id="majorType" onchange="selectMajor()">
						<option value="all">全部</option>
						<c:forEach items="${TYPELIST}" var="type">
							<option value="${type.KLDM }">${type.KLMC }</option>
						</c:forEach>
				</select>
				</span>
				专业: <span class="select-box inline">
				<select class="select" name="major" id="major">
				</select>
				</span>
				年份: <span class="select-box inline">
				<select class="select"
						name="year">
						<option value="all">全部</option>
						<c:forEach items="${YEARLIST}" var="year">
							<option value="${year.YEAR1 }">${year.YEAR1 }</option>
						</c:forEach>
				</select>
				</span>
				<input type="submit" class="btn btn-primary radius" name="button"
					   value="查询" />
			</div>
		</form>
<%--		<form style="float: right"--%>
<%--			  name="SearchState" method="post"--%>
<%--			  Action="mainpage?ActionType=mainpage_Select1">--%>
<%--			<div class="text-c">--%>
<%--				<input type="submit" class="btn btn-primary radius" name="button"--%>
<%--					   value="查看历年情况" />--%>
<%--			</div>--%>
<%--		</form>--%>
		<h1 style="padding-left: 40%;color: white;" >一志愿录取、报考详情表</h1>
		<div class="mt-20">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="search_shadow"></td>
				</tr>
				<tr>
					<td class="main_blank"></td>
				</tr>
			</table>

			<ec:table items="USERLIST" var="row"
					  action="mainpage?ActionType=mainpage_Select5"
					  view="html"
			>
				<ec:row>
					<ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
					<ec:column property="ZYMC" style="text-align:center;" title="专业" />
					<ec:column property="KLMC" style="text-align:center;" title="专业类别"/>
					<ec:column property="LQZY_sum" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="录取人数" />
					<ec:column property="BKZY_allsum" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="报考人数" />
					<ec:column property="LQZY1_sum" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="一志愿录取人数" />
					<ec:column property="BKZY1_sum" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="一志愿报考人数" />
					<ec:column property="LQZY1_rate" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="一志愿录取率" />
					<ec:column property="BKZY1_rate" style="text-align:center;" escapeAutoFormat="true" sortable="false" title="一志愿报考率" />
					<ec:column property="YEAR1" style="text-align:center;" title="年份"/>
					<ec:column property="EDIT" style="text-align:center;" title="操作" viewsDenied="xls">
						<a href="#" onClick="formPost(window.SearchState,'','mainpage?ActionType=mainpage_Select1&MAJOR=<c:out value='${row.ZYDH }'/>&MAJORTYPE=<c:out value='${row.KLDM }'/>&YEAR=<c:out value='${row.YEAR1 }'/>',this);">详情</a>
					</ec:column>
				</ec:row>
			</ec:table>
		</div>
	</div>
</div>
</body>
</html>