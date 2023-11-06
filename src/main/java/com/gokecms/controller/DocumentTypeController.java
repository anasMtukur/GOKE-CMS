package com.gokecms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.model.DocumentType;
import com.gokecms.repository.DocumentTypeRepository;

public class DocumentTypeController extends BaseController {
	private static final long serialVersionUID = -4758388982229399144L;
	private DocumentTypeRepository repository;
	
	public void init() {
    	repository = new DocumentTypeRepository();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	if( isAuthenticated(request, response) ) {
    		
    		String action = request.getServletPath();
    		
    		try {
                switch (action) {
                    case "/doctype":
                    	listAll(request, response);
                        break;
                    case "/doctype/add":
                        insert(request, response);
                        break;
                    case "/doctype/delete":
                        delete(request, response);
                        break;
                    case "/doctype/update":
                        update(request, response);
                        break;
                    default:
                    	listAll(request, response);
                        break;
                }
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
	        
    	}
    }
    
    private void listAll(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
        
    	List < DocumentType > items = repository.getAll();
        request.setAttribute("listDoctypes", items);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/doctype.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void insert(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
    	
    	String title = request.getParameter("title");
        String description = request.getParameter("description");

        DocumentType entity = new DocumentType( title, description );
        repository.save( entity );
        
        response.sendRedirect("/doctype");
        
    }

    private void update(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        
        DocumentType entity = repository.get(id);
        if( entity == null ) {
        	response.sendRedirect("/doctype");
        }
        
        entity.setTitle(title);
        entity.setDescription(description);
        repository.update( entity );
        response.sendRedirect("/doctype");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
                
        repository.delete(id);
        response.sendRedirect("/doctype");
    }
}
