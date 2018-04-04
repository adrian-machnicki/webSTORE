<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="signup.pageTitle" /> </title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/signin.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<div id="login-form" class="text-center">
		
		<form:form action="register" modelAttribute="userDto" method="post" cssClass="form-signin">
			<img class="mb-4" src="<c:url value="/resources/icons/bootstrap-solid.svg" />"
	  			alt="" width="72" height="72">
	  		<h1 class="h3 mb-3 font-weight-normal">
	  			<spring:message code="signup.prompt" />
	  		</h1>
	  		
	  		<div class="form-group">
	  			<label for="username" class="sr-only">
	  				<spring:message code="signup.username" />
	  			</label>
				<form:input path="username" id="username"
							placeholder="Username" 
							class="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="username" cssClass="invalid-feedback" />
			</div>
			
			<div class="form-group">
	  			<label for="email" class="sr-only">
	  				<spring:message code="signup.email" />
	  			</label>
	  			<form:input path="email" id="email" placeholder="Email"
							class="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="email" cssClass="invalid-feedback" />
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="password" class="sr-only">
	  				<spring:message code="signup.password" />
	  			</label>
	  			<form:password path="password"  name="password" placeholder="Password"
									cssClass="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="password" cssClass="invalid-feedback" />
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="passwordConfirm" class="sr-only">
	  				<spring:message code="signup.passwordConfirm" />
	  			</label>
	  			<form:password path="passwordConfirm" id="passwordConfirm" placeholder="Confirm password"
									class="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="passwordConfirm" cssClass="invalid-feedback" />
				<form:errors path="" cssClass="text-danger" />
	  		</div>
	  		
	  		<div class="checkbox mb-3">
	    		<label>
	      			<input type="checkbox" value="remember-me">
	      			<spring:message code="signup.acceptPolicy" />
	    		</label>
	  		</div>
	  		
	  		<input type="submit" class="btn btn-lg btn-primary btn-block" value="<spring:message code="signup.submit" />" />
	  		
			
		</form:form>
		
	</div>
 
 
	<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body></html>
