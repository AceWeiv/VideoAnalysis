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
					<li><a href="KLineSearch">查询数据</a></li>
					<li class="active"><a href="#">实时数据<span class="sr-only">(current)</span></a></li>
					<li><a href="KLineSearch2">查询数据(带拖动)</a></li>
					<li><a href="GetScreenShot">查询截屏</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">实时数据</h1>
				
				<% List<Config> cons = (List<Config>)request.getAttribute("cons"); %>
				
				<select id="cam_id">
				<% for(Config con:cons){ %>
				<option value=<%=con.getId() %>><%=con.getName() %></option>
				<% } %>
				</select>
				
				时间间隔(s):<input id="timeInterval" value="3">
				
				<button id="start" value=1>开始</button>

				<div id="main" style="width: 800px; height: 480px;"></div>
				
				<img id="ScreenShot"/>

				<script type="text/javascript">
					var myChart = echarts.init(document.getElementById('main'));
					
					var start = document.getElementById("start");
					
					var sendRq = new Object();
					var result_all = new Object();
					
					var cam_id = 2;
					
					start.onclick = function(){
						var timeInterval = document.getElementById("timeInterval").value;
						var cam_ids = document.getElementById("cam_id");
						for (var i = 0; i < cam_ids.length; i++) {
							if (cam_ids[i].selected == true) {
								cam_id = cam_ids[i].value;
							}
						}
						
						if(start.value==1){
							start.innerHTML="暂停";
							start.value=0;
							sendRq = setInterval("getData()",timeInterval*1000);
						}else{
							start.innerHTML="开始";
							start.value=1;
							clearInterval(sendRq);
						}
					}

						function getData() {
											$.ajax({
														type : "post",
														async : true,
														url : "RealTime",
														data : {"cam_id" : cam_id},
														dataType : "json",
														success : function(
																result) {
															if (result) {
																result_all = result;
																var times = [];
																var car_nums = [];
																var man_nums = [];
																for (var i = 0; i < result.length; i++) {
																	times.unshift(result[i].time);
																	car_nums.unshift(result[i].car_num);
																	man_nums.unshift(result[i].man_num);
																}
																myChart.hideLoading();
																myChart.setOption({
																			title : {
																				text : '最新数据'
																			},
																			tooltip : {},
																			legend : {
																				data : [
																						'车辆',
																						'人数' ]
																			},
																			xAxis : {
																				data : times
																			},
																			yAxis : {},
																			series : [
																					{
																						name : '车辆',
																						type : 'line',
																						data : car_nums,
																						symbolSize : 15
																					},
																					{
																						name : '人数',
																						type : 'line',
																						data : man_nums,
																						symbolSize : 15
																					} ]
																		});
															}
														},
														error : function(
																errorMsg) {
															alert("图片请求数据失败!");
															myChart.hideLoading();
														}
													});
										}
						
						myChart.on("click", function(param){
							//console.log(param.dataIndex);
							//console.log(result_all[param.dataIndex].screenshot);
							//window.open(result_all[result_all.length-1-param.dataIndex].screenshot);
							//document.getElementById("ScreenShot").src="/pictrue/"+result_all[result_all.length-1-param.dataIndex].screenshot;
							window.open("/pictrue/"+result_all[result_all.length-1-param.dataIndex].screenshot, height=100, width=400, top=200, left=200, toolbar="no"); 
						});
						
				</script>

			</div>
		</div>
	</div>
</body>
</html>