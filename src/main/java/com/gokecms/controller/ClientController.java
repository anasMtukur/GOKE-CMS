package com.gokecms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.app.Categories;
import com.gokecms.app.CategoryName;
import com.gokecms.model.Client;
import com.gokecms.model.ClientFile;
import com.gokecms.model.SystemUser;
import com.gokecms.repository.ClientFileRepository;
import com.gokecms.repository.ClientRepository;
import com.gokecms.repository.SystemUserRepository;

import javax.servlet.RequestDispatcher;

public class ClientController extends BaseController {
	private static final long serialVersionUID = -4758388982229399144L;
	private static final String UPLOAD_PATH    = "/home/anas/CMS/uploads";
	private ClientRepository repository;
	private ClientFileRepository docRepository;
	private String finder = "MERCHANT";
	
	public void init() {
    	repository = new ClientRepository();
    	docRepository = new ClientFileRepository();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	if( isAuthenticated(request, response) ) {
    		if(request.getParameter("q") != null) {
        		finder = request.getParameter("q");
        	}
    		
    		String action = request.getServletPath();
    		
    		try {
                switch (action) {
                    case "/client":
                    	listClients(request, response);
                        break;
                    case "/client/add":
                        insert(request, response);
                        break;
                    case "/client/delete":
                        delete(request, response);
                        break;
                    case "/client/update":
                        update(request, response);
                        break;
                    default:
                    	listClients(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
	        
    	}
    }
    
    private void listClients(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	List<CategoryName> categories = Arrays.asList( Categories.items );
    	request.setAttribute("category", finder);
    	request.setAttribute("categories", categories);
    	
    	List < Client > clients = repository.find("category", finder);
        request.setAttribute("listClients", clients);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clients.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void insert(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
    	
    	String name = request.getParameter("name");
        String number = request.getParameter("clientno");
        String email = request.getParameter("email");
        String category = request.getParameter("category");

        Client entity = new Client( name, number, email, category );
        int clientId = repository.save( entity );
        
        File uploadDir = new File( UPLOAD_PATH + File.separator + clientId );
        if ( !uploadDir.exists() ) { 
        	uploadDir.mkdir(); 
        }
        
        response.sendRedirect("/client-file/" + clientId + "/");
        
    }

    private void update(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String number = request.getParameter("clientno");
        String email = request.getParameter("email");
        String category = request.getParameter("category");
        
        Client entity = repository.get(id);
        if( entity == null ) {
        	response.sendRedirect("/client?q=" + finder);
        }
        
        entity.setName(name);
        entity.setNumber(number);
        entity.setEmail(email);
        entity.setCategory(category);
        repository.update( entity );
        response.sendRedirect("/client");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String clientDir = UPLOAD_PATH + File.separator + id + File.separator;
        Path dir = Paths.get( clientDir ); //path to the directory  
        if( dir.toFile().exists() ) {
	        Files
	            .walk(dir) // Traverse the file tree in depth-first order
	            .sorted(Comparator.reverseOrder())
	            .forEach(path -> {
	                try {
	                    Files.delete(path);  //delete each file or directory
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
        }
        
        List < ClientFile > files = docRepository.find( "client", String.valueOf( id ) );
        for( ClientFile doc: files ) {
        	docRepository.delete( doc.getId() );
        }
                
        repository.delete(id);
        response.sendRedirect("/client?q=" + finder);
    }
}
