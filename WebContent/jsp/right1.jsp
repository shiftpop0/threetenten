<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src = "../js/jquery-1.12.1.js" type = "text/javascript"></script>
	<script src = "../js/common.js" type = "text/javascript"></script>
	<script src = "../js/highcharts.js"></script>
	<script src = "../js/highcharts-3d.js"></script>
	<script src="../js/series-label.js"></script>
	<script src="../js/exporting.js"></script>
	<script src="../js/export-data.js"></script>
	
	    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/highcharts.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/fontawesome.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/templatemo-style.css"/>
		<style>
			    div{position:relative;}
			    button{
			        position:absolute;
			        right:0;
			        top: 0;
			    }
		</style>
		<script>		
		$(function(){
			var zhuanye1= '${sessionScope.USERLIST[0].majorName}';
			var zhuanye2= '${sessionScope.USERLIST[1].majorName}';
			var zhuanye3= '${sessionScope.USERLIST[2].majorName}';
			
			alert(list);
		});
		</script>
		
		<title>无标题文档</title>
	</head>

	<body bgcolor="#DAEEF3" style="overflow-Y: scroll; overflow-x: hidden;" id = "reportsPage">
	<div class="row tm-content-row">
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block" >
                    <form Action="enterpage?ActionType=enterpage_Select5" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class=" btn-primary" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tm-block-title">一志愿录取专业前五</h2>
                        <div id="container" style="height:250px">
							<script language="JavaScript">

							var zhuanye1= '${sessionScope.ENTERLIST1[0].enroll}';
							var zhuanye2= '${sessionScope.ENTERLIST1[1].enroll}';
							var zhuanye3= '${sessionScope.ENTERLIST1[2].enroll}';
							var zhuanye4= '${sessionScope.ENTERLIST1[3].enroll}';
							var zhuanye5= '${sessionScope.ENTERLIST1[4].enroll}';

							arrayObj=[zhuanye1,zhuanye2,zhuanye3,zhuanye4,zhuanye5];
							var sum1= parseInt('${sessionScope.ENTERLIST1[0].sum}');
							var sum2= parseInt('${sessionScope.ENTERLIST1[1].sum}');
							var sum3= parseInt('${sessionScope.ENTERLIST1[2].sum}');
							var sum4= parseInt('${sessionScope.ENTERLIST1[3].sum}');
							var sum5= parseInt('${sessionScope.ENTERLIST1[4].sum}');
							$('#container').highcharts({
								title:{
									text:"录取人数统计表"
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
                    <form Action="enterpage?ActionType=enterpage_Select7" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class=" btn-primary" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tm-block-title">近三年文史类专业录取排名前五专业</h2>
                        <div id="wyy" style="height:250px">
                            <script language="JavaScript">
							var major1 = new Array();
							var major4 = new Array();

							<c:forEach items="${ENTERLIST3}" var="major" varStatus="i">
								var a = parseInt('${major.sum}');
								var b = a+"";
								major4.push('${major.majorName}'+'('+b+'人)');
								major1.push(a);
							</c:forEach>
							var b = major1[0]+major1[1]+major1[2]+major1[3]+major1[4];
							Highcharts.chart('wyy', {
							    chart: {
							        plotBackgroundColor: null,
							        plotBorderWidth: null,
							        plotShadow: false,
							        type: 'pie'
							    },
							    title: {
							        text: '近三年情况'
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
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller">
                    <form Action="enterpage?ActionType=enterpage_Select6" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class=" btn-primary" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tm-block-title">专业热度排行</h2>
                        <div id="lzz" style="height:250px">
							<script language="JavaScript">

							var zhuanye1= '${sessionScope.ENTERLIST2[0].majorName}';
							var zhuanye2= '${sessionScope.ENTERLIST2[1].majorName}';
							var zhuanye3= '${sessionScope.ENTERLIST2[2].majorName}';
							var zhuanye4= '${sessionScope.ENTERLIST2[3].majorName}';
							var zhuanye5= '${sessionScope.ENTERLIST2[4].majorName}';

							arrayObj=[zhuanye1,zhuanye2,zhuanye3,zhuanye4,zhuanye5];
							var sum1= parseInt('${sessionScope.ENTERLIST2[0].hot}');
							var sum2= parseInt('${sessionScope.ENTERLIST2[1].hot}');
							var sum3= parseInt('${sessionScope.ENTERLIST2[2].hot}');
							var sum4= parseInt('${sessionScope.ENTERLIST2[3].hot}');
							var sum5= parseInt('${sessionScope.ENTERLIST2[4].hot}');
							$('#lzz').highcharts({
								title:{
									text:"专业热度统计表"
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
									name: '占比(%)',
									data: [sum1,sum2,sum3,sum4,sum5]
								}]
							});
							</script>
							</div>                        
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block">
                    <form Action="enterpage?ActionType=enterpage_Select8" method="post">
                    	<input type="hidden" name="ID" value="1"/>
                    	<button type="submit" class=" btn-primary" id="btn1">详情查看</button>
                    </form>
                        <h2 class="tm-block-title">近三年工科类专业录取排名前五专业</h2>
                        <div id="www" style="height:250px">
                       <script language="JavaScript">
							var major1 = new Array();
							var major4 = new Array();

							<c:forEach items="${ENTERLIST4}" var="major" varStatus="i">
								var a = parseInt('${major.sum}');
								var b = a+"";
								major4.push('${major.majorName}'+'('+b+'人)');
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
							        text: '近三年平均情况'
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
				<div class="col-12 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block tm-block-taller " style="overflow-Y: scroll">
                        <h2 class="tm-block-title">理工类专业录取TOP5</h2>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">名次</th>
									<th scope="col">省份</th>
									<th scope="col">录取人数</th>
									<th scope="col">录取率</th>
									<th scope="col">调剂率</th>
									<th scope="col">平均分</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${ENTERLIST5}" var="user" varStatus="i">
								<tr>
									<td>${i.index + 1 }</td>
									<td>${user.majorName }</td>
									<td>${user.sum }</td>
									<td>${user.rate }</td>
									<td>${user.rels }</td>
									<td><fmt:formatNumber type="number" value="${user.scoure}" pattern="0.00" maxFractionDigits="2"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<h2 class="tm-block-title">文史类专业录取TOP5</h2>
						<table class="table">
							<thead>
								<tr>
									<th scope="col">名次</th>
									<th scope="col">省份</th>
									<th scope="col">录取人数</th>
									<th scope="col">录取率</th>
									<th scope="col">调剂率</th>
									<th scope="col">平均分</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${sessionScope.ENTERLIST6}" var="user" varStatus="i">
								<tr>
									<td>${i.index + 1 }</td>
									<td>${user.majorName }</td>
									<td>${user.sum }</td>
									<td>${user.rate }</td>
									<td>${user.rels }</td>
									<td><fmt:formatNumber type="number" value="${user.scoure}" pattern="0.00" maxFractionDigits="2"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
                    </div>
                </div>	
		</div>
	</body>
</html>
