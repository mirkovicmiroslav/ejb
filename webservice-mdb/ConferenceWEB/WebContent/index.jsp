<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add conference</title>
</head>
<body>
	
	<form action="/ConferenceWEB/ConferenceServlet" method="post">
		Naziv:<input type="text" name="title"><br>
		Drzava(na Engleskom):<input type="text" name="country"><br>
		Grad:<input type="text" name="city"><br>
		Oblast:<input type="text" name="field"><br>
		Datum od:<input type="date" name="dateFrom"><br>
		Datum do:<input type="date" name="dateTo"><br>
		<input type="submit" value="Add">
	</form>
	
</body>
</html>