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
	//审核
	$("#btnsubmit").click(function() {
	    if(confirm("是否进行审核?")){
		    $.ajax({
		        type : "post",
		        dataType : "text",
		        url : "attendance?ActionType=worktime_Inspect",
		        data : $("#ec").serialize(),
		        success : function(massage) {
		            if (massage == 'success') {
		            	goTo("attendance?ActionType=worktime_InspectInit");
		            }
		        }
		    });
	    }   
	
	    return false;
	 });
/*	
	//返回
	$("#btnreturn").click(function() {
		goTo("attendance?ActionType=worktime_InspectInit");
	});

	//选择检查框是否选中
	$(function() {
		var flag = true;
		$('[name=RCODE]:checkbox').each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$('#RALL').attr("checked", flag);
	});
*/	
});

</script>
<body>
<div id="current">当前位置：学生考勤 &gt; 工作时间审核</div>
<form name="InspectForm" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="4" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td width="89%" height="40" align="right"><input id="btnsubmit" name="btnsubmit" type="button" class="btn_4" value="审核通过" /></td>
          <td width="2%" align="center">&nbsp;</td>
          <td align="left"></td>
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
<ec:table items="TIMEINFO" var="row"  showPagination="false" 
    action="attendance?ActionType=worktime_InspectInit"
    view="html" 
    autoIncludeParameters="false" >
    
    <ec:row>
    	<ec:column property="rcode" title="<input type='checkbox' name='RALL' id='RALL'  />选择" style="text-align:center;" >
	        <input type="checkbox" name="RCODE" id="RCODE"  value="${row.theOrder}" />
        </ec:column>
        
        <ec:column style="text-align:center;" property="rowcount" cell="rowCount" title="序号" sortable="false"></ec:column>
        <ec:column property="userName" style="text-align:center;" title="学生姓名" />
        <ec:column property="inputTime" style="text-align:center;" title="日期" />
        <ec:column property="morningBeginTime" style="text-align:center;" title="上午开始时间" />
        <ec:column property="morningEndTime" style="text-align:center;" title="上午结束时间" />
        <ec:column property="noonBeginTime" style="text-align:center;" title="下午开始时间" />
        <ec:column property="noonEndTime" style="text-align:center;" title="下午结束时间" />
        <ec:column property="nightBeginTime" style="text-align:center;" title="晚上开始时间" />
        <ec:column property="nightEndTime" style="text-align:center;" title="晚上结束时间" />
    </ec:row>
</ec:table>
</body>
<script >

$(function(){
	//调用封装函数，进行全选
//    checkAll('RALL','RCODE');
	$('#RALL').click(function(){
		$('[name=RCODE]:checkbox').prop('checked', this.checked);
	});

	$('input[name=RCODE]').click(function(){
		var flag = true;
		$('[name=RCODE]:checkbox').each(function(){
			if(!this.checked){
				flag = false;
			}
		});
		$('#RALL').prop("checked", flag);
	});
});


</script>
</html>