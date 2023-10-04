package com.gokecms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.model.SystemUser;

public abstract class BaseController  extends HttpServlet {

	private static final long serialVersionUID = -6080158485252312740L;

	protected boolean isAuthenticated( HttpServletRequest request, HttpServletResponse response ) 
    		throws IOException, ServletException {
    	SystemUser authUser = (SystemUser) request.getSession().getAttribute("logged_account");
    	
    	if( null == authUser ) {
    		response.sendRedirect("/");
            
            return false;//Some
    	}
    	
    	request.setAttribute( "authUser", authUser );
    	
    	return true;
    }
}
