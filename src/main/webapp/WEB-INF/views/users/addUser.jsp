<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script type="text/javascript">
	function back(){
		window.history.back();
	}
</script>
<title><spring:message code="addUser.title" /></title>
</head>
<body>
	<section class="container">
		<form:form modelAttribute="newUser" class="form-horizontal">
			<fieldset>
				<legend>
					<spring:message code="addUser.legend" />
				</legend>
				<form:errors path="*" cssClass="alert alert-danger" element="div" />
				<div class="row">
					<div class="col-xs-3">
						<label for="username"><spring:message code="addUser.from.userName.label" /> </label>
					</div>
					<div class="col-xs-4">
						<form:input id="username" path="username" type="text"
							class="form-control" />
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-3">
						<label for="firstName"><spring:message code="addUser.from.firstName.label" /> </label>
					</div>
					<div class="col-xs-4">
						<form:input id="firstName" path="firstName" type="text"
							class="form-control" />
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-3">
						<label for="lastName"><spring:message code="addUser.from.lastName.label" /> </label>
					</div>
					<div class="col-xs-4">
						<form:input id="lastName" path="lastName" type="text"
							class="form-control" />
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-3">
						<label for="password"><spring:message code="addUser.from.password.label" /></label>
					</div>
					<div class="col-xs-4">
						<form:input id="password" path="password" type="password"
							class="form-control" />
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-3">
						<label for="permission"><spring:message
							code="addUser.from.permission.label" /></label>
					</div>
					<div class="col-xs-4">
						<form:radiobutton path="permission" value="ROLE_ADMIN" />
						<spring:message code="addUser.radiobutton.administrator" />
						<form:radiobutton checked="checked" path="permission" value="ROLE_USER" />
						<spring:message code="addUser.radiobutton.user" />
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-3">
						<label for="email"><spring:message
							code="addUser.from.email.label" /></label>
					</div>
					<div class="col-xs-4">
						<form:input id="email" path="email" type="text"
							class="form-control" />
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-4"></div>
					<div class="col-xs-4">
						<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="addUser.sumbmit.button" />" />
						<input type="button" onclick='back()' id="btnCancel" class="btn btn-primary" value="<spring:message code="addUser.cancel.button" />"/>
					</div>
				</div><br>
			</fieldset>
		</form:form>
	</section>
</body>
</html>