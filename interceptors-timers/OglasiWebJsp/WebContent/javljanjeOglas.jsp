<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javljanje na oglas!</title>
</head>
<body>
	
	<% String hidden = request.getParameter("id"); %>
		
	<form action="/OglasiWebJsp/JavljanjeServlet" method="post">
		Unesi tekst prijave: <input type="text" name="text" id="text">
		<input type="hidden" id="id" name="id" value="<%=hidden %>" />
		<input type="submit" value="Potvrdi" name="button" /> <br>
	</form>
	
</body>
</html>