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
	//增加
	$("#btnsubmit").click(function() {
	    if(confirm("是否要修改角色授权?")){
		    $.ajax({
		        type : "post",
		        dataType : "text",
		        url : "authrole?ActionType=authrole_Authrize&ROLE_CODE=${CONDITIONMAP.ROLE_CODE}",
		        data : $("#ec").serialize(),
		        success : function(massage) {
		            if (massage == 'success') {
		            	goTo("authrole?ActionType=authrole_SelectInit");
		            }
		        }
		    });
	    }   
	
	    return false;
	 });
	
	//返回
	$("#btnreturn").click(function() {
		goTo("authrole?ActionType=authrole_SelectInit");
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
<div id="current">当前位置：权限管理 &gt; 角色授权</div>
<form name="AuthrizeForm" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="search_table">
    <tr class="search_title_tr">
      <td colspan="4" class="search_title_L"><img src="../images/search_titleleft.jpg" width="84" height="30" align="top" /></td>
      <td width="25%" class="search_title_R"><img src="../images/search-titleright.jpg" width="8" height="30" align="top" /></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td width="89%" height="40" align="right"><input id="btnsubmit" name="btnsubmit" type="button" class="btn_2" value="提交" /></td>
          <td width="2%" align="center">&nbsp;</td>
          <td align="left"><input id="btnreturn" name="btnreturn" type="button" class="btn_2" value="返回" /></td>
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
<ec:table items="ROLEMENU" var="row"  showPagination="false" 
    action="authrole?ActionType=authrole_AuthrizeInit"
    view="html" 
    autoIncludeParameters="false" >
    
    <ec:row>
    	<ec:column property="rcode" title="<input type='checkbox' name='MALL' id='MALL'  />选择" style="text-align:center;" >
		    <c:choose>
			    <c:when test="${row.roleCode != '' && row.roleCode != null}">
			        <input type="checkbox" name="MCODE" id="MCODE"  checked = "checked" value="${row.mc}" />
			    </c:when>
			    <c:otherwise>
			        <input type="checkbox" name="MCODE" id="MCODE"  value="${row.mc}" />
			    </c:otherwise>
		    </c:choose>
        </ec:column>
        
        <ec:column style="text-align:center;" property="rowcount" cell="rowCount" title="序号" sortable="false"></ec:column>
        <ec:column property="mc" style="text-align:center;" title="菜单号" />
        <ec:column property="menuName" style="text-align:center;" title="菜单名称" />
        <ec:column property="linkPage" style="text-align:center;" title="连接地址" />
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