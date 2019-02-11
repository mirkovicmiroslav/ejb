<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pretraga oglasa</title>
</head>
<body>

	<form action="/OglasiWebJsp/SearchServlet" method="post">
		Unesi tekst oglasa: <input type="text" name="text" id="text">
		<input type="submit" value="Pretrazi" name="button" /> <br>
	</form>

	<c:if test="${!empty oglasi}">
		<table border="1">
			<th>Tekst oglasa</th>
			<th>Broj pregleda</th>
			<th>Detalji oglasa...</th>
			<c:forEach var="o" items="${oglasi }">
				<tr>
					<td>${o.text }</td>
					<td>${o.brojPregleda }</td>
					<td><a href="/OglasiWebJsp/SearchServlet?id=${o.idOglas}">Detaljno!</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>