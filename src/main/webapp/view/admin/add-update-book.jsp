<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Add new book</title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
		
			<jsp:include page="${request.contextPath}/view/admin-sidebar"></jsp:include>
			
			<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
				<h2>Add new book</h2>
				
				<div class="col-12 col-md-9 pull-mWd-3 bd-content">
					<div class="bd-example">
				
						<form:form action="add" modelAttribute="book" method="POST" id="addBookForm">
							<form:hidden path="id"/>
							
							<div class="form-group row">
								<label for="title" class="col-3 col-form-label">Title:</label>
								<div class="col-9">
									<form:input path="title" id="title" class="form-control" cssErrorClass="form-control is-invalid" />
									<form:errors path="title" cssClass="invalid-feedback" />
								</div>
							</div>
							
							<c:forEach items="${book.authors}" var="author" varStatus="status">
								<div class="form-group row">
									<label for="firstName" class="col-3 col-form-label">Authors first name:</label>
									<div class="col-9">
										<form:input path="authors[${status.index}].firstName" id="firstName"
													class="form-control" cssErrorClass="form-control is-invalid" />
										<form:errors path="authors[${status.index}].firstName" cssClass="invalid-feedback" />
									</div>
									<label for="lastName" class="col-3 col-form-label">Authors last name:</label>
									<div class="col-9">
										<form:input path="authors[${status.index}].lastName" id="lastName"
													class="form-control" cssErrorClass="form-control is-invalid" />
										<form:errors path="authors[${status.index}].lastName" cssClass="invalid-feedback" />
									</div>
								</div>
								<c:if test="${status.last}">
									<div class="form-group row">
										<div class="col-10"></div>
										<div class="col-2">
											<button onclick="addAuthor()" class="btn btn-outline-success">Add author</button>
										</div>
									</div>
								</c:if>
							</c:forEach>
							
							<div class="form-group row">
								<label for="price" class="col-3 col-form-label">Price:</label>
								<div class="col-9">
									<form:input path="price" id="price" class="form-control" />
								</div>
							</div>
							
							<div class="form-group row">
								<label for="amount" class="col-3 col-form-label">Amount:</label>
								<div class="col-9">
									<form:input path="amount" id="amount" class="form-control" />
								</div>
							</div>
							
							<div class="form-group row">
								<label for="pages" class="col-3 col-form-label">Number of pages:</label>
								<div class="col-9">
									<form:input path="details.pages" id="amount" class="form-control" />
								</div>
							</div>
							
							<div class="form-group row">
								<label for="description" class="col-3 col-form-label">Description:</label>
								<div class="col-9">
									<form:input path="details.description" id="description" class="form-control" />
									<form:errors path="details.description" cssClass="invalid-feedback" />
								</div>
							</div>
							
							<button type="submit" value="Save" class="btn btn-primary">Submit</button>

						</form:form>
					
					</div>
				</div>
				
				<p>
					<a href="${request.contextPath}/admin/books">Back</a>
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