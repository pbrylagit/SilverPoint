<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title><spring:message code="users.title" /></title>
</head>
<body>
	<section class="container">
		<div class="row">
			<table class="table table-hover">
				<tr>
					<th><spring:message code="users.username" /></th>
					<th><spring:message code="users.firstName" /></th>
					<th><spring:message code="users.lastName" /></th>
					<th><spring:message code="users.permission" /></th>
					<th><spring:message code="users.email" /></th>
					<th><spring:message code="users.edit" /></th>
					<th><spring:message code="users.delete" /></th>
				</tr>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.username}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>
							<c:choose>
								<c:when test="${user.permission == 'ROLE_CLIENT'}"> <spring:message code="permission.client" /></c:when>
								<c:when test="${user.permission == 'ROLE_USER'}"> <spring:message code="permission.user" /></c:when>
								<c:otherwise> <spring:message code="permission.administrator" /> </c:otherwise>
							</c:choose>
						</td>
						<td>${user.email}</td>
						<td width="5%" align="center">
							<a href=" <spring:url value="/users/edit?id=${user.userId}" />"
									class="btn btn-xs btn-primary"> <span
									class="glyphicon-pencil glyphicon" /></span></a>
						</td>
						<td width="5%" align="center">
						<a href=" <spring:url value="/users/delete?id=${user.userId}" />"
									onclick="return confirm('Chcesz skasować tego użytkownika?');"
									class="btn btn-xs btn-danger"> <span
									class="glyphicon glyphicon-remove" /></span></a>
						
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
</body>
</html>