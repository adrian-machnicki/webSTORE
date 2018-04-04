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
				<h2>Users management</h2>
				
				
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
							<th>Username</th>
							<th>First name</th>
							<th>Last name</th>
							<th>Email</th>
							<th>Address</th>
							<th>City</th>
							<th>Block/Unblock</th>
							<th>Remove</th>
						</thead>
						
						<tbody>
							<c:forEach var="user" items="${ users }">	
							
								<c:url var="blockOrUnblockUser" value="/admin/users/block-unblock">
									<c:param name="id" value="${ user.id }" />
								</c:url>
								<c:url var="removeUser" value="/admin/users/remove">
									<c:param name="id" value="${ user.id }" />
								</c:url>
													
								<tr>
									<td>${ user.id }</td>
									<td>${ user.username }</td>
									<td>${ user.firstName }</td>
									<td>${ user.lastName }</td>
									<td>${ user.email }</td>
									<td>${ user.userDetails.address }</td>
									<td>${ user.userDetails.city }</td>
									<td>
										<a href="${ blockOrUnblockUser }">
											<c:if test="${ user.enabled == 1 }">
												Block
											</c:if>
											<c:if test="${ user.enabled == 0 }">
												Unblock
											</c:if>
										</a>
									</td>
									<td>
										<a href="${ removeUser }">Remove</a>
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