<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,com.ace.domain.Config"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>视频分析</title>
<!-- Bootstrap core CSS -->
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap-3.3.7-dist/dashboard.css" rel="stylesheet">
<script src="js/echarts.js"></script>
<script src="js/jquery.min.js"></script>
<script src="My97DatePicker/My97DatePicker/WdatePicker.js"></script>
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">视频分析</a>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="ConfigData">配置数据源</a></li>
					<li class="active"><a href="#">查询数据<span class="sr-only">(current)</span></a></li>
					<li><a href="RealTime">实时数据</a></li>
					<li><a href="KLineSearch2">查询数据(带拖动)</a></li>
					<li><a href="GetScreenShot">查询截屏</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">查询数据</h1>

				<div id="main" style="width: 800px; height: 480px;"></div>

				<% List<Config> cons = (List<Config>)request.getAttribute("cons"); %>
				
				<select id="cam_id">
				<% for(Config con:cons){ %>
				<option value=<%=con.getId() %>><%=con.getName() %></option>
				<% } %>
				</select>
				
				<select id="select_time">
					<option value="1">每1分钟</option>
					<option value="2">每2分钟</option>
					<option value="3">每5分钟</option>
				</select> 
				开始时间:<input type="text" style="margin-top:100px;" id="start_time" class="Wdate" onclick="dateClick()"/> 
				结束时间:<input type="text" style="margin-top:100px;" id="end_time" class="Wdate" onclick="dateClick()"/> 

				<button id="getData" type="button">查询数据</button>

				<script type="text/javascript">
					var myChart = echarts.init(document.getElementById('main'));
					var selected_time = 0;

					var upColor = '#ec0000';
					var upBorderColor = '#8A0000';
					var downColor = '#00da3c';
					var downBorderColor = '#008F28';
					
					var result_all = new Object();

					$(function() {
						$("#getData").click(
										function() {
											var start_time = document.getElementById("start_time").value;
											var end_time = document.getElementById("end_time").value;
											if (start_time < end_time) {
												var cam_ids = document.getElementById("cam_id");
												for (var i = 0; i < cam_ids.length; i++) {
													if (cam_ids[i].selected == true) {
														cam_id = cam_ids[i].value;
													}
												}
												
												var select_time = document.getElementById("select_time");
												for (var i = 0; i < select_time.length; i++) {
													if (select_time[i].selected == true) {
														selected_time = select_time[i].value;
													}
												}

												$.ajax({
															type : "post",
															async : true,
															url : "KLineSearch",
															data : {
																"cam_id" : cam_id,	
																"method" : selected_time,
																"start_time" : start_time,
																"end_time" : end_time
															},
															dataType : "json",
															success : function(result) {
																if (result) {
																	result_all = result;
																	//console.log(result_all.length);
																	var times = [];
																	var car_datas = [];
																	var man_datas = [];
																	for (var i = 0; i < result.length; i++) {
																		times.push(result[i].time);
																		var car_data = new Array(
																				result[i].open_car_num,
																				result[i].end_car_num,
																				result[i].lowest_car_num,
																				result[i].highest_car_num);
																		car_datas.push(car_data);
																		var man_data = new Array(
																				result[i].open_man_num,
																				result[i].end_man_num,
																				result[i].lowest_man_num,
																				result[i].highest_man_num);
																		man_datas.push(man_data);
																	}
																	myChart.hideLoading();
																	myChart.setOption({
																				title : {
																					text : '数据查询',
																					left : 0
																				},
																				tooltip : {
																					trigger : 'axis',
																					axisPointer : {
																						type : 'cross'
																					}
																				},
																				legend : {
																					data : [
																							'车辆',
																							"人数" ]
																				},
																				grid : {
																					left : '10%',
																					right : '10%',
																					bottom : '15%'
																				},
																				xAxis : {
																					type : 'category',
																					data : times,
																					scale : true,
																					boundaryGap : false,
																					axisLine : {
																						onZero : false
																					},
																					splitLine : {
																						show : false
																					},
																					splitNumber : 20,
																					min : 'dataMin',
																					max : 'dataMax'
																				},
																				yAxis : {
																					scale : true,
																					splitArea : {
																						show : true
																					}
																				},
																				dataZoom : [
																						{
																							type : 'inside',
																							start : 50,
																							end : 100
																						},
																						{
																							show : true,
																							type : 'slider',
																							y : '90%',
																							start : 50,
																							end : 100
																						} ],
																				series : [
																						{
																							name : '车辆',
																							type : 'candlestick',
																							data : car_datas,
																							itemStyle : {
																								normal : {
																									color : upColor,
																									color0 : downColor,
																									borderColor : upBorderColor,
																									borderColor0 : downBorderColor
																								}
																							},
																						},
																						{
																							name : '人数',
																							type : 'candlestick',
																							data : man_datas,
																							itemStyle : {
																								normal : {
																									color : upColor,
																									color0 : downColor,
																									borderColor : upBorderColor,
																									borderColor0 : downBorderColor
																								}
																							},
																						} ]
																			});
																}
															},
															error : function(errorMsg) {
																alert("图片请求数据失败!");
																myChart.hideLoading();
															}
														});
											} else {
												alert("开始时间必须小于结束时间!");
											}
										});
					});
					
					function dateClick() { 
						WdatePicker({ 
							dateFmt : 'yyyy-MM-dd HH:mm',
							lang: 'zh-cn',
							highLineWeekDay:true, 
							readOnly:true, 
							}); 
						}
					
					myChart.on("click", function(param){
						//console.log(param.dataIndex);
						window.open("/pictrue/"+result_all[param.dataIndex].screenshot);
					});
				</script>

			</div>
		</div>
	</div>
</body>
</html>
