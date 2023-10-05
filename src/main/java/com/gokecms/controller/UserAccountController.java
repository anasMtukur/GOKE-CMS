package com.gokecms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.model.SystemUser;
import com.gokecms.repository.SystemUserRepository;

public class UserAccountController  extends BaseController {
    private static final long serialVersionUID = 1L;
    private SystemUserRepository repository;

    public void init() {
    	repository = new SystemUserRepository();
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
	                case "/account":
	                	accountInfo(request, response);
	                    break;
	                
	                case "/account/update":
	                    updateUser(request, response);
	                    break;
	                default:
	                	accountInfo(request, response);
	                    break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
    	}
    	
    }
    
    private void accountInfo(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	SystemUser authUser = (SystemUser) request.getSession().getAttribute("logged_account");
    	SystemUser user = repository.get(authUser.getId());
    	
    	request.setAttribute( "activeUser", user );
        RequestDispatcher dispatcher = request.getRequestDispatcher("/account.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Password: " + password);
        SystemUser user = repository.get(id);
        if(user != null) {
        	user.setName(name);
        	user.setUsername(email);
        	if(password != null && password.strip() != "") {
        		user.setPassword(password);
        	}
        	
        	repository.update(user);
        }
        
        response.sendRedirect("/account");
    }

}
