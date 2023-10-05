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
								
								<div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
                                    <div class="au-card-title" style="background-image:url('images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi zmdi-account-calendar"></i>User Account
                                        </h3>
                                        
                                    </div>
                                    <div class="au-inbox-wrap">
                                        <div class="au-message">
                                            <form action="/account/update" method="post">
												<input type="hidden" id="id" name="id" value="<c:out value='${activeUser.id}' />" class="form-control">
	                                            <div class="au-message-list">
	                                                <div class="container-fluid">
		                                            	<div class="row">
		                                            		<div class="col-sm-12 p-4">
		                                            			<div class="form-group">
										                            <label for="name" class=" form-control-label">Fullname</label>
										                            <input type="text" id="name" name="name" value="<c:out value='${activeUser.name}' />" class="form-control">
										                        </div>
										                        <div class="form-group">
										                            <label for="email" class=" form-control-label">Email Address</label>
										                            <input type="text" id="email" name="email" autocomplete="none" value="<c:out value='${activeUser.username}' />" placeholder="user@example.com" class="form-control">
										                            <small>This will be used as username for login</small>
										                        </div>
										                        <div class="row form-group">
										                             <div class="col col-md-12">
										                             	 <label for="password" class=" form-control-label">Password</label>
										                                 <div class="input-group">
										                                     <input type="password" id="password" name="password" autocomplete="new-password" placeholder="Password" class="form-control">
										                                     <!-- <div class="input-group-btn">
										                                         <button type="button" class="btn btn-primary">Generate <i class="ml-2 fa fa-refresh"></i></button>
										                                     </div> -->
										                                 </div>
										                                 <small>Use a strong password and keep it safe. You wont be able to view this password again.</small>
										                             </div>
										                         </div>
		                                            		</div>
		                                            	</div>
		                                            </div>
	                                            </div>
	                                            <div class="au-message__footer">
	                                            	<div class="container-fluid">
		                                            	<div class="row">
		                                            		<div class="col-sm-12 text-right px-4">
		                                            			<button type="submit" name="submit" value="add" class="au-btn btn-primary">Update Account Info <i class="ml-2 fa fa-check-circle"></i></button>
		                                            			<a href="/signout" class="au-btn btn-danger">Logout <i class="ml-2 fa fa-lock"></i></a>
		                                            		</div>
		                                            	</div>
		                                            </div>
	                                            </div>
	                                    	</form>
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

	<%@include file="fragment-footer-tags.jsp" %>

</body>

</html>
<!-- end document-->
