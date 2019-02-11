<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dodavanje oglasa</title>
</head>
<body>

	<form action="/OglasiWebJsp/OglasiServlet" method="post">
		Tekst oglasa: <input type="text" name="text" id="text"> 
		<input type="submit" value="Submit" name="button"/>
	</form>

</body>
</html>