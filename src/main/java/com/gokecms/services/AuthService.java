package com.gokecms.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.model.SystemUser;

public class AuthService {
	public boolean isAuthenticated( HttpServletRequest request, HttpServletResponse response ) 
    		throws IOException, ServletException {
    	SystemUser authUser = (SystemUser) request.getSession().getAttribute("logged_account");
    	
    	if( null == authUser ) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
            
            return false;
    	}
    	
    	request.setAttribute( "authUser", authUser );
    	
    	return true;
    }
}
