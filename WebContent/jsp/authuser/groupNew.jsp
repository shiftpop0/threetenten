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
        //判断小组名是否重复
        $("#GROUP_CODE").blur(function() {

			var flag = $("input[name=FLAG]").val();
			//更新不需要判断，新建需要判断
            if(flag == 'update'){
                return false;
            };
            var GROUP_CODE = $(this);
            if (GROUP_CODE.val() != "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authuser?ActionType=authgroup_CheckDup",
                    data : {
                        "GROUP_CODE" : GROUP_CODE.val()
                    },
                    success : function(massage) {
                        if (massage == 'success') {
                            //alert(massage);
                            $('#msg').html('此小组已经存在，请使用其他小组编号！');
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
            if(!reg.test($("input[name=GROUP_NAME]").val())){
                alert("请输入合法的小组名称！");
                return false;
            }
            var txt = $("#msg").text();
            if ( txt == "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authuser?ActionType=authgroup_Add",
                    data : $("#AddGroupForm").serialize(),
                    success : function(massage) {
                        if (massage == 'success') {
                        	goTo("authuser?ActionType=authgroup_SelectInit");
                        }
                    }
                });
               
            }
            return false;
         });
 
        //返回
        $("#btnreturn").click(function() {
        	goTo("authuser?ActionType=authgroup_SelectInit");
        });
        
		$("#AddGroupForm").validate({
			rules: {
				GROUP_NAME: "required",
				GROUP_CODE: "required",
				GROUP_MEMO: {
					required: true,
					minlength: 2
				}
			},
			messages: {
				GROUP_NAME: "请输入小组名称",
				GROUP_CODE: "请输入小组编号",
				GROUP_MEMO: {
					required: "输入小组说明",
					minlength: "小组说明不少于两个字符"
				}
			}
		});
       
    });
    

</script>

</head>
<body>
    <c:choose>
	    <c:when test="${CONDITIONMAP.FLAG == 'update'}">
   			<div id="current">当前位置：用户管理 &gt; 小组管理 &gt; 小组修改</div>
	    </c:when>
	    <c:otherwise>
   			<div id="current">当前位置：用户管理 &gt; 小组管理 &gt; 新建小组</div>
	    </c:otherwise>
    </c:choose>

    <form id="AddGroupForm" name="AddGroupForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="xxpage_td2">小组编号：</td>
                            <td class="xxpage_td2"><input name="GROUP_CODE" id="GROUP_CODE" type="text"
                                 <c:if test="${CONDITIONMAP.FLAG == 'update'}" > readonly = 'readonly' </c:if>
                                value="<c:out value="${GROUPINFO.groupCode}"/>" />
                            <div id="msg" style="color: red;"></div></td>
                        </tr>
                        <tr>
                            <td width="14%" class="xxpage_td2">小组名称：</td>
                            <td width="86%" class="xxpage_td2">
                              	<input name="GROUP_NAME" id="GROUP_NAME" type="text" value="<c:out value="${GROUPINFO.groupName}"/>" />
                            	<input type="hidden" name="FLAG" value="<c:out value="${CONDITIONMAP.FLAG}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">所在单位</td>
                            <td width="86%" class="xxpage_td2">
                              <input name="ORGAN_NAME" id="ORGAN_NAME" type="text"  readonly = 'readonly' 
                              value="<c:out value="${CONDITIONMAP.ORGAN_NAME}"/>" />
                              <input type="hidden" name="ORGAN_CODE" id="ORGAN_CODE" value="<c:out value="${CONDITIONMAP.ORGAN_CODE}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">小组说明：</td>
                            <td width="86%" class="xxpage_td2">
                              <input name="GROUP_MEMO" id="GROUP_MEMO" type="text" 
                              value="<c:out value="${GROUPINFO.groupMemo}"/>" />
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