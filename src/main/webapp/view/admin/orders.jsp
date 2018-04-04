<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Orders management</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
</head>
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<div class="container-fluid">
		<div class="row">
		
			<jsp:include page="${request.contextPath}/view/admin-sidebar"></jsp:include>
			
			<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
				<h2>Orders management</h2>
				
				<form:form method="get">
					<div class="form-group float-right">
						<div class="form-inline float-right">
							<input type="text" class="form-control" placeholder="Id, username or city" name="search" />
							<input type="submit" value="Search" class="btn btn-primary" />
						</div>
					</div>
				</form:form>
				
				<div class="table-responsive">
					
					<table class="table table-hover">
						<thead>
							<th>Id</th>
							<th>User</th>
							<th>Amount</th>
							<th>Date</th>
							<th>Book title</th>
							<th>Author</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Paid</th>
							<th>Sent</th>
						</thead>
						
						<tbody>
							<c:forEach var="order" items="${orders}">
							
								<c:url var="orderDetailsUrl" value="/admin/orders/order">
									<c:param name="id" value="${order.id}" />
								</c:url>
							
								<tr class="table-primary">
									<td>
										<b>
											<a href="${orderDetailsUrl}">${order.id}</a>
										</b>
									</td>
									
									<td>
										<b>${order.user.firstName} ${order.user.lastName}</b>
									</td>
									
									<td>
										<b>
											$<fmt:formatNumber value="${order.amount}" type="number"
													minFractionDigits="2" maxFractionDigits="2" />
										</b>
									</td>
									
									<td>
										<b>
											<fmt:formatDate pattern="YYYY-MM-dd HH:mm" value="${order.date}" />			
										</b>					
									</td>
									
									<td colspan="4"></td>
									
									<td>
										<form:form action="orders/paid" method="post" id="setPaidLink${order.id}">
											<input type="hidden" name="orderId" value="${ order.id }" />
											<c:if test="${ order.paid }">
												<span class="oi oi-circle-check text-success"></span>
											</c:if>
											<c:if test="${ !order.paid }">
												<span class="oi oi-circle-x text-secondary"></span>
											</c:if>
											<a href="javascript:{}" onclick="submitSetPaid(${order.id})">
												<c:if test="${ order.paid }">
													Set unpaid
												</c:if>
												<c:if test="${ !order.paid }">
													Set paid
												</c:if>
											</a>
										</form:form>
									</td>
									
									<td>
										<form:form action="orders/sent" method="post" id="setSentLink${order.id}">
											<input type="hidden" name="orderId" value="${ order.id }" />
											<c:if test="${ order.sent }">
												<span class="oi oi-circle-check text-success"></span>
											</c:if>
											<c:if test="${ !order.sent }">
												<span class="oi oi-circle-x text-secondary"></span>
											</c:if>
											<a href="javascript:{}" onclick="submitSetSent(${order.id})">
												<c:if test="${ order.sent }">
													Set unsent
												</c:if>
												<c:if test="${ !order.sent }">
													Set sent
												</c:if>
											</a>
										</form:form>
									</td>
									
								</tr>
								
								<c:forEach var="record" items="${order.records}">
									<tr style="border-collapse: collapse;">
									
										<td colspan="4"></td>
										
										<td>${record.book.title}</td>
										
										<td>
											<c:forEach var="author" items="${record.book.authors}" varStatus="status">
												${author.firstName} ${author.lastName}<!--
												--><c:if test="${!status.last}">, </c:if>
											</c:forEach>
										</td>
										
										<td>
											$<fmt:formatNumber value="${record.price}" type="number"
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