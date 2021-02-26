<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script src="../../lib/layui/layui.js" charset="utf-8"></script>



	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />


	<title>数据导入</title>
	<!-- <style>
		#SetJob label.error {
			margin-left: 10px;
			width: auto;
			color:red;
			display: inline;
		}
	</style> -->
</head>
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
<%-- <div>当前位置：数据管理 &gt; 数据导入</div>--%>
  <h1>数据导入</h1>
 	<form id="year" method="post" action="datainput?ActionType=put">
   	  	选择年份: <span class="select-box inline"> <select class="select"
					name="year"  onchange="valueChange(this)">
						<option value="all">全部</option>
						<c:forEach items="${YEARLIST}" var="year">
							<option class="opt"  value="${year.years }">${year.years }</option>
						</c:forEach>
				</select>
		</span>
   	  	<!-- <input type="submit" class="btn_2" value="年份选择" /> -->
	   
	</form>
    <form method="post" action="datainput?ActionType=fileup&year=${YEAR}" enctype="multipart/form-data">
        
        
         
   	  	 ${YEAR}年数据导入：<input type="file" name="file"/>
   	  	 
   	  	 <input name="btnadd" type="submit" class="btn_2" value="提交" />
	   
	</form>
	
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
	 <form method="post" action="datainput?ActionType=delete">
   	  	 <h1>清除数据表数据</h1>
   	  	 <input type="submit" class="btn_8" value="清除数据表数据" />
	   
	</form> 
	
	<form method="post" action="datainput?ActionType=calculate">
   	  	 <h1>生成数据计算</h1>
   	  	 <input type="submit" class="btn_6" value="生成数据计算" />
	   
	</form> 
	<form method="post" action="datainput?ActionType=cratetable">
   	  	 <h1>创建表</h1>
   	  	 <input type="submit" class="btn_4" value="创建表" />
	   
	</form> 
		<form name="SearchState" method="post"
			Action="export?ActionType=bd_new">
			<div class="text-c">
				<input type="submit" class="btn btn-primary radius" name="button"
					value="导出新生办理情况清单Excle表" />
			</div>
		</form>
		<form name="SearchState" method="post"
			Action="export?ActionType=lq">
			<div class="text-c">
				<input type="submit" class="btn btn-primary radius" name="button"
					value="导出录取志愿率情况Excle表" />
			</div>
		</form>
				<form name="SearchState" method="post"
			Action="export?ActionType=lq_20">
			<div class="text-c">
				<input type="submit" class="btn btn-primary radius" name="button"
					value="导出调剂率高于20%专业录取报到情况Excle表" />
			</div>
		</form>
				<form name="SearchState" method="post"
			Action="export?ActionType=lz_bd">
			<div class="text-c">
				<input type="submit" class="btn btn-primary radius" name="button"
					value="导出录取志愿率及报到情况Excle表" />
			</div>
		</form>
		<form method="post" action="datainput?ActionType=Excle&year=${YEAR}" enctype="multipart/form-data">
        
         <h1>Excle导入</h1>	
   	  	  Excle导入：<input type="file" name="file"/>
   	  	 
   	  	 <input name="btnadd" type="submit" class="btn_2" value="提交" />
	   
		</form>
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