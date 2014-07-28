<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<%
				if (null != request.getAttribute("invalidLogin")) {
					out.println(request.getAttribute("invalidLogin"));

				}
			%>
		</p>
		<form action="LoginValidator" method="post">
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