<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html style="height: 90%;">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>404 Not Found</title>

	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/open-iconic-bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
	
</head>
<body style="height: 100%;">
   
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>
	
	<section class="h-100">
		<div class="container h-100">
			<div class="d-flex align-items-center justify-content-center h-100">
				<div class="d-flex flex-column"">
					<div>
						<h1>Oops!</h1>
	               		<h2>404 Not Found</h2>
	               		<div>
	               			Sorry, an error has occured, Requested page not found!
	               		</div>
	               		<div>
	                   		<a href="${request.contextPath}/home" class="btn btn-primary btn-lg">
	                   			<span class="oi oi-home"></span>
	                       		Take Me Home
	                       	</a>
	                       	<a href="${request.contextPath}/information" class="btn btn-secondary btn-lg">
	                       		<span class="oi oi-envelope-closed"></span>
	                       		Contact Support
	                       	</a>
	                       </div>
					</div>
	       		</div>		
			</div>
		</div>
	</section>

<!-- 
	<div class="container-fluid">
		<div class="row">
			
			<main class="col-sm-8 offset-sm-2 col-md-8 offset-md-2 pt-3">
			
				<div class="col-md-12">
					<div>
						<h1>Oops!</h1>
                		<h2>404 Not Found</h2>
                		<div>
                			Sorry, an error has occured, book with id=${bookId} not found!
                		</div>
                		<div>
                    		<a href="${request.contextPath}/home" class="btn btn-primary btn-lg">
                    			<span class="oi oi-home"></span>
                        		Take Me Home
                        	</a>
                        	<a href="${request.contextPath}/information" class="btn btn-secondary btn-lg">
                        		<span class="oi oi-envelope-closed"></span>
                        		Contact Support
                        	</a>
                        </div>
					</div>
        		</div>
			
			</main>
			
		</div>
	</div>
 -->

    <script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/support.js" />"></script>
  
</body></html>