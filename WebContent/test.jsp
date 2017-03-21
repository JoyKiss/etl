<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="FileCopyServlet" method="post">

		文件FROM:<input style="width:80%" type="text" name="fileFrom" value="/common/crontab/cron/"></input>
		<br />
		文件TO:<input style="width:80%" type="text" name="fileTo" value="/common/crontab/cron/"></input>
		<br />
		<input type="submit" value="submit"/>
	</form>
	<hr style="width:100%"/>
	${message}
	
</body>
</html>