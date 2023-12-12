<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
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
								
								<div class="user-data m-b-30" style="border-radius: 25px;">
									<div class="col-sm-12">
										<div class="d-flex justify-content-between align-items-center">
		                                    <h3 class="title-3" style="padding-left: 20px;">
		                                        <i class="zmdi zmdi-account-calendar"></i>
		                                        system users
		                                    </h3>
		                                    <button data-toggle="modal" data-target="#addModal" class="au-btn au-btn-icon au-btn--green au-btn--small">
	                                            <i class="zmdi zmdi-plus"></i>add User
	                                        </button>
	                                	</div>
	                                </div>
                                    <div class="table-responsive table-data">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <!-- <td>
                                                        <label class="au-checkbox">
                                                            <input type="checkbox">
                                                            <span class="au-checkmark"></span>
                                                        </label>
                                                    </td> -->
                                                    <td>name</td>
                                                    <td>Blocked</td>
                                                    <td>Role</td>
                                                    <c:if test="${authUser.superAdmin}">
                                                    <td></td>
                                                    </c:if>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="user" items="${listUser}">
                                                <tr>
                                                    <!-- <td>
                                                        <label class="au-checkbox">
                                                            <input type="checkbox">
                                                            <span class="au-checkmark"></span>
                                                        </label>
                                                    </td> -->
                                                    <td>
                                                        <div class="table-data__info">
                                                            <h6><c:out value="${user.name}" /></h6>
                                                            <span>
                                                                <a href="javascript:;"><c:out value="${user.username}" /></a>
                                                            </span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                    	<c:if test="${user.blocked}">
                                                        	<span class="role admin">Yes</span> 
                                                        </c:if>
                                                        <c:if test="${!user.blocked}"> 
                                                        	<span class="role member">No</span>  
                                                        </c:if>
                                                    </td>
                                                    
                                                    <td>
                                                    	<c:if test="${user.superAdmin}">
                                                        	<span class="role admin">Super User</span> 
                                                        </c:if>
                                                        <c:if test="${user.businessUnit}"> 
                                                        	<span class="role member">Business Unit</span>  
                                                        </c:if>
                                                        <c:if test="${user.hr}"> 
                                                        	<span class="role member">HR</span>  
                                                        </c:if>
                                                        <c:if test="${user.admin}"> 
                                                        	<span class="role member">Admin</span>  
                                                        </c:if>
                                                        <c:if test="${user.finance}"> 
                                                        	<span class="role member">Finance</span>  
                                                        </c:if>
                                                    </td>
                                                    <c:if test="${authUser.superAdmin}">
                                                    <td class="text-right">
                                                        <div class="table-data-feature">
                                                        	<button class="item" 
                                                        		data-toggle="modal" 
                                                        		data-target="#editModal"
                                                        		data-id='${user.id}'
                                                        		data-name='${user.name}'
                                                        		data-email='${user.username}'
                                                        		data-super='${user.superAdmin}'
                                                        		data-business='${user.businessUnit}'
                                                        		data-admin='${user.admin}'
                                                        		data-hr='${user.hr}'
                                                        		data-finance='${user.finance}'
                                                        		data-blocked='${user.blocked}'
                                                        		title="Edit">
	                                                            <i class="zmdi zmdi-edit text-primary"></i>
	                                                        </button>
	                                                        
	                                                        
                                                        	<form action="/user/toggle-block" method="post">
	                                                        	<input type="hidden" name="id" value='<c:out value="${user.id}" />'>
	                                                        	<c:if test="${user.blocked}">
			                                                        <button name="submit" class="item m-r-1" data-toggle="tooltip" data-placement="top" title="Unblock">
			                                                            <i class="zmdi zmdi-check text-danger"></i>
			                                                        </button>
		                                                        </c:if>
		                                                        <c:if test="${!user.blocked}"> 
			                                                        <button name="submit" class="item mr-1" data-toggle="tooltip" data-placement="top" title="Block">
			                                                            <i class="zmdi zmdi-block text-danger"></i>
			                                                        </button>
		                                                        </c:if>
		                                                    </form>
	                                                        
	                                                        <form action="/user/delete" method="post">
	                                                        	<input type="hidden" name="id" value='<c:out value="${user.id}" />'>
		                                                        <button type="submit" class="item" data-toggle="tooltip" data-placement="top" title="Delete">
		                                                            <i class="zmdi zmdi-delete text-danger"></i>
		                                                        </button>
		                                                    </form>
	                                                    </div>
                                                    </td>
                                                    </c:if>
                                                </tr>
                                                </c:forEach>
                                                
                                            </tbody>
                                        </table>
                                    </div>
                                    
                                </div>
                                <!-- END USER DATA-->
								
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<!-- END PAGE CONTAINER-->
		
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<form action="/user/add" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="addModalLabel">Add New User</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
	                            <label for="company" class=" form-control-label">Fullname</label>
	                            <input type="text" name="name" placeholder="John Doe" class="form-control">
	                        </div>
	                        <div class="form-group">
	                            <label for="vat" class=" form-control-label">Email Address</label>
	                            <input type="text" name="email" autocomplete="none" placeholder="user@example.com" class="form-control">
	                            <small>This will be used as username for login</small>
	                        </div>
	                        <div class="row form-group">
	                             <div class="col col-md-12">
	                             	 <label for="company" class=" form-control-label">Password</label>
	                                 <div class="input-group">
	                                     <input type="password" autocomplete="new-password" name="password" placeholder="Password" class="form-control">
	                                     <!-- <div class="input-group-btn">
	                                         <button class="btn btn-primary">Generate</button>
	                                     </div> -->
	                                 </div>
	                                 <small>Use a strong password and keep it safe. You wont be able to view this password again.</small>
	                             </div>
	                         </div>
	                         <div class="row form-group">
	                         	<div class="col-12">
	                         		<label class="form-control-label">User Role</label>
	                         	</div>
	                         	<div class="col">
		                         	<div class="form-check-inline form-check">
		                         		<c:forEach var="role" items="${roles}" varStatus="loop">
	                                    <label for="inline-radio${loop.index}" class="form-check-label mr-4">
	                                        <input type="radio" name="role" id="inline-radio${loop.index}" value="${role}" class="form-check-input"> ${role}
	                                    </label>
	                                    </c:forEach>
	                                </div>
                                </div>
		                        
	                    	 </div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							<button type="submit" name="submit" value="add" class="btn btn-primary">Confirm</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<form action="/user/update" method="post">
					<input type="hidden" id="id" name="id" class="form-control">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="editModalLabel">Update User</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
	                            <label for="name" class=" form-control-label">Fullname</label>
	                            <input type="text" id="name" name="name" placeholder="John Doe" class="form-control">
	                        </div>
	                        <div class="form-group">
	                            <label for="email" class=" form-control-label">Email Address</label>
	                            <input type="text" id="email" name="email" autocomplete="none" placeholder="user@example.com" class="form-control">
	                            <small>This will be used as username for login</small>
	                        </div>
	                        <div class="row form-group">
	                             <div class="col col-md-12">
	                             	 <label for="password" class=" form-control-label">Password</label>
	                                 <div class="input-group">
	                                     <input type="password" id="password" name="password" autocomplete="new-password" placeholder="Password" class="form-control">
	                                     <!-- <div class="input-group-btn">
	                                         <button class="btn btn-primary">Generate</button>
	                                     </div> -->
	                                 </div>
	                                 <small>Use a strong password and keep it safe. You wont be able to view this password again.</small>
	                             </div>
	                         </div>
	                         <div class="row form-group">
	                         	<div class="col-12">
	                         		<label class="form-control-label">User Role</label>
	                         	</div>
	                         	<div class="col">
		                         	<div class="form-check-inline form-check">
		                         		<c:forEach var="role" items="${roles}" varStatus="loop">
	                                    <label for="inline-radio${loop.index}" class="form-check-label mr-4">
	                                        <input type="radio" name="role" id="edit-role-${loop.index}" value="${role}" class="form-check-input"> ${role}
	                                    </label>
	                                    </c:forEach>
	                                </div>
                                </div>
	                    	 </div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
							<button type="submit" name="submit" value="update" class="btn btn-primary">Confirm</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>

	<%@include file="fragment-footer-tags.jsp" %>
	
	<script>
		$('#editModal').on('shown.bs.modal', function ( event ) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			  
		  	$('#id').val( button.data('id') )
		  	$('#name').val( button.data('name') )
		  	$('#email').val( button.data('email') )
		  	$('#edit-role-0').prop( "checked", button.data('super') );
		  	$('#edit-role-1').prop( "checked", button.data('business') );
		  	$('#edit-role-2').prop( "checked", button.data('hr') );
		  	$('#edit-role-3').prop( "checked", button.data('admin') );
		  	$('#edit-role-4').prop( "checked", button.data('finance') );
		})
	</script>

</body>

</html>
<!-- end document-->
