<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Home</title>

	<link rel="icon" href="<c:url value="/resources/icons/book.ico" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">

</head>
<body>
   
	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<main role="main">


		<div class="jumbotron">
			<div class="container">
  				<h1 class="display-3">Wanna a book? You've come to the right place!</h1>
  				<p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
  				<p><a class="btn btn-primary btn-lg" href="${request.contextPath}/books/all" role="button">Shop now »</a></p>
  			</div>
		</div>

		<div class="container">
 
			<div class="row">
  				<div class="col-md-4">
 					<h2>Promotions</h2>
 					<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
 					<p><a class="btn btn-secondary" href="#" role="button">Check promotions »</a></p>
  				</div>
  				<div class="col-md-4">
					<h2>Bestsellers</h2>
					<p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
					<p><a class="btn btn-secondary" href="#" role="button">Check bestsellers »</a></p>
  				</div>
  				<div class="col-md-4">
  					<h2>Book rankings</h2>
  					<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
  					<p><a class="btn btn-secondary" href="#" role="button">Check book rankings »</a></p>
  				</div>
			</div>

  		<hr>

		</div> <!-- /container -->

	</main>

    <script src="<c:url value="/resources/js/jquery-3.3.1.slim.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/popper.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/support.js" />"></script>
  
</body></html>