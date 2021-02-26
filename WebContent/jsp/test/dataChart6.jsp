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
    <title>dataChart6</title>
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
<div id="current">当前位置：小组功能测试 &gt; 录取分数预测</div>

<form name="SearchState" method="post">
    <div class="text-c">
        专业: <span class="select-box inline">
				<select class="select" name="PROVINCE">
                    <option value="zhuanye1">top5</option>
                    <option value="zhuanye1">top10</option>
                    <option value="all">全部</option>
				</select>
				</span>
        年份: <span class="select-box inline">
				<select class="select" name="PROVINCE">
                    <option value="year1">2015</option>
                    <option value="year2">2016</option>
                    <option value="year3">2017</option>
                    <option value="year3">2018</option>
                    <option value="year3">2019</option>
				</select>
				</span>
        <input type="submit" class="btn btn-primary radius" name="button"
               value="查询" />
    </div>
</form>

<div id="container" style="height: 60%;width:80% "></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('container'));
    // 指定图表的配置项和数据
    var option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: [
                ['product', '信管', '网安', '软件', '计科','英语'],
                ['一志愿率', 10, 30.4, 65.1, 53.3,60],
                ['所有志愿率', 86.5, 92.1, 85.7, 83.1,50],
                //['Cheese Cocoa', 24.1, 67.2, 79.5, 86.4，30]
            ]
        },
        xAxis: [
            {type: 'category', gridIndex: 0},
            {type: 'category', gridIndex: 1}
        ],
        yAxis: [
            {gridIndex: 0},
            {gridIndex: 1}
        ],
        grid: [
            {bottom: '55%'},
            {top: '55%'}
        ],
        series: [
            // These series are in the first grid.
            // {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            {type: 'bar', seriesLayoutBy: 'row'},
            // These series are in the second grid.
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
            {type: 'bar', xAxisIndex: 1, yAxisIndex: 1}
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
