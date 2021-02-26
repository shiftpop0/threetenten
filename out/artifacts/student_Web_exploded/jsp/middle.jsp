<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>演示系统</title>

    <style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        -->
    </style>
    <style>
        .navPoint {
            COLOR: white;
            CURSOR: hand;
            FONT-FAMILY: Webdings;
            FONT-SIZE: 9pt
        }
    </style>
    <script>
        function switchSysBar() {
            var locate = location.href.replace('middle.jsp', '');
            var ssrc = document.all("img1").src.replace(locate, '');
//	alert(ssrc);
            if (ssrc == "../images/kaiguan01.jpg") {
                document.all("img1").src = "../images/kaiguan_1.jpg";
                document.all("frmTitle").style.display = "none";
            } else {
                document.all("img1").src = "../images/kaiguan01.jpg";
                document.all("frmTitle").style.display = "";
            }
        }
    </script>

</head>
<body>
<table width="100%" height="666" border="0" cellpadding="0" cellspacing="0">
    <tr>

        <form action="#" method="post"></form>
        <td height="100%" width="197" bgcolor="#daeef3" id=frmTitle noWrap name="frmTitle" align="center" valign="top">
            <iframe name="I1" height="100%" width="197" src="left.jsp" border="0" frameborder="0" scrolling="yes">
                浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
            </iframe>
        </td>

        <td width="8" valign="middle" background="../images/kaiguan_bg.jpg" onclick=switchSysBar()>
            <SPAN class=navPoint id=switchPoint title=关闭/打开左栏><img src="../images/kaiguan01.jpg" name="img1" width=8
                                                                   height=60 id=img1></SPAN>
        </td>

        <td height="100%" align="center" valign="top">
            <iframe id="iframe" name="I2" height="100%" width="99.9%" border="0" frameborder="0" scrolling="yes"
                    src="right.jsp">
                浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。
            </iframe>
        </td>
    </tr>
</table>

</body>
</html>