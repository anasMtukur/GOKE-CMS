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
				<div class="section__content section__content--p30">
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-12">
								
								<div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                    <div class="au-card-title" style="background-image:url('images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi zmdi-account-calendar"></i>Client KYC Archiving System</h3>
                                        <button class="au-btn-plus" data-toggle="modal" data-target="#mediumModal">
                                            <i class="zmdi zmdi-plus"></i>
                                        </button>
                                    </div>
                                    <div class="au-inbox-wrap js-inbox-wrap">
                                        <div class="au-message js-list-load">
                                            <div class="au-message__noti">
                                                <p>
                                                	Total Clients: 
                                                    <strong>
                                                    	<c:out value="${listClients.size()}" />
                                                    </strong>
                                                </p>
                                            </div>
                                            <div class="au-message-list">
                                            
                                            	<c:forEach var="client" items="${listClients}">
                                                <div class="au-message__item-noclick">
                                                    <div class="au-message__item-inner">
                                                        <div class="au-message__item-text" style="min-width: 70%; max-width: 70%;">
                                                            <div class="avatar-wrap">
                                                                <div class="avatar">
                                                                    <img src="images/icon/client.png" alt='<c:out value="${client.name}" />'>
                                                                </div>
                                                            </div>
                                                            <div class="text">
                                                                <h5 class="name d-flex w-100 justify-content-between align-items-center">
                                                                	<c:out value="${client.name}" />
                                                                	<span>
	                                                                	<a class="btn btn-link" 
	                                                                		href='/client-file/<c:out value="${client.id}" />/'>
	                                                                		<i class="zmdi zmdi-folder-person"></i>
	                                                                	</a>
	                                                                	
	                                                                	<button 
	                                                                		class="btn btn-link" 
	                                                                		data-cid='<c:out value="${client.id}" />'
	                                                                		data-cname='<c:out value="${client.name}" />'
	                                                                		data-cnum='<c:out value="${client.number}" />'
	                                                                		data-email='<c:out value="${client.email}" />'
	                                                                		data-toggle="modal" 
	                                                                		data-target="#manageModal">
	                                                                		<i class="zmdi zmdi-edit"></i>
	                                                                	</button>
                                                                	</span>
                                                                </h5>
                                                                <p><c:out value="${client.number}" /></p>
                                                            </div>
                                                        </div>
                                                        <div class="au-message__item-time d-none d-md-inline">
                                                            <span><c:out value="${client.createdAt}" /></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                </c:forEach>
                                                
                                            </div>
                                            <!-- <div class="au-message__footer">
                                                <button class="au-btn au-btn-load js-load-btn">load more</button>
                                            </div> -->
                                    	</div>
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
	
	<!-- modal medium -->
	<div class="modal fade" id="mediumModal" tabindex="-1" role="dialog" aria-labelledby="mediumModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="mediumModalLabel">Add New Client</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/client/add" method="post">
					<div class="modal-body">
						<div class="form-group">
                            <label>Client Name</label>
                            <input class="au-input au-input--full" type="text" name="name" placeholder="Client Name">
                        </div>
                        <div class="form-group">
                            <label>Client Number</label>
                            <input class="au-input au-input--full" type="text" name="clientno" placeholder="Client Number e.g: CL0001">
                        </div>
                        <div class="form-group">
                            <label>Client Email</label>
                            <input class="au-input au-input--full" type="email" name="email" placeholder="Client Email e.g: client@test.com">
                            <small>This email will be used to notify the merchant on expired documents</small>
                        </div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
						<button type="submit" name="submit" value="add" class="btn btn-primary">Confirm</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end modal medium -->
	
	<!-- modal medium -->
	<div class="modal fade" id="manageModal" tabindex="-1" role="dialog" aria-labelledby="manageModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="manageModalLabel">Update Client</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/client/update" method="post">
					<div class="modal-body">
						<div class="form-group">
                            <label>Client ID</label>
                            <input class="au-input au-input--full" type="text" name="id" id="cid" required readonly />
                        </div>
						<div class="form-group">
                            <label>Client Name</label>
                            <input class="au-input au-input--full" type="text" name="name" id="cname" placeholder="Client Name">
                        </div>
                        <div class="form-group">
                            <label>Client Number</label>
                            <input class="au-input au-input--full" type="text" name="clientno"  id="cnum" placeholder="Client Number e.g: CL0001">
                        </div>
                        <div class="form-group">
                            <label>Client Email</label>
                            <input class="au-input au-input--full" type="email" name="email" id="email" placeholder="Client Email e.g: client@test.com">
                            <small>This email will be used to notify the merchant on expired documents</small>
                        </div>
                        
                        <button type="submit" name="submit" value="update" class="btn btn-block btn-primary">Update</button>
					</div>
				</form>
				<c:if test="${authUser.superAdmin}">
				<div class="modal-footer">
					<form action="/client/delete" method="post">
						<input class="au-input au-input--full" type="hidden" name="id" id="c_id" required readonly />
						<button type="submit" class="btn btn-danger">Delete</button>
					</form>
				</div>
				</c:if>
			</div>
		</div>
	</div>
	<!-- end modal medium -->

	<%@include file="fragment-footer-tags.jsp" %>
	<script>
		$('#manageModal').on('shown.bs.modal', function ( event ) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			  
		  	$('#cid').val( button.data('cid') )
		  	$('#c_id').val( button.data('cid') )
		  	$('#cname').val( button.data('cname') )
		  	$('#cnum').val( button.data('cnum') )
		  	$('#email').val( button.data('email') )
		})
	</script>
</body>

</html>
<!-- end document-->
