<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Register</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/signin.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<div id="login-form" class="text-center">
		
		<form:form action="register" modelAttribute="userDto" method="post" cssClass="form-signin">
			<img class="mb-4" src="<c:url value="/resources/bootstrap-solid.svg" />"
	  			alt="" width="72" height="72">
	  		<h1 class="h3 mb-3 font-weight-normal">Register New User</h1>
	  		
	  		<div class="form-group">
	  			<label for="username" class="sr-only">Username</label>
				<form:input path="username" id="username" placeholder="Username" 
							class="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="username" cssClass="invalid-feedback" />
			</div>
			
			<div class="form-group">
	  			<label for="email" class="sr-only">Email</label>
	  			<form:input path="email" id="email" placeholder="Email"
									class="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="email" cssClass="invalid-feedback" />
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="password" class="sr-only">Email</label>
	  			<form:password path="password"  name="password" placeholder="Password"
									cssClass="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="password" cssClass="invalid-feedback" />
	  		</div>
	  		
	  		<div class="form-group">
	  			<label for="passwordConfirm" class="sr-only">Email</label>
	  			<form:password path="passwordConfirm" id="passwordConfirm" placeholder="Confirm password"
									class="form-control" cssErrorClass="form-control is-invalid" />
				<form:errors path="passwordConfirm" cssClass="invalid-feedback" />
				<form:errors path="" cssClass="text-danger" />
	  		</div>
	  		
	  		<div class="checkbox mb-3">
	    		<label>
	      			<input type="checkbox" value="remember-me"> I accept the policy
	    		</label>
	  		</div>
	  		
	  		<input type="submit" class="btn btn-lg btn-primary btn-block" value="Register" />
	  		
			
		</form:form>
		
	</div>
 
 
	<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body></html>




<!------------------------------------------------------------------




<form:form action="register" modelAttribute="userDto" method="post" cssClass="form-horizontal">

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2>Register New User</h2>
				<hr>
			</div>
		</div>
       	
		<div class="row">
			<div class="col-md-3 field-label-responsive">
   				<label for="name">Username</label>
			</div>
  			<div class="col-md-6">
				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					
						<form:input path="username" id="username" placeholder="Username" 
									class="form-control" cssErrorClass="form-control is-invalid" />
									
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-control-feedback">
					<span class="text-danger align-middle">
					</span>
				</div>
			</div>
		</div>
       	
		<div class="row">
			<div class="col-md-3 field-label-responsive">
				<label for="email">E-Mail Address</label>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					
						<form:input path="email" id="email" placeholder="Email"
									class="form-control" cssErrorClass="form-control is-invalid" />
						
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-control-feedback">
					<span class="text-danger align-middle">
					</span>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-3 field-label-responsive">
				<label for="password">Password</label>
			</div>
			<div class="col-md-6">
				<div class="form-group has-danger">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">

						<form:input path="password" name="password" placeholder="Password"
									cssClass="form-control" cssErrorClass="form-control is-invalid" />
						
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-control-feedback">
					<span class="text-danger align-middle">
						<i class="fa fa-close"> Example Error Message</i>
					</span>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-3 field-label-responsive">
				<label for="password">Confirm Password</label>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
					
						<form:input path="passwordConfirm" id="passwordConfirm" placeholder="Confirm password"
									class="form-control" cssErrorClass="form-control is-invalid" />
					
					</div>
				</div>
			</div>
		</div>
		
		<form:errors path="">
			<h1>PASSWORDS DO NOT MATCH!</h1>
		</form:errors>
		
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<button type="submit" class="btn btn-success"> Register</button>
			</div>
		</div>
	
	</form:form>




 ------------------------------------------------------------------->
