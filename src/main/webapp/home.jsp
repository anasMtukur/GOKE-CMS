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
	            
	            <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                    <div class="au-card-title" style="background-image:url('images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi zmdi-account-calendar"></i>
                                        	Client Categories    
                                        </h3>
                                    </div>
                                    <div class="au-task">
                                        <div class="au-task__title">
                                            <p>System KYC Categories</p>
                                        </div>
                                        <div class="au-task-list" style="height: auto;">
                                            <c:forEach var="item" items="${counters}">
                                            <div class="au-task__item au-task__item--primary">
                                           		<div class="au-task__item-inner d-flex justify-content-between">
                                                    <h5 class="task" style="width: 20%;">
                                                        ${item.category}
                                                    </h5>
                                                    <span class="time">${item.count}</span>
                                                    <c:if test='${authUser.superAdmin || 
                                                    	( item.category == "ADMIN" && authUser.admin ) || 
                                                    	( item.category == "STAFF" && authUser.hr ) || 
                                                    	( item.category == "MERCHANT" || item.category == "AGENT" && authUser.businessUnit ) || 
                                                    	( item.category == "FINANCE" && authUser.finance )}'>
                                                    <div>
	                                                    <a href="/client?q=<c:out value='${item.category}' />" title="Clients" class="au-btn-round">
	                                            			<i class="fas fa-user"></i>
	                                        			</a>
	                                        			<a href="/doctype?q=<c:out value='${item.category}' />" title="Document Types" class="au-btn-round">
	                                            			<i class="fa fa-files-o"></i>
	                                        			</a>
                                        			</div>
                                        			</c:if>
                                                </div>
                                            </div>
                                            </c:forEach>
                                        </div>
                                        <div class="au-task__footer"></div>
                                    </div>
                                </div>
                            </div>
            			</div>
                    </div>
                </div>
				
			</div>
		</div>
		<!-- END PAGE CONTAINER-->
	</div>
	
	<%@include file="fragment-footer-tags.jsp" %>
</body>

</html>
<!-- end document-->
