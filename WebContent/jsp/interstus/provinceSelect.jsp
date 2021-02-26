<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page-container">
	<form name="SearchState" method="post"
			Action="interstus?ActionType=interstuinfoSelectedByProvince">
			<div class="text-c">
				省份: <span class="select-box inline"> 
				<select class="select" name="PROVINCE">
						<option value="all">全部</option>
						<c:forEach items="${PROVINCETABLE}" var="PROVINCE">
							<option value="${PROVINCE.DQMC}">${PROVINCE.DQMC}</option>
						</c:forEach>
				</select>
				</span> 
				<input type="submit" class="btn btn-primary radius" name="button"
					value="查询" />
			</div>
		</form>
			<div>
				<table>
					<tbody>
					<c:forEach items="${PROVINCELIST}" var="PROVINCE">
						<tr>
							<td>${PROVINCE.DQDM}</td>
							<td>${PROVINCE.DQMC}</td>
							<td>${PROVINCE.FWMC}</td>
							<td>${PROVINCE.FWMC1}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
</body>
</html>