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
			<c:forEach items="${userList}" var="user">
				<div class="col-sm-6 col-md-4" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h3>${user.firstName}
								${user.lastName}
								<c:choose>
									<c:when test="${user.permission == 'ROLE_CLIENT'}"> (<spring:message code="permission.client" />)</c:when>
									<c:when test="${user.permission == 'ROLE_USER'}"> (<spring:message code="permission.user" />)</c:when>
									<c:otherwise> (<spring:message code="permission.administrator" />) </c:otherwise>
								</c:choose>
							</h3>
							<p>${user.password}</p>
							<p>${user.email}</p>
							<p>
								<a href=" <spring:url value="/users/edit?id=${user.userId}" />"
									class="btn btn-primary"> <span
									class="glyphicon-pencil glyphicon" /></span> <spring:message
										code="addUser.edit.button" />
								</a> <a
									href=" <spring:url value="/users/delete?id=${user.userId}" />"
									class="btn btn-primary"> <span
									class="glyphicon-trash glyphicon" /></span> <spring:message
										code="addUser.delete.button" />
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>