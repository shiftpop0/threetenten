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
		#AddUserForm label.error {
			margin-left: 10px;
			width: auto;
			color:red;
			display: inline;
		}
	</style>

<script>

    $(function() {
        //判断登录名是否重复
        $("#ROLE_CODE").blur(function() {

			var flag = $("input[name=FLAG]").val();
			//更新不需要判断，新建需要判断
            if(flag == 'update'){
                return false;
            };
            var ROLE_CODE = $(this);
            if (ROLE_CODE.val() != "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authrole?ActionType=authrole_CheckDup",
                    data : {
                        "ROLE_CODE" : ROLE_CODE.val()
                    },
                    success : function(massage) {
                        if (massage == 'success') {
                            //alert(massage);
                            $('#msg').html('此角色已经存在，请使用其他角色编号！');
                        } else {
                            $('#msg').html('');
                        }
                    }
                });
            }
        });
        //增加
        $("#btnadd").click(function() {
            var reg = /^([\u4e00-\u9fa5]+|([a-zA-Z]+\s?)+)$/ ;
            if(!reg.test($("input[name=ROLE_NAME]").val())){
                alert("请输入合法的角色名称！");
                return false;
            }
            var txt = $("#msg").text();
            if ( txt == "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authrole?ActionType=authrole_Add",
                    data : $("#AddRoleForm").serialize(),
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
        
		$("#AddRoleForm").validate({
			rules: {
				ROLE_NAME: "required",
				ROLE_CODE: "required",
				ROLE_MEMO: {
					required: true,
					minlength: 2
				}
			},
			messages: {
				ROLE_NAME: "请输入用户名",
				ROLE_CODE: "请输入登录名",
				ROLE_MEMO: {
					required: "输入单位名称",
					minlength: "单位名称不少于两个字符"
				}
			}
		});
       
    });
    

</script>

</head>
<body>
    <c:choose>
	    <c:when test="${CONDITIONMAP.FLAG == 'update'}">
   			<div id="current">当前位置：权限管理 &gt; 角色管理 &gt; 角色修改</div>
	    </c:when>
	    <c:otherwise>
   			<div id="current">当前位置：权限管理 &gt; 角色管理 &gt; 新建角色</div>
	    </c:otherwise>
    </c:choose>

    <form id="AddRoleForm" name="AddRoleForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="xxpage_td2">角色编号：</td>
                            <td class="xxpage_td2"><input name="ROLE_CODE" id="ROLE_CODE" type="text"
                                 <c:if test="${CONDITIONMAP.FLAG == 'update'}" > readonly = 'readonly' </c:if>
                                value="<c:out value="${ROLEINFO.roleCode}"/>" />
                            <div id="msg" style="color: red;"></div></td>
                        </tr>
                        <tr>
                            <td width="14%" class="xxpage_td2">角色名称：</td>
                            <td width="86%" class="xxpage_td2">
                              	<input name="ROLE_NAME" id="ROLE_NAME" type="text" value="<c:out value="${ROLEINFO.roleName}"/>" />
                            	<input type="hidden" name="FLAG" value="<c:out value="${CONDITIONMAP.FLAG}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">角色说明：</td>
                            <td width="86%" class="xxpage_td2">
                              <input name="ROLE_MEMO" id="ROLE_MEMO" type="text" 
                              value="<c:out value="${ROLEINFO.roleMemo}"/>" />
                            </td>
                        </tr>
                    </table>
                 </td>
            </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="49%" height="40" align="right"><input id="btnadd" name="btnadd" type="button" class="btn_2" value="确认" /></td>
                <td width="2%" align="center">&nbsp;</td>
                <td align="left"><input id="btnreturn" name="btnreturn" type="button" class="btn_2" value="返回" /></td>
            </tr>
        </table>
    </form>

</body>
</html>