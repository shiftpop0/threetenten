<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <title>省份详情图表</title>
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
        <a href="javascript:void(0);">招生地图</a>
        <a><cite>省份录取详情</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right" onclick="history.back()" title="返回">
        <i class="layui-icon layui-icon-prev" style="line-height:30px"></i>
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
    $("#YEAR").val("${YEAR}");
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=mapProvince&name=${PROVINCE}&year="+data.value;
        })
    })
</script>
<div id="container" class="echarts-container"></div>
</body>
<script type="text/javascript">
    var mapProvince = ${mapProvince}
    var major = []
    var number = []
    mapProvince.forEach(function (val,index) {
        major[index]=val.ZYMC
        number[index]=val.number
    })

    console.log(major+number)
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom,'dark');
    var app = {};
    option = null;
    option = {
        title: {
            text:"${PROVINCE}${YEAR}年招生录取详情",
        },
        color: ['#dd6b66'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            top:'10%',
            left: '5%',
            right: '6%',
            bottom: '7%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                gridIndex: 0,
                data: major,
                axisTick: {
                    alignWithLabel: false
                },
                axisLabel: {
                    interval:0,
                    formatter:function (value) {
                        return textToPortrait(value);
                    }
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
                name: '录取人数',
                type: 'bar',
                barWidth: '60%',
                data: number
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
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
</html>