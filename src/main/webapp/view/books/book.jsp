<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="book.pageTitle" /> ${book.title}</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
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
					<spring:message code="book.id" />
					${book.id}
				</p>
				
				<p>
					<spring:message code="book.title" />
					${book.title}
				</p>
				
				<p>
					<spring:message code="book.author" />
					<c:forEach var="author" items="${book.authors}">
						${author.firstName} ${author.lastName} 
					</c:forEach>
				</p>
				
				<p>
					<spring:message code="book.pages" />
					${book.details.pages}
				</p>
				
				<p>
					<spring:message code="book.description" />
					${book.details.description}
				</p>
				
				<p>
					<a href="${addToCartLink}">
						<spring:message code="book.addToCart" />
					</a>
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