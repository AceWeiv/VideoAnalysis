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
				<h1 class="page-header">新增摄像头</h1>
				
				<form action="AddCam" method="post">
					摄像头描述:<input type="text" name="name">
					摄像头地址:<input type="text" name="adress">
					<br>
					<br>
					是否可用:<select name="isActive">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
					阈值(车):
					<select name="threshold_car">
						<option value="50">50</option>
						<option value="75">75</option>
						<option value="90">90</option>
						<option value="95">95</option>
					</select>
					阈值(人):
					<select name="threshold_man">
						<option value="50">50</option>
						<option value="75">75</option>
						<option value="90">90</option>
						<option value="95">95</option>
					</select>
					<input type="submit" value="提交">
				</form>

			</div>
		</div>
	</div>
</body>
</html>