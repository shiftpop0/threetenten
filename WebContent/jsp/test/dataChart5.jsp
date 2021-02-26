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
    <title>地区分数段人数统计</title>
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
        <a href="javascript:void(0);">招生统计</a>
        <a><cite>地区分数段人数统计</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<form class="layui-form" lay-filter="dataChart5">
    <div class="layui-form-item nav-select">
        <label class="layui-form-label">入学年份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select name="year" lay-filter="year" id="YEAR">
                <option value="2018">2018</option>
                <option value="2017">2017</option>
                <option value="2016">2016</option>
                <option value="2015">2015</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item nav-select2">
        <label class="layui-form-label">分科:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select name="branch" lay-filter="branch" id="BRANCH">
                <option value="B">理工</option>
                <option value="0">文史</option>
                <option value="4">美术统考</option>
                <option value="L">体育理</option>
            </select>
        </div>
    </div>
</form>
<script>
    //jQuery语法控制select下拉框默认选项
    $("#YEAR").val("${YEAR}")
    $("#BRANCH").val("${BRANCH}")
    layui.use('form',function () {
        var form = layui.form;
        var $ = layui.jquery;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=dataChart5&branch="+$("#BRANCH").val()+"&YEAR="+$("#YEAR").val();
        })
        form.on('select(branch)',function (data) {
            window.location.href="test?ActionType=dataChart5&branch="+$("#BRANCH").val()+"&YEAR="+$("#YEAR").val();
        })
    })
</script>
<div id="container" class="echarts-container"></div>
<%--两个脚本分开写以便辨别,不影响前端代码运行
    ↓此js为数据处理以及图表渲染--%>
<script>
    //  页面加载时触发该函数,将默认查询或者下拉框查询后传来的数据来调用渲染函数,注意有五个参数
    renderEcharts(${choiceData1},${choiceData2},${choiceData3},${choiceData4});

    //以下为数据处理，将后台session中数据处理成echarts所需数据格式，取名为dataParse
    function dataParse(sessionData){
        var arrData=[];
        var jsonData = sessionData;
        var jsonDataLength = jsonData.length;
        for (var i = 0; i < jsonDataLength; i++) {
            arrData[i]= jsonData[i].number;
        }
        return arrData;
    }
    //省份数据处理
    function dataParse2(sessionData) {
        var arrData=[];
        var jsonData = sessionData;
        var jsonDataLength = jsonData.length;
        for (var i = 0; i < jsonDataLength; i++) {
            arrData[i]= jsonData[i].DQMC;
        }
        return arrData;
    }


    //renderEcharts即渲染echarts，将渲染echarts图表所需的组件和模块封装入单个的函数中，方便下拉框点击调用
    function renderEcharts(sessionData1,sessionData2,sessionData3,provinceData) {
        //  在函数内部调用数据处理函数,因为每一次查询都是一次独立的查询,要保证变量不被污染
        var arrData1 = dataParse(${choiceData1});
        var arrData2 = dataParse(${choiceData2});
        var arrData3 = dataParse(${choiceData3});
        var arrData4 = dataParse(${choiceData4});
        var shengfen = dataParse2(${choiceData1});
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('container'),'dark');

        // 指定图表的配置项和数据
        option = {
            title:{
                text:"${YEAR}年统计详情",
            },
            tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                top:'10%',
                left: '5%',
                right: '6%',
                bottom: '7%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : shengfen //DQMC
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'其他',
                    type:'line',
                    stack: '总量',
                    areaStyle: {},
                    data: arrData1//choicedata1.number
                },
                {
                    name:'高录取线15以上',
                    type:'line',
                    stack: '总量',
                    areaStyle: {},
                    data: arrData2
                },
                {
                    name:'高录取线25以上',
                    type:'line',
                    stack: '总量',
                    areaStyle: {},
                    data: arrData3
                },
                {
                    name:'高录取线35以上',
                    type:'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: arrData4
                },
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        if(arrData1 == 0){
            document.getElementById('container').innerHTML="<h1>抱歉，暂无数据!</h1>"
        }else {
            myChart.setOption(option);
        }
    }
</script>
</body>
</html>
