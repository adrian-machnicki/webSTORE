<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="https://v4-alpha.getbootstrap.com/favicon.ico">

<title>Admin Panel</title>

<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/dashboard.css" />">
<body>

	<jsp:include page="${request.contextPath}/view/navbar"></jsp:include>

	<div class="container-fluid">
		<div class="row">

			<!-- INCLUDE SIDEBAR HERE -->
			<jsp:include page="${request.contextPath}/view/admin-sidebar"></jsp:include>

			<main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
			<h1>Dashboard</h1>

				<section class="row text-center placeholders">
					<div class="col-6 col-sm-3 placeholder">
						<img src="data:image/gif;base64,R0lGODlhAQABAIABAAJ12AAAACwAAAAAAQABAAACAkQBADs="
							width="200" height="200" class="img-fluid rounded-circle"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<div class="text-muted">Something else</div>
					</div>
					<div class="col-6 col-sm-3 placeholder">
						<img src="data:image/gif;base64,R0lGODlhAQABAIABAADcgwAAACwAAAAAAQABAAACAkQBADs="
							width="200" height="200" class="img-fluid rounded-circle"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-6 col-sm-3 placeholder">
						<img src="data:image/gif;base64,R0lGODlhAQABAIABAAJ12AAAACwAAAAAAQABAAACAkQBADs="
							width="200" height="200" class="img-fluid rounded-circle"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-6 col-sm-3 placeholder">
						<img src="data:image/gif;base64,R0lGODlhAQABAIABAADcgwAAACwAAAAAAQABAAACAkQBADs="
							width="200" height="200" class="img-fluid rounded-circle"
							alt="Generic placeholder thumbnail">
						<h4>Label</h4>
						<span class="text-muted">Something else</span>
					</div>
				</section>
			</main>
		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/tether.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js" />"></script>

</body>
</html>

