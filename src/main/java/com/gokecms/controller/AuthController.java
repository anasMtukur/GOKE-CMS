package com.gokecms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.model.SystemUser;
import com.gokecms.repository.SystemUserRepository;

public class AuthController  extends HttpServlet {
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
    	String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/auth":
                	authenticateUser(request, response);
                    break;
                case "/signout":
                	signOut(request, response);
                    break;
                
                default:
                	signOut(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void authenticateUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
			SystemUser authUser = this.findAuthUser( username );
			
			if( authUser == null ) {
				request.setAttribute("error", "Username or password incorrect. Please check your username and try again.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		        dispatcher.forward(request, response);
		        
		        return;
			}
			
			if( !password.equals( authUser.getPassword() ) ) {
				request.setAttribute("error", "Username or password incorrect. Please check your username and try again.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		        dispatcher.forward(request, response);
		        
		        return;
			}
			
			authUser.setPassword(null);
			
			request.getSession().setAttribute( "logged", authUser.getId() );
			request.getSession().setAttribute( "logged_account", authUser );
	        //RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
	        //dispatcher.forward(request, response);
			
			response.sendRedirect( "/home" );
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    private void signOut(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
    	
    	request.getSession().invalidate();
    	response.sendRedirect( "/" );
    }

    private SystemUser findAuthUser( String username )
    		throws SQLException, IOException, ServletException {
        List < SystemUser > listUsers = repository.find( "username", username );
        
        if( listUsers == null ) {
        	return null;
        }
        
        if( listUsers.size() <= 0 ) {
        	return null;
        }
        
        return listUsers.get(0);
    }
    
    
}

