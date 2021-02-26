<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>数据导入</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" >
<%--	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>--%>
<%--	<script src = "../js/jquery.validate.js" type = "text/javascript"></script>--%>
<%--	<script src = "../js/messages_zh.js" type = "text/javascript"></script>--%>
<%--	<script src = "../js/common.js" type = "text/javascript"></script>--%>

	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" href="../../css/font.css">
	<link rel="stylesheet" href="../../css/xadmin.css">
	<link rel="stylesheet" href="../../css/echartsHtmlbase.css">

	<style type="text/css">
		body{
			background: #333;
		}
		.input{
			width:430px;
			height: 105px;
			background: #cccccc;
			margin: 0 auto;
			border:5px solid #ffe390;
			margin-top: 60px;
			border-style:outset;
			padding: 20px;
			text-align: center;
			border-top-color: #f8f8f8;
			border-bottom-color: #f8f8f8;
			border-left-color: #f8f8f8;
			border-right-color: #f8f8f8;
			border-width:10px;
		}
		.input_1{
			height: 50px;
			text-align: center;
			line-height: 50px;
			font-size: 24px;
			font-weight: 800;
			border-bottom: 2px solid #b1b1b1;

		}
		.input_2{
			height: 60px;
			text-align: center;
			margin-top:18px;
		}
		.input_2_input{
			background: #eeeeee;
			border:2px solid #fff;
			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19); 
		}
		.btn{
			box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
		}
		
		.btn:hover {
		    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
		}
	</style>

</head>
<script src="../../lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../../js/xadmin.js"></script>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/dark.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>

<body>
	<div class="x-nav">
		<span class="layui-breadcrumb">
			<a href="javascript:void(0);">数据导入</a>
			<a><cite>手动导入</cite></a>
		</span>
		<a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
			<i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
		</a>
	</div>



	<div class="input">
		<form method="post" action="datainput?ActionType=Excle&year=${YEAR}" enctype="multipart/form-data">
			<div class="input_1"><span>Excle数据导入</span></div>
			<div class="input_2">
				<input type="file" name="file" class="input_2_input"/>
				<input name="btnadd" type="submit" class="btn" value="提交" onclick=""/>
			</div>
		</form>
	</div>
	<div class="input">
		<form method="post" action="datainput?ActionType=delete">
			<div class="input_1"><span>清除数据表数据</span></div>
			<div class="input_2">
				<input type="submit" class="btn" value="清除数据表数据" />
			</div>
		</form>
	</div>
	<div class="input">
		<form name="SearchState" method="post"  Action="export?ActionType=lz_bd">
			<div class="input_1"><span>导出Excel数据</span></div>
			<div class="input_2">
				<input type="submit" class="btn" name="button"  value="导出录取志愿率及报到情况Excle表" />
			</div>
		</form>
	</div>

<script>
    //console.log(1)
    /* $(function(){
       $('.selsct').on("change",function(){
           alert(1)
       })
   })  */
    var select=document.getElementsByClassName('select')[0]
    var Opt=document.getElementsByClassName('opt')
    var year=document.getElementById('year')
    console.log(year)
    for(var i=0;i<Opt.length;i++){
        select.onclick=function(){

            this.selected=true
        }
    }
    function valueChange(obj){
        var objS = document.getElementsByClassName('select')[0]
        var value = objS.options[objS.selectedIndex].getAttribute('value');
        console.log(value);
        year.submit()

    }

</script>

<script>
    //上传
    /*$("#btnadd").click(function() {
        //console.log($("#SetJobForm").serialize());
        //return false;
           var formData = new FormData($("#SetJobForm")[0]);
           console.log(formData);
            $.ajax({
                type : "post",
                dataType:"json",
                url : "upload?ActionType=fileup",
                contentType:"application/json; charset=utf-8",
                data : formData,
                success : function(massage) {
                    console.log(message);
                    if (massage == 'success') {
                        goTo("upload?ActionType=fileup");
                    }
                },
                error:function(e){
                    console.log(e);
                }

            });
        }
     );*/
</script>

</body>
</html>