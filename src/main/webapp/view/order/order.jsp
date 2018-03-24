<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
				
				<h3>ORDER ID: ${order.id}</h3>
				<h3>Selected books:</h3>
				
				<div class="table-responsive">
					<table class="table table-striped">
					
						<thead>
							<th>Title</th>
							<th>Author</th>
							<th>Unit price</th>
							<th>Quantity</th>
							<th>Amount</th>
						</thead>
						
						<tbody>
							
							<c:forEach var="record" items="${order.records}">
								<tr>
									<th scope="row">
										${record.book.title}
									</th>
								
									<td>
										<c:forEach var="author" items="${record.book.authors}" varStatus="status">
											${author.firstName} ${author.lastName}<!--
											--><c:if test="${!status.last}">, </c:if>
										</c:forEach>
										<!-- AUTHORS -->
									</td>
									
									<td>
										${record.price}
									</td>
									
									<td>
										${record.quantity}
									</td>
									
									<td>
										${record.amount}
									</td>		
								</tr>
							</c:forEach>
						</tbody>
							
						<tfoot>
							<tr>
								<td colspan="3"></td>
								
								<td>
									Sum:
								</td>
								
								<td>
									<b>${order.amount}</b>
								</td>
							</tr>
						</tfoot>
						
					</table>
				</div>
				
				<p>
					<c:url var="goToCheckoutForm" value="/order/checkout" />
							
					<a href="${goToCheckoutForm}" class="btn btn-info float-right" role="button">Checkout</a>
				</p>
				
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