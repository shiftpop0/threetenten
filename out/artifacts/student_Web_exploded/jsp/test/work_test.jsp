<!DOCTYPE html>
<!--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>-->

<html>
<head>
    <meta charset="utf-8">
    <title>work_test</title>
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
        <a><cite>就业地图</cite></a>
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
            window.location.href="test?ActionType=work_test&YEAR="+data.value;
        })
    })
</script>
<%--<button style="position: absolute;top: 10%;left: 20%;z-index:98" id="city">切换至市（区）级数据</button>--%>

<div id="container" class="echarts-container" style="height: 100%"></div>
<%--消除左下角百度地图Logo--%>
<style>
    .BMap_cpyCtrl
    {
        display:none;
    }
    .anchorBL{
        display:none;
    }
</style>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=AGDry2U0jGn7bwz8HVsPnYQyr2LzBMQ9"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
<script type="text/javascript">

    renderEcharts(${provinceList})

    function renderEcharts(data){
        var topCity = data
        var mapCoordinate = {}

        topCity.forEach(function(val,index){
            mapCoordinate[val.name]=[val.longitude,val.latitude]
        })

        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        var data = topCity;
        var geoCoordMap = mapCoordinate;

        var convertData = function (data) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name: data[i].name,
                        value: geoCoord.concat(data[i].value)
                    });
                }
            }
            return res;
        };

        option = {
            title: {
                text: '${YEAR}年就业数据',
                subtext: 'data from 华科',
                sublink: 'http://jwgl.ncist.edu.cn/home.aspx',
                left: 'center'
            },
            //悬浮内容展示
            tooltip : {
                trigger: 'item',
                formatter: function (data) {
                    return data.seriesName+'<br>'+
                        '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#6d92c7;"></span>'
                        +data.name+': '+data.value[2]+'人'
                }
            },
            bmap: {
                center: [112, 33],
                zoom:5,
                roam: true,
                mapStyle: {
                    styleJson: [{
                        'featureType': 'water',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'land',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#f3f3f3'
                        }
                    }, {
                        'featureType': 'railway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#fdfdfd'
                        }
                    }, {
                        'featureType': 'highway',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'geometry.fill',
                        'stylers': {
                            'color': '#fefefe'
                        }
                    }, {
                        'featureType': 'poi',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'green',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'subway',
                        'elementType': 'all',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'manmade',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'local',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'arterial',
                        'elementType': 'labels',
                        'stylers': {
                            'visibility': 'off'
                        }
                    }, {
                        'featureType': 'boundary',
                        'elementType': 'all',
                        'stylers': {
                            'color': 'black'
                        }
                    }, {
                        'featureType': 'building',
                        'elementType': 'all',
                        'stylers': {
                            'color': '#d1d1d1'
                        }
                    }, {
                        'featureType': 'label',
                        'elementType': 'labels.text.fill',
                        'stylers': {
                            'color': '#999999'
                        }
                    }]
                }
            },
            series : [
                {
                    name: 'Others',
                    type: 'scatter',
                    coordinateSystem: 'bmap',
                    data: convertData(data),
                    symbolSize: function (val) {
                        return Math.sqrt(val[2])*4;
                    },
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: false
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#446fb9'
                        }
                    }
                },
                {
                    name: 'Top3',
                    type: 'effectScatter',
                    coordinateSystem: 'bmap',
                    data: convertData(data.sort(function (a, b) {
                        return b.value - a.value;
                    }).slice(0, 3)),
                    symbolSize: function (val) {
                        return Math.sqrt(val[2])*4;;
                    },
                    showEffectOn: 'render',
                    rippleEffect: {
                        brushType: 'stroke'
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {

                            formatter: '{b}',
                            position: 'right',
                            show: true,
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: "#6d92c7",
                            shadowBlur: 10,
                            shadowColor: '#6d92c7'
                        }
                    },
                    zlevel: 1
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
        myChart.on('click', function (params) {
            <%--console.log('test?ActionType=provinceJobTable&year=${YEAR}&province='+params.name);--%>
            xadmin.open('就业详情表','test?ActionType=provinceJobTable&year=${YEAR}&province='+params.name);
        });
    }
</script>
</body>
</html>