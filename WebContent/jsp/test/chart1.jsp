<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>就业城市水平</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" >
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/xadmin.css">
    <link rel="stylesheet" href="../../css/echartsHtmlbase.css">
    <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
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
        <a><cite>就业城市水平</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>
<div id="main" class="echarts-container"></div>
<ec:table items="majorList" var="row"
          action="test?ActionType=chart"
          view="html">
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号"
                   sortable="false"/>
        <ec:column property="major" style="text-align:center;" title="专业"/>
        <ec:column property="year" style="text-align:center;" title="毕业年份"/>
        <ec:column property="count" style="text-align:center;" title="总人数"/>
        <ec:column property="level1" style="text-align:center;" title="一线城市人数"/>
        <ec:column property="level2" style="text-align:center;" title="二线城市人数"/>
        <ec:column property="level3" style="text-align:center;" title="其他城市人数"/>
        <ec:column property="detail" style="text-align:center;" title="详情"/>
    </ec:row>
</ec:table>
<script type="text/javascript">
    var major = ${majorListJSON};   //
    console.log(major);
    setTimeout(function () {
        var myChart = echarts.init(document.getElementById('main'),'dark');
        var option = {
            legend: {},
            tooltip: {
                trigger: 'axis',
                showContent: false
            },
            dataset: {
                source: [
                    ['product', '2017', '2018', '2019', '2020'],
                    ['一线城市', getLevelCount('2017','1'), getLevelCount('2018','1'), getLevelCount('2019','1'), 0, 0, 0],
                    ['二线城市', getLevelCount('2017','2'), getLevelCount('2018','2'), getLevelCount('2019','2'),  0, 0, 0],
                    ['其他', getLevelCount('2017','3'), getLevelCount('2018','3'), getLevelCount('2019','3'),  0, 0, 0],

                ]
            },
            xAxis: {type: 'category'},
            yAxis: {gridIndex: 0},
            grid: {top: '55%'},
            series: [
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                {
                    type: 'pie',
                    id: 'pie',
                    radius: '30%',
                    center: ['50%', '25%'],
                    label: {
                        formatter: '{b}: {@2017} ({d}%)'
                    },
                    encode: {
                        itemName: 'product',
                        value: '2017',
                        tooltip: '2017'
                    }
                }
            ]
        };

        myChart.on('updateAxisPointer', function (event) {
            var xAxisInfo = event.axesInfo[0];
            if (xAxisInfo) {
                var dimension = xAxisInfo.value + 1;
                myChart.setOption({
                    series: {
                        id: 'pie',
                        label: {
                            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                        },
                        encode: {
                            value: dimension,
                            tooltip: dimension
                        }
                    }
                });
            }
        });

        myChart.setOption(option);

    });

    function getLevelCount(year,level) {
        var count=0;

        for(var i=0;i<major.length;i++){
            var temp=major[i];

            if (temp.year===year){
                var num=0;
                if (level==='1'){
                    num = parseInt(temp.level1);
                }else if(level==='2'){
                    num = parseInt(temp.level2);
                }else{
                    num = parseInt(temp.level3);
                }
                count+=num;
            }
        }
        console.log(count)
        return count;
    }
</script>
</body>
</html>