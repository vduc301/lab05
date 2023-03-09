<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<h1>Error: Page Not Found</h1>
	<p>
		The requested URL
		<%=request.getRequestURI()%>
		was not found on this server.
	</p>
	<p>Click <a href="index">here</a> for return to homepage</p>
</body>
</html>