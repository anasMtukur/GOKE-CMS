<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
 "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%@include file="fragment-header-tags.jsp" %>

<body class="animsition">
	<div class="page-wrapper" style="padding-bottom: 0;">
		<%@include file="fragment-header-menu.jsp" %>

		<!-- PAGE CONTENT-->
        <div class="page-content--bgf7">

			<!-- MAIN CONTENT-->
			<div class="main-content">
			
				<!-- WELCOME-->
	            <section class="welcome  section__content--p30">
	                <div class="container-fluid">
	                    <div class="row">
	                        <div class="col-md-12">
	                            <h1 class="title-4">
	                            	Client KYC Archiving System
	                            </h1>
	                            <hr class="line-seprate">
	                        </div>
	                    </div>
	                </div>
	            </section>
	            <!-- END WELCOME-->
            
				<!-- STATISTIC-->
	            <section class="statistic statistic2 m-t-30 section__content--p30">
	                <div class="container-fluid">
	                    <div class="row">
	                        <div class="col-md-12 col-lg-4">
	                            <a href="/client?q=STAFF" class="w-100 statistic__item statistic__item--green">
	                                <h2 class="number"><c:out value='${counters.STAFF}' /></h2>
	                                <span class="desc">STAFF</span>
	                                <div class="icon">
	                                    <i class="zmdi zmdi-account-o"></i>
	                                </div>
	                            </a>
	                        </div>
	                        <div class="col-md-12 col-lg-4">
	                            <a href="/client?q=MERCHANT" class="w-100 statistic__item statistic__item--orange">
	                                <h2 class="number"><c:out value='${counters.MERCHANT}' /></h2>
	                                <span class="desc">MERCHANTS</span>
	                                <div class="icon">
	                                    <i class="zmdi zmdi-shopping-cart"></i>
	                                </div>
	                            </a>
	                        </div>
	                        <div class="col-md-12 col-lg-4">
	                            <a href="/client?q=AGENT" class="w-100 statistic__item statistic__item--blue">
	                                <h2 class="number"><c:out value='${counters.AGENT}' /></h2>
	                                <span class="desc">AGENTS</span>
	                                <div class="icon">
	                                    <i class="zmdi zmdi-calendar-note"></i>
	                                </div>
	                            </a>
	                        </div>
	                        
	                    </div>
	                </div>
	            </section>
	            <!-- END STATISTIC-->
			</div>
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	
	<%@include file="fragment-footer-tags.jsp" %>
</body>

</html>
<!-- end document-->
