<%--
  Created by IntelliJ IDEA.
  User: as
  Date: 2019/12/7
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>调剂率</title>
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
        <a><cite>调剂率</cite></a>
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
            window.location.href="test?ActionType=dataChart3&YEAR="+data.value;
        })
    })
</script>
<div id="container" class="echarts-container"></div>
<script type="text/javascript">
    renderEcharts(${topAdjustRat});
    function renderEcharts(sessionData) {
        var jsonData=sessionData;
        var arrData = [['amount', 'zhuanye','luqunum','tiaoji']];
        var jsonDataLength = jsonData.length;
        for (var i=0;i<jsonDataLength;i++){
            arrData[i+1] = [];
            arrData[i+1][0]=jsonData[i].number;
            arrData[i+1][1]=jsonData[i].ZYMC;
            arrData[i+1][2]=jsonData[i].LQZY_sum;
            arrData[i+1][3]=jsonData[i].TiaoJi_sum;
        }for (var i=0;i<jsonDataLength;i++){
            arrData[jsonDataLength-i] = [];
            arrData[jsonDataLength-i][0]=jsonData[i].number;
            arrData[jsonDataLength-i][1]=jsonData[i].ZYMC;
            arrData[jsonDataLength-i][2]=jsonData[i].LQZY_sum;
            arrData[jsonDataLength-i][3]=jsonData[i].TiaoJi_sum;
        }
        var max = JSON.parse(arrData[1][0])
        var min = JSON.parse(arrData[jsonDataLength][0])

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('container'),'dark');

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '${YEAR}年专业调剂率排名',
            },
            dataset: {
                source: arrData
            },
            tooltip:{
                formatter:(params)=>{
                    return params.name+
                        '<br>调剂率：'+params.data[0]+'%'+
                        '<br>录取人数：'+params.data[2]+
                        '人<br>调剂人数：'+params.data[3]+'人';
                }
            },
            grid: {
                top:'10%',
                left: '5%',
                right: '6%',
                bottom: '10%',
                containLabel: true
            },
            xAxis: {name: '调剂率'},
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
                    color: ['#c9d5da', '#509aa0']
                }
            },
            series: [
                {
                    type: 'bar',
                    encode: {
                        // Map the "amount" column to X axis.
                        x: 'amount',
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
                    end: 60
                },
                {
                    type: 'inside',
                    yAxisIndex: [0],
                    start: 100,
                    end: 60
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

</script>

</body>
</html>
