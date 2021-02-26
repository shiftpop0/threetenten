<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>专业对口率</title>
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
        <a><cite>专业对口率</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
<%--    <input id="check" type="submit" name="check" style="line-height:1.6em;margin-top:3px;float:right" value="查询" />--%>
</div>
<form class="layui-form nav-select">
    <div class="layui-form-item">
        <label class="layui-form-label">入学年份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select lay-filter="year" id="YEAR">
                <option value="2019">2019</option>
                <option value="2018">2018</option>
                <option value="2017">2017</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item nav-selectXY2">
        <label class="layui-form-label">选择学院:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select name="college" lay-filter="college" id="COLLEGE">
                <option value="计算机学院">计算机学院</option>
            </select>
        </div>
    </div>
</form>
<script>
    $("#YEAR").val("${YEAR}")
    layui.use('form',function () {
        var form = layui.form;
        form.on('select(year)',function (data) {
            window.location.href="test?ActionType=chart3&YEAR="+$("#YEAR").val();
        })
        /*form.on('select(college)',function (data) {
            window.location.href="test?ActionType=chart3&YEAR="+$("#YEAR").val()+"&COLLEGE="+$("#COLLEGE").val();
        })*/
    })
</script>
<div id="container" class="echarts-container"></div>
<script type="text/javascript">
    var collegeList = ${collegeList};
    for(let i = 0;i<collegeList.length;i++){
        collegeStr = "<option value='"+collegeList[i].college+"'>"+collegeList[i].college+"</option>"
    }
    document.getElementById("COLLEGE").innerHTML=collegeStr;
    $("#YEAR").val("${YEAR}")
    $("#COLLEGE").val("${COLLEGE}")
    $("#check").click(function(){
        // console.log("test?ActionType=chart3&YEAR="+$("#YEAR").val()+"&COLLEGE="+$("#COLLEGE").val());
        window.location.href="test?ActionType=chart3&YEAR="+$("#YEAR").val()+"&COLLEGE="+$("#COLLEGE").val();
    });

    var resArr = ${dklList};
    var professions = [];
    var number = [];
    var total_number = [];
    var dkl = [];
    for(let i = 0;i < resArr.length; i++){
        number[i] = resArr[i].number;
        total_number[i] = resArr[i].total_number;
        professions[i] = resArr[i].major;
        dkl[i] = resArr[i].dkl;
    }

    var dom = document.getElementById("container");
    var myChart = echarts.init(dom,'dark');
    var app = {};
    option = null;
    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['相关就业人数', '毕业人数', '专业对口就业率']
        },
        xAxis: [
            {
                type: 'category',
                data: professions,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '人数',
                axisLabel: {
                    formatter: '{value} 人'
                }
            },
            {
                type: 'value',
                name: '占比',
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [
            {
                name: '相关就业人数',
                type: 'bar',
                data: number
            },
            {
                name: '毕业人数',
                type: 'bar',
                data: total_number
            },
            {
                name: '专业对口就业率',
                type: 'line',
                yAxisIndex: 1,
                data: dkl
            }
        ]
    };
    if (option && typeof option == "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>