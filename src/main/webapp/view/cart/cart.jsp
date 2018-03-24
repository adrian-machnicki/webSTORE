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
									
									<td>${record.quantity}</td>
									 
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
				<p>
				
					<c:if test="${not empty cart.books}">
						<c:url var="goToOrderLink" value="/order" />
							
						<a href="${goToOrderLink}" class="btn btn-info float-right" role="button">Check order</a>
					</c:if>
					
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