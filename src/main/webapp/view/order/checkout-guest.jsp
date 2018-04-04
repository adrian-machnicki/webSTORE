<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="checkout.pageTitle" /></title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/form-validation.css" />">
	
</head>
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>


	<div class="container">
		<div class="py-5 text-center">
  			<h2>
  				<spring:message code="checkout.header" />
  			</h2>
  			<p class="lead">
  				<spring:message code="checkout.prompt" />
  			</p>
		</div>

		<div class="row">
  			<div class="col-md-4 order-md-2 mb-4">
  			
 				<h4 class="d-flex justify-content-between align-items-center mb-3">
   					<span class="text-muted">
   						<spring:message code="checkout.yourCart" />
   					</span>
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
	     						$<fmt:formatNumber value="${record.price}" type="number"
											minFractionDigits="2" maxFractionDigits="2" />
	     					</span>
	   					</li>
   					</c:forEach>
       				
       				<li class="list-group-item d-flex justify-content-between">
         				<span>
         					<spring:message code="checkout.total" />
         				</span>
         				<strong>
         					$<fmt:formatNumber value="${order.amount}" type="number"
											minFractionDigits="2" maxFractionDigits="2" />
         				</strong>
       				</li>
     			</ul>
   			</div>
   			
   			
   			<div class="col-md-8 order-md-1">
     			<h4 class="mb-3">
     				<spring:message code="checkout.billingAddress" />
     			</h4>
     			
     			<form:form modelAttribute="shippingDetails" method="post">
     				
	       			<div class="row">
	       			
	         			<div class="col-md-6 mb-3">
	           				<label for="firstName">
	           					<spring:message code="checkout.firstName" />
	           				</label>
	           				<form:input path="firstName" id="firstName"
	           							cssClass="form-control" cssErrorClass="form-control is-invalid"
	           							placeholder="First name" />
	           				<form:errors path="firstName" cssClass="invalid-feedback" />
	         			</div>
	         			
	         			<div class="col-md-6 mb-3">
	           				<label for="secondName">
	           					<spring:message code="checkout.secondName" />
		           				<span class="text-muted">
		           					<spring:message code="checkout.optional" />
		           				</span>
	           				</label>
	           				<form:input path="secondName" id="secondName"
	           							cssClass="form-control" cssErrorClass="form-control is-invalid"
	           							placeholder="Second name" />
	           				<form:errors path="secondName" cssClass="invalid-feedback" />
	         			</div>
	       			</div>
	       			
	       			<div class="mb-3">
	         			<label for="lastName">
	         				<spring:message code="checkout.lastName" />
	         			</label>
	         				<div class="input-group">
	           					<form:input path="lastName" id="lastName"
	           								cssClass="form-control" cssErrorClass="form-control is-invalid"
	           								placeholder="Last name" />
	           					<form:errors path="lastName" cssClass="invalid-feedback" />
	          				</div>
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="email">
	          				<spring:message code="checkout.email" />
	          			</label>
       					<form:input path="email" id="email"
       								cssClass="form-control" cssErrorClass="form-control is-invalid"
       								placeholder="Email" />
       					<form:errors path="email" cssClass="invalid-feedback" />
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="address">
	          				<spring:message code="checkout.address" />
	          			</label>
	          			<form:input path="address" id="address"
	          						cssClass="form-control" cssErrorClass="form-control is-invalid"
	          						placeholder="Address" />
	          			<form:errors path="address" cssClass="invalid-feedback" />
	        		</div>
	        		
	        		<div class="mb-3">
	          			<label for="city">
	          				<spring:message code="checkout.city" />
	          			</label>
	          			<form:input path="city" id="city"
	          						cssClass="form-control" cssErrorClass="form-control is-invalid"
	          						placeholder="City" />
	          			<form:errors path="city" cssClass="invalid-feedback" />
	        		</div>
	        		
	        		<hr class="mb-4">
	        		<button class="btn btn-primary btn-lg btn-block" type="submit">
	        			<spring:message code="checkout.placeOrder" />
	        		</button>
	        		
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