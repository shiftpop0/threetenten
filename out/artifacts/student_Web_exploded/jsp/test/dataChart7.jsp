<%--
  Created by IntelliJ IDEA.
  User: as
  Date: 2019/12/7
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <title>dataChart7</title>
    <script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
    <script src = "../js/common.js" type = "text/javascript"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>

    <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
</head>
<!-- 引入 echarts.js
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/echarts.min.js"></script> -->

<body>
<div id="current">当前位置：小组功能测试 &gt; 地区专业录取人数统计</div>

<form name="SearchState" method="post">
    <div class="text-c">
        省份: <span class="select-box inline">
				<select class="select" id="PROVINCE">
						<option value="北京">北京</option>
				</select>
				</span>
        年份: <span class="select-box inline">
				<select class="select" id="YEAR" name="YEAR">
                    <option value="2018">2018年</option>
                    <option value="2017">2017年</option>
                    <option value="2016">2016年</option>
                    <option value="2015">2015年</option>
                    <option value="2014">2014年</option>
                    <option value="2013">2013年</option>
				</select>
				</span>
        <button id="check" class="btn btn-primary radius">查询</button>
    </div>
</form>
<script>
    var provinceList = ${provinceList};
    var selectStr = "";
    for(let i=0;i<provinceList.length;i++){
        selectStr+='<option value="'+provinceList[i].DQMC+'">'+provinceList[i].DQMC+'</option>';
    }
    document.getElementById("PROVINCE").innerHTML=selectStr;
    $("#PROVINCE").val("${PROVINCE}")
    $("#YEAR").val("${YEAR}")
    $("#check").click(function(){
        window.location.href="test?ActionType=dataChart2&YEAR="
            +$("#YEAR").val()+"&PROVINCE="+$("#PROVINCE").val();
    });
</script>
<div id="container" style="height: 60%;width:80% "></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('container'));
    // 指定图表的配置项和数据
    var option = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '直接访问',
                type: 'bar',
                barWidth: '60%',
                data: [10, 52, 200, 334, 390, 330, 220]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
