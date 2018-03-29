<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Checkout</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/form-validation.css" />">
	
</head>
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>


	<div class="container">
		<div class="py-5 text-center">
  			<h2>Checkout form</h2>
  			<p class="lead">Verify whether there is everything okay.</p>
		</div>

		<div class="row">
  			<div class="col-md-4 order-md-2 mb-4">
  			
 				<h4 class="d-flex justify-content-between align-items-center mb-3">
   					<span class="text-muted">Your cart</span>
   					<span class="badge badge-secondary badge-pill">${fn:length(order.records)}</span>
 				</h4>
 				
 				<ul class="list-group mb-3">
 				
 					<c:forEach var="record" items="${order.records}">
	   					<li class="list-group-item d-flex justify-content-between lh-condensed">
	     					<div>
	       						<h6 class="my-0">${record.quantity}x ${record.book.title}</h6>
	       						
	       						<small class="text-muted">	       						
		       						<c:forEach var="author" items="${record.book.authors}" varStatus="status">
		       							${author.firstName} ${author.lastName}<!--
										--><c:if test="${!status.last}">, </c:if>
		       						</c:forEach>
	       						</small>
	       						
	     					</div>
	     					<span class="text-muted">
	     						<fmt:formatNumber value="${record.price}" type="number"
											minFractionDigits="2" maxFractionDigits="2" /> zł
	     					</span>
	   					</li>
   					</c:forEach>
       				
       				<li class="list-group-item d-flex justify-content-between bg-light">
         				<div class="text-success">
           					<h6 class="my-0">Promo code</h6>
           					<small>EXAMPLECODE</small>
         				</div>
         				<span class="text-success">-$5</span>
       				</li>
       				
       				<li class="list-group-item d-flex justify-content-between">
         				<span>Total (PLN)</span>
         				<strong>
         					<fmt:formatNumber value="${order.amount}" type="number"
											minFractionDigits="2" maxFractionDigits="2" /> zł
         				</strong>
       				</li>
     			</ul>

     			<form class="card p-2">
       				<div class="input-group">
         				<input type="text" class="form-control" placeholder="Promo code">
         				<div class="input-group-append">
           					<button type="submit" class="btn btn-secondary">Redeem</button>
         				</div>
       				</div>
     			</form>
   			</div>
   			
   			
   			<div class="col-md-8 order-md-1">
     			<h4 class="mb-3">Billing address</h4>
     			
     			<form:form modelAttribute="user" method="post">
     				<form:hidden path="id" />
     				<form:hidden path="username" />
     				<form:hidden path="password"/>
     				
	       			<div class="row">
	       			
	         			<div class="col-md-6 mb-3">
	           				<label for="firstName">First name</label>
	           				<form:input path="firstName" id="firstName"
	           							cssClass="form-control" cssErrorClass="form-control is-invalid"
	           							placeholder="First name" readonly="true" />
	           				<form:errors path="firstName" cssClass="invalid-feedback" />
	         			</div>
	         			
	         			<div class="col-md-6 mb-3">
	           				<label for="secondName">Second name <span class="text-muted">(Optional)</span></label>
	           				<form:input path="secondName" id="secondName"
	           							cssClass="form-control" cssErrorClass="form-control is-invalid"
	           							readonly="true" />
	           				<form:errors path="secondName" cssClass="invalid-feedback" />
	         			</div>
	       			</div>
	       			
	       			<div class="mb-3">
	         			<label for="lastName">Last name</label>
	         				<div class="input-group">
	           					<form:input path="lastName" id="lastName"
	           								cssClass="form-control" cssErrorClass="form-control is-invalid"
	           								placeholder="Last name" readonly="true" />
	           					<form:errors path="lastName" cssClass="invalid-feedback" />
	          				</div>
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="email">Email</label>
       					<form:input path="email" id="email"
       								cssClass="form-control" cssErrorClass="form-control is-invalid"
       								placeholder="Email" readonly="true" />
       					<form:errors path="email" cssClass="invalid-feedback" />
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="address">Address</label>
	          			<form:input path="userDetails.address" id="address"
	          						cssClass="form-control" cssErrorClass="form-control is-invalid"
	          						placeholder="Address" readonly="true" />
	          			<form:errors path="userDetails.address" cssClass="invalid-feedback" />
	        		</div>
	        		
	        		<div class="mb-3">
	          			<label for="city">City</label>
	          			<form:input path="userDetails.city" id="city"
	          						cssClass="form-control" cssErrorClass="form-control is-invalid"
	          						placeholder="City" readonly="true" />
	          			<form:errors path="userDetails.city" cssClass="invalid-feedback" />
	        		</div>
	        		
	        		<hr class="mb-4">
	        		<button class="btn btn-primary btn-lg btn-block" type="submit">Place order</button>
	        		
      			</form:form>
      			
    		</div>
  		</div>

		<footer class="my-5 pt-5 text-muted text-center text-small">
    		<p class="mb-1">© 2018 webSTORE</p>
   			<ul class="list-inline">
     				<li class="list-inline-item"><a href="/#">Privacy</a></li>
     				<li class="list-inline-item"><a href="/#">Terms</a></li>
     				<li class="list-inline-item"><a href="/#">Support</a></li>
   			</ul>
  		</footer>
	</div>


	<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/support.js" />"></script>
</body>
</html>