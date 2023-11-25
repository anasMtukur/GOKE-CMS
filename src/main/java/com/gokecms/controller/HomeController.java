package com.gokecms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gokecms.app.Categories;
import com.gokecms.model.Client;
import com.gokecms.model.ClientFile;
import com.gokecms.model.SystemUser;
import com.gokecms.repository.ClientFileRepository;
import com.gokecms.repository.ClientRepository;
import com.gokecms.repository.SystemUserRepository;

import javax.servlet.RequestDispatcher;

public class HomeController extends BaseController {
	private static final long serialVersionUID = -4758388982229399144L;
	private static final String UPLOAD_PATH    = "/home/anas/CMS/uploads";
	private ClientRepository repository;
	
	public void init() {
    	repository = new ClientRepository();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	if( isAuthenticated(request, response) ) {
    		List<String> categories = Arrays.asList( Categories.items );
    		HashMap<String, Long> counters = new HashMap<String, Long>();
    		
    		for(String category: categories){
    			long count = repository.countBy("category", category);
    			counters.put(category, count);
    		}
    		
    		request.setAttribute("categories", categories);
    		request.setAttribute("counters", counters);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
	        
    	}
    }
    
}
