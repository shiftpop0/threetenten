<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="../js/jquery-1.12.1.js" type="text/javascript"></script>
    <script src="../js/common.js" type="text/javascript"></script>
    <script src="../js/echarts-en.common.js"></script>

    <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css"/>
    <title>Insert title here</title>
</head>

<body>
<h1>测试页面</h1>
<div id="main" style="width: 600px;height:400px;">

</div>


<ec:table items="middleSchoolList" var="row"
          action="test?ActionType=test"
          view="html">
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号"
                   sortable="false"/>
        <ec:column property="XM" style="text-align:center;" title="姓名"/>
        <ec:column property="ZXMC" style="text-align:center;" title="中学名称"/>
        <ec:column property="CJ" style="text-align:center;" title="成绩"/>
    </ec:row>
</ec:table>

<script type="text/javascript">
    var selectedMapByProvince = '<%=request.getAttribute("middleSchoolList")%>';
    console.log('test数组'+selectedMapByProvince);

    var XM = new Array()
    var ZXMC = new Array()
    var CJ = new Array()

    <c:forEach items="${middleSchoolList}" var="element" varStatus="i">
        var zxmc = '${element.ZXMC}';
        var xm = '${element.XM}';
        var cj = '${element.CJ}';
        XM.push(xm);
        ZXMC.push(zxmc);
        CJ.push(cj);
    </c:forEach>

    console.log(XM)
    console.log(ZXMC)
    console.log(CJ)

    /*数据接口测试
    *
    * 1.完整格式*/
    var count;
    var countByScore = function(minScore,maxScore){
        count=0;
        for(var i=0;i<CJ.length;i++){
            if ( CJ[i] > minScore && CJ[i] < maxScore){
                count++;
            }
        }
        var result = count;
        count = 0;
        return result;
    }

    const testData1 = [
        {
            name: '人数',
            value: countByScore(600,610)
        }
        , {
            name: '人数',
            value: countByScore(610,620)
        }
        , {
            name: '人数',
            value: countByScore(620,630)
        }
        , {
            name: '人数',
            value: countByScore(630,640)
        }
        , {
            name: '人数',
            value: countByScore(640,650)
        }
        , {
            name: '人数',
            value: countByScore(650,660)
        }];

    const testData2 = [10, 20, 30, 40, 50, 60]
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '成绩600分以上的学生'
        },
        tooltip: {
            formatter: function (params) {
                // console.log(params)
                var p = params
                if (p.componentIndex == 0) {
                    return p.seriesName + '<br>' + p.data.name + '<br>' + p.data.value
                } else if (p.componentIndex == 1) {
                    return p.seriesName + '<br>值：' + p.data
                }
            }
        },
        legend: {
            data: ['销量']
        },
        xAxis: {
            data: ["600", "610", "620", "630", "640", ">650"]
        },
        yAxis: {},
        series: [{
            name: '专业1',
            type: 'bar',
            data: testData1
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>