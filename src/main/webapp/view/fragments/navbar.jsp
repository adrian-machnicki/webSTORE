<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="navbar navbar-dark navbar-expand-md sticky-top bg-dark flex-md-nowrap p-0">

	<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${request.contextPath}/home">webSTORE - your book store</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    
	     <div class="collapse navbar-collapse" id="navbarCollapse">
	    	<ul class="navbar-nav mr-auto">
	    	
	    		<li class="nav-item <c:if test="${ navbarTab == 'HOME' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/home">
						<spring:message code="nav.home" />
						<span class="sr-only">(current)</span>
					</a>
				</li>
				
				<li class="nav-item <c:if test="${ navbarTab == 'BOOKS' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/books">
						<spring:message code="nav.books" />
					</a>
				</li>
				
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item <c:if test="${ navbarTab == 'PROFILE' }">active</c:if>">
						<a class="nav-link" href="${request.contextPath}/user">
							<spring:message code="nav.profile" />
						</a>
					</li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item <c:if test="${ navbarTab == 'ADMIN' }">active</c:if>">
						<a class="nav-link" href="${request.contextPath}/admin">
							<spring:message code="nav.admin" />
						</a>
					</li>
				</sec:authorize>
				
				<li class="nav-item <c:if test="${ navbarTab == 'HELP' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/information">
						<spring:message code="nav.help" />
					</a>
				</li>
	    	</ul>
	    	
	    	<ul class="navbar-nav px-3 float-right">
	    	
	    		<spring:url var="english" value="">
	    			<spring:param name="language" value="en" />
	    		</spring:url>
	    		
	    		<spring:url var="polish" value="">
	    			<spring:param name="language" value="pl" />
	    		</spring:url>
	    		
	    		<spring:url var="german" value="">
	    			<spring:param name="language" value="de" />
	    		</spring:url>
	    	
	    	
      			<li class="nav-item dropdown">
      				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink"
      					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      					<span class="oi oi-globe"></span>
      					<spring:message code="nav.lang" />
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="${english}">English</a>
						<a class="dropdown-item" href="${polish}">Polski</a>
						<a class="dropdown-item" href="${german}">Deutsch</a>
					</div>
				</li>
			</ul>
	    	
	    	<ul class="navbar-nav px-3">
				<li class="nav-item <c:if test="${ navbarTab == 'CART' }">active</c:if>">
					<a class="nav-link" href="${request.contextPath}/cart">
						<spring:message code="nav.cart" />
					</a>
				</li>
			</ul>
			
			<sec:authorize access="isAnonymous()">
		    	<ul class="navbar-nav px-3">
	        		<li class="nav-item">
						<a class="nav-link" href="${request.contextPath}/login">
							<spring:message code="nav.login" />
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${request.contextPath}/register">
							<spring:message code="nav.signup" />
						</a>
					</li>
	      		</ul>
      		</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
		    	<ul class="navbar-nav px-3">
		    	
		    		<li class="nav-item">
		    			<span class="nav-link">
		    				<spring:message code="nav.signedIn" />
 							<b><sec:authentication property="principal.username" /></b>
            			</span>
					</li> 
		    	
		    	
	        		<li class="nav-item text-nowrap">
	          			<form action="${request.contextPath}/logout" method="post" id="logoutForm">
	          				<input type="hidden"
									name="${_csrf.parameterName}"
									value="${_csrf.token}" />
	          				<a href="javascript:void(null);" class="nav-link" onclick="submitLogout()">
	          					<spring:message code="nav.logout" />
	          				</a>
	          			</form>
	        		</li>
	      		</ul>
      		</sec:authorize>
      		
	    </div>   
</nav>