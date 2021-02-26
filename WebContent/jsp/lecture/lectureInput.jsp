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

$(function() {
	//类型框回显
    $("select[name='LECT_TYPE']").val('<c:out value="${LECTINFO.type}" />');
  	//多行文本框框回显
    $("#LECT_MEMO").val('<c:out value="${LECTINFO.memo}" />');
});

$(function(){
        //增加
        $("#btnadd").click(function() {
            var reg = /^20\d{2}-[0-1][0-9]-[0-3][0-9]$/ ;
            if(!reg.test($("input[name=INPUT_TIME]").val())){
                alert("请输入合法的日期！");
                return false;
            };
//            formPost(window.AddMeetForm, '','lecture?ActionType=lecture_Add', this);
            

            $.ajax({
                type : "post",
                dataType : "text",
                url : "lecture?ActionType=lecture_Add",
                data : $("#AddLectForm").serialize(),
                success : function(massage) {
                    if (massage == 'success') {
                    	goTo("lecture?ActionType=lecture_SelectInit");
                    }
                }
            });
               
            return false;
         });

        //返回
        $("#btnreturn").click(function() {
        	goTo("lecture?ActionType=lecture_SelectInit");
        });
});        
</script>

</head>
<body>
    <c:choose>
	    <c:when test="${CONDITIONMAP.FLAG == 'add'}">
   			<div id="current">当前位置：学术交流 &gt; 交流信息 &gt; 录入</div>
	    </c:when>
	    <c:otherwise>
   			<div id="current">当前位置：学术交流&gt; 交流信息 &gt; 修改</div>
	    </c:otherwise>
    </c:choose>

    <form id="AddLectForm" name="AddLectForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="18%" class="xxpage_td2">输入日期：</td>
                            <td width="82%" class="xxpage_td2">
                              	<input name="INPUT_TIME" id="INPUT_TIME" type="text" value="<c:out value="${LECTINFO.inputDate}"/>" />
                            	<input type="hidden" name="FLAG" value="<c:out value="${CONDITIONMAP.FLAG}"/>" />
                            	<input type="hidden" name="LECT_CODE" value="<c:out value="${CONDITIONMAP.LECT_CODE}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">地点：</td>
                            <td  class="xxpage_td2">
                              <input name="LECT_LOCATION" id="LECT_LOCATION" type="text" 
                              value="<c:out value="${LECTINFO.location}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">主题：</td>
                            <td class="xxpage_td2"><input name="LECT_TOPIC" id="LECT_TOPIC" type="text"
                                value="<c:out value='${LECTINFO.topic}'/>" />
                            </td>
                        </tr>
                         <tr>
                            <td class="xxpage_td2">参加人员：</td>
                            <td class="xxpage_td2">
                              <input name="LECT_ATTENDEES" id="LECT_ATTENDEES" type="text" 
                              value="<c:out value="${LECTINFO.attendees}"/>" />
                            </td>
                        </tr>
                        <tr>
						    <td  width="14%" class="xxpage_td2">类型：</td>
						    <td  width="86%" class="xxpage_td2">
						        <select name = "LECT_TYPE" id = "LECT_TYPE">
						        	<option value = "0">讲座</option>
						        	<option value = "1">论文</option>
						        	<option value = "2">图书</option>
						        </select>
						     </td>

                        </tr>
                        <tr>
                            <td class="xxpage_td2">备注：</td>
                            <td class="xxpage_td2">
								<textarea rows="3" cols="30" name="LECT_MEMO" id="LECT_MEMO" ></textarea> 
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