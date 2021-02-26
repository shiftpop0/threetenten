<%--
  Created by IntelliJ IDEA.
  User: as
  Date: 2019/12/7
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>dataChart4</title>
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
    <span class="layui-breadcrumb" style="margin-right: 10px">
        <a href="javascript:void(0);">招生分析</a>
        <a><cite>志愿率</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-bg-black" onclick="first()">按第一志愿录取率排名</a>
    <a class="layui-btn layui-btn-sm layui-bg-black" onclick="total()">按总填报录取率排名</a>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<form class="layui-form nav-select">
    <div class="layui-form-item">
        <label class="layui-form-label">入学年份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select lay-filter="year" id="YEAR">
                <option value="2018">2018年</option>
                <option value="2017">2017年</option>
                <option value="2016">2016年</option>
                <option value="2015">2015年</option>
                <option value="2014">2014年</option>
                <option value="2013">2013年</option>
            </select>
        </div>
    </div>
</form>
<script>
    $("#YEAR").val("${YEAR}")
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=dataChart4&YEAR="+data.value;
        })
    })
</script>
<div id="container" class="echarts-container"></div>
<script>
    renderEcharts(${choiceData});
    function renderEcharts(data){
        var choiceData = [['product'],['一志愿率'],['所有志愿率']];
        var jsonData=data;
        for(var i=0;i<jsonData.length;i++){
            choiceData[0][i+1]=jsonData[i].ZYMC;
            choiceData[1][i+1]=jsonData[i].first_choice;
            choiceData[2][i+1]=jsonData[i].total_choice;
        }
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('container'),'dark');
        // 指定图表的配置项和数据
        option = {
            legend: {
                top:'5%',
                width:'60%'
            },
            tooltip: {},
            title: {
                text: '${YEAR}年专业志愿率排名',
                left: 'center',
            },
            dataset: {
                source: choiceData
            },
            xAxis: [
                {
                    type: 'category',
                    gridIndex: 0,
                    axisLabel: {
                        interval:0,
                        formatter:function (value) {
                            return textToPortrait(value);
                        }
                    }},
                {type: 'category',gridIndex: 1}
            ],
            yAxis: [
                {gridIndex: 0},
                {gridIndex: 1}
            ],
            grid: [
                {
                    top:'15%',
                    right: '55%',
                    bottom:'22%'
                },
                {
                    top: '15%',
                    left: '50%',
                    bottom:'22%'
                }
            ],
            series: [
                // These series are in the first grid.
                {type: 'bar', seriesLayoutBy: 'row'},
                {type: 'bar', seriesLayoutBy: 'row'},
                // These series are in the second grid.
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1},
                {type: 'bar', xAxisIndex: 1, yAxisIndex: 1}
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option,true);
    }
    function total() {
        renderEcharts(${choiceData2});
    }
    function first(){
        renderEcharts(${choiceData});
    }
    function textToPortrait(text) {
        if(text.indexOf("(") != -1){
            var arr = text.split("(");
            var arr1=arr[0].split("").join("\n");
            return arr1+"\n("+arr[1];
        }else
            return  text.split("").join("\n");
    }
</script>
</body>
</html>
