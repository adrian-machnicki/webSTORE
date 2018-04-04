<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
	<meta name="author" content="Adrian Machnicki">
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title><spring:message code="confirmation.pageTitle" /></title>
	
	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<main role="main" class="container">

		<div>
			<h1>
				<spring:message code="confirmation.header" />
				${orderId}
			</h1>
			<p class="lead">
				<spring:message code="confirmation.confirmation" />
			</p>
		</div>

	</main>


	<script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/support.js" />"></script>
</body>
</html>