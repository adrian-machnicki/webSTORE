	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
	
		<ul class="nav flex-column">
		
			<li class="nav-item">
         		<a class="nav-link active" href="/#">
           			<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
           				stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
           					<path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
           					<polyline points="9 22 9 12 15 12 15 22"></polyline>
           			</svg>
           			Dashboard <span class="sr-only">(current)</span>
         		</a>
       		</li>
              
			<li class="nav-item">
				<a class="nav-link" href="${request.contextPath}/user/orders">
    				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
    					stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file">
    						<path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
    						<polyline points="13 2 13 9 20 9"></polyline>
    				</svg>
    				Your orders
  				</a>
			</li>
			
			<li class="nav-item">
  				<a class="nav-link" href="${request.contextPath}/user/books">
    				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
    					stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-layers">
    					<polygon points="12 2 2 7 12 12 22 7 12 2"></polygon>
    					<polyline points="2 17 12 22 22 17"></polyline>
    					<polyline points="2 12 12 17 22 12"></polyline>
    				</svg>
    				Your books
  				</a>
			</li>
			
			<li class="nav-item">
  				<a class="nav-link" href="${request.contextPath}/user/profile">
    				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
    					stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users">
    						<path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
    						<circle cx="9" cy="7" r="4"></circle>
    						<path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
    						<path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
    				</svg>
    				Profile
  				</a>
			</li>

		</ul>
	
	</div>
</nav>
