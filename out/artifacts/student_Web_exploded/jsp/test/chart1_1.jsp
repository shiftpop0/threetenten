<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>test</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../lib/layui/css/layui.css"  media="all">
</head>

<!-- 引入 echarts.js -->

<%--<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />--%>

<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:void(0);">就业分析</a>
        <a><cite>就业城市水平</cite></a>
        <a><cite>详情</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
    </a>
</div>

<%--<div id="current">当前位置：小组功能测试 &gt; 就业城市水平 > 详情</div>--%>
<script>
    console.log("${year}")
    console.log("${majorName}")

</script>
<h1 style="text-align:center;">该专业学生就业详情</h1>
<div class="layui-card-body ">
    <table class="layui-table layui-form">
    <thead>
    <tr>
        <th>序号</th>
        <th>学院</th>
        <th>专业</th>
        <th>姓名</th>
        <th>单位所在地</th>
        <th>公司</th>
    </tr>
    </thead>
    <tbody id="colunm">
    </tbody>
    </table>
</div>
<%--<script src="../../lib/layui/layui.js" charset="utf-8"></script>--%>
</body>
<script>
    // items="detail"
    <%--var detail = '<%=request.getAttribute("detail")%>' ;--%>
    var detail = ${detail};
    var tableStr = '<tr>';
    // alert(detail);
    console.log(detail)
    detail.forEach((v,i)=>{
        var index=i+1
        tableStr += '<td>'+index+'</td>'+
            '<td>'+v.college+'</td>'+
            '<td>'+v.major+'</td>'+
            '<td>'+v.name+'</td>'+
            '<td>'+v.dwszd+'</td>'+
            '<td>'+v.company+'</td></tr>';
    })
    // print(tableStr);
    console.log(tableStr)
    document.getElementById("colunm").innerHTML=tableStr;
</script>
<%--    <ec:table items="detail" var="row"--%>
<%--
<%--        <ec:row>--%>
<%--            <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号"--%>
<%--                       sortable="false"/>--%>
<%--            <ec:column property="college" style="text-align:center;" title="学院"/>--%>
<%--            <ec:column property="major" style="text-align:center;" title="专业"/>--%>
<%--            <ec:column property="name" style="text-align:center;" title="姓名"/>--%>
<%--            <ec:column property="dwszd" style="text-align:center;" title="单位所在地"/>--%>
<%--            <ec:column property="company" style="text-align:center;" title="公司"/>--%>
<%--        </ec:row>--%>
<%--    </ec:table>--%>



</html>