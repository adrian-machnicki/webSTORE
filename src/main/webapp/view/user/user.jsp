<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Admin Panel</title>
	
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
			
			
				<!-- EXAMPLE CONTENT START -->
					
					<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
            			<h1 class="h2">Dashboard</h1>
            			<div class="btn-toolbar mb-2 mb-md-0">
              				<div class="btn-group mr-2">
                				<button class="btn btn-sm btn-outline-secondary">Share</button>
				                <button class="btn btn-sm btn-outline-secondary">Export</button>
		              		</div>
		              		<button class="btn btn-sm btn-outline-secondary dropdown-toggle">
		                		<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
		                			stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
		                			class="feather feather-calendar">
		                				<rect x="3" y="4" width="18" height="18" rx="2" ry="2">
		                				</rect>
		                				<line x1="16" y1="2" x2="16" y2="6"></line>
		                				<line x1="8" y1="2" x2="8" y2="6"></line>
		                				<line x1="3" y1="10" x2="21" y2="10"></line>
		                		</svg>
		                		This week
		              		</button>
		            	</div>
		          	</div>
				
				<!-- EXAMPLE CONTENT END -->
				

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