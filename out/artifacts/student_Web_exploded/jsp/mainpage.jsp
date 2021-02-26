<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="stylesheet" href="../css/font.css">
		<link rel="stylesheet" href="../css/xadmin.css">
		<link rel="stylesheet" href="../css/theme10.min.css">
		<title>演示在线学习系统</title>
	</head>
	<script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="../js/xadmin.js"></script>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script type="text/javascript" src="../js/indexMenu.js"></script>
	<body class="index">
	<!-- 顶部开始 -->
	<div class="container">
		<div class="logo">
			<a href="./index.html">招生分析系统</a></div>
		<div class="left_open">
			<a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
		</div>
		<ul class="layui-nav right" lay-filter="">
			<li class="layui-nav-item">
				<a href="javascript:;">admin</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a onclick="xadmin.open('个人信息','http://www.baidu.com')">个人信息</a></dd>
					<dd>
						<a onclick="xadmin.open('切换帐号','http://www.baidu.com')">切换帐号</a></dd>
					<dd>
						<a href="./login.jsp">退出</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item to-index">
				<a href="#">前台首页</a></li>
		</ul>
	</div>
	<!-- 顶部结束 -->
	<!-- 中部开始 -->
	<!-- 左侧菜单开始 -->
	<div class="left-nav">
		<div id="side-nav">
			<ul id="nav">
			</ul>
		</div>
	</div>
	<!-- 左侧菜单结束 -->
	<!-- 右侧主体开始 -->
	<div class="page-content">
		<div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
			<ul class="layui-tab-title">
				<li class="home">
					<i class="layui-icon">&#xe68e;</i>系统首页
                </li>
            </ul>
			<div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
				<dl>
					<dd data-type="this">关闭当前</dd>
					<dd data-type="other">关闭其它</dd>
					<dd data-type="all">关闭全部</dd>
                </dl>
			</div>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
					<iframe src='./right.jsp' name="middle" frameborder="0" scrolling="yes" class="x-iframe"></iframe>
				</div>
			</div>
			<div id="tab_show"></div>
		</div>
	</div>
	<div class="page-content-bg"></div>
	<style id="theme_style"></style>
	<!-- 右侧主体结束 -->
	<!-- 中部结束 -->
	</body>

	<%--<frameset rows="90,*,50" cols="*" framespacing="0" frameborder="no" border="0">
	  <frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame"  />
	  <frame src="middle.jsp" name="mainFrame" id="mainFrame" />
	  <frame src="bottom.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
	</frameset>--%>

	<script>
	var data = "${sessionScope.RESULTMAP.MENULIST}";
	var menuData = [];
	//解析得到的字符串，转化成需要的数据格式
	var index = 0;
	//解析第一个符号"["

	var menuItem;
	if (data.length > 2) {
		data = data.substr(1, data.length - 2);
		menuItem = data.split("}");
	}

	//循环处理到结束
	var menuIndex = 0;
	while (menuIndex < menuItem.length - 1) {
		var menuString = menuItem[menuIndex];

		var msIndex = 0;
		while (menuString[msIndex] != "{") {
			msIndex++;
		}
		msIndex++;

		menuString = menuString.substr(msIndex, menuString.length - msIndex);

		var menuDetail = menuString.split(",");

		var item = new Object();

		var detailIndex = 0;
		while (detailIndex < menuDetail.length) {

			var detailItem = menuDetail[detailIndex];

			var detailString = detailItem.split("=");
			var detailKey = detailString[0];
			detailKey = detailKey.replace(" ", "");
			var detailValue = detailString[1];
			detailValue = detailValue.replace(" ", "");

			if (detailKey == "id1") {
				item.id1 = detailValue;
			}

			if (detailKey == "name1") {
				item.name1 = detailValue;
			}

			if (detailKey == "pid1") {
				item.pid1 = detailValue;
			}

			if (detailKey == "url1") {
				if (detailString.length > 2) {
					item.url1 = detailValue + "=" + detailString[2];
				} else {
					item.url1 = detailValue;
				}
			}

			detailIndex++;
		}

		//添加children节点
		if (item.pid1 == "-1") {
			item.children1 = [];
		} else {
			item.children1 = null;
		}

		menuData[menuIndex] = item;

		menuIndex++;

	}
	window.onload = indexInit(menuData)

	function skipBlank(data, index) {
		var i = index;
		while (data[i] == ' ') {
			i++;
		}
		return i;
	}
	</script>
</html>