<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
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
	     					<span class="text-muted">${record.price}</span>
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
         				<strong>${order.amount} zł</strong>
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
     			<form class="needs-validation" novalidate="">
     			
	       			<div class="row">
	         			<div class="col-md-6 mb-3">
	           				<label for="firstName">First name</label>
	           				<input type="text" class="form-control" id="firstName" placeholder="" value="" required="">
	          				<div class="invalid-feedback">
	            				Valid first name is required.
	          				</div>
	         			</div>
	         			<div class="col-md-6 mb-3">
	           				<label for="lastName">Last name</label>
	           				<input type="text" class="form-control" id="lastName" placeholder="" value="" required="">
	           				<div class="invalid-feedback">
	             				Valid last name is required.
	           				</div>
	         			</div>
	       			</div>
	
	       			<div class="mb-3">
	         			<label for="username">Username</label>
	         				<div class="input-group">
	           					<div class="input-group-prepend">
	             					<span class="input-group-text">@</span>
	           					</div>
	           					<input type="text" class="form-control" id="username" placeholder="Username" required="">
	           					<div class="invalid-feedback" style="width: 100%;">
	              					Your username is required.
	            				</div>
	          				</div>
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="email">Email <span class="text-muted">(Optional)</span></label>
	          			<input type="email" class="form-control" id="email" placeholder="you@example.com">
	          			<div class="invalid-feedback">
	            			Please enter a valid email address for shipping updates.
	          			</div>
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="address">Address</label>
	          			<input type="text" class="form-control" id="address" placeholder="1234 Main St" required="">
	          			<div class="invalid-feedback">
	            			Please enter your shipping address.
	          			</div>
	        		</div>
	
	        		<div class="mb-3">
	          			<label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
	          			<input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
	        		</div>
	
	        		<div class="row">
	          			<div class="col-md-5 mb-3">
	            			<label for="country">Country</label>
	            			<select class="custom-select d-block w-100" id="country" required="">
	              				<option value="">Choose...</option>
	              				<option>United States</option>
	            			</select>
	            			<div class="invalid-feedback">
	             	 			Please select a valid country.
	            			</div>
	          			</div>
	          			<div class="col-md-4 mb-3">
	            			<label for="state">State</label>
	            			<select class="custom-select d-block w-100" id="state" required="">
	             	 			<option value="">Choose...</option>
	              				<option>California</option>
	            			</select>
	            			<div class="invalid-feedback">
	              				Please provide a valid state.
	            			</div>
	          			</div>
	          			<div class="col-md-3 mb-3">
	            			<label for="zip">Zip</label>
	            			<input type="text" class="form-control" id="zip" placeholder="" required="">
	            			<div class="invalid-feedback">
	              				Zip code required.
	            			</div>
	          			</div>
	        		</div>
	        		<hr class="mb-4">
	        		<div class="custom-control custom-checkbox">
	          			<input type="checkbox" class="custom-control-input" id="same-address">
	          			<label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
	        		</div>
	        		<div class="custom-control custom-checkbox">
	          			<input type="checkbox" class="custom-control-input" id="save-info">
	          			<label class="custom-control-label" for="save-info">Save this information for next time</label>
	        		</div>
	        		<hr class="mb-4">
	
	        		<h4 class="mb-3">Payment</h4>
	
	        		<div class="d-block my-3">
	          			<div class="custom-control custom-radio">
	            			<input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" required="">
	            			<label class="custom-control-label" for="credit">Credit card</label>
	          			</div>
	          			<div class="custom-control custom-radio">
	            			<input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required="">
	            			<label class="custom-control-label" for="debit">Debit card</label>
	          			</div>
	          			<div class="custom-control custom-radio">
	            			<input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required="">
	            			<label class="custom-control-label" for="paypal">Paypal</label>
	          			</div>
	        		</div>
	        		
	        		<div class="row">
	          			<div class="col-md-6 mb-3">
	            			<label for="cc-name">Name on card</label>
	            			<input type="text" class="form-control" id="cc-name" placeholder="" required="">
	            			<small class="text-muted">Full name as displayed on card</small>
	            			<div class="invalid-feedback">
	              				Name on card is required
	            			</div>
	          			</div>
	          			<div class="col-md-6 mb-3">
	            			<label for="cc-number">Credit card number</label>
	            			<input type="text" class="form-control" id="cc-number" placeholder="" required="">
	            			<div class="invalid-feedback">
	              				Credit card number is required
	            			</div>
	          			</div>
	        		</div>
	        		
	        		<div class="row">
	          			<div class="col-md-3 mb-3">
	            			<label for="cc-expiration">Expiration</label>
	            			<input type="text" class="form-control" id="cc-expiration" placeholder="" required="">
	            			<div class="invalid-feedback">
	              				Expiration date required
	            			</div>
	          			</div>
	          			<div class="col-md-3 mb-3">
	            			<label for="cc-expiration">CVV</label>
	            			<input type="text" class="form-control" id="cc-cvv" placeholder="" required="">
	            			<div class="invalid-feedback">
	              				Security code required
	            			</div>
	          			</div>
	        		</div>
	        		<hr class="mb-4">
	        		<button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
      			</form>
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