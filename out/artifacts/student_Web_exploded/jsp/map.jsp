<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<link rel="icon"
	href="https://static.jianshukeji.com/hcode/images/favicon.ico">
<style>
.highcharts-key-cn-3682 {
	stroke-width: 0
}
</style>
<script src="../js/jquery-1.12.1.js"></script>
<script src="../js/highmaps.js"></script>
</head>
<body>
	<div id="container" style="height: 600px"></div>
	<div id="container"></div>
	<script src="../js/china.js"></script>
	<script>
 		var data = [ {
			name : '${sessionScope.SCHOOLLIST[0].name}',
			value : '${sessionScope.SCHOOLLIST[0].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[0].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[0].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[0].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[0].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[0].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[0].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[1].name}',
			value : '${sessionScope.SCHOOLLIST[1].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[1].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[1].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[1].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[1].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[1].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[1].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[2].name}',
			value : '${sessionScope.SCHOOLLIST[2].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[2].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[2].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[2].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[2].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[2].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[2].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[3].name}',
			value : '${sessionScope.SCHOOLLIST[3].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[3].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[3].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[3].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[3].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[3].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[3].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[4].name}',
			value : '${sessionScope.SCHOOLLIST[4].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[4].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[4].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[4].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[4].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[4].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[4].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[5].name}',
			value : '${sessionScope.SCHOOLLIST[5].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[5].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[5].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[5].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[5].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[5].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[5].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[6].name}',
			value : '${sessionScope.SCHOOLLIST[6].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[6].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[6].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[6].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[6].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[6].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[6].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[7].name}',
			value : '${sessionScope.SCHOOLLIST[7].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[7].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[7].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[7].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[7].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[7].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[7].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[8].name}',
			value : '${sessionScope.SCHOOLLIST[8].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[8].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[8].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[8].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[8].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[8].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[8].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[9].name}',
			value : '${sessionScope.SCHOOLLIST[9].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[9].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[9].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[9].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[9].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[9].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[9].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[10].name}',
			value : '${sessionScope.SCHOOLLIST[10].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[10].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[10].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[10].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[10].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[10].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[10].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[11].name}',
			value : '${sessionScope.SCHOOLLIST[11].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[11].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[11].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[11].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[11].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[11].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[11].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[12].name}',
			value : '${sessionScope.SCHOOLLIST[12].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[12].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[12].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[12].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[12].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[12].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[12].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[13].name}',
			value : '${sessionScope.SCHOOLLIST[13].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[13].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[13].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[13].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[13].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[13].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[13].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[14].name}',
			value : '${sessionScope.SCHOOLLIST[14].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[14].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[14].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[14].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[14].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[14].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[14].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[15].name}',
			value : '${sessionScope.SCHOOLLIST[15].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[15].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[15].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[15].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[15].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[15].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[15].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[16].name}',
			value : '${sessionScope.SCHOOLLIST[16].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[16].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[16].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[16].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[16].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[16].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[16].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[17].name}',
			value : '${sessionScope.SCHOOLLIST[17].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[17].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[17].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[17].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[17].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[17].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[17].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[18].name}',
			value : '${sessionScope.SCHOOLLIST[18].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[18].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[18].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[18].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[18].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[18].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[18].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[19].name}',
			value : '${sessionScope.SCHOOLLIST[19].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[19].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[19].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[19].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[19].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[19].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[19].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[20].name}',
			value : '${sessionScope.SCHOOLLIST[20].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[20].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[20].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[20].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[20].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[20].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[20].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[21].name}',
			value : '${sessionScope.SCHOOLLIST[21].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[21].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[21].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[21].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[21].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[21].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[21].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[22].name}',
			value : '${sessionScope.SCHOOLLIST[22].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[22].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[22].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[22].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[22].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[22].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[22].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[23].name}',
			value : '${sessionScope.SCHOOLLIST[23].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[23].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[23].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[23].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[23].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[23].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[23].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[24].name}',
			value : '${sessionScope.SCHOOLLIST[24].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[24].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[24].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[24].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[24].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[24].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[24].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[25].name}',
			value : '${sessionScope.SCHOOLLIST[25].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[25].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[25].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[25].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[25].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[25].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[25].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[26].name}',
			value : '${sessionScope.SCHOOLLIST[26].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[26].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[26].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[26].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[26].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[26].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[26].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[27].name}',
			value : '${sessionScope.SCHOOLLIST[27].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[27].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[27].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[27].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[27].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[27].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[27].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[28].name}',
			value : '${sessionScope.SCHOOLLIST[28].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[28].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[28].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[29].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[28].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[28].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[28].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[29].name}',
			value : '${sessionScope.SCHOOLLIST[29].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[29].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[29].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[29].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[29].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[29].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[29].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[30].name}',
			value : '${sessionScope.SCHOOLLIST[30].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[30].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[30].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[30].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[30].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[30].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[30].stusum3}',
		}, {
			name : '${sessionScope.SCHOOLLIST[31].name}',
			value : '${sessionScope.SCHOOLLIST[31].value}',
			ZXMC1 : '${sessionScope.SCHOOLLIST[31].ZXMC1}',
			ZXMC2 : '${sessionScope.SCHOOLLIST[31].ZXMC2}',
			ZXMC3 : '${sessionScope.SCHOOLLIST[31].ZXMC3}',
			stusum1 : '${sessionScope.SCHOOLLIST[31].stusum1}',
			stusum2 : '${sessionScope.SCHOOLLIST[31].stusum2}',
			stusum3 : '${sessionScope.SCHOOLLIST[31].stusum3}',
		}, {
			name : '',
			value : 100,
		}/* 最后一个数据不晓得什么用但是这个value可以控制界面上的数轴值 而且不会变黑*/
		]; 	
 		/* var data = ${sessionScope.SCHOOLLIST1}; */ 
		var mapArray = Highcharts.maps['cn/china'];
		/* var data = '${sessionScope.PROVINCELIST}'; */

		
		$('#container')
				.highcharts(
						'Map',
						{
							colors : [ '#67B86D', '#19E1CF', '#f45b5b',
									'#7798BF', '#aaeeee', '#ff0066', '#eeaaee',
									'#55BF3B', '#DF5353', '#7798BF', '#aaeeee' ],
							credits : {
								enabled : true,
								text : '',
								style : {
									color : 'rgba(255, 255, 255, 0.6)'
								},
								position : {
									y : -40
								}
							},
							chart : {
								spacing : 10,
								height: 500,
								//backgroundColor: '#F49952'
							},
							mapNavigation : {
								enabled: true,
								enableButtons: true,
								buttonOptions : {
									verticalAlign : 'bottom',
									theme : {
										fill : 'rgba(255, 255, 255, 0.2)',
										stroke : 'rgba(255, 255, 255, 0.7)',
										style : {
											color : 'black'
										},
										states : {
											hover : {
												fill : 'rgba(255, 255, 255, 0.4)',
												stroke : 'rgba(255, 255, 255, 0.7)'
											},
											select : {
												fill : 'rgba(255, 255, 255, 0.4)',
												stroke : 'rgba(255, 255, 255, 0.7)'
											}
										}
									}
								},
					
								enabled : true,
								/* 鼠标滚轮缩放 */
								enableMouseWheelZoom : true,
							},
							title : {
								text : '生源地就读中学 & 各省分布情况',
								floating : true
							},
							subtitle : {
								text : '模拟数据仅供参考',
								floating : true,
								y : 30,
								style : {
									color : 'rgba(255, 255, 255, 0.9)'
								}
							},
							xAxis : {
								minRange : 200
							},
							  colorAxis: {
								min: 0,
								max: 400,
								stops: [
									[0, '#8DEEEE'],
									[0.5, '#5CACEE'],
									[1, '#191970']
								]
							},  
							legend : {
								enabled : true,
								layout : 'vertical',
								align : 'left',
								verticalAlign : 'bottom',
								y: -60,
								//left: -20,
								floating : true
							},
							tooltip : {
								useHTML : true,
								headerFormat : '<span style="font-size: 10 px;font - weight: bold">{point.key}</span><table>',
								pointFormatter : function() {
									var str = '<tr><td>学校数量：</td><td ><b>';
									if (this.shapeType === 'circle') {
										str += this.z + '</b> 万</td></tr>';
									} else {
										str += (this.value || '-')
												+ '</b> 所</td></tr>'
												+ '<tr><td>TOP1：</td><td><b>'
												+ '<tr><td>中学名称：</td><td><b>'
												+ (this.ZXMC1 || '-')
												+ '<tr><td>TOP2：</td><td><b>'
												+ '<tr><td>中学名称：</td><td><b>'
												+ (this.ZXMC2 || '-')
												+ '<tr><td>TOP3：</td><td><b>'
												+ '<tr><td>中学名称：</td><td><b>'
												+ (this.ZXMC3 || '-')
												+ '</b></td></tr>';
									}
									return str;
								},
								footerFormat : '</table>'
							},
							plotOptions : {
								series : {
									animation : {
									// duration: animDuration
								
									}
								}
							},
							series : [ {
								data : data,
								mapData : mapArray,
								joinBy : [ 'name', 'name' ],
								name : '省份',
								/* 点击地图地区跳转事件 */
								point: {
									events: {
										click: function() {
											/* alert(this.name) */
											// Create the chart
											/*  $('#container').highcharts({
												chart: {
													type: 'column'
												},
												title: {
													text: this.name+'录取人数Top3中学'
												},
												xAxis: {
													type: 'category'
												},
												legend: {
													enabled: false
												},
												plotOptions: {
													series: {
														borderWidth: 0,
														dataLabels: {
															enabled: true
														}
													}
												},
												series: [{
													name: '人数',
													colorByPoint: true,
													data: [{
														name: this.ZXMC1,
														y: Number(this.stusum1),
														drilldown: 'ZXMC1'
													}, {
														name: this.ZXMC2,
														y: Number(this.stusum2),
														drilldown: 'ZXMC2'
													}, {
														name: this.ZXMC3,
														y: Number(this.stusum3),
														drilldown: 'ZXMC3'
													}]
												}]
											});  */
											var family = this.name;
											window.location.href=("stuusers?ActionType=stuusers_MiddleSchool_Init&family="+family);
										}
									}
								},
								showInLegend : false,
								states : {
									hover : {
										/* 鼠标移动到的颜色 */
										color : '#BADA55'
									}
								},
								borderWidth : 1,
								dataLabels : {
									enabled : true,
									format : '{point.name}',
									style : {
										fontSize : '10px',
										fontWeight : 'normal',
										color : '#333',
										textShadow : 'none'
									}
								}
							} ],
							navigation : {
								buttonOptions : {
									symbolStroke : 'rgba(255, 255, 255, 0.8)',
									theme : {
										fill : 'rgba(255, 255, 255, 0.2)',
										states : {
											hover : {
												fill : 'rgba(255, 255, 255, 0.4)',
												stroke : 'transparent'
											},
											select : {
												fill : 'rgba(255, 255, 255, 0.4)',
												stroke : 'transparent'
											}
										}
									}
								}
							}
						}, function(map) {
						});
	</script>
</body>
</html>