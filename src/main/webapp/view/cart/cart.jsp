<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Your cart</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			
			<main class="col-sm-8 offset-sm-2 col-md-8 offset-md-2 pt-3">
				
				<c:if test="${ not empty cart.books }">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<th>Title</th>
								<th>Author</th>
								<th>Unit price</th>
								<th>Quantity</th>
								<th>Remove from cart</th>
							</thead>
	
							<tbody>
								<c:forEach var="record" items="${cart.books}">
								
									<!-- REMOVE FROM CART URL -->
									<spring:url var="removeUrl" value="cart/remove">
										<spring:param name="id" value="${record.book.id}" />
									</spring:url>
								
									<tr>
										<td id="bookId" hidden>${record.book.id}</td>
										<td>${record.book.title}</td>
									
										<td>
											<c:forEach var="author" items="${record.book.authors}">
												${author.firstName} ${author.lastName} 
											</c:forEach>
										</td>
										
										<td>
											<fmt:formatNumber value="${record.book.price}" type="number"
												minFractionDigits="2" maxFractionDigits="2" /> zł
										</td>
										
										<td>
											<select class="form-control form-control-sm"
													onchange="changeQuantity(${record.book.id}, this.value)">
												<option value="${record.quantity}" selected>${record.quantity}</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
											</select>
										</td>
										 
										<td>
											<a href="${removeUrl}">Remove</a>
										</td>
									
									</tr>					
								</c:forEach>
							</tbody>
		
							<tfoot>
								<tr>
									<td colspan="3"></td>
									<td><b>Sum:</b></td>
									<td>
										<fmt:formatNumber value="${cart.finalPrice}" type="number"
												minFractionDigits="2" maxFractionDigits="2" /> zł
									</td>
								</tr>
							</tfoot>
		
						</table>
					</div>
				</c:if>
				
				<c:if test="${ empty cart.books }">
					<h3>Your cart is empty.</h3>
				</c:if>
				
				
				<c:if test="${not empty cart.books}">
					<p class="float-right">
					
						<c:url var="goToCheckoutFormLogged" value="order/checkout" />					
						<c:url var="goToCheckoutFormGuest" value="order/checkout/guest" />

						<sec:authorize access="isAuthenticated()">
							<a href="${goToCheckoutFormLogged}" class="btn btn-info" role="button">Buy</a>		
						</sec:authorize>

						<sec:authorize access="isAnonymous()">
							<a href="${goToCheckoutFormLogged}" class="btn btn-info" role="button">Buy as logged user</a>
							<a href="${goToCheckoutFormGuest}" class="btn btn-info" role="button">Buy as guest</a>		
						</sec:authorize>

					</p>
				</c:if>
					
				
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