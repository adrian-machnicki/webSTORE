<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Your profile</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>
	
	<!-- INCLUDE NAVBAR HERE -->
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
		
			<!-- INCLUDE SIDEBAR HERE -->
			<jsp:include page="${request.contextPath}/view/user-sidebar"></jsp:include>
			
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			
				<c:if test="${ not empty orders }">
					<div class="table-responsive">
					
						<table class="table table-hover">
							<thead>
								<th>Id</th>
								<th>Date</th>
								<th>Amount</th>
								<th>Book title</th>
								<th>Author</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Paid</th>
								<th>Sent</th>
							</thead>
							
							<tbody>
								<c:forEach var="order" items="${orders}">
									<tr class="table-primary">
										<td><b>${order.id}</b></td>
										<td>
											<b>
												<fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${order.date}" />	
											</b>
										</td>
										
										<td>
											<b>
												<fmt:formatNumber value="${order.amount}" type="number"
														minFractionDigits="2" maxFractionDigits="2" /> zł
											</b>
										</td>
										
										<td colspan="4"></td>

										<td>
											<c:if test="${ order.paid }">
												<span class="oi oi-circle-check text-success"></span>
											</c:if>
											<c:if test="${ !order.paid }">
												<span class="oi oi-circle-x text-secondary"></span>
											</c:if>
										</td>
										
										<td>
											<c:if test="${ order.sent }">
												<span class="oi oi-circle-check text-success"></span>
											</c:if>
											<c:if test="${ !order.sent }">
												<span class="oi oi-circle-x text-secondary"></span>
											</c:if>
										</td>
										
									</tr>
									
									<c:forEach var="record" items="${order.records}">
										<tr style="border-collapse: collapse;">
											<td colspan="3"></td>
											
											<td>${record.book.title}</td>
											
											<td>
												<c:forEach var="author" items="${record.book.authors}" varStatus="status">
													${author.firstName} ${author.lastName}<!--
													--><c:if test="${!status.last}">, </c:if>
												</c:forEach>
											</td>
											
											<td>
												<fmt:formatNumber value="${record.price}" type="number"
														minFractionDigits="2" maxFractionDigits="2" />	
											</td>
											
											<td>${record.quantity}</td>
											
											<td colspan="2"></td>
										</tr>
									</c:forEach>
									
								</c:forEach>
							</tbody>
						</table>
	
					</div>
				</c:if>
				
				<c:if test="${ empty orders }">
					<h3>You have not placed any orders yet.</h3>
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