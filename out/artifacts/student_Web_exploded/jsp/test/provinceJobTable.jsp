<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2020/2/26
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>就业详情表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../lib/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-card-body ">
    <table class="layui-table layui-form">
        <thead>
        <tr>
            <th>学院</th>
            <th>专业</th>
            <th>姓名</th>
            <th>学号</th>
            <th>毕业年份</th>
            <th>单位所在地</th>
            <th>就业单位</th>
            <th>市（区）</th>
            <th>省份</th>
        </tr>
        </thead>
        <tbody id="jobTable">
        </tbody>
    </table>
</div>
<%--<script src="../../lib/layui/layui.js" charset="utf-8"></script>--%>
</body>
<script>
    var provinceJobList = ${provinceJobList};
    var tableStr = '<tr>';
    provinceJobList.forEach(function(val,index){
        tableStr += '<td>'+val.college+'</td>'+
                '<td>'+val.major+'</td>'+
                '<td>'+val.name+'</td>'+
                '<td>'+val.student_number+'</td>'+
                '<td>'+val.year+'</td>'+
                '<td>'+val.dwszd+'</td>'+
                '<td>'+val.company+'</td>'+
                '<td>'+val.city+'</td>'+
                '<td>'+val.province+'</td></tr>';
    })
    console.log(tableStr)
    document.getElementById("jobTable").innerHTML=tableStr;
</script>
</html>
