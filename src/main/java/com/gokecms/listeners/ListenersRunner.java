package com.gokecms.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ListenersRunner implements ServletContextListener {
	private static String STATUS_FILE_PATH = "/home/anas/CMS/status.json";
	public ListenersRunner() { }
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
		
		  DottedIService serviceCheck = new DottedIService();
		  
		  String status = savedServiceStatus(); 
		  boolean serviceStatus = status.equalsIgnoreCase( "UNKNOWN" ) || status.equalsIgnoreCase( "UP" ); 
		  if( serviceStatus ){ 
			  serviceCheck.start(); 
		  }
		  
		  if( status.equalsIgnoreCase( "DOWN" ) ) { System.exit(0); }    	
    	
    }
    
    private static String savedServiceStatus() {
    	
    	File file = new File( STATUS_FILE_PATH );

        if (file.exists()) {
            try {
                // Parse JSON data from the file
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(file);
                JsonNode statusNode = rootNode.findValue("Status");
                // Check if the "status" field is present and a string
                if (statusNode != null && statusNode.isTextual()) {
                    return statusNode.asText();
                } else {
                    System.out.println("Invalid or missing 'status' field in the JSON file.");
                    
                    return "DOWN";
                }

            } catch (IOException e) {
                System.out.println("Error reading JSON file: " + e.getMessage());
            }
        } else {
            System.out.println( "File does not exist: " + STATUS_FILE_PATH );

        	return "DOWN";
        }

        return "UNKNOWN";
    	
    }

}
