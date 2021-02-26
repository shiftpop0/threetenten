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
var majors = eval('${MAJORLIST}');
/* for(var key in majors){
	console.log(key);
	console.log(majors[key].ZYMC);
} */
console.log(majorTypes);
console.log(majors);
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
<div id="current">当前位置：预测 &gt; 专业分数预测</div>
			<form name="SearchState" method="post"
			Action="calculate?ActionType=calculate_Score&FLAG=select">
			<div class="text-c">
				省份: <span class="select-box inline"> 
				<select class="select"
					name="province">
						<option value="all">全部</option>
						<c:forEach items="${PROVINCELIST}" var="province">
							<option value="${province.DQDM }">${province.DQMC }</option>
						</c:forEach>
				</select>
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
				<input type="submit" class="btn btn-primary radius" name="button"
					value="分析" />
			</div>
		</form>
				<div class="mt-20">
		<div id="wyy" style="height:400px" align="center">
        <script>
		var sum1 = parseInt('${SCORELIST[0].YEAR_2013_sorce}');
		var sum2 = parseInt('${SCORELIST[0].YEAR_2014_sorce}');
		var sum3 = parseInt('${SCORELIST[0].YEAR_2015_sorce}');
		var sum4 = parseInt('${SCORELIST[0].YEAR_2016_sorce}');
		var sum5 = parseInt('${SCORELIST[0].YEAR_2017_sorce}');
		var sum6 = parseInt('${SCORELIST[0].YEAR_2018_sorce}');
		var sum7 = parseInt('${SCORELIST[0].YEAR_2019_score}');
		var sum8 = parseInt('${SCORELIST[0].YEAR_2020_score}');
		console.log(sum7);
		var major = '${SCORELIST[0].ZYMC}';
		var province = '${SCORELIST[0].DQMC}';
        var chart = Highcharts.chart('wyy', {
            chart: {
                type: 'line'
            },
            title: {
                text: province+major+'专业录取人数'
            },
            xAxis: {
                categories: ['2013年','2014年','2015年','2016年','2017年', '2018年', '2019年', '2020年']
            },
            yAxis: {
                title: {
                    text: '录取人数'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        // 开启数据标签
                        enabled: true          
                    },
                    // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    enableMouseTracking: true
                }
            },
            series: [{
                name: major,
                data: [sum1,sum2,sum3,sum4,sum5,sum6,sum7,sum8]
            }]
        });
        </script>
		</div>
	</div>
	</body>
</html>