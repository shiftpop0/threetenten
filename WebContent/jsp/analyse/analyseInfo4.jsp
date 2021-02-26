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
<script>
	var majorTypes = eval('${TYPELIST}');
	var majors = eval('${MAJORLIST1}');
	function selectMajor() {
		//得到当前选中省份
		var majorType = document.getElementById("majorType");
		var majorTypeValue = majorType.value;
		var j = 0;
		var majorArray = new Array();
		//console.log(majorTypeValue)
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
</head>
	<body>
		<div id="current">当前位置：对比分析 &gt; 专业热度分析</div>
			<form name="SearchState" method="post"
			Action="analyse?ActionType=analyse_School4&FLAG=select">
			<div class="text-c">
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
				<input type="submit" class="btn btn-primary radius"
					value="分析" />
			</div>
		</form>
		<div class="mt-20">
		<div id="wyy" align="center">
		<script language="JavaScript">
			var major = new Array();
			var sum = new Array();
			var b;
			<c:forEach items="${MAJORLIST}" var="rate" varStatus="i">
				var e = '${rate.YEAR1}';
				b = '${rate.ZYMC}';
				major.push(e);
			</c:forEach>
			<c:forEach items="${MAJORLIST}" var="rate" varStatus="i">
				var a = parseInt('${rate.hot_qz}');
				sum.push(a);
			</c:forEach>

			var chart = Highcharts.chart('wyy', {
				chart: {
					type: 'line'
				},
				title: {
					text: b+'专业热度分析'
				},
				xAxis: {
					categories: [major[0],major[1],major[2],major[3],major[4],major[5]]
				},
				yAxis: {
					title: {
						text: '权重'
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
					name: b,
					data: [sum[0],sum[1],sum[2],sum[3],sum[4],sum[5]]
				}]
			});

			</script>
		</div>
		</div>
	</body>
</html>