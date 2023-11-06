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
                                            <i class="zmdi zmdi-account-calendar"></i>Client Management System</h3>
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
                                                    	<c:out value="${listDoctypes.size()}" />
                                                    </strong>
                                                </p>
                                            </div>
                                            <div class="au-message-list">
                                            
                                            	<c:forEach var="client" items="${listDoctypes}">
                                                <div class="au-message__item-noclick">
                                                    <div class="au-message__item-inner">
                                                        <div class="au-message__item-text" style="min-width: 70%;">
                                                            <div class="avatar-wrap">
                                                                <div class="avatar">
                                                                    <img src="images/icon/file.png" alt='<c:out value="${client.title}" />'>
                                                                </div>
                                                            </div>
                                                            <div class="text">
                                                                <h5 class="name d-flex w-100 justify-content-between align-items-center">
                                                                	<c:out value="${client.title}" />
                                                                	<span>	                                                                	
	                                                                	<button 
	                                                                		class="btn btn-link" 
	                                                                		data-cid='<c:out value="${client.id}" />'
	                                                                		data-title='<c:out value="${client.title}" />'
	                                                                		data-descr='<c:out value="${client.description}" />'
	                                                                		data-toggle="modal" 
	                                                                		data-target="#manageModal">
	                                                                		<i class="zmdi zmdi-edit"></i>
	                                                                	</button>
                                                                	</span>
                                                                </h5>
                                                                <p><c:out value="${client.description}" /></p>
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
					<h5 class="modal-title" id="mediumModalLabel">Add Document Type</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/doctype/add" method="post">
					<div class="modal-body">
						<div class="form-group">
                            <label>Document Type Title</label>
                            <input class="au-input au-input--full" type="text" name="title" placeholder="Document Title">
                        </div>
                        <div class="form-group">
                            <label>Document Type Description</label>
                            <textarea class="au-input au-input--full" rows="3" name="description" placeholder="Description of the document type"></textarea>
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
					<h5 class="modal-title" id="manageModalLabel">Medium Modal</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/doctype/update" method="post">
					<div class="modal-body">
						<div class="form-group">
                            <label>Client ID</label>
                            <input class="au-input au-input--full" type="text" name="id" id="cid" required readonly />
                        </div>
						<div class="form-group">
                            <label>Document Type Title</label>
                            <input class="au-input au-input--full" type="text" id="title" name="title" placeholder="Document Title">
                        </div>
                        <div class="form-group">
                            <label>Document Type Description</label>
                            <textarea class="au-input au-input--full" rows="3" id="descr" name="description" placeholder="Description of the document type"></textarea>
                        </div>
                        
                        <button type="submit" name="submit" value="update" class="btn btn-block btn-primary">Update</button>
					</div>
				</form>
				<div class="modal-footer">
					<form action="/doctype/delete" method="post">
						<input class="au-input au-input--full" type="hidden" name="id" id="c_id" required readonly />
						<button type="submit" class="btn btn-danger">Delete</button>
					</form>
				</div>
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
		  	$('#title').val( button.data('title') )
		  	$('#descr').val( button.data('descr') )
		})
	</script>
</body>

</html>
<!-- end document-->
