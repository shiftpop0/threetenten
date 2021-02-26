<%@ taglib uri="/tld/extremecomponents" prefix="ec" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/jquery.validate.js" type = "text/javascript"></script>
	<script src = "../js/messages_zh.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	
	<link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />
	<title></title>
	<style>
		#AddTimeForm label.error {
			margin-left: 10px;
			width: auto;
			color:red;
			display: inline;
		}
	</style>

<script>

$(function(){
        //增加
        $("#btnadd").click(function() {
            var reg = /^20\d{2}-[0-1][0-9]-[0-3][0-9]$/ ;
            if(!reg.test($("input[name=INPUT_TIME]").val())){
                alert("请输入合法的日期！");
                return false;
            }

            reg = /^[0-2]\d:[0-5]\d$/ ;
            if(!reg.test($("input[name=MORNING_BEGIN]").val())){
                alert("请输入合法的上午开始时间！");
                return false;
            }

            if(!reg.test($("input[name=MORNING_END]").val())){
                alert("请输入合法的上午结束时间！");
                return false;
            }

            if(!reg.test($("input[name=NOON_BEGIN]").val())){
                alert("请输入合法的下午开始时间！");
                return false;
            }

            if(!reg.test($("input[name=NOON_END]").val())){
                alert("请输入合法的下午结束时间！");
                return false;
            }

            if(!reg.test($("input[name=NIGHT_BEGIN]").val())){
                alert("请输入合法的晚上开始时间！");
                return false;
            }

            if(!reg.test($("input[name=NIGHT_END]").val())){
                alert("请输入合法的晚上结束时间！");
                return false;
            }

            $.ajax({
                type : "post",
                dataType : "text",
                url : "attendance?ActionType=worktime_Add",
                data : $("#AddTimeForm").serialize(),
                success : function(massage) {
                    if (massage == 'success') {
                    	goTo("attendance?ActionType=worktime_SelectInit");
                    }
                }
            });
               
            return false;
         });

        //返回
        $("#btnreturn").click(function() {
        	goTo("attendance?ActionType=worktime_SelectInit");
        });
});        
</script>

</head>
<body>
    <c:choose>
	    <c:when test="${CONDITIONMAP.FLAG == 'add'}">
   			<div id="current">当前位置：学生考勤 &gt; 工作时间录入 &gt; 录入时间</div>
	    </c:when>
	    <c:otherwise>
   			<div id="current">当前位置：学生考勤 &gt; 工作时间录入 &gt; 录入时间</div>
	    </c:otherwise>
    </c:choose>

    <form id="AddTimeForm" name="AddTimeForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="18%" class="xxpage_td2">输入日期：</td>
                            <td width="82%" class="xxpage_td2">
                              	<input name="INPUT_TIME" id="INPUT_TIME" type="text" value="<c:out value="${TIMEINFO.inputDate}"/>" />
                            	<input type="hidden" name="FLAG" value="<c:out value="${CONDITIONMAP.FLAG}"/>" />
                            	<input type="hidden" name="TIME_CODE" value="<c:out value="${CONDITIONMAP.TIME_CODE}"/>" />
                            </td>
                        </tr>
                        <tr>
                        <tr>
                            <td class="xxpage_td2">上午开始时间：</td>
                            <td  class="xxpage_td2">
                              <input name="MORNING_BEGIN" id="MORNING_BEGIN" type="text" 
                              value="<c:out value="${TIMEINFO.morningBeginTime}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">上午结束时间：</td>
                            <td class="xxpage_td2"><input name="MORNING_END" id="MORNING_END" type="text"
                                value="<c:out value='${TIMEINFO.morningEndTime}'/>" />
                            </td>
                        </tr>
                         <tr>
                            <td class="xxpage_td2">下午开始时间：</td>
                            <td class="xxpage_td2">
                              <input name="NOON_BEGIN" id="NOON_BEGIN" type="text" 
                              value="<c:out value="${TIMEINFO.noonBeginTime}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">下午结束时间：</td>
                            <td class="xxpage_td2"><input name="NOON_END" id="NOON_END" type="text"
                                value="<c:out value='${TIMEINFO.noonEndTime}'/>" />
                            </td>
                        </tr>
                         <tr>
                            <td class="xxpage_td2">晚上开始时间：</td>
                            <td class="xxpage_td2">
                              <input name="NIGHT_BEGIN" id="NIGHT_BEGIN" type="text" 
                              value="<c:out value="${TIMEINFO.nightBeginTime}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">晚上结束时间：</td>
                            <td class="xxpage_td2"><input name="NIGHT_END" id="NIGHT_END" type="text"
                                value="<c:out value='${TIMEINFO.nightEndTime}'/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">备注：</td>
                            <td class="xxpage_td2"><input name="TIME_MEMO" id="TIME_MEMO" type="text"
                                value="<c:out value='${TIMEINFO.memo}'/>" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan = "2" class="xxpage_td2">
                            	说明：时间格式：19:12，表示下午7点12分
                            </td>
                        </tr>
                        <tr>
                            <td colspan = "2" class="xxpage_td2">
                            	输入日期格式：2017-09-21，表示2017年9月21日
                            </td>
                        </tr>
                    </table>
                 </td>
            </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="49%" height="40" align="right"><input id="btnadd" name="btnadd" type="button" class="btn_2" value="提交" /></td>
                <td width="2%" align="center">&nbsp;</td>
                <td align="left"><input id="btnreturn" name="btnreturn" type="button" class="btn_2" value="返回" /></td>
            </tr>
        </table>
    </form>

</body>
</html>