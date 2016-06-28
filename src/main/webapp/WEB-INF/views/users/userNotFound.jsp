<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title><spring:message code="userNotFound.title" /></title>
</head>
<body>
	<section>
		<div class="container">
			<h1 class="alert alert-danger"><spring:message code="userNotFound.information" />: ${invalidUserId}.</h1>
		</div>
	</section>
	<section>
		<div class="container">
			<p>
				<a href="<spring:url value="/users" />" class="btn btn-primary">
					<span class="glyphicon-hand-left glyphicon"></span> <spring:message code="userNotFound.backButton" />
				</a>
			</p>
		</div>
	</section>
</body>
</html>