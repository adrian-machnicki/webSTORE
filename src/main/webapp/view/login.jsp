<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="login.pageTitle" /></title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/signin.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>
	
	<div id="login-form" class="text-center">
		<form class="form-signin" method="post">
	  		<img class="mb-4" src="<c:url value="/resources/icons/bootstrap-solid.svg" />"
	  			alt="" width="72" height="72">
	  		<h1 class="h3 mb-3 font-weight-normal">
	  			<spring:message code="login.prompt" />
	  		</h1>
	  		
	  		<c:if test="${param.error != null}">
		  		<div class="alert alert-danger" role="alert">
		  			<spring:message code="login.invalid" />
				</div>
	  		</c:if>
	  		
	  		<c:if test="${param.logout != null}">
	  			<div class="alert alert-info" role="alert">
	  				<spring:message code="login.loggedOut" />
				</div>
	  		</c:if>
	  		
	  		<label for="username" class="sr-only">
	  			<spring:message code="login.username" />
	  		</label>
	  		<input type="text" name="username" id="username" class="form-control"
	  			placeholder="<spring:message code="login.username" />" autofocus="true" />
	  		
	  		<label for="password" class="sr-only">
	  			<spring:message code="login.password" />
	  		</label>
	  		<input type="password" name="password" id="password" class="form-control"
	  			placeholder="<spring:message code="login.password" />" />
	  		
	  		<div class="checkbox mb-3">
	    		<label>
	      			<input type="checkbox" value="remember-me">
	      			<spring:message code="login.rememberMe" />
	    		</label>
	  		</div>
	  		
	  		<input type="hidden"
				name="${_csrf.parameterName}"
				value="${_csrf.token}" />
	  		
	  		<input type="submit" class="btn btn-lg btn-primary btn-block"
	  			value="<spring:message code="login.submit" />" />
	  		<p class="mt-5 mb-3 text-muted">©2018</p>
		</form>
	</div>

	<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body></html>