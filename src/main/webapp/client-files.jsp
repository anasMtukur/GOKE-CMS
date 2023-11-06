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
                                    <div class="au-card-title" style="background-image:url('/images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi zmdi-account-calendar"></i>
                                            <c:out value="${clientInfo.name}" />
                                        </h3>
                                        <button class="au-btn-plus" data-toggle="modal" data-target="#mediumModal">
                                            <i class="zmdi zmdi-plus"></i>
                                        </button>
                                    </div>
                                    <div class="au-inbox-wrap js-inbox-wrap">
                                        <div class="au-message js-list-load">
                                            <div class="au-message__noti">
                                                <p>Client Number: <c:out value="${clientInfo.number}" /></p>
                                            </div>
                                            <div class="au-message-list">
                                            
                                            	<c:forEach var="file" items="${listClientFiles}">
                                                <div class="au-message__item-noclick">
                                                    <div class="au-message__item-inner">
                                                        <div class="au-message__item-text" style="min-width: 70%;">
                                                            <div class="avatar-wrap">
                                                                <div class="avatar">
                                                                    <img src="/images/icon/file.png" alt='file-icon'>
                                                                </div>
                                                            </div>
                                                            <div class="text">
                                                                <h5 class="name d-flex w-100 justify-content-between align-items-center">
                                                                	<c:out value="${file.title}" />
                                                                	<span>
	                                                                	<a class="btn btn-link" 
	                                                                		href='download-single?reference=<c:out value="${file.id}" />'>
	                                                                		<i class="zmdi zmdi-download"></i>
	                                                                	</a>
	                                                                	
	                                                                	<button 
	                                                                		class="btn btn-link" 
	                                                                		data-id='<c:out value="${file.id}" />'
	                                                                		data-title='<c:out value="${file.title}" />'
	                                                                		data-source='<c:out value="${file.source}" />'
	                                                                		data-expiry='<c:out value="${file.expiry}" />'
	                                                                		data-toggle="modal" 
	                                                                		data-target="#manageModal">
	                                                                		<i class="zmdi zmdi-edit"></i>
	                                                                	</button>
                                                                	</span>
                                                                </h5>
                                                                <p><c:out value="${file.source}" /></p>
                                                                <small>
	                                                                <c:if test="${file.hasExpiry}">
																	    This document expires on 
																	    <span class="text-danger">
																	    	<c:out value="${file.expiry}" />
																	    </span>
																	</c:if>
																</small>
                                                            </div>
                                                        </div>
                                                        <div class="au-message__item-time d-none d-md-inline">
                                                            <span><c:out value="${file.createdAt}" /></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                </c:forEach>
                                                
                                            </div>
                                            <div class="au-message__footer">
                                                <a href="download" class="au-btn au-btn-icon au-btn--blue">
                                                	<i class="zmdi zmdi-download"></i>
                                                	Download All
                                                </a>
                                            </div>
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
					<h5 class="modal-title" id="mediumModalLabel">Add File</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="add" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
                            <label>Document Type</label>
                            <!-- <input class="au-input au-input--full" type="text" name="title" placeholder="FileType Name"> -->
                            <select  id="select" class="form-control" name="title" required>
                            	<option value=''> Select Document Type </option>
                            	<c:forEach var="doctype" items="${listDoctypes}">
	                            	<option value='<c:out value="${doctype.title}" />'>
	                            		<c:out value="${doctype.title}" />
	                            	</option>
                            	</c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Upload File</label>
                            <input class="au-input au-input--full" type="file" name="file" />
                        </div>
                        <div class="form-group">
	                        <label>
	                            <input type="checkbox" value="true" name="hasexpiry"> This document have expiration date
	                        </label>
                        </div>
                        <div class="form-group">
                             <label for="expiry" class="control-label mb-1">Expiration</label>
                             <input id="expiry" name="expiry" type="date" class="form-control" value="" data-val="true" data-val-required="Please enter the document expiration" data-val-cc-exp="Please enter a valid month and year">
                             <span class="help-block" data-valmsg-for="cc-exp" data-valmsg-replace="true"></span>
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
					<h5 class="modal-title" id="manageModalLabel">Update File</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="update" method="post" enctype="multipart/form-data">
					<div class="modal-body">
						<input class="au-input au-input--full" type="hidden" name="source"  id="fsource">
						<input class="au-input au-input--full" type="hidden" name="operation" value="update">
						
						<div class="form-group">
                            <label>Client Document ID</label>
                            <input class="au-input au-input--full" type="text" name="id" id="cfid" required readonly />
                        </div>
						<div class="form-group">
                            <label>Document Type</label>
                            <input class="au-input au-input--full" type="text" name="title" id="cname">
                        </div>
                        <div class="form-group">
	                        <label>
	                            <input type="checkbox" name="hasexpiry" value="true"> This document have expiration date
	                        </label>
                        </div>
                        <div class="form-group">
                             <label for="expiry" class="control-label mb-1">Expiration</label>
                             <input id="expiry" name="expiry" type="date" class="form-control" value="" data-val="true" data-val-required="Please enter the document expiration" data-val-cc-exp="Please enter a valid month and year">
                             <span class="help-block" data-valmsg-for="cc-exp" data-valmsg-replace="true"></span>
                         </div>
                        <div class="form-group">
                            <label>Upload File</label>
                            <input class="au-input au-input--full" type="file" name="file" />
                        </div>
                        
                        <button type="submit" name="submit" value="update" class="btn btn-block btn-primary">Update</button>
					</div>
				</form>
				<div class="modal-footer">
					<form action="delete" method="post">
						<input class="au-input au-input--full" type="hidden" name="id" id="cf_id" required readonly />
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
			console.log(button.data('expiry'));
		  	$('#cfid').val( button.data('id') )
		  	$('#cf_id').val( button.data('id') )
		  	$('#cname').val( button.data('title') )
		  	$('#fsource').val( button.data('source') )
		  	$('#expiry').val( button.data('expiry') )
		})
	</script>
</body>

</html>
<!-- end document-->
