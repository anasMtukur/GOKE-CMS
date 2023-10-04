package com.gokecms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.model.SystemUser;
import com.gokecms.repository.SystemUserRepository;

public class SystemUserController extends BaseController {
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
	                case "/user":
	                	listUser(request, response);
	                    break;
	                case "/user/add":
	                    insertUser(request, response);
	                    break;
	                case "/user/delete":
	                    deleteUser(request, response);
	                    break;
	                case "/user/update":
	                    updateUser(request, response);
	                    break;
	                default:
	                    listUser(request, response);
	                    break;
	            }
	        } catch (SQLException ex) {
	            throw new ServletException(ex);
	        }
    	}
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException {
        List < SystemUser > listUser = repository.getAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SystemUser existingUser = repository.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
    	String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean blocked = request.getParameter("block") == null ? false : true;

        SystemUser newUser = new SystemUser( name, email, password, blocked );
        newUser.setCreatedAt(new Date());
        
        repository.saveUser(newUser);
        response.sendRedirect("/user");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean blocked = request.getParameter("block") == null ? false : true;

        System.out.println("Password: " + password);
        SystemUser user = repository.get(id);
        if(user != null) {
        	user.setName(name);
        	user.setUsername(email);
        	if(password != null && password != "") {
        		user.setPassword(password);
        	}
        	user.setBlocked(blocked);
        	
        	repository.update(user);
        }
        
        response.sendRedirect("/user");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        repository.delete(id);
        response.sendRedirect("/user");
    }
}