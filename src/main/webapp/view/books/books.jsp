<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Books</title>
	
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
							<th>Price</th>
							<th>Category</th>
							<th>Add to cart</th>
						</thead>
						
						<tbody>
							<c:forEach var="book" items="${books}">
								
								<!-- Single book page url -->
								<spring:url var="bookLink" value="/books">
									<spring:param name="id" value="${book.id}" />
								</spring:url>
								
								<!-- Add to cart url -->
								<spring:url var="addToCartLink" value="/cart/add">
									<spring:param name="bookId" value="${book.id}" />
								</spring:url>
							
								<tr>
									<td>
										<a href="${bookLink}">
											${book.title}
										</a>
									</td>
									
									<td>
										<c:forEach var="author" items="${book.authors}" varStatus="status">
											${author.firstName} ${author.lastName}<!--
											--><c:if test="${!status.last}">, </c:if>
										</c:forEach>
									</td>
									
									<td>
										<fmt:formatNumber value="${book.price}" type="number"
											minFractionDigits="2" maxFractionDigits="2" /> zł
									</td>
									
									<td>
									</td>
									
									<td>
										<a href="${addToCartLink}">
											Add
										</a>
									</td>
									
								</tr>
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