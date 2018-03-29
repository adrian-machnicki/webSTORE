<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Books management</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
</head>
<body>

<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<div class="container-fluid">
		<div class="row">
		
			<jsp:include page="${request.contextPath}/view/admin-sidebar"></jsp:include>
			
			<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
				<h2>Books management</h2>
				
				<div class="table-responsive">
					<input type="button" value="Add book" class="btn btn-primary"
							onclick="window.location.href='books/add'" />
							
					<form:form method="get">
						<div class="form-group float-right">
							<div class="form-inline float-right">
								<input type="text" class="form-control" placeholder="Search book" name="search" />
								<input type="submit" value="Search" class="btn btn-primary" />
							</div>
						</div>
					</form:form>
					
					<table class="table table-hover">
						<thead>
							<th>Id</th>
							<th>Title</th>
							<th>Author</th>
							<th>Amount</th>
							<th>Price</th>
							<th>Update</th>
							<th>Delete</th>
						</thead>
						
						<tbody>
							<c:forEach var="book" items="${books}">
								<c:url var="updateLink" value="/admin/books/update">
									<c:param name="bookId" value="${book.id}"></c:param>
								</c:url>
							
								<c:url var="deleteLink" value="/admin/books/delete">
									<c:param name="bookId" value="${book.id}"></c:param>
								</c:url>
							
								<tr>
									<td>
										${book.id}
									</td>
									
									<td>
										${book.title}
									</td>
									
									<td>
										<c:forEach var="author" items="${book.authors}" varStatus="status">
											${author.firstName} ${author.lastName}<!--
											--><c:if test="${!status.last}">, </c:if>
										</c:forEach>
									</td>
									
									<td>
										${book.amount}
									</td>
									
									<td>
										<fmt:formatNumber value="${book.price}" type="number"
											minFractionDigits="2" maxFractionDigits="2" /> zł
									</td>
									
									<td>
										<a href="${updateLink}">Update</a>
									</td>
									
									<td>
										<a href="${deleteLink}">Delete</a>
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