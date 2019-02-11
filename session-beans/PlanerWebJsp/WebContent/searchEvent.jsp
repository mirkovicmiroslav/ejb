<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search for an event</title>
</head>
<body>

	Search for an event?
	<br>
	<form action="/PlanerWebJsp/SearchServlet" method="post">

		From date <input type="date" name="fromDate" id="fromDate"> <br>
		<input type="submit" value="Submit" name="button" /> <br>
	</form>
	<br>
	<br>

	<c:if test="${!empty events}">
		<table border="1">
			<th>Description</th>
			<th>Date from</th>
			<th>Date to</th>
			<c:forEach var="e" items="${events }">
				<tr>
					<td>${e.description }</td>
					<td>${e.fromDate }</td>
					<td>${e.toDate }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>