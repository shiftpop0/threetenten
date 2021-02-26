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
        $("#USER_CODE").blur(function() {

			var flag = $("input[name=FLAG]").val();
			//更新不需要判断，新建需要判断
            if(flag == 'update'){
                return false;
            };
            var USER_CODE = $(this);
            if (USER_CODE.val() != "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authuser?ActionType=authuser_CheckDup",
                    data : {
                        "USER_CODE" : USER_CODE.val()
                    },
                    success : function(massage) {
                        if (massage == 'success') {
                            //alert(massage);
                            $('#msg').html('此用户已经存在，请使用其他用户名！');
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
            if(!reg.test($("input[name=USER_NAME]").val())){
                alert("请输入合法的用户名！");
                return false;
            }
            var txt = $("#msg").text();
            if ( txt == "") {
//                alert("==" + txt + "==" );
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authuser?ActionType=authuser_Add",
                    data : $("#AddUserForm").serialize(),
                    success : function(massage) {
                        if (massage == 'success') {
                        	goTo("authuser?ActionType=authuser_SelectInit");
                        }
                    }
                });
               
            }
            return false;
         });

        //返回
        $("#btnreturn").click(function() {
        	goTo("authuser?ActionType=authuser_SelectInit");
        });
        
		$("#AddUserForm").validate({
			rules: {
				USER_NAME: "required",
				USER_CODE: "required",
				ORG_NAME: {
					required: true,
					minlength: 2
				},
				TELPHONE: {
					required: true,
					minlength: 5
				},
				PHONE: {
					required: true,
					minlength: 5
				},
				EMAIL: {
					required: true,
					email: true
				}
			},
			messages: {
				USER_NAME: "请输入用户名",
				USER_CODE: "请输入登录名",
				ORG_NAME: {
					required: "输入单位名称",
					minlength: "单位名称不少于两个字符"
				},
				TELPHONE: {
					required: "输入电话号码",
					minlength: "电话号码不少于5位"
				},
				PHONE: {
					required: "输入手机号码",
					minlength: "手机号码不少于5位"
				},
				EMAIL: "输入符合要求的邮箱地址"
			}
		});
        
    });
    function init() {
        return $("#AddPersonForm").valid({
            onsubmit : false
        });
    }
    
    //设置值
    function setValue(code, name) {
        $("#ORG_CODE").val(code);
        $("#ORG_NAME").val(name);
    }


</script>

</head>
<body>
    <c:choose>
	    <c:when test="${CONDITIONMAP.FLAG == 'add'}">
   			<div id="current">当前位置：权限管理 &gt; 用户管理 &gt; 新建用户</div>
	    </c:when>
	    <c:when test="${CONDITIONMAP.FLAG == 'update'}">
   			<div id="current">当前位置：权限管理 &gt; 用户管理 &gt; 用户修改</div>
	    </c:when>
	    <c:otherwise>
   			<div id="current">当前位置：权限管理 &gt; 用户管理 &gt; 新建用户</div>
	    </c:otherwise>
    </c:choose>

    <form id="AddUserForm" name="AddUserForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="14%" class="xxpage_td2">人员姓名：</td>
                            <td width="86%" class="xxpage_td2">
                              	<input name="USER_NAME" id="USER_NAME" type="text" value="<c:out value="${USERINFO1.userName}"/>" />
                            	<input type="hidden" name="FLAG" value="<c:out value="${CONDITIONMAP.FLAG}"/>" />
                            </td>
                        </tr>
                        <tr>
						      <td  width="14%" class="xxpage_td2">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
						      <td  width="86%" class="xxpage_td2">
						        <select name = "USER_SEX" id = "USER_SEX">
						        	<option value = "0">男</option>
						        	<option value = "1">女</option>
						        </select>
						       </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">登录名称：</td>
                            <td class="xxpage_td2"><input id="USER_CODE"
                                name="USER_CODE" id="USER_CODE" type="text"
                                 <c:if test="${CONDITIONMAP.FLAG == 'update'}" > readonly = 'readonly' </c:if>
                                value="<c:out value="${USERINFO1.userCode}"/>" />
                            <div id="msg" style="color: red;"></div></td>
                        </tr>
                        <c:if test="${CONDITIONMAP.FLAG == 'add'}" >
	                        <tr>
	                            <td class="xxpage_td2">登录密码：</td>
	                            <td class="xxpage_td2"><input id="PASSWORD" name="PASSWORD" type="password"
	                                value="" />
	                            </td>
	                        </tr>
						</c:if>
                        <tr>
                            <td class="xxpage_td2">所属单位：</td>
                            <td width="86%" class="xxpage_td2">
                              <input name="ORG_NAME" id="ORG_NAME" type="text" 
                              value="<c:out value="${USERINFO1.deptName}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">联系电话：</td>
                            <td class="xxpage_td2"><input name="TELPHONE" id="TELPHONE" type="text"
                                value="<c:out value='${USERINFO1.telphone}'/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">手机：</td>
                            <td class="xxpage_td2"><input name="PHONE" id="PHONE" type="text"
                                value="<c:out value='${USERINFO1.phone}'/>"  />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">电子邮件：</td>
                            <td class="xxpage_td2"><input name="EMAIL" id="EMAIL" type="text"
                                value="<c:out value="${USERINFO1.email}"/>" />
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