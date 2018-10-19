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
					<li class="active"><a href="#">配置数据源<span class="sr-only">(current)</span></a></li>
					<li><a href="KLineSearch">查询数据</a></li>
					<li><a href="RealTime">实时数据</a></li>
					<li><a href="KLineSearch2">查询数据(带拖动)</a></li>
					<li><a href="GetScreenShot">查询截屏</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">配置数据源</h1>

				<p id="if_createdata_is_on"></p>

				<button id="send_mes" type="button"></button>
				
				<button id="start_analysis" type="button">开始视频分析</button>
				
				<% List<Config> cons = (List<Config>)request.getAttribute("cons"); %>

				<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>摄像头描述</th>
								<th>摄像头地址</th>
								<th>是否可用</th>
								<th>阈值(车)</th>
								<th>阈值(人)</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<% for(Config con: cons){ %>
							<tr>
								<td><%=con.getId() %></td>
								<td><%=con.getName()%></td>
								<td><%=con.getAdress() %></td>
								<td><% if(con.isActive()){
									out.println("是");
								}else{
									out.println("否");
								} %>
								</td>
								<td><%=con.getThreshold_car()%></td>
								<td><%=con.getThreshold_man()%></td>
								<td><a href="EditCam?id=<%=con.getId() %>">修改</a>
									<a href="DeleteCam?id=<%=con.getId() %>">删除</a></td>
							</tr>
							<% }%>
						</tbody>
					</table>
					
					<a href="AddCam">添加新的摄像头</a>

						<script type="text/javascript">
					window.onload=function(){
						var i = '<%=request.getAttribute("ifDatasource")%>';
							if (i == "1") {
								document.getElementById("if_createdata_is_on").innerHTML = "当前正在插入数据";
								document.getElementById("send_mes").innerHTML = "停止生成数据";
							} else {
								document.getElementById("if_createdata_is_on").innerHTML = "当前没有插入数据";
								document.getElementById("send_mes").innerHTML = "开始生成数据";
							}

							$(function() {
								$("#send_mes").click(
												function() {
													$("#send_mes").attr("disabled",true);
													$.ajax({
																type : "post",
																async : true,
																url : "ConfigData1",
																data : {
																	"do" : i
																},
																dataType : "json",
																success : function(
																		result) {
																	if (i == "1") {
																		document.getElementById("if_createdata_is_on").innerHTML = "当前没有插入数据";
																		document.getElementById("send_mes").innerHTML = "开始生成数据";
																		i = "0";
																	} else {
																		document.getElementById("if_createdata_is_on").innerHTML = "当前正在插入数据";
																		document.getElementById("send_mes").innerHTML = "停止生成数据";
																		i = "1";
																	}
																	$("#send_mes").removeAttr("disabled");
																}
															});
												});
							});
							
							$(function(){
								$("#start_analysis").click(
										function(){
											$("#start_analysis").attr("disabled",true);
											$.ajax({
												type : "post",
												async : true,
												url : "ConfigData2",
												data : {"do" : "do"},
												dataType : "json",
												success : function(result){
													$("#start_analysis").removeAttr("disabled");
												},error : function(errorMsg){
													alert("error!!!");
													$("#start_analysis").removeAttr("disabled");
												}
											});
											}
								);
							});
						}
					</script>
			</div>
		</div>
	</div>
</body>
</html>