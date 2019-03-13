<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login/loginSubmit" method="get">
	<table>
		<tr>
			<td>UserName</td>
			<td><input type="text" name="userName"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="userPassword"></td>
		</tr>
		<tr>
			<td><input type="submit" name="Submit"></td>
			<td><a href="<c:url value="/login/registerPage"/>">Register</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>