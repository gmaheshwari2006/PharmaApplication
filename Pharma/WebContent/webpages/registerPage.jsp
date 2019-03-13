<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>

</head>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: normal;
}
</style>
<body>
	<form:form
		action="${pageContext.request.contextPath}/login/registerSubmit"
		method="get" modelAttribute="user">
		<form:errors path="*" cssClass="error" />
		<table>
			${test}
			<tr>
				<td>UserName</td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input path="userPassword" /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><form:input path="fName" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lName" /></td>
			</tr>
			<tr>
				<td>Email Id</td>
				<td><form:input path="emailid" /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><form:select path="city">
						<c:forEach items="${cities}" var="city">
							<option value="${city.cityName}">${city.cityName}</option>
						</c:forEach>
				</form:select></td>
			</tr>
			<tr>
				<td><input type="submit" name="Submit"></td>

			</tr>
		</table>
	</form:form>
</body>
</html>