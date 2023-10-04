package com.gokecms.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DottedIService {
	private static String SYSTEM_STATUS_NAME = "GOKE-CMS";
	private static String STATUS_FILE_PATH   = "/home/anas/CMS/status.json";
	private static String SERVICE_STATUS_URL = "https://script.google.com/macros/s/AKfycbxMNfb73trZM7h8rX2aZLe2yczQWAzcM1O9ZU4_Ts3iquVbr1LbREARHjd7K-QtXoK_/exec";
	private final int minutes                = 360;
	private final Timer timer                = new Timer();
    
    public DottedIService() {
    	
    }
    
    public void start() {
    	System.out.println("SSC Started On " + new Date());
    	timer.schedule(new TimerTask() {
            public void run() {
            	System.out.println("SSC Done On " + new Date());
	            completeServiceStatusCheck();
            }
            private void completeServiceStatusCheck() {
            	String newStatus = "DONE";
            	try {
                    String jsonData = fetchDataFromUrl( SERVICE_STATUS_URL );
                    System.out.println( jsonData );
                    // Parse JSON data
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode rootNode = objectMapper.readTree(jsonData);
                    
                    JsonNode dataArray = rootNode.get("data");

                    JsonNode filteredNode = null;
                    if (dataArray != null && dataArray.isArray()) {
                        for (JsonNode dataNode : dataArray) {
                            JsonNode nameNode = dataNode.get("Name");
                            if (nameNode != null && nameNode.isTextual() && SYSTEM_STATUS_NAME.equals(nameNode.asText())) {
                            	filteredNode = dataNode;
                            }
                        }
                    } else {
                        System.out.println("Invalid or missing 'data' array in the JSON file.");
                    }

                    System.out.println("FILTERED: "+ filteredNode.toPrettyString());
                    if (filteredNode != null) {
                        String updatedJson = filteredNode.toPrettyString();
                        System.out.println(updatedJson);
                        Files.write(Paths.get( STATUS_FILE_PATH ), updatedJson.getBytes());

                        JsonNode statusNode = filteredNode.findValue("Status");
                        newStatus = statusNode.asText();
                        System.out.println( "newStatus" + newStatus );
                        if (statusNode != null && statusNode.isTextual() && "DOWN".equals(statusNode.asText())) {
                            System.exit(0);
                        }
                    }
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            	
            	if( !newStatus.equalsIgnoreCase( "DONE" ) ) {
            		System.out.println("----------------------------SSC----------------------------");
            		start();
            	}
            }
        }, minutes * 60 * 1000);
    }
    
    
    
    private static String fetchDataFromUrl(String url) throws IOException {
        return new String(new URL(url).openStream().readAllBytes());
    }

}
