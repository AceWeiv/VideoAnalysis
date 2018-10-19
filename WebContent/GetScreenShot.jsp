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
					<li><a href="KLineSearch">查询数据</a></li>
					<li><a href="RealTime">实时数据</a></li>
					<li><a href="KLineSearch2">查询数据(带拖动)</a></li>
					<li class="active"><a href="#">查询截屏<span class="sr-only">(current)</span></a></li>
				</ul>
			</div>
			<div id="d" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">查询图片</h1>

				<% List<Config> cons = (List<Config>)request.getAttribute("cons"); %>
				
				<select id="cam_id">
				<% for(Config con:cons){ %>
				<option value=<%=con.getId() %>><%=con.getName() %></option>
				<% } %>
				</select>
				
				开始时间:<input type="text" style="margin-top:100px;" id="start_time" class="Wdate" onclick="dateClick()"/> 
				
				<select id="select_time">
					<option value="1">附近1分钟</option>
					<option value="2">附近2分钟</option>
					<option value="3">附近5分钟</option>
				</select>

				<button id="getData" type="button">查询</button>

				<script type="text/javascript">
					var selected_time = 0;
					
					var result_all = new Object();

					$(function() {
						$("#getData").click(
										function() {
												var start_time = document.getElementById("start_time").value;
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
															async : false,
															url : "GetScreenShot",
															data : {
																"cam_id" : cam_id,	
																"method" : selected_time,
																"start_time" : start_time,
															},
															dataType : "json",
															success : function(result) {
																if (result) {
																	var d = document.getElementById("d");
																	for (var i = 0; i < result.length; i++) {
																		var a = document.createElement('a');
																		var br = document.createElement('br');
																		a.href = "/pictrue/"+result[i].screenshot;
																		a.innerText = result[i].time+"   车辆数:"+result[i].car_num+"   人数:"+result[i].man_num;
																		a.target = "_blank";
																		d.appendChild(br);
																		d.appendChild(a);
																	}
																}
															},
															error : function(errorMsg) {
																alert("图片不存在!!!");
															}
														});
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
				</script>

			</div>
		</div>
	</div>
</body>
</html>
