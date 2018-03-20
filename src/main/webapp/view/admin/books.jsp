<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Books</title>
	
	<link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">
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
					
					<table class="table table-hover">
						<tr>
							<th>Id</th>
							<th>Title</th>
							<th>Author</th>
							<th>Amount</th>
							<th>Price</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>
						
						<c:forEach var="book" items="${books}">
							<c:url var="updateLink" value="/admin/books/update">
								<c:param name="bookId" value="${book.id}"></c:param>
							</c:url>
						
							<c:url var="deleteLink" value="/admin/books/delete">
								<c:param name="bookId" value="${book.id}"></c:param>
							</c:url>
						
							<tr>
								<td>${book.id}</td>
								
								<td>${book.title}</td>
								
								<td>
									<c:forEach var="author" items="${book.authors}">
										${author.firstName} ${author.lastName},
									</c:forEach>
								</td>
								
								<td>${book.amount}</td>
								
								<td>${book.price}</td>
								
								<td><a href="${updateLink}">Update</a></td>
								
								<td><a href="${deleteLink}">Delete</a></td>
								
							</tr>
						</c:forEach>
						
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

