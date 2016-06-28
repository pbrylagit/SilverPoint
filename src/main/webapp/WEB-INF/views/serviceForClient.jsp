<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title><spring:message code="service.title" /></title>
</head>
<body>KLIENCI
	<section class="container">
		<div class="row">
			<table class="table table-hover">
			
				<tr>
					<th>Id</th>
					<th><spring:message code="service.product" /></th>
					<th><spring:message code="service.description" /></th>
					<th><spring:message code="service.price" /></th>
					<th><spring:message code="service.status" /></th>
					<th><spring:message code="service.edit" /></th>
					<th><spring:message code="service.delete" /></th>
				</tr>
				<c:forEach items="${serviceList}" var="service">
					<tr>
						<td width="5%">${service.serviceId}</td>
						<td>${service.product}</td>
						<td>${service.description}</td>
						<td>${service.price}PLN</td>
						<td><c:choose>
								<c:when test="${service.status == 'notDone'}"><spring:message code="service.notDone" /></c:when>
								<c:when test="${service.status == 'done'}"><spring:message code="service.done" /></c:when>
							</c:choose></td>
						<td width="5%" align="center"><a
							href=" <spring:url value="/service/edit?id=${service.serviceId}" />"
							class="btn btn-xs btn-primary"> <span
								class="glyphicon-pencil glyphicon" /></span></a></td>
						<td width="5%" align="center"><a
							href="<spring:url value="/service/delete?id=${service.serviceId}" />"
							onclick="return confirm('<spring:message code="service.message" />');"
							class="btn btn-xs btn-danger"> <span
								class="glyphicon glyphicon-remove" /></span></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
</body>
</html>