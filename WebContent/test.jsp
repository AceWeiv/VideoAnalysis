<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="My97DatePicker/My97DatePicker/WdatePicker.js"></script>
<title>Insert title here</title>
</head>
<body>

<h1>Hello world!</h1>

<input type="text" style="margin-top:100px;" id="stime" class="Wdate" onclick="dateClick()"/> 

<script>
function dateClick() { 
	WdatePicker({ 
		dateFmt : 'yyyy-MM-dd HH:mm',
		lang: 'zh-cn',
		highLineWeekDay:true, 
		readOnly:true, 
		}); 
	}
		
</script>


</body>
</html>