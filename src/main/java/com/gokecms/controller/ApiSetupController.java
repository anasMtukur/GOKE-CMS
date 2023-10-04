package com.gokecms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import com.gokecms.model.ApiToken;
import com.gokecms.model.SystemUser;
import com.gokecms.repository.ApiTokenRepository;

public class ApiSetupController extends BaseController {
	private static final long serialVersionUID = 26946876414345872L;
	private ApiTokenRepository repository;

    public void init() {
    	repository = new ApiTokenRepository();
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
	                case "/api-setup-doc":
	                	list(request, response);
	                    break;
	                case "/api-setup-doc/add":
	                    insert(request, response);
	                    break;
	                case "/api-setup-doc/delete":
	                    delete(request, response);
	                    break;
	                default:
	                    list(request, response);
	                    break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
    	}
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
        List < ApiToken > list = repository.getAll();
        request.setAttribute("tokenList", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/api-setup-doc.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insert(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
        String token = RandomStringUtils.random(25, true, true);

        ApiToken entity = new ApiToken( token );
        entity.setCreatedAt(new Date());
        
        repository.save(entity);
        response.sendRedirect("/api-setup-doc");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        repository.delete(id);
        response.sendRedirect("/api-setup-doc");
    }
}
