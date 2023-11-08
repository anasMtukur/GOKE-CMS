package com.gokecms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gokecms.model.ApiDataResponse;
import com.gokecms.model.Client;
import com.gokecms.model.ClientFile;
import com.gokecms.model.ClientFileResponse;
import com.gokecms.repository.ApiTokenRepository;
import com.gokecms.repository.ClientFileRepository;
import com.gokecms.repository.ClientRepository;
import com.google.gson.Gson;

public class ApiController  extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_PATH    = "/home/ebahn/goke";
    //private static final String UPLOAD_PATH    = "/home/anas/CMS/uploads";
    private ApiTokenRepository tokenRepository;
    private ClientRepository clientRepository;
    private ClientFileRepository cllientFileRepository;
    private Gson gson = new Gson();

    public void init() {
    	clientRepository      = new ClientRepository();
    	cllientFileRepository = new ClientFileRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String action = request.getServletPath();
		System.out.println( action );
		try {
            switch (action) {
                case "/api/v1":
                	apiHome(request, response);
                    break;
                    
                case "/api/v1/add-client":
                	insertClient(request, response);
                	break;
                	
                case "/api/v1/clients":
                	String responseJsonString = "{\n"
        					+ "    \"message\": \"Requested Endpoint Not Found.\"\n"
        					+ "}";;
                	String path = request.getPathInfo();
                	
                	if( path == null || path.equals( "/" )) {
                		List < Client > clients = clientRepository.getAll();
                		responseJsonString = this.gson.toJson( clients );
                	}else {
                	
	                	String[] paths = path.replaceFirst("/", "").split("/");
	                	if( paths.length > 0 ) {
	                		String param = paths[0];
	                		String value = paths[1];
	                		boolean includeFiles = path.contains("/files");
	                		
	                		responseJsonString = fetchData( param, value, includeFiles, request, response );
	                	}
                	}
                	
                	PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(responseJsonString);
                    out.flush();   
                    break;
                
                default:
                	apiHome(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    	
    }
    
    private void apiHome(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/api-setup-doc.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private String fetchData( String queryParam, String queryValue, boolean includeFiles, HttpServletRequest request, HttpServletResponse response  ) {
    	ApiDataResponse dataResponse = new ApiDataResponse();
    	
    	Client client = this.getClientBy(queryParam, queryValue);
    	if( null != client ) {
    		dataResponse.setClient(client);
    	}
		
    	if( includeFiles && client != null ) {
    		List < ClientFile > files = cllientFileRepository.find( "client", String.valueOf( client.getId() ) );
    		if( null != files && files.size() > 0 ) {
    			List<ClientFileResponse> docs = files.stream().map( (d) -> fromFileToApiResponse( d, request, response ) ).collect( Collectors.toList() );
    			dataResponse.setDocuments( docs );
    		}
    	}
    	
    	String data = this.gson.toJson( dataResponse );
    	
    	return data;
    }
    
    private Client getClientBy( String param, String value ) {
    	Client client = null;
    	
    	if( param.equalsIgnoreCase("id") ) {
    		client = clientRepository.get( Integer.parseInt(value) );
    	}else {
    		try {
    			client = clientRepository.find(param, value).get(0);
    		}catch(Exception e) {
    			System.out.println( e.getMessage() );
    		}
    	}
    	
    	return client;
    }
    
    private void insertClient(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	Client entity = mapper.readValue(request.getInputStream(), Client.class);

    	System.out.println( entity.getName() );
        //Client entity = new Client( name, number, email );
        int clientId = clientRepository.save( entity );
        
        File uploadDir = new File( UPLOAD_PATH + File.separator + clientId );
        if ( !uploadDir.exists() ) { 
        	uploadDir.mkdir(); 
        }
        
        String data = this.gson.toJson( entity );
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print( data );
        out.flush();
    }
    
    private ClientFileResponse fromFileToApiResponse ( ClientFile file, HttpServletRequest request, HttpServletResponse response ) {    
		String url = String.valueOf( request.getRequestURL() ).split( "/api" )[0];		
	    
    	String fileSource = url + "/client-file/download-single?reference=" + file.getId();
    	
    	return new ClientFileResponse( file.getTitle(), fileSource, file.getCreatedAt(), file.getUpdatedAt() );
    }

}
