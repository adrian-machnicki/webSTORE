<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
			
				<p>
					<spring:url var="addToCartLink" value="/cart/add">
						<spring:param name="bookId" value="${book.id}" />
					</spring:url>
				</p>
				
				<p>
					Id: ${book.id}
				</p>
				
				<p>
					Title: ${book.title}
				</p>
				
				<p>
					<c:forEach var="author" items="${book.authors}">
						${author.firstName} ${author.lastName} 
					</c:forEach>
				</p>
				
				<p>
					Pages: ${book.details.pages}
				</p>
				
				<p>
					Description: ${book.details.description}
				</p>
				
				<p>
					<a href="${addToCartLink}">Add to cart</a>
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