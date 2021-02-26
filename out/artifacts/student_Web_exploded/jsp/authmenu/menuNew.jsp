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
        //判断菜单编号是否重复
        $("#MENU_CODE").blur(function() {
			var flag = $("input[name=FLAG]").val();
			//更新不需要判断，新建需要判断
			if(flag == 'update'){
                return false;
            };
            var MENU_CODE = $(this);
            if (MENU_CODE.val() != "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authmenu?ActionType=authmenu_CheckDup",
                    data : {
                        "MENU_CODE" : MENU_CODE.val()
                    },
                    success : function(massage) {
                        if (massage == 'success') {
                            //alert(massage);
                            $('#msg').html('此菜单编号已经存在，请使用其他用编号！');
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
                alert("请输入合法的菜单名称！");
                return false;
            }
            var txt = $("#msg").text();
            if ( txt == "") {
                $.ajax({
                    type : "post",
                    dataType : "text",
                    url : "authmenu?ActionType=authmenu_Add",
                    data : $("#AddUserForm").serialize(),
                    success : function(massage) {
                        if (massage == 'success') {
                        	goTo("authmenu?ActionType=authmenu_SelectInit");
                        }
                    }
                });
               
            }
            return false;
         });

    	//上级菜单回显
    	$(function() {
    	$("select[name='MENU_PARRENT']")
    	.val(
    	'<c:out value="${MENUINFO.parrentCode}" />');
    	});

        //返回
        $("#btnreturn").click(function() {
        	goTo("authmenu?ActionType=authmenu_SelectInit");
        });
        
		$("#AddUserForm").validate({
			rules: {
				MENU_NAME: "required",
				MENU_CODE: "required",
				LINK_PAGE: {
					required: true,
					minlength: 2
				},
				MENU_ORDER: {
					required: true,
					minlength: 2
				}
			},
			messages: {
				MENU_NAME: "请输入菜单名",
				MENU_CODE: "请输入菜单编号",
				LINK_PAGE: {
					required: "输入连接地址",
					minlength: "连接地址不少于两个字符"
				},
				MENU_ORDER: {
					required: "输入菜单顺序号",
					minlength: "顺序号不少于两个字符"
				}
			}
		});
        
    });

</script>

</head>
<body>
    <c:choose>
	    <c:when test="${CONDITIONMAP.FLAG == 'update'}">
   			<div id="current">当前位置：权限管理 &gt; 菜单管理 &gt; 菜单修改</div>
	    </c:when>
	    <c:otherwise>
   			<div id="current">当前位置：权限管理 &gt; 菜单管理 &gt; 新建菜单</div>
	    </c:otherwise>
    </c:choose>

    <form id="AddUserForm" name="AddUserForm" method="post">
        <table width="600" height="10" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td height="30" align="center" class="xxpage">
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="xxpage_td2">菜单编号：</td>
                            <td class="xxpage_td2"><input name="MENU_CODE" id="MENU_CODE" type="text"
                                 <c:if test="${CONDITIONMAP.FLAG == 'update'}" > readonly = 'readonly' </c:if>
                                value="<c:out value="${MENUINFO.menuCode}"/>" />
                            <div id="msg" style="color: red;"></div></td>
                        </tr>
                        <tr>
                            <td width="14%" class="xxpage_td2">菜单名称：</td>
                            <td width="86%" class="xxpage_td2">
                              	<input name="MENU_NAME" id="MENU_NAME" type="text" value="<c:out value="${MENUINFO.menuName}"/>" />
                            	<input type="hidden" name="FLAG" value="<c:out value="${CONDITIONMAP.FLAG}"/>" />
                            </td>
                        </tr>
                        <tr>
                            <td width="14%" class="xxpage_td2">连接页面：</td>
                            <td width="86%" class="xxpage_td2">
                              	<input name="LINK_PAGE" id="LINK_PAGE" type="text" value="<c:out value="${MENUINFO.linkPage}"/>" />
                            </td>
                        </tr>
                        <tr>
						      <td  width="14%" class="xxpage_td2">上级菜单：</td>
						      <td  width="86%" class="xxpage_td2">
						        <select name = "MENU_PARRENT" id = "MENU_PARRENT">
<!-- 						        	<option value = "0">男</option>
						        	<option value = "1">女</option>
						        </select>
									<select> 
 -->									 
								    <c:forEach items="${PARRENTINFO}" var="parrent">  
								        <option value = ${parrent.menuCode}>${parrent.menuName}</option>  
								     </c:forEach>  
								  </select>  						        
						       </td>
                        </tr>
                        <tr>
                            <td class="xxpage_td2">菜单序号：</td>
                            <td width="86%" class="xxpage_td2">
                              <input name="MENU_ORDER" id=""MENU_ORDER"" type="text" 
                              value="<c:out value="${MENUINFO.menuOrder}"/>" />
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