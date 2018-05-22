<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="user.pageTitle" /></title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>
	
	<!-- INCLUDE NAVBAR HERE -->
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
		
			<!-- INCLUDE SIDEBAR HERE -->
			<jsp:include page="${request.contextPath}/view/user-sidebar"></jsp:include>
			
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			
				<div>
					
					<form:form modelAttribute="user" method="post">
						<form:hidden path="id" />
					
						<div class="form-group row">
							<label for="firstName" class="col-sm-2 col-form-label">
								<spring:message code="user.firstName" />
							</label>
							<div class="col-sm-10">
								<form:input path="firstName" id="firstName"
											cssClass="form-control" cssErrorClass="form-control is-invalid" />
								<form:errors path="firstName" cssClass="invalid-feedback" />
							</div>
						</div>
						
						<div class="form-group row">
							<label for="secondName" class="col-sm-2 col-form-label">
								<spring:message code="user.secondName" />
								<span class="text-muted">
									(<spring:message code="user.optional" />)
								</span>
							</label>
							<div class="col-sm-10">
								<form:input path="secondName" id="secondName"
											cssClass="form-control" cssErrorClass="form-control is-invalid" />
								<form:errors path="secondName" cssClass="invalid-feedback" />
							</div>
						</div>
						
						<div class="form-group row">
							<label for="lastName" class="col-sm-2 col-form-label">
								<spring:message code="user.lastName" />
							</label>
							<div class="col-sm-10">
								<form:input path="lastName" id="lastName"
											cssClass="form-control" cssErrorClass="form-control is-invalid" />
								<form:errors path="lastName" cssClass="invalid-feedback" />
							</div>
						</div>
						
						<hr />
						
						<div class="form-group row">
							<label for="address" class="col-sm-2 col-form-label">
								<spring:message code="user.address" />
							</label>
							<div class="col-sm-10">
								<form:input path="address" id="address"
											cssClass="form-control" cssErrorClass="form-control is-invalid" />
								<form:errors path="address" cssClass="invalid-feedback" />
							</div>
						</div>
						
						<div class="form-group row">
							<label for="city" class="col-sm-2 col-form-label">
								<spring:message code="user.city" />
							</label>
							<div class="col-sm-10">
								<form:input path="city" id="city"
											cssClass="form-control" cssErrorClass="form-control is-invalid" />
								<form:errors path="city" cssClass="invalid-feedback" />
							</div>
						</div>
						
						<hr />
						
						<div class="form-group row">
							<label for="city" class="col-sm-2 col-form-label">
								<spring:message code="user.email" />
							</label>
							<div class="col-sm-10">
								<form:input path="email" id="email"
											cssClass="form-control" cssErrorClass="form-control is-invalid" />
								<form:errors path="email" cssClass="invalid-feedback" />
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-10">
								<button type="submit" value="Save" class="btn btn-primary">
									<spring:message code="user.saveProfile" />
								</button>
							</div>
						</div>
						
					</form:form>
				
				</div>

        	</main>
		
		</div>
	</div>
	
	<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/support.js" />"></script>

</body>
</html>