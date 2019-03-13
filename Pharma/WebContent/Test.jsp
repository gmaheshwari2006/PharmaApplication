<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Test Page
	<a href="<c:url value="/login/loginPage"/>">LoginPage</a>
	
	<a href="<c:url value="/login/India/loginPage"/>">CountryLoginPage</a>
	
	<a href="<c:url value="/login/firstReq"/>">FirstRequest</a>
</body>
</html>