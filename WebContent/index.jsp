<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/angular.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	<form action="UserLoginServlet" method="post" > -->
<!-- 		<input type="text" name="name"></input> -->
<!-- 		<input type="text" name="passwd"></input> -->
<!-- 		<input type="text" name="ip" value="192.168.154.2"></input>		 -->
<!-- 		<input type="submit" value="submit"/> -->
<!-- 	</form> -->
<!-- 	<hr style="width:100%"/> -->
<!-- 	<form action="Command2Servlet" method="post" > -->
<!-- 			<input type="text" name="name"></input> -->
<!-- 		<input type="text" name="passwd"></input> -->
<!-- 		<input type="text" name="ip" value="192.168.154.2"></input>		 -->
<!-- 		<br /> -->
<!-- 		<input style="width:80%" type="text" name="command"></input> -->
<!-- 		<br/> -->
<!-- 		<input type="submit" value="submit"/> -->
<!-- 	</form> -->
	<form action="Test" method="post">
			<input name="type" type="radio"  ${type eq 1 or type eq null or type eq '' ? 'checked':''}  value="1">启用
			<input name="type" type="radio" ${type eq 2 ? 'checked':''} value="2">停用
			<input name="type" type="radio" ${type eq 3 ? 'checked':''} value="3">command执行
		<br />
		用&nbsp&nbsp&nbsp&nbsp户:<input type="text" name="name" value="${name}"></input>
		<input type="text" name="passwd" value="${passwd}"></input>
		<input type="text" name="ip" value="192.168.154.2"></input>		
		<br />
		作业内容:<input style="width:80%" type="text" name="command" value="${command}"></input>
		<br/>

		作业路径:<input style="width:80%" type="text" name="filePath" value="/common/crontab/cron/"></input>
		<br />
		作业文件:<input style="width:80%" type="text" name="fileName" value="${fileName}"></input>
		<br />
		<input type="submit" value="submit"/>
	</form>
	<hr style="width:100%"/>
	<form action="CommandServlet" method="post">
		命令行:<input style="width:80%" type="text" name="command" value="${command}"></input>
		<input type="submit" value="submit">
	</form>
	<hr style="width:100%"/>
	${message}
	
</body>
</html>