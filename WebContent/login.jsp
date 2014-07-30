<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Image repository</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<div id="loginForm">
		<p id="error">
			<c:if test="${not empty requestScope.invalidLogin }">
				<c:out value="${requestScope.invalidLogin }"></c:out>
			</c:if>
		</p>
		<form action="LoginValidator" method="post"
			onsubmit="return validateLoginForm(this)">
			<table>
				<tr>
					<td id="top">Login</td>
				</tr>
				<tr>
					<td><label for="username">Username:</label> <span
						id="necessary">*&nbsp</span> <input type="text" name="username"
						required /></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label> <span
						id="necessary">*&nbsp</span> <input type="password"
						name="password" required /></td>
				</tr>
				<tr id="bottom">
					<td><input type="submit" value="Login>>" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>