<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<title>Insert title here</title>
</head>
<div class="page-container">
    <form>
	<div class="text-c"> 
	
		报考情况:
		<span class="select-box inline">
			<select class="select" id="" name="">
				<option value="0">报考</option>
				<option value="AccountInfo">AccountInfo</option>
			</select>
		</span>
		录取情况:
		<span class="select-box inline">
			<select class="select" id="" name="">
				<option value="0">录取</option>
				<option value="AccountInfo">AccountInfo</option>
			</select>
		</span>
		
		地区:
		<span class="select-box inline">
			<select class="select" id="" name="">
				<option value="0">地区</option>
				<option value="AccountInfo">AccountInfo</option>
			</select>
		</span>
		<span class="select-box inline">
			 <div class="choice">
				<label class="radio">同今年比<input type="radio" name="radio" value="1" checked><i></i></label>
				<label class="radio">同历年比<input type="radio" value="2" name="radio"><i></i></label>
			</div>
		</span>

		<input type="hidden" id="" name="">
		<button type="submit" class="btn btn-primary radius" id="" name="">对比分析</button>
	</div>
	</form>
	</div>
	
		<div class="col-md-12"  margin-top:100px  >
				<div class="panel panel-default">
						<div class="panel-heading">分析图表</div>
						<canvas id="lineChart" style="height:350px"></canvas>
					
				</div>  	
		</div> 
					
	

</body>
</html>