<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Your cart</title>
	
	<link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">
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
						<tr>
							<th>Title</th>
							<th>Author</th>
							<th>Unit price</th>
							<th>Quantity</th>
							<th>Remove from cart</th>
						</tr>

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
								
								<td>${record.book.price}</td>
								
								<td>${record.quantity}</td>
								 
								<td>
									<a href="${removeUrl}">Remove</a>
								</td>
							
							</tr>					
						</c:forEach>

						<tr>
							<td colspan="3"></td>
							<td><b>Sum:</b></td>
							<td>${cart.finalPrice}</td>
						</tr>
	
					</table>
				</div>
				
			</main>
	
		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery-3.1.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>

