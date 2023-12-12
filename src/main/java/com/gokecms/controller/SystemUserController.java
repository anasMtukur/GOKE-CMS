package com.gokecms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;

import com.gokecms.app.Categories;
import com.gokecms.app.CategoryName;
import com.gokecms.app.RoleName;
import com.gokecms.app.Roles;
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
	                case "/user/toggle-block":
	                    toggleBlockUser(request, response);
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
        List<RoleName> roles = Arrays.asList( Roles.items );
        
        request.setAttribute("listUser", listUser);
        request.setAttribute("roles", roles);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
    	String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean blocked = request.getParameter("block") == null ? false : true;
        
        if(!EnumUtils.isValidEnum(RoleName.class, role)){
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
            request.setAttribute("error", "Role is not valid");
            dispatcher.forward(request, response);
        }

        boolean isSuper = RoleName.SUPER_ADMIN.equals( RoleName.valueOf(role) );
        boolean isBusinessUnit = RoleName.BUSINESS_UNIT.equals( RoleName.valueOf(role) );
        boolean isHr = RoleName.HR.equals( RoleName.valueOf(role) );
        boolean isAdmin = RoleName.ADMIN.equals( RoleName.valueOf(role) );
        boolean isFinance = RoleName.FINANCE.equals( RoleName.valueOf(role) );

        SystemUser newUser = new SystemUser( name, email, password, blocked, isSuper, isBusinessUnit, isAdmin, isHr, isFinance );
        newUser.setCreatedAt(new Date());
        
        repository.saveUser(newUser);
        response.sendRedirect("/user");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean blocked = request.getParameter("block") == null ? false : true;
        
        if(!EnumUtils.isValidEnum(RoleName.class, role)){
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
            request.setAttribute("error", "Role is not valid");
            dispatcher.forward(request, response);
        }

        boolean isSuper = RoleName.SUPER_ADMIN.equals( RoleName.valueOf(role) );
        boolean isBusinessUnit = RoleName.BUSINESS_UNIT.equals( RoleName.valueOf(role) );
        boolean isHr = RoleName.HR.equals( RoleName.valueOf(role) );
        boolean isAdmin = RoleName.ADMIN.equals( RoleName.valueOf(role) );
        boolean isFinance = RoleName.FINANCE.equals( RoleName.valueOf(role) );
        
        SystemUser user = repository.get(id);
        if(user != null) {
        	user.setName(name);
        	user.setUsername(email);
        	if(password != null && password != "" && password.length() > 3) {
        		user.setPassword(password);
        	}
        	user.setBlocked(blocked);
        	user.setSuperAdmin(isSuper);
        	user.setBusinessUnit(isBusinessUnit);
        	user.setAdmin(isAdmin);
        	user.setHr(isHr);
        	user.setFinance(isFinance);
        	
        	repository.update(user);
        }
        
        response.sendRedirect("/user");
    }
    
    private void toggleBlockUser(HttpServletRequest request, HttpServletResponse response) 
    		throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        SystemUser user = repository.get(id);
        if(user != null) {
        	
        	user.setBlocked(!user.isBlocked());
        	
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