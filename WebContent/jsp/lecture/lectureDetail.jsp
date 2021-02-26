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
<title>Insert title here</title>
</head>
<script>
    $(function() {
        //查询
        $("#btnreturn").click(
            function() {
                formPost(window.AddDetailForm, '','lecture?ActionType=lecture_SelectInit', this);
            });
        //新建交流记录
        $("#btnadd").click(function(){
            var theOrder = $("input[name=LECT_CODE]").val();
            var memo = $("#LECT_MEMO").val();
 //alert(theOrder + memo);
	        $.ajax({
	            type : "post",
	            dataType : "text",
	            url : "lecture?ActionType=lecture_DetailAdd&LECT_CODE="+theOrder,
	            data : $("#AddDetailForm").serialize(),
	            success : function(massage) {
	                if (massage == 'success') {
	                	goTo("lecture?ActionType=lecture_Detail&LECT_CODE="+theOrder);
	                }
	            }
	        });
            
            return false;
        });
    });

</script>

<body>
<div id="current">当前位置：学术交流 &gt; 交流信息 &gt; 详细</div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
    </tr>

    <tr>
       <td  align = 'center' >学术交流讨论内容</td>
    </tr>
  </table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="search_shadow"></td>
    </tr>
    <tr>
        <td class="main_blank"></td>
    </tr>
</table>
<!--数据列表-START-->
<ec:table items="LECTINFO" var="row"
    action="lecture?ActionType=lecture_Detail"
    view="html" 
    autoIncludeParameters="false"
     >
    
    <ec:exportXls fileName="userList.xls" />
    <ec:row>
        <ec:column style="text-align:center;" width="7%" property="rowcount" cell="rowCount" title="序号" sortable="false"/>
        <ec:column property="inputTime" style="text-align:center;" title="时间" />
        <ec:column property="userName" style="text-align:center;" title="姓名" />
       <ec:column property="content" style="text-align:center;" title="内容" />
    </ec:row>
</ec:table>

<form name="AddDetailForm" id="AddDetailForm" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
    </tr>

    <tr>
       <td class="xxpage_td2">
        <input type="hidden" name="LECT_CODE" id="LECT_CODE" value="<c:out value="${CONDITIONMAP.LECT_CODE}"/>" />
       </td>
    </tr>
    
    <tr>
       <td width="15%" class="xxpage_td1">发表评论：</td>
       <td width="85%" class="xxpage_td2">
			<textarea rows="5" cols="200" name="LECT_MEMO" id="LECT_MEMO" ></textarea> 
       </td>
    </tr>

    <tr>
      <td colspan="2" class="search_td1" align = 'center'>
         <input id="btnadd" name="btnadd" type="button" class="btn_4" value="提交" />
         <input id="btnreturn" name="btnreturn" type="button" class="btn_2" value="返回" />
      </td>
    </tr>
  </table>
</form>

</body>
</html>