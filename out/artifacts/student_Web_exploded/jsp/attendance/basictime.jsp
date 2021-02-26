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

$(function(){
	//提交
	$("#btnsubmit").click(function() {
	    if(confirm("是否要设置基础时间?")){
		    
            var reg = /^20\d{2}-[0-1][0-9]-[0-3][0-9]$/ ;
            if(!reg.test($("input[name=START_TIME]").val())){
                alert("请输入合法的开始日期！");
                return false;
            }

            if(!reg.test($("input[name=END_TIME]").val())){
                alert("请输入合法的结束日期！");
                return false;
            }

			var start = $("input[name=START_TIME]").val();
			var end = $("input[name=END_TIME]").val();
			var len = $("input[name=BASIC_TIME]").val();

		    $.ajax({
		        type : "post",
		        dataType : "text",
		        url : "attendance?ActionType=basictime_Set&START_TIME=" + start+"&END_TIME=" + end+"&BASIC_TIME=" + len,
		        data : $("#ec").serialize(),
		        success : function(massage) {
		            if (massage == 'success') {
		            	goTo("attendance?ActionType=basictime_DetailInit");
		            }
		        }
		    });
	    }   
	
	    return false;
	 });
	
	//查看设置基础时间详细记录
	$("#btnreturn").click(function() {
		goTo("attendance?ActionType=basictime_DetailInit");
	});

	//选择检查框是否选中
	$(function() {
		var flag = true;
		$('[name=MCODE]:checkbox').each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$('#MALL').attr("checked", flag);
	});
	
});

</script>
<body>
<div id="current">当前位置：学生考勤 &gt; 设置基础时间</div>
<form name="BasicTimeForm" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="5" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>
    
    <tr>
      <td width="15%" class="search_td1">开始日期：</td>
      <td class="search_td2">
        <input name="START_TIME" id = "START_TIME" type="text" size="16" value="<c:out value='${CONDITIONMAP.START_TIME}' />" />
      </td>
      <td width="15%" class="search_td1">结束日期：</td>
      <td class="search_td2">
        <input name="END_TIME" id = "END_TIME" type="text" size="16" value="<c:out value='${CONDITIONMAP.END_TIME}' />" />
      </td>
      
      <td width="20%" class="search_td1">每天基础时间（分钟）：</td>
      <td width="20%" class="search_td2">
        <input name="BASIC_TIME" id = "BASIC_TIME" type="text" size="16" value="<c:out value='${CONDITIONMAP.BASIC_TIME}' />" />
      </td>
      </tr>
      <tr>
          <td colspan =3 class="search_td1">开始时间和结束时间格式实例：2017-09-01表示2017年9月1日</td>
          <td colspan =2 class="search_td1">每天基础时间是分钟数，例如384，最高数字720</td>
          <td align="center">
              <input id="btnsubmit" name="btnsubmit" type="button" class="btn_2" value="提交" />
              <input id="btnreturn" name="btnreturn" type="button" class="btn_4" value="查看详情" />
          </td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
      </tr>
  </table>
  </form>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="search_shadow"></td>
    </tr>
    <tr>
        <td class="main_blank"></td>
    </tr>
</table>
<!--数据列表-START-->
<ec:table items="USERINFO" var="row"  showPagination="false" 
    action="attendance?ActionType=basictime_SelectInit"
    view="html" 
    autoIncludeParameters="false" >
    
    <ec:row>
    	<ec:column property="rcode" title="<input type='checkbox' name='MALL' id='MALL'  />选择" style="text-align:center;" >
	        <input type="checkbox" name="MCODE" id="MCODE"  value="${row.userCode}" />
        </ec:column>
        
        <ec:column style="text-align:center;" property="rowcount" cell="rowCount" title="序号" sortable="false"></ec:column>
        <ec:column property="userCode" style="text-align:center;" title="用户编号" />
        <ec:column property="userName" style="text-align:center;" title="用户名称" />
        <ec:column property="deptName" style="text-align:center;" title="单位名称" />
    </ec:row>
</ec:table>
</body>
<script >

$(function(){
	//调用封装函数，进行全选
	$('#MALL').click(function(){
		$('[name=MCODE]:checkbox').prop('checked', this.checked);
	});

	$('input[name=MCODE]').click(function(){
		var flag = true;
		$('[name=MCODE]:checkbox').each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$('#MALL').prop("checked", flag);
	});
});


</script>
</html>