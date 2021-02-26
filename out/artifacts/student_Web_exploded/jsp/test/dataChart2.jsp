<%--
  Created by IntelliJ IDEA.
  User: as
  Date: 2019/12/7
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>录取成绩排名</title>
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
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">招生分析</a>
        <a><cite>录取成绩排名</cite></a>
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
    <div class="layui-form-item nav-select3">
        <label class="layui-form-label">省份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select name="province" lay-filter="province" id="PROVINCE">
                <option value="北京">北京</option>
            </select>
        </div>
    </div>
</form>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
<script>
    var provinceList = ${provinceList};
    var selectStr = "";
    for(let i=0;i<provinceList.length;i++){
        selectStr+='<option value="'+provinceList[i].DQMC+'">'+provinceList[i].DQMC+'</option>';
    }
    document.getElementById("PROVINCE").innerHTML=selectStr;
    $("#PROVINCE").val("${PROVINCE}")
    $("#YEAR").val("${YEAR}")
    $("#BRANCH").val("${BRANCH}")
    layui.use('form',function () {
        var form = layui.form;
        var $ = layui.jquery;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=dataChart2&YEAR="
                +$("#YEAR").val()+"&PROVINCE="+$("#PROVINCE").val()+"&BRANCH="+$("#BRANCH").val();
        })
        form.on('select(branch)',function (data) {
            window.location.href="test?ActionType=dataChart2&YEAR="
                +$("#YEAR").val()+"&PROVINCE="+$("#PROVINCE").val()+"&BRANCH="+$("#BRANCH").val();
        })
        form.on('select(province)',function (data) {
            window.location.href="test?ActionType=dataChart2&YEAR="
                +$("#YEAR").val()+"&PROVINCE="+$("#PROVINCE").val()+"&BRANCH="+$("#BRANCH").val();
        })
    })
    $("#check").click(function(){
        window.location.href="test?ActionType=dataChart2&YEAR="
            +$("#YEAR").val()+"&PROVINCE="+$("#PROVINCE").val()+"&BRANCH="+$("#BRANCH").val();
    });
</script>
<div id="container" class="echarts-container"></div>
<script type="text/javascript">
    // 处理成绩数据
    function jsonToArray(score) {
        var array = [[]];
        for(let i=0;i<score.length;i++){
                array[i]=[];
                for(let j=0;j<score[i].length;j++){
                array[i][j]=score[i][j].CJ;
            }
        }
        return array;
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('container'),'dark');
    // 指定图表的配置项和数据
    var sessionData = ${score};
    var data = echarts.dataTool.prepareBoxplotData(jsonToArray(sessionData));

    var majorArr=[]
    var majorLength=${topFiveMajor}.length
    for(let i = 0;i<majorLength;i++){
        majorArr[i]=${topFiveMajor}[i].ZYMC
    }
    function majorName(value){
        return majorArr[value]
    }

     option = {
        title: [
            {
                text: '${YEAR}年${PROVINCE}专业平均成绩排名&箱线图',
            },
            {
                text: '上边缘: Q3 + 1.5 * IQR \n下边缘: Q1 - 1.5 * IQR',
                borderColor: '#999',
                borderWidth: 1,
                textStyle: {
                    fontSize: 14
                },
                left: '10%',
                top: '90%'
            }
        ],
         tooltip: {
             trigger: 'item',
             axisPointer: {
                 type: 'shadow'
             }
         },
        grid: {
            top:'10%',
            left: '5%',
            right: '6%',
            bottom: '10%',
            containLabel: true
        },
        xAxis: {
            name: '专业',
            type: 'category',
            data: data.axisData,
            boundaryGap: true,
            nameGap: 30,
            splitArea: {
                show: true
            },
            //横坐标段name
            axisLabel: {
                formatter: function (value) {
                    return majorName(value);
                }
            },
            splitLine: {
                show: true
            }
        },
        yAxis: {
            type: 'value',
            name: '高考成绩',
            splitArea: {
                show: true
            }
        },
        series: [
            {
                name: 'boxplot',
                type: 'boxplot',
                data: data.boxData,
                tooltip: {
                    formatter: function (param) {
                        return [
                            '专业: ' + majorName(param.name),
                            '上边缘: ' + param.data[5],
                            '下边缘: ' + param.data[1],
                            '中位数: ' + param.data[3],
                            'Q1~Q3: ' + param.data[2]+'~'+param.data[4],
                        ].join('<br/>');
                    }
                }
            },
            {
                name: '异常值',
                type: 'scatter',
                data: data.outliers
            }
        ],
         dataZoom: [
             {
                 type: 'slider',
                 show: true,
                 xAxisIndex: [0],
                 start: 0, //数据窗口范围的起始百分比
                 end: 400/sessionData.length,
                 top:'6%'
             },
             {
                 type: 'inside',
                 xAxisIndex: [0],
                 start: 0,
                 end: 400/sessionData.length
             }
         ]
    };

    // 使用刚指定的配置项和数据显示图表。
    if(majorLength == 0){
        document.getElementById('container').innerHTML="<h1>抱歉，暂无数据!</h1>"
    }else {
        myChart.setOption(option);
    }
</script>


</body>
</html>

