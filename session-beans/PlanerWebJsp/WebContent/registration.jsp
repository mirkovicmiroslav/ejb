<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration form</title>
</head>
<body>
	
	<form action="/PlanerWebJsp/AccountServlet" method="post">
		First name: <input type="text" name="firstName" id="firstName"> <br> 
		Last name <input type="text" name="lastName" id="lastName"> <br>
		Username: <input type="text" name="username" id="username"> <br> 
		Password: <input type="password" name="password" id="password"> <br>
 
		<input type="submit" value="Submit" name="button" /> <br>
	</form>
	
</body>
</html>