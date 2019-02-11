<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	Add an event?
	<br>
	<form action="/PlanerWebJsp/EventServlet" method="post">

		Description: <input type="text" name="description" id="description">
		<br> From date <input type="date" name="fromDate" id="fromDate">
		<br> To date: <input type="date" name="toDate" id="toDate">
		<br> <select name="types">
			<c:forEach var="t" items="${types}">
				<option value="${t.id}">${t.name}</option>
			</c:forEach>
		</select> <input type="submit" value="Submit" name="button" /> <br>
	</form>
	
</body>
</html>