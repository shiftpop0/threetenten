<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2019/11/6
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>中国地图测试</title>
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
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="../../js/echarts_china.js"></script>
<script type="text/javascript" src="../../js/dark.js"></script>
<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">招生统计</a>
        <a><cite>招生地图</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<form class="layui-form nav-select">
    <div class="layui-form-item">
        <label class="layui-form-label">入学年份:</label>
        <div class="layui-input-inline layui-show-xs-block">
            <select lay-filter="filter" id="YEAR">
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
        form.on('select(filter)',function (data) {
            window.location.href="test?ActionType=map_Test&YEAR="+data.value;
        })
    })
    /*$("#YEAR").change(function () {
        alert(1)
        var year = $(this).children('option:selected').val()
    })*/
</script>
<div id="Map" class="echarts-container"></div>
<script>
    var selectedMapByProvince=${selectedMapByProvince};
    option = {
        title: {
            text:"${YEAR}年招生全国省份分布图",
        },
        tooltip: {//鼠标拂过提示框
            //动态监听
            //需要展出效果：{b}省<br>录取总人数:{c}<br>省控线:{省控线}
            //formatter:自定义展示数据功能
            formatter: function(params){
                var res =' ';
                var myseries = option.series;
                //如果该省份存在data值
                if(!(params.data==null)){
                    for (var i = 0; i < myseries.length; i++){
                        for(var j=0;j<myseries[i].data.length;j++){
                                if(myseries[i].data[j].name==params.name){
                                res+=myseries[i].data[j].name+'<br>'
                                    +'总录取人数：'+myseries[i].data[j].value
                                    +'<br>省控线(文):'+myseries[i].data[j].skx+'分<br>'
                                    +'省控线(理):'+myseries[i].data[j].skx2+'分<br>'
                                    +'录取专业top3:<br>1.'+myseries[i].data[j].first+
                                    '<br>2.'+myseries[i].data[j].second+
                                    '<br>3.'+myseries[i].data[j].third;
                            }
                        }
                    }
                    return res;
                }else{
                    return "该省暂无招生信息！"
                }
            }
        },
        visualMap: {//范围标尺
            min: 0,
            text:['标尺','点击筛选范围'],
            realtime: false,
            calculable: true,//是否隐藏手柄
            inRange: {
                color: ['#f5ced1', '#9a0300']
                // color: ['#FFC0A0', '#FF6B20']
            },
            itemWidth:30,
            // itemHeight:30,
            //100px
            top:100,
            showLabel:true,
            type:'piecewise',//连续型：continuous
            pieces:[
                {min:0,max:50},
                {min:50,max:100},
                {min:100,max:150},
                {min:150,max:200},
                {min:200},
            ],
        },
        geo: {//地理坐标系组件
            map: 'china',
            roam: true,//是否开启鼠标缩放和平移漫游
            label: {
                normal: {
                    show: true,//是否在地图上显示省名
                    textStyle: {
                        color: 'rgb(0,0,0)'//文字颜色和透明度
                    }
                }
            },
            itemStyle: {
                normal:{
                    borderColor: 'rgba(255,255,255,1)',//区域边界颜色和透明度
                    // borderWidth:1,
                },
                emphasis:{
                    areaColor: null,
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 2,
                    shadowColor: 'rgba(204,204,204,0.9)',
                }
            }
        },
        series : [
            {
                name: '省录取信息',
                type: 'map',
                roam: true,
                coordinateSystem: 'geo',
                geoIndex: 0,
                // tooltip: {show: false},
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
                        color: '#F06C00'
                    }
                },
                data:
                    ${selectedMapByProvince},
            }
        ]
    };


    var chart=echarts.init(document.getElementById("Map"),'dark')

    chart.on('click', function (params) {
        var name = params.name;
        <%--console.log("test?ActionType=mapProvince&name="+name+"&year=${YEAR}");--%>
        window.location.href=("test?ActionType=mapProvince&name="+name+"&year=${YEAR}");
    });
    chart.setOption(option)
</script>
</body>
</html>
