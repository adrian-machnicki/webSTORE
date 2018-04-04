<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="books.pageTitle" /></title>
	
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
			
				<form:form method="get">
					<div class="form-group float-right">
						<div class="form-inline float-right">
							<input type="text" class="form-control"
								placeholder="<spring:message code="books.search" />" name="search" />
							<input type="submit" class="btn btn-primary"
								value="<spring:message code="books.searchSubmit" />" />
						</div>
					</div>
				</form:form>
				
				<div class="table-responsive">
					
					<table class="table table-hover">

						<thead>
							<th>
								<spring:message code="books.title" />
							</th>
							
							<th>
								<spring:message code="books.author" />
							</th>
							
							<th>
								<spring:message code="books.price" />
							</th>
							
							<th>
								<spring:message code="books.addToCart" />
							</th>
							
						</thead>
						
						<tbody>
							<c:forEach var="book" items="${books}">
								
								<!-- Single book page url -->
								<spring:url var="bookLink" value="/books/book/${book.id}" />
								
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
										$<fmt:formatNumber value="${book.price}" type="number"
											minFractionDigits="2" maxFractionDigits="2" />
									</td>
									
									<td>
										<c:if test="${fn:contains(addedBooksIds, book.id)}">
											<span class="text-muted">
												<spring:message code="books.added" />
											</span>
										</c:if>	
																	
										<c:if test="${not fn:contains(addedBooksIds, book.id)}">
											<a href="${addToCartLink}">
												<spring:message code="books.add" />
											</a>
										</c:if>																
									</td>
									
								</tr>
							</c:forEach>
							
						</tbody>
						
					</table>
					
					<ul class="pagination justify-content-end">
						<c:url var="previous" value="/books">
							<c:param name="page" value="${currentPage-1}" />
						</c:url>
					
						<c:if test="${currentPage > 1}">
							<li class="page-item">
								<a class="page-link" href="${previous}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
								</a>
							</li>	
						</c:if>
						
						<c:forEach begin="1" end="${maxPage}" step="1" varStatus="i">
							<c:choose>
							
								<c:when test="${currentPage == i.index}">
									<li class="page-item active"><a class="page-link" href="#">${currentPage}</a></li>
								</c:when>
								
								<c:otherwise>
									<c:url var="url" value="/books">
										<c:param name="page" value="${i.index}" />
									</c:url>
									<li class="page-item"><a class="page-link" href="${url}">${i.index}</a></li>									
								</c:otherwise>
								
							</c:choose>					
						</c:forEach>
						
						<c:url var="next" value="/books">
							<c:param name="page" value="${currentPage+1}" />
						</c:url>
						<c:if test="${currentPage+1 <= maxPage}">
							<li class="page-item">
								<a class="page-link" href="${next}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
									<span class="sr-only">Next</span>
								</a>
							</li>
						</c:if>

					</ul>
					
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