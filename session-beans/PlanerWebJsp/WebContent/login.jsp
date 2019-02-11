<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	
	<form action="/PlanerWebJsp/LoginServlet" method="post">
		Username: <input type="text" name="username" id="username"> <br> 
		Password: <input type="password" name="password" id="password"> <br>
		<input type="submit" value="Login" name="button" /> <br>
	</form><br><br>
	
</body>
</html>