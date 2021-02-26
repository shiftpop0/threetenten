<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2020/2/25
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>就业类型</title>
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
        <a href="javascript:void(0);">就业分析</a>
        <a><cite>就业类型</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<form class="layui-form nav-select">
    <div class="layui-form-item">
        <label class="layui-form-label">学院:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select name="college" lay-filter="college" id="COLLEGE">
                <option value="计算机学院">计算机学院</option>
            </select>
        </div>
    </div>
</form>
<script>
// 只有一个学院的数据,监听下拉框
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(college)',function (data) {
            if(data.value==计算机学院)
                window.href.location="request_url"
        })
    })
</script>
<div id="container" class="echarts-container"></div>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="../../js/dark.js"></script>
<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom,'dark');
    var app = {};
    option = null;

    option = {
        title: [{
            text: '近三年毕业生就业情况'
        }, {
            subtext: '2017年',
            left: '16.67%',
            top: '65%',
            textAlign: 'center'
        }, {
            subtext: '2018年',
            left: '50%',
            top: '65%',
            textAlign: 'center'
        }, {
            subtext: '2019年',
            left: '83.33%',
            top: '65%',
            textAlign: 'center'
        }],
        tooltip:{
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend:{

        },
        series: [{
            name:'2017',
            type: 'pie',
            radius: '33%',
            center: ['50%', '50%'],
            data: ${careerType1},
            animation: true,
            label: {
                position: 'outer',
                alignTo: 'none',
                bleedMargin: 5
            },
            left: 0,
            right: '66.6667%',
            top: 0,
            bottom: '14%',
        }, {
            name:'2018',
            type: 'pie',
            radius: '33%',
            center: ['50%', '50%'],
            data: ${careerType2},
            animation: true,
            label: {
                position: 'outer',
                alignTo: 'labelLine',
                bleedMargin: 5
            },
            left: '33.3333%',
            right: '33.3333%',
            top: 0,
            bottom: '14%',
        }, {
            name:'2019',
            type: 'pie',
            radius: '33%',
            center: ['50%', '50%'],
            data: ${careerType3},
            animation: true,
            label: {
                position: 'outer',
                alignTo: 'labelLine',
                bleedMargin: 5
            },
            left: '66.6667%',
            right: 0,
            top: 0,
            bottom: '14%'
        }]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>