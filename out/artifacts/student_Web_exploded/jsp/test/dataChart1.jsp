<%--
  Created by IntelliJ IDEA.
  User: as
  Date: 2019/11/9
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>专业热度</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" >
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/xadmin.css">
    <link rel="stylesheet" href="../../css/echartsHtmlbase.css">
</head>
<script src="../../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../../js/xadmin.js"></script>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/dark.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">招生分析</a>
        <a><cite>专业热度排名</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<form class="layui-form nav-select">
    <div class="layui-form-item">
        <label class="layui-form-label">入学年份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select lay-filter="year" id="YEAR">
                <option value="2018">2018</option>
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
            </select>
        </div>
    </div>
</form>
<script>
    $("#YEAR").val("${YEAR}")
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=dataChart1&YEAR="+data.value;
        })
    })
</script>
<div id="container" class="echarts-container"></div>
<script type="text/javascript">
    renderEcharts(${proTop});
    function renderEcharts(sessionData){
        var arrData = [['amount', 'zhuanye']];
        var jsonData=sessionData;
        var jsonDataLength = jsonData.length;
        for (var i=0;i<jsonDataLength;i++){
            arrData[i+1] = [];
            arrData[i+1][0]=jsonData[i].number;
            arrData[i+1][1]=jsonData[i].ZYMC;
        }for (var i=0;i<jsonDataLength;i++){
            arrData[jsonDataLength-i] = [];
            arrData[jsonDataLength-i][0]=jsonData[i].number;
            arrData[jsonDataLength-i][1]=jsonData[i].ZYMC;
        }
        var max = JSON.parse(arrData[1][0])
        var min = JSON.parse(arrData[jsonDataLength][0])

        var myChart = echarts.init(document.getElementById('container'),'dark');
        // 指定图表的配置项和数据
        var option = {
            tooltip:{

            },
            title: {
                text: '${YEAR}年专业热度排名',
            },
            //数据源
            dataset: {
                //图
                source: arrData
            },
            grid: {
                top:'10%',
                left: '5%',
                right: '6%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: {name: '录取人数'},
            yAxis: {type: 'category'},

            visualMap: {
                orient: 'horizontal',
                right: '7%',
                bottom: '3%',
                //根据数据调整
                min: min,
                max: max,
                text: ['最高', '最低'],
                // Map the score column to color
                dimension: 0,
                inRange: {
                    color: ['#eedd78', '#dd6b66']
                }
            },
            series: [
                {
                    type: 'bar',
                    encode: {
                        // Map the "amount" column to X axis.
                        x: '录取人数',
                        // Map the "product" column to Y axis
                        y: 'zhuanye'
                    }
                }
            ],
            dataZoom: [
                {
                    type: 'slider',
                    show: true,
                    yAxisIndex: [0],
                    left: '93%',
                    start: 100, //数据窗口范围的起始百分比
                    end: 70
                },
                {
                    type: 'inside',
                    yAxisIndex: [0],
                    start: 100,
                    end: 70
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

</script>

</body>
</html>
