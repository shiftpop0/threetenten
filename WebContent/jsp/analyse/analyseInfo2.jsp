<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	
	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />

	<script src = "../js/highcharts.js"></script>
	<script src = "../js/highcharts-3d.js"></script>
	<script src="../js/series-label.js"></script>
	<script src="../js/exporting.js"></script>
	<script src="../js/export-data.js"></script>
	
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/highcharts.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/fontawesome.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-style.css"/>
<title>Insert title here</title>
</head>
	<body>
	<div id="current">当前位置：对比分析 &gt; 各专业对比分析</div>
	<div class="page-container">
		<form name="SearchState" method="post"
			Action="analyse?ActionType=analyse_School2&FLAG=select">
			<div class="text-c">

				省份1: <span class="select-box inline"> 
				<!-- 选择专业类别 -->
				<select class="select" name="type" id="majorType" onchange="selectMajor()">
						<option value="all">全部</option>
						<c:forEach items="${FAMILYLIST}" var="type">
							<option value="${type.DQDM }">${type.DQMC }</option>
						</c:forEach>
				</select>
				</span> 
				省份2: <span class="select-box inline"> 
				<!-- 选择专业类别 -->
				<select class="select" name="type1" id="majorType" onchange="selectMajor()">
						<option value="all">全部</option>
						<c:forEach items="${FAMILYLIST}" var="type">
							<option value="${type.DQDM }">${type.DQMC }</option>
						</c:forEach>
				</select>
				</span> 
				年份: <span class="select-box inline"> 
				<select class="select" name="year">
						<option value="all">全部</option>
						<c:forEach items="${YEARLIST}" var="type">
							<option value="${type.years }">${type.years }</option>
						</c:forEach>
				</select>
				</span> 
				<input type="submit" class="btn btn-primary radius" name="button"
					value="分析" />
			</div>
		</form>
		<div class="mt-20">
		<div id="wyy" align="center">
		<script language="JavaScript">
			var major = new Array();
			var family = new Array();
			var sum = new Array();
			var sum1 = new Array();
			var b;
			var a;
			<c:forEach items="${SCHOOLLIST}" var="rate" varStatus="i">
				b = '${rate.DQMC}'
			</c:forEach>
			<c:forEach items="${SCHOOLLIST1}" var="rate" varStatus="i">
				a = '${rate.DQMC}'
			</c:forEach>
			<c:forEach items="${MAJORLIST}" var="rate" varStatus="i">
				var e = '${rate.ZYMC}';
				major.push(e);
			</c:forEach>
			<c:forEach items="${MAJORLIST}" var="rate" varStatus="i">
				var d = parseInt('${rate.sum2}');
				var c = parseInt('${rate.sum1}');
				sum.push(d);
				sum1.push(c);
			</c:forEach>

			var chart = Highcharts.chart('wyy', {
				chart: {
					type: 'line'
				},
				title: {
					text: '各专业报名人数(一志愿)'
				},
				xAxis: {
					categories: [major[1],major[2],major[3],major[4],major[5],major[6],major[7],major[8],major[9],major[10],major[11],major[12],major[13],major[14],major[15],major[16],major[17],major[18],major[19],major[20],major[21],major[22],major[23],major[24],major[25],major[26],major[27],major[28],major[29],major[30],major[31],major[32],major[33],major[34],major[35],major[36],major[37],major[38],major[39],major[40],major[41],major[42],major[43],major[44],major[45],major[46],major[47],major[48],major[49],major[50],major[51],major[52],major[53],major[54],major[55],major[56],major[57],major[58]]
				},
				yAxis: {
					title: {
						text: '人数'
					}
				},
				plotOptions: {
					line: {
						dataLabels: {
							// 开启数据标签
							enabled: true          
						},
						// 关闭鼠标跟踪，对应的提示框、点击事件会失效
						enableMouseTracking: false
					}
				},
				series: [{
					name: a,
					data: [sum[0],sum[1],sum[2],sum[3],sum[4],sum[5],sum[6],sum[7],sum[8],sum[9],sum[10],sum[11],sum[12],sum[13],sum[14],sum[15],sum[16],sum[17],sum[18],sum[19],sum[20],sum[21],sum[22],sum[23],sum[24],sum[25],sum[26],sum[27],sum[28],sum[29],sum[30],sum[31],sum[32],sum[33],sum[34],sum[35],sum[36],sum[37],sum[38],sum[39],sum[40],sum[41],sum[42],sum[43],sum[44],sum[45],sum[46],sum[47],sum[48],sum[49],sum[50],sum[51],sum[52],sum[53],sum[54],sum[55],sum[56],sum[57],sum[58]]
				}, {
					name: b,
					data: [sum1[0],sum1[1],sum1[2],sum1[3],sum1[4],sum1[5],sum1[6],sum1[7],sum1[8],sum1[9],sum1[10],sum1[11],sum1[12],sum1[13],sum1[14],sum1[15],sum1[16],sum1[17],sum1[18],sum1[19],sum1[20],sum1[21],sum1[22],sum1[23],sum1[24],sum1[25],sum1[26],sum1[27],sum1[28],sum1[29],sum1[30],sum1[31],sum1[32],sum1[33],sum1[34],sum1[35],sum1[36],sum1[37],sum1[38],sum1[39],sum1[40],sum1[41],sum1[42],sum1[43],sum1[44],sum1[45],sum1[46],sum1[47],sum1[48],sum1[49],sum1[50],sum1[51],sum1[52],sum1[53],sum1[54],sum1[55],sum1[56],sum1[57],sum1[58]]
				}]
			});

			</script>
		</div>
		</div>
	</div>
	</body>
</html>