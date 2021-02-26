<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>系统首页</title>
<%--	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>--%>
<%--	<script src = "../js/common.js" type = "text/javascript"></script>--%>
<%--	<script src = "../js/highcharts.js"></script>--%>
<%--	<script src = "../js/highcharts-3d.js"></script>--%>
<%--	<script src="../js/series-label.js"></script>--%>
	<script src="../js/exporting.js"></script>
	<script src="../js/export-data.js"></script>
<%--    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/highcharts.css"/>--%>
<%--    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/fontawesome.min.css"/>--%>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css"/>
<%--    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-style.css"/>--%>
        <link rel="stylesheet" href="../css/font.css">
        <link rel="stylesheet" href="../css/xadmin.css">
        <link rel="stylesheet" href="../css/echartsHtmlbase.css">
        <link href="<%=request.getContextPath() %>/css/extremecomponents.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css" />

		<style>
                body{
                    background: #333;
                }
			    div{position:relative;}
			    button{
			        position:absolute;
			        right:0;
			        top: 0;
			    }
                .tit{
                    /*left:50px;*/
                    font-size:25px;
                    /*padding-left:10px;*/
                    /*background: grey;*/
                    text-align: center;
                    color:white;
                }
                .btn-check{
                    margin-top:5px;
                    display: inline-block;
                    padding-right: 0px;
                    background-image: linear-gradient(#ddd, #bbb);
                    border: 1px solid rgba(0,0,0,.2);
                    border-radius: .3em;
                    box-shadow: 0 1px white inset;
                    /*text-align: center;*/
                    text-shadow: 0 1px 1px grey;
                    color: #000000;
                    font-weight: bold;
                    cursor: pointer ;
                }
		</style>
<!-- 		<script>		
		$(function(){
<%--			var zhuanye1= '${sessionScope.USERLIST[0].majorName}';--%>
<%--			var zhuanye2= '${sessionScope.USERLIST[1].majorName}';--%>
<%--			var zhuanye3= '${sessionScope.USERLIST[2].majorName}';--%>
			
			alert(list);
		});
		</script> -->
		
		<title>系统首页</title>
	</head>

    <script src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/dark.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
	<body bgcolor="#DAEEF3" style="overflow-Y: scroll; overflow-x: hidden;" id = "reportsPage">
    <div class="x-nav">
    <span class="layui-breadcrumb">
<%--        <a href="javascript:void(0);">系统首页</a>--%>
        <a><cite>系统首页</cite></a>
    </span>
        <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right;" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px;"></i>
        </a>
<%--        <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:1.6em;margin-top:3px;float:right" onclick="history.back()" title="返回">--%>
<%--            <i class="layui-icon layui-icon-prev" style="line-height:30px"></i>--%>
<%--        </a>--%>
    </div>


	<div class="row tm-content-row">

                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block" >
                    <form Action="mainpage?ActionType=mainpage_Select5" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class="btn-check" >详情查看</button>
                    </form>
                        <h2 class="tit">一志愿录取人数专业前五排名</h2>
                        <div id="container" style="height:250px">
							<script language="JavaScript">

							var zhuanye1= '${sessionScope.MAJORLIST3[0].ZYMC}';
							var zhuanye2= '${sessionScope.MAJORLIST3[1].ZYMC}';
							var zhuanye3= '${sessionScope.MAJORLIST3[2].ZYMC}';
							var zhuanye4= '${sessionScope.MAJORLIST3[3].ZYMC}';
							var zhuanye5= '${sessionScope.MAJORLIST3[4].ZYMC}';

							arrayObj=[zhuanye1,zhuanye2,zhuanye3,zhuanye4,zhuanye5];
							var sum1= parseInt('${sessionScope.MAJORLIST3[0].LQZY1_sum}');
							var sum2= parseInt('${sessionScope.MAJORLIST3[1].LQZY1_sum}');
							var sum3= parseInt('${sessionScope.MAJORLIST3[2].LQZY1_sum}');
							var sum4= parseInt('${sessionScope.MAJORLIST3[3].LQZY1_sum}');
							var sum5= parseInt('${sessionScope.MAJORLIST3[4].LQZY1_sum}');

							<c:if test="${sum1}">
							</c:if>
							
							$('#container').highcharts({
								title:{
									text:"一志愿录取人数"
								},
								
								chart: {
									type: 'column'
								},
								xAxis: {
									//categories: [zhuanye1, zhuanye2,zhuanye3,zhuanye4,zhuanye5]
									categories: arrayObj
									},
								plotOptions: {
									series: {
										allowPointSelect: true
									}
								},
								series: [{
									name: '人数',
									data: [sum1,sum2,sum3,sum4,sum5]
								}]
							});
							</script>
							</div>
                    </div>
                   
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-overflow">
                    <form Action="mainpage?ActionType=mainpage_Select7" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class="btn-check" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tit">专业调剂率前五排名</h2>
                        <div id="wyy" style="height:250px">
                            <script language="JavaScript">
							var major1 = new Array();
							var major4 = new Array();

							<c:forEach items="${RLRATELIST}" var="rate" varStatus="i">
								var a = parseInt('${rate.TiaoJisum}');
								var b = a+"";
								major4.push('${rate.ZYMC}');
								major1.push(a);
							</c:forEach>
							//var b = major1[0]+major1[1]+major1[2]+major1[3]+major1[4];
							Highcharts.chart('wyy', {
							    chart: {
							        plotBackgroundColor: null,
							        plotBorderWidth: null,
							        plotShadow: false,
							        type: 'pie'
							    },
							    title: {
							        text: '占调剂人数比重'
							    },
							    tooltip: {
							        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							    },
							    plotOptions: {
							        pie: {
							            allowPointSelect: true,
							            cursor: 'pointer',
							            dataLabels: {
							                enabled: false
							            },
							            showInLegend: true
							        }
							    },
							    series: [{
							        name: 'Brands',
							        colorByPoint: true,
							        data: [{
							            name: major4[0],
							            y: major1[0]
							        }, {
							            name: major4[1],
							            y: major1[1]
							        }, {
							            name: major4[2],
							            y: major1[2]
							        }, {
							            name: major4[3],
							            y: major1[3]
							        }, {
							            name: major4[4],
							            y: major1[4]
							        }]
							    }]
							});
                            </script>
                        </div>
                	</div>
				</div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller">
                    <form Action="mainpage?ActionType=mainpage_Select6" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class="btn-check" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tit">志愿录取率前五专业</h2>
                        <div id="lzz" style="height:250px">
							<script language="JavaScript">

							var zhuanye1= '${sessionScope.MAJORLIST2[0].ZYMC}';
							var zhuanye2= '${sessionScope.MAJORLIST2[1].ZYMC}';
							var zhuanye3= '${sessionScope.MAJORLIST2[2].ZYMC}';
							var zhuanye4= '${sessionScope.MAJORLIST2[3].ZYMC}';
							var zhuanye5= '${sessionScope.MAJORLIST2[4].ZYMC}';

							arrayObj=[zhuanye1,zhuanye2,zhuanye3,zhuanye4,zhuanye5];
							var sum1= parseInt('${sessionScope.MAJORLIST2[0].LQZY_sum}');
							var sum2= parseInt('${sessionScope.MAJORLIST2[1].LQZY_sum}');
							var sum3= parseInt('${sessionScope.MAJORLIST2[2].LQZY_sum}');
							var sum4= parseInt('${sessionScope.MAJORLIST2[3].LQZY_sum}');
							var sum5= parseInt('${sessionScope.MAJORLIST2[4].LQZY_sum}');
							$('#lzz').highcharts({
								title:{
									text:"录取志愿人数"
								},
								
								chart: {
									type: 'column'
								},
								xAxis: {
									//categories: [zhuanye1, zhuanye2,zhuanye3,zhuanye4,zhuanye5]
									categories: arrayObj
									},
								plotOptions: {
									series: {
										allowPointSelect: true
									}
								},
								series: [{
									name: '人数',
									data: [sum1,sum2,sum3,sum4,sum5]
								}]
							});
							</script>
							</div>                        
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block">
                    <form Action="mainpage?ActionType=mainpage_Select8" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class="btn-check" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tit">最热专业前五排名</h2>
                        <div id="www" style="height:250px">
                        <script language="JavaScript">
							var major1 = new Array();
							var major4 = new Array();

							<c:forEach items="${MAJORLIST}" var="major" varStatus="i">
								var a = parseInt('${major.qz}');
								var b = a+"";
								major4.push('${major.ZYMC}');
								major1.push(a);
							</c:forEach>
							var b = major1[0]+major1[1]+major1[2]+major1[3]+major1[4];
							Highcharts.chart('www', {
							    chart: {
							        plotBackgroundColor: null,
							        plotBorderWidth: null,
							        plotShadow: false,
							        type: 'pie'
							    },
							    title: {
							        text: '报考热门程度统计表'
							    },
							    tooltip: {
							        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							    },
							    plotOptions: {
							        pie: {
							            allowPointSelect: true,
							            cursor: 'pointer',
							            dataLabels: {
							                enabled: false
							            },
							            showInLegend: true
							        }
							    },
							    series: [{
							        name: 'Brands',
							        colorByPoint: true,
							        data: [{
							            name: major4[0],
							            y: major1[0]/b
							        }, {
							            name: major4[1],
							            y: major1[1]/b
							        }, {
							            name: major4[2],
							            y: major1[2]/b
							        }, {
							            name: major4[3],
							            y: major1[3]/b
							        }, {
							            name: major4[4],
							            y: major1[4]/b
							        }]
							    }]
							});
                        </script>
                        </div>  
                    </div>
                </div>
			
		</div>
	</body>
</html>
