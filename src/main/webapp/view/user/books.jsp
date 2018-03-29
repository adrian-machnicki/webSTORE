<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Your profile</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
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
			
				<c:if test="${ not empty books }">
					<div class="table-responsive">
					
						<table class="table table-hover">
							<thead>
								<th>Title</th>
								<th>Author</th>
							</thead>
							
							<tbody>
								<c:forEach var="book" items="${books}">
									<tr>
										<td>
											${book.title}
										</td>
										
										<td>
											<c:forEach var="author" items="${book.authors}" varStatus="status">
												${author.firstName} ${author.lastName}<!--
												--><c:if test="${!status.last}">, </c:if>
											</c:forEach>
										</td>								
									</tr>
								</c:forEach>
							</tbody>
						</table>
	
					</div>
				</c:if>
				
				<c:if test="${ empty books }">
					<h3>You have not bought any books yet.</h3>
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