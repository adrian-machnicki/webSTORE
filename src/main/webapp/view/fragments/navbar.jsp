<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-dark navbar-expand-md sticky-top bg-dark flex-md-nowrap p-0">

	<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${request.contextPath}/home">webSTORE - your book store</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    
	     <div class="collapse navbar-collapse" id="navbarCollapse">
	    	<ul class="navbar-nav mr-auto">
	    	
	    		<li class="nav-item <c:if test="${ navbarTab == 'HOME' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
				</li>
				
				<li class="nav-item <c:if test="${ navbarTab == 'BOOKS' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/books">Books</a>
				</li>
				
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item <c:if test="${ navbarTab == 'PROFILE' }">active</c:if>">
						<a class="nav-link" href="${request.contextPath}/user">Profile</a>
					</li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item <c:if test="${ navbarTab == 'ADMIN' }">active</c:if>">
						<a class="nav-link" href="${request.contextPath}/admin">Admin</a>
					</li>
				</sec:authorize>
				
				<li class="nav-item <c:if test="${ navbarTab == 'HELP' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/information">Help</a>
				</li>
	    	</ul>
	    	
	    	<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
				<li class="nav-item <c:if test="${ navbarTab == 'CART' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/cart">Cart</a>
				</li>
			</ul>
			
			<sec:authorize access="isAnonymous()">
		    	<ul class="navbar-nav px-3">
	        		<li class="nav-item">
						<a class="nav-link" href="${request.contextPath}/login">Login</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${request.contextPath}/register">Sign up</a>
					</li>
	      		</ul>
      		</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
		    	<ul class="navbar-nav px-3">
		    	
		    		<li class="nav-item">
		    			<span class="nav-link">
 							Signed in as <b><sec:authentication property="principal.username" /></b>
            			</span>
					</li> 
		    	
		    	
	        		<li class="nav-item text-nowrap">
	          			<form action="${request.contextPath}/logout" method="post" id="logoutForm">
	          				<input type="hidden"
									name="${_csrf.parameterName}"
									value="${_csrf.token}" />
	          				<a href="javascript:void(null);" class="nav-link" onclick="submitLogout()">Logout</a>
	          			</form>
	        		</li>
	      		</ul>
      		</sec:authorize>
      		
	    </div>   
</nav>