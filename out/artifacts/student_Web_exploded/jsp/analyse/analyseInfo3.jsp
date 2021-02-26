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
		<div id="current">当前位置：对比分析 &gt; 地区对比分析</div>
			<form name="SearchState" method="post"
			Action="analyse?ActionType=analyse_School3&FLAG=select">
			
			<div class="text-c">
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
			var sum = new Array();
			var sum1 = new Array();
			var sum2 = new Array();
			var sum3 = new Array();
			var sum4 = new Array();
			var sum5 = new Array();
			<c:forEach items="${MAJORLIST6}" var="rate" varStatus="i">
				var e = '${rate.ZYMC}';
				major.push(e);
			</c:forEach>
			<c:forEach items="${MAJORLIST6}" var="rate" varStatus="i">
				var a = parseInt('${rate.sum1}');
				var b = parseInt('${rate.sum2}');
				var c = parseInt('${rate.sum3}');
				var d = parseInt('${rate.sum4}');
				var f = parseInt('${rate.sum5}');
				var g = parseInt('${rate.sum6}');
				sum.push(a);
				sum1.push(b);
				sum2.push(c);
				sum3.push(d);
				sum4.push(f);
				sum5.push(g);
			</c:forEach>

			var chart = Highcharts.chart('wyy', {
				chart: {
					type: 'line'
				},
				title: {
					text: '各地区专业报名人数对比(一志愿)'
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
					name: '华北',
					data: [sum[0],sum[1],sum[2],sum[3],sum[4],sum[5],sum[6],sum[7],sum[8],sum[9],sum[10],sum[11],sum[12],sum[13],sum[14],sum[15],sum[16],sum[17],sum[18],sum[19],sum[20],sum[21],sum[22],sum[23],sum[24],sum[25],sum[26],sum[27],sum[28],sum[29],sum[30],sum[31],sum[32],sum[33],sum[34],sum[35],sum[36],sum[37],sum[38],sum[39],sum[40],sum[41],sum[42],sum[43],sum[44],sum[45],sum[46],sum[47],sum[48],sum[49],sum[50],sum[51],sum[52],sum[53],sum[54],sum[55],sum[56],sum[57],sum[58]]
				}, {
					name: '华南',
					data: [sum1[0],sum1[1],sum1[2],sum1[3],sum1[4],sum1[5],sum1[6],sum1[7],sum1[8],sum1[9],sum1[10],sum1[11],sum1[12],sum1[13],sum1[14],sum1[15],sum1[16],sum1[17],sum1[18],sum1[19],sum1[20],sum1[21],sum1[22],sum1[23],sum1[24],sum1[25],sum1[26],sum1[27],sum1[28],sum1[29],sum1[30],sum1[31],sum1[32],sum1[33],sum1[34],sum1[35],sum1[36],sum1[37],sum1[38],sum1[39],sum1[40],sum1[41],sum1[42],sum1[43],sum1[44],sum1[45],sum1[46],sum1[47],sum1[48],sum1[49],sum1[50],sum1[51],sum1[52],sum1[53],sum1[54],sum1[55],sum1[56],sum1[57],sum1[58]]
				}, {
					name: '华东',
					data: [sum2[0],sum2[1],sum2[2],sum2[3],sum2[4],sum2[5],sum2[6],sum2[7],sum2[8],sum2[9],sum2[10],sum2[11],sum2[12],sum2[13],sum2[14],sum2[15],sum2[16],sum2[17],sum2[18],sum2[19],sum2[20],sum2[21],sum2[22],sum2[23],sum2[24],sum2[25],sum2[26],sum2[27],sum2[28],sum2[29],sum2[30],sum2[31],sum2[32],sum2[33],sum2[34],sum2[35],sum2[36],sum2[37],sum2[38],sum2[39],sum2[40],sum2[41],sum2[42],sum2[43],sum2[44],sum2[45],sum2[46],sum2[47],sum2[48],sum2[49],sum2[50],sum2[51],sum2[52],sum2[53],sum2[54],sum2[55],sum2[56],sum2[57],sum2[58]]
				}, {
					name: '西南',
					data: [sum3[0],sum3[1],sum3[2],sum3[3],sum3[4],sum3[5],sum3[6],sum3[7],sum3[8],sum3[9],sum3[10],sum3[11],sum3[12],sum3[13],sum3[14],sum3[15],sum3[16],sum3[17],sum3[18],sum3[19],sum3[20],sum3[21],sum3[22],sum3[23],sum3[24],sum3[25],sum3[26],sum3[27],sum3[28],sum3[29],sum3[30],sum3[31],sum3[32],sum3[33],sum3[34],sum3[35],sum3[36],sum3[37],sum3[38],sum3[39],sum3[40],sum3[41],sum3[42],sum3[43],sum3[44],sum3[45],sum3[46],sum3[47],sum3[48],sum3[49],sum3[50],sum3[51],sum3[52],sum3[53],sum3[54],sum3[55],sum3[56],sum3[57],sum3[58]]
				}, {
					name: '西北',
					data: [sum4[0],sum4[1],sum4[2],sum4[3],sum4[4],sum4[5],sum4[6],sum4[7],sum4[8],sum4[9],sum4[10],sum4[11],sum4[12],sum4[13],sum4[14],sum4[15],sum4[16],sum4[17],sum4[18],sum4[19],sum4[20],sum4[21],sum4[22],sum4[23],sum4[24],sum4[25],sum4[26],sum4[27],sum4[28],sum4[29],sum4[30],sum4[31],sum4[32],sum4[33],sum4[34],sum4[35],sum4[36],sum4[37],sum4[38],sum4[39],sum4[40],sum4[41],sum4[42],sum4[43],sum4[44],sum4[45],sum4[46],sum4[47],sum4[48],sum4[49],sum4[50],sum4[51],sum4[52],sum4[53],sum4[54],sum4[55],sum4[56],sum4[57],sum4[58]]
				}, {
					name: '东北',
					data: [sum5[0],sum5[1],sum5[2],sum5[3],sum5[4],sum5[5],sum5[6],sum5[7],sum5[8],sum5[9],sum5[10],sum5[11],sum5[12],sum5[13],sum5[14],sum5[15],sum5[16],sum5[17],sum5[18],sum5[19],sum5[20],sum5[21],sum5[22],sum5[23],sum5[24],sum5[25],sum5[26],sum5[27],sum5[28],sum5[29],sum5[30],sum5[31],sum5[32],sum5[33],sum5[34],sum5[35],sum5[36],sum5[37],sum5[38],sum5[39],sum5[40],sum5[41],sum5[42],sum5[43],sum5[44],sum5[45],sum5[46],sum5[47],sum5[48],sum5[49],sum5[50],sum4[51],sum5[52],sum5[53],sum5[54],sum5[55],sum5[56],sum5[57],sum5[58]]
				}]
			});

			</script>
		</div>
		</div>
		<div>
			<p>华北：北京、天津、河北、山西、内蒙古</p>
			<p>华南：河南、湖北、湖南、广东、广西、海南、香港、澳门</p>
			<p>华东：上海、江苏、浙江、安徽、福建、江西、山东、台湾</p>
			<p>西南：重庆、四川、云南、西藏、贵州</p>
			<p>西北：陕西、甘肃、青海、宁夏、新疆</p>
			<p>东北：辽宁、吉林、黑龙江</p>
		</div>
	</body>
</html>