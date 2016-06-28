<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('dropdown-toggle').dropdown()
	});
</script>
</head>
<body>
	<div>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="<spring:url value="/" />" class="navbar-brand">Silver Point</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="<spring:url value="/service" />"><spring:message code="baseTemplate.notifications" /></a></li>
						<li><a href="<spring:url value="/service/add" />"><spring:message code="baseTemplate.add" /></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><spring:message code="baseTemplate.users" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<spring:url value="/users" />"><spring:message code="baseTemplate.list" /></a></li>
								<li><a href="<spring:url value="/users/b" />"><spring:message code="baseTemplate.blocks" /></a></li>
								<li><a href="<spring:url value="/users/add" />"><spring:message code="baseTemplate.add" /></a></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><spring:message code="baseTemplate.language" /><span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="?language=pl"><spring:message code="baseTemplate.language.polish" /></a></li>
								<li><a href="?language=en"><spring:message code="baseTemplate.language.english" /></a></li>
							</ul></li>
						<li><a href="<c:url value="/logout" />"><spring:message code="baseTemplate.logout" /></a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</nav>
	</div>
	<div class="container">
		<div class="row">
			<tiles:insertAttribute name="content" />
		</div>

		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>

</body>
</html>