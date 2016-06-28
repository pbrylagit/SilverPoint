<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
<title><spring:message code="editService.title" /></title>
</head>
<body>
	<section class="container">
		<form:form modelAttribute="editService">
			<fieldset>
				<legend>
					<spring:message code="addService.legend" />
				</legend>
				<form:hidden path="serviceId" value="${serviceId}"/>
				<form:hidden path="startDate" value="${startDate}"/>
				<form:hidden path="adder" value="${adder}"/>
				<div class="row">
					<div class="col-xs-4">
						<label for="owner"><spring:message code="addService.owner" /></label>
					</div>
					<div class="col-xs-4">
						<label for="contact"><spring:message code="addService.phone" /></label>
					</div>
					<div class="col-xs-4">
						<label for="address"><spring:message code="addService.address" /></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<form:input id="owner" path="owner" type="text"
							class="form-control" />
					</div>
					<div class="col-xs-4">
						<form:input id="contact" path="contact" type="text"
							class="form-control" />
					</div>
					<div class="col-xs-4">
						<form:input id="address" path="address" type="text"
							class="form-control" />
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-6">
						<label for="product"><spring:message code="addService.product" /></label>
					</div>
					<div class="col-xs-6">
						<label for="serialNumber"><spring:message code="addService.serialNumber" /></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6">
						<form:input id="product" path="product" type="text"
							class="form-control" />
					</div>
					<div class="col-xs-6">
						<form:input id="serialNumber" path="serialNumber" type="text"
							class="form-control" />
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-8">
						<label for="accessories"><spring:message code="addService.accessories" /></label>
					</div>
					<div class="col-xs-4">
						<label for="sellDate"><spring:message code="addService.sellDate" /></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<form:input id="accessories" path="accessories" type="text"
							class="form-control" />
					</div>
					<div class="col-xs-4">
						<form:input id="sellDate" name="sellDate" path="sellDate" type="datatime-local"
							class="form-control" placeholder=" np. 21-01-2016"/>
					</div>
				</div>
				<br>
				<label for="description"><spring:message code="addService.description" /></label>
				<textarea id="description" name="description" class="form-control" rows="4"><c:out value="${editService.description}" /></textarea>
				<br> 
				<label for="service"><spring:message code="addService.service" /></label>
				<textarea id="service" name="service" class="form-control" rows="4"><c:out value="${editService.service}" /></textarea>
				<br> 
				<label for="damage"><spring:message code="addService.damage" /></label>
				<textarea id="damage" name="damage" class="form-control" rows="4"><c:out value="${editService.damage}" /></textarea>
				<br> 
				<label for="recommendations"><spring:message code="addService.recommendations" /></label>
				<textarea id="recommendations" name="recommendations" class="form-control" rows="2"><c:out value="${editService.recommendations}" /></textarea>
				<br> 
				<label for="price">Cena</label>
				<div class="row">
					<div class="col-xs-4">
						<form:input id="price" path="price" type="text"
							class="form-control" />
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-4">
						<label for="warranty"><spring:message code="addService.warrantyLabel" /></label>
					</div>
					<div class="col-xs-4">
						<label for="employeesList"><spring:message code="addService.employee" /></label>
					</div>
					<div class="col-xs-4">
						<label for="status"><spring:message code="addService.status" /></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-4">
						<select class="form-control" name="warranty" id="warranty">
							<option value="outOfWarranty" ${editService.warranty == 'outOfWarranty' ? 'selected' : ''}><spring:message code="addService.outOfWarranty" /></option>
							<option value="warranty" ${editService.warranty == 'warranty' ? 'selected' : ''}><spring:message code="addService.warranty" /></option>
						</select>
					</div>
					<div class="col-xs-4">
						<select class="form-control" name="employee" id="employee">
							<c:forEach items="${employees}" var="employee">
								<option>${employee.firstName} ${employee.lastName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-4">
						<select class="form-control" name="status" id="status">
							<option value="notDone" ${editService.status == 'notDone' ? 'selected' : ''}><spring:message code="addService.notDone" /></option>
							<option value="done" ${editService.status == 'done' ? 'selected' : ''}><spring:message code="addService.done" /></option>
						</select>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-xs-6">
						<input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="addService.submit" />" />
					</div>
					<div class="col-xs-6">
						<input type="button" onclick='back()' id="btnCancel" class="btn btn-primary" value="<spring:message code="addService.canel" />"/>
					</div>
				</div><br>
			</fieldset>
		</form:form>
	</section>
</body>
</html>