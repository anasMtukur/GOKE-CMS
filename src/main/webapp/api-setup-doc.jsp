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
							<div class="col-lg-3">
								<div class="card">
									<!-- <div class="card-header">
										<h4>Menu</h4>
									</div> -->
									<div class="card-body">
										<p class="text-muted m-b-15">
											API Setup and documentation to allow creating new API keys and allow integration with other systems 
										</p>

										<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
											<a class="nav-item nav-link active" 
												id="nav-home-tab" data-toggle="tab" 
												href="#nav-home" role="tab" 
												aria-controls="nav-home" aria-selected="true">
												API Documentation
											</a>
											
											<c:if test="${tokenList != null}">
											<a class="nav-item nav-link" 
												id="nav-api-tab" data-toggle="tab" 
												href="#nav-api" role="tab" 
												aria-controls="nav-api"
											 	aria-selected="false">
											 	API Access Token
											</a>
											</c:if>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-9">
								<div class="card">
									<!-- <div class="card-header">
										<h4>Content</h4>
									</div> -->
									<div class="card-body">
										<div class="default-tab">
											
											<div class="tab-content pl-3 pt-2" id="nav-tabContent" style="min-height: 75vh;">
												<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
													<h2 class="pb-2 display-5">API Documentation</h2>
													<h4 class="pb-2 display-5">Base URL: /api/v1</h4>
													
													<div class="row mt-4">
							                            <div class="col-lg-12">
							                            	<div class="au-card recent-report">
							                                    <div class="au-card-inner">
							                                        <h4 class="pb-6 display-5">Authentication</h4>
							                                        <div class="chart-info mt-4">
							                                            <p>
							                                            	All API endpoints make use of Authorization Tokens to authenticate incoming requests. 
							                                            	<br /><br />
							                                            	You can generate a token under API Access Token.
							                                            	<br /><br />
							                                            	The generated token must be added to all request headers under authorization bearer token 
							                                            	<br /><br />
							                                            	Please remember to include the space character between bearer and the actual token
							                                            </p>
							                                        </div>
							                                        <div class="recent-report__chart">
							                                        
							                                        	<pre>"Authorization": "Bearer 1234567890"</pre>
							                                            
							                                        </div>
							                                    </div>
							                                </div>
							                                
							                                <div class="au-card recent-report">
							                                    <div class="au-card-inner">
							                                        <h4 class="pb-2 display-5">Endpoint: <mark>/clients</mark></h4>
							                                        <div class="chart-info">
							                                            <p>List of all clients</p>
							                                        </div>
							                                        <div class="recent-report__chart">
							                                        
<pre>
[
	{
		"id":1,
		"name":"Anas M Tukur",
		"number":"CL0001",
		"createdAt":"Sep 26, 2023, 2:15:26 PM",
		"updatedAt":"Sep 26, 2023, 2:15:26 PM"
	},
	{
		"id":2,
		"name":"Dalas County",
		"number":"CL0002",
		"createdAt":"Oct 3, 2023, 9:34:25 AM",
		"updatedAt":"Oct 3, 2023, 9:34:25 AM"
	}
]
</pre>
							                                            
							                                        </div>
							                                    </div>
							                                </div>
							                                
							                                <div class="au-card recent-report">
							                                    <div class="au-card-inner">
							                                        <h4 class="pb-2 display-5">Endpoint: <mark>/add-client</mark></h4>
							                                        <div class="chart-info">
							                                            <p>
							                                            	Add new client
							                                            </p>
							                                        </div>
							                                        <div class="recent-report__chart">
							                                        	<h5 class="mb-4">Request Body</h5>
<pre>
{
	"name":"Anas M Tukur",
	"number":"CL0001",
	"email":"anas@mail.com"
}
</pre>
							                                        </div>
							                                        <div class="recent-report__chart">
							                                        	<h5 class="mb-4">Response Body</h5>
<pre>
{
	"id":1,
	"name":"Anas M Tukur",
	"number":"CL0001",
	"email":"anas@mail.com",
	"createdAt":"Sep 26, 2023, 2:15:26 PM",
	"updatedAt":"Sep 26, 2023, 2:15:26 PM"
}
</pre>
							                                        </div>
							                                    </div>
							                                </div>
							                                
							                                <div class="au-card recent-report">
							                                    <div class="au-card-inner">
							                                        <h4 class="pb-2 display-5">Endpoint: <mark>/clients/<i>{queryParameter}</i>/<i>{parameterValue}</i></mark></h4>
							                                        <div class="chart-info">
							                                            <p>
							                                            	Details of client whose query parameter field matches query parameter value. 
							                                            	<br /><br />Query Parameter can only be one of the following:
								                                    	</p>
							                                        </div>
							                                        <div class="chart-info">
							                                            <ul>
							                                            	<li>
							                                            		<b>id:</b>
							                                            		ID of the client
							                                            	</li>
							                                            	<li>
							                                            		<b>number:</b>
							                                            		Client merchant number
							                                            	</li>
							                                            </ul>
							                                        </div>
							                                        
							                                        <div class="chart-info">
							                                            <p>
							                                            	<strong>Example: </strong>
							                                            	<i>http://78.110.169.252:9191/api/v1/clients/<b>id</b>/<b>1</b></i>
							                                            </p>
							                                        </div>
							                                        
							                                        <div class="recent-report__chart">
<pre>
{
	"client":{
		"id":1,
		"name":"Anas M Tukur",
		"number":"CL0001",
		"email":"anas@mail.com",
		"createdAt":"Sep 26, 2023, 2:15:26 PM",
		"updatedAt":"Sep 26, 2023, 2:15:26 PM"
	}
}
</pre>
							                                        </div>
							                                    </div>
							                                </div>							                                
							                                
							                                <div class="au-card recent-report">
							                                    <div class="au-card-inner">
							                                        <h4 class="pb-2 display-5">Endpoint: <mark>/clients/<i>{queryParameter}</i>/<i>{parameterValue}</i>/files</mark></h4>
							                                        <div class="chart-info">
							                                            <p>
							                                            	Details and files of client whose query parameter field matches query parameter value
							                                            	<br /><br />Query Parameter can only be one of the following:
							                                            </p>
							                                        </div>
							                                        <div class="chart-info">
							                                            <ul>
							                                            	<li>
							                                            		<b>id:</b>
							                                            		ID of the client
							                                            	</li>
							                                            	<li>
							                                            		<b>number:</b>
							                                            		Client merchant number
							                                            	</li>
							                                            </ul>
							                                        </div>
							                                        
							                                        <div class="chart-info">
							                                            <p>
							                                            	<strong>Example: </strong>
							                                            	<i>http://78.110.169.252:9191/api/v1/clients/<b>id</b>/<b>1</b>/files</i>
							                                            </p>
							                                        </div>
							                                        <div class="recent-report__chart">
<pre>
{
	"client":{
		"id":1,
		"name":"Anas M Tukur",
		"number":"CL0001",
		"email":"anas@mail.com",
		"createdAt":"Sep 26, 2023, 2:15:26 PM",
		"updatedAt":"Sep 26, 2023, 2:15:26 PM"
	},
	"documents":[
		{
			"title":"Nepa Bill",
			"source":"http://localhost:1212/client-file/download-single?reference=1",
			"createdAt":"Sep 26, 2023, 2:15:44 PM",
			"updatedAt":"Sep 26, 2023, 2:15:44 PM"
		},
		{
			"title":"Certificate",
			"source":"http://localhost:1212/client-file/download-single?reference=2",
			"createdAt":"Sep 26, 2023, 2:15:55 PM",
			"updatedAt":"Sep 26, 2023, 2:15:55 PM"
		},
		{
			"title":"Water Bill",
			"source":"http://localhost:1212/client-file/download-single?reference\u003d3",
			"createdAt":"Sep 26, 2023, 2:16:26 PM",
			"updatedAt":"Sep 26, 2023, 2:16:26 PM"
		}
	]
}
</pre>
							                                        </div>
							                                    </div>
							                                </div>
							                            </div>
							                    	</div>
												</div>
												
												<div class="tab-pane fade" id="nav-api" role="tabpanel" aria-labelledby="nav-api-tab">
													<div class="overview-wrap">
					                                    <h2 class="title-1">overview</h2>
					                                    <a href="/api-setup-doc/add" class="au-btn au-btn-icon au-btn--blue">
					                                        <i class="zmdi zmdi-plus"></i>Generate New Token</a>
					                                </div>
													
													<div class="row m-t-20">
							                            <div class="col-md-12">
							                                <!-- DATA TABLE-->
							                                <div class="table-responsive m-b-4">
							                                    <table class="table table-borderless table-data3">
							                                        <thead>
							                                            <tr>
							                                                <th width="60%">Token</th>
							                                                <th>Created At</th>
							                                                <th>
							                                                	<i class="fa fa-cog"></i>
																			</th>
							                                            </tr>
							                                        </thead>
							                                        <tbody>
							                                        	<c:forEach var="item" items="${tokenList}">
							                                            <tr>
							                                                <td>
							                                                	<div class="d-flex justify-content-between align-items-center">
							                                                		<h6 id='<c:out value="${item.id}" />'>
							                                                			<c:out value="${item.token}" />
							                                                		</h6>
							                                                		
							                                                		<span>
							                                                			<button onclick="copy_data('<c:out value="${item.id}" />')" class="btn btn-link"> <i class="fa fa-copy"></i> </button>
							                                                		</span>
							                                                	</div>
							                                                </td>
							                                                <td>
							                                                	<c:out value="${item.createdAt}" />
							                                                </td>								                                                
							                                                <td>
							                                                	<div class="table-data-feature">
							                                                		<form action="/api-setup-doc/delete" method="post">
							                                                			<input type="hidden" name="reference" value='<c:out value="${item.id}" />' />
								                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Delete">
								                                                            <i class="zmdi zmdi-delete"></i>
								                                                        </button>
							                                                        </form>
							                                                    </div>
							                                                </td>
							                                            </tr>
							                                            </c:forEach>
							                                        </tbody>
							                                    </table>
							                                </div>
							                                <!-- END DATA TABLE-->
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
				</div>
			</div>
			

		</div>
		<!-- END PAGE CONTAINER-->

	</div>

	<%@include file="fragment-footer-tags.jsp" %>
	<script>
		function copy_data(containerid) {
			  var range = document.createRange();
			  range.selectNode( document.getElementById( containerid ) ); //changed here
			  window.getSelection().removeAllRanges(); 
			  window.getSelection().addRange(range); 
			  document.execCommand("copy");
			  window.getSelection().removeAllRanges();
		}
	</script>
</body>

</html>
<!-- end document-->
