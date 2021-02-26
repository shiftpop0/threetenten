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
		#ChangePassowrdForm label.error {
			margin-left: 10px;
			width: auto;
			color:red;
			display: inline;
		}
	</style>

<script>

    $(function() {
        //修改密码
        $("#btnsubmit").click(function() {
			
            var oldPassword = $("#PASSWORDOLD").val();
            var newPassword = $("#PASSWORDNEW").val();
            var cfmPassword = $("#PASSWORDCFM").val();

            if ( newPassword == cfmPassword) {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "personal?ActionType=personal_Change",
                    data : $("#ChangePassowrdForm").serialize(),
                    success : function(massage) {
                        if (massage == 'success') {
                        	alert("修改密码成功！");
                        }
                        else{
                        	alert("修改密码失败！");
                        }
                    }
                });
               
            }
            else{
                alert("新密码两次输入不一致，请重新输入！");
            }
            return false;
         });

		$("#ChangePassowrdForm").validate({
			rules: {
				PASSWORDOLD: "required",
				PASSWORDNEW: "required",
				PASSWORDCFM: {
					required: true,
					minlength: 2,
					equalTo: "#PASSWORDNEW"
				}
			},
			messages: {
				PASSWORDOLD: "请输入密码",
				PASSWORDNEW: "请输入新密码",
				PASSWORDCFM: {
					required: "输入确认密码",
					minlength: "确认密码不少于两个字符",
					equalTo: "新密码与确认密码不一致，重新输入"
				}
			}
		});
        
    });
    
</script>

</head>
<body>

   	<div id="current">当前位置：个人中心 &gt; 修改密码</div>
    <form id="ChangePassowrdForm" name="ChangePassowrdForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="xxpage_td2">原密码：</td>
                            <td class="xxpage_td2"><input id="PASSWORDOLD" name="PASSWORDOLD" type="password"
                                value="" />
                            	<input type="hidden" id="USER_CODE" name="USER_CODE" value="<c:out value="${CONDITIONMAP.USER_CODE}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">新密码：</td>
                            <td class="xxpage_td2"><input id="PASSWORDNEW" name="PASSWORDNEW" type="password"
                                value="" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">确认密码：</td>
                            <td class="xxpage_td2"><input id="PASSWORDCFM" name="PASSWORDCFM" type="password"
                                value="" />
                            </td>
                        </tr>
                    </table>
                 </td>
            </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td width="49%" height="40" align="right"><input id="btnsubmit" name="btnsubmit" type="button" class="btn_2" value="提交" /></td>
                <td width="2%" align="center">&nbsp;</td>
                <td align="left"></td>
            </tr>
        </table>
    </form>

</body>
</html>