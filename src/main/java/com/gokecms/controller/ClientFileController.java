package com.gokecms.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gokecms.model.Client;
import com.gokecms.model.ClientFile;
import com.gokecms.model.DocumentType;
import com.gokecms.repository.ClientFileRepository;
import com.gokecms.repository.ClientRepository;
import com.gokecms.repository.DocumentTypeRepository;

public class ClientFileController  extends BaseController {
	private static final long serialVersionUID = -4758388982229399144L;
	private static final int ARBITARY_SIZE     = 2156;
	private static final int MEMORY_THRESHOLD  = 1024 * 1024 * 1000;  // 100MB
    private static final int MAX_FILE_SIZE     = 1024 * 1024 * 1000; // 400MB
    private static final int MAX_REQUEST_SIZE  = 1024 * 1024 * 1200; // 500MB
    private static final String UPLOAD_PATH    = "/home/anas/CMS/uploads";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
	private ClientFileRepository repository;
	private ClientRepository clientRepository;
	private DocumentTypeRepository doctypeRepository;
	
	public void init() {
    	repository = new ClientFileRepository();
    	clientRepository = new ClientRepository();
    	doctypeRepository = new DocumentTypeRepository();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	if( isAuthenticated(request, response) ) {
    		
    		// Get the dynamic 'id' from the request URL - client_file/3/add
    		String[] path = (request.getPathInfo()).replaceFirst("/", "").split("/");
    		int id        = Integer.parseInt(path[0]);
            String action = path.length > 1 ? path[1] : request.getServletPath();
                        
            Client client = clientRepository.get(id);
            if( client == null ) {
            	response.sendRedirect("/client");
            }
            
			try { 
				switch (action) { 
				  	case "/client-files": 
				  		listClientFiles(request, response, client); 
				  		break; 
				  	case "add": 
				  		insertOrUpdate(request, response, client); 
				  		break;
				  	case "delete": 
				  		delete(request, response, client); 
				  		break; 
				  	case "update": 
				  		insertOrUpdate(request, response, client); 
				  		break; 
				  	case "download": 
				  		download(request, response, client); 
				  		break; 
				  	case "download-single": 
				  		downloadSingle(request, response, client); 
				  		break; 
				  	default:
				  		listClientFiles(request, response, client); 
				  		break; 
				} 
			} catch (SQLException | ParseException ex) {
				throw new ServletException(ex); 
			}
			 
	        
    	}
    }
    
    private void listClientFiles(HttpServletRequest request, HttpServletResponse response, Client client)
    		throws SQLException, IOException, ServletException {
    	int clientId = client.getId();
    	
    	List < ClientFile > files = repository.find( "client", String.valueOf( clientId ) );
    	List<DocumentType> doctypes = doctypeRepository.getAll();
        request.setAttribute("clientInfo", client);
        request.setAttribute("listClientFiles", files);
        request.setAttribute("listDoctypes", doctypes);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/client-files.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void insertOrUpdate(HttpServletRequest request, HttpServletResponse response, Client client) 
    		throws SQLException, IOException, ServletException, ParseException {
    	int clientId = client.getId();
    	String title="", source="", exSource="", docId="", operation="add", expiry="2023-09-26";
    	boolean fileProcessed = false, hasExpiry = false;

    	if (!ServletFileUpload.isMultipartContent(request)) {
    		request.setAttribute("error", "Form must be enctype multiform/form-data.");
    		response.sendRedirect( "/client-file/" + clientId + "/" );
            return;
        }
    	
    	ServletFileUpload upload = setupUpload();
    	
    	try {
    		List<FileItem> formItems = upload.parseRequest(request);
    		if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                	if (item.isFormField()) {
                		
                		String name = item.getFieldName();
                	    String value = item.getString();
                	    
                	    if( name.equals("title") ) {
                	    	title = value;
                	    }
                	    
                	    if( name.equals("source") ) {
                	    	exSource = value;
                	    }
                	    
                	    if( name.equals("id") ) {
                	    	docId = value;
                	    }
                	    
                	    if( name.equals("hasexpiry") ) {
                	    	hasExpiry = Boolean.parseBoolean( value );
                	    }
                	    
                	    if( name.equals("expiry") ) {
                	    	expiry = value;
                	    }
                	    
                	    if( name.equals("operation") ) {
                	    	operation = value;
                	    }
                	                    	    
                	}else {
                		if( operation.equalsIgnoreCase( "update" ) && exSource != "" ) {
                			try{
	                    		File f = new File( exSource );
	                    		if( f.exists() ) {
	                    			f.delete();
	                    		}
                    		}catch (Exception e) {
                    			e.printStackTrace();
                    		}
                		}
                		
                		File uploadDir = new File( UPLOAD_PATH + File.separator + client.getId() );
                        if (!uploadDir.exists()) { uploadDir.mkdir(); }
                        
                        String fileName = new File(item.getName()).getName();
                        
                        source = UPLOAD_PATH + File.separator + client.getId() + File.separator + fileName;
                        File storeFile = new File( source );
 
                        item.write(storeFile);
                        
                        fileProcessed = true;
                		
                	}
                }
    		}
    		
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}

    	if( !fileProcessed ) {
    		System.out.println("There was an error saving the file. Please try again.");
    		request.setAttribute("error", "There was an error saving the file. Please try again.");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/client-files.jsp");
            dispatcher.forward(request, response);
    	}
    	
    	Date expDate = formatter.parse(expiry);
    	System.out.println(expDate + " == " + hasExpiry);
    	
    	if( operation.equalsIgnoreCase( "add" ) ) {
    	
	        ClientFile entity = new ClientFile( clientId, title, source, expDate, hasExpiry );
	        repository.save( entity );
	        
    	}
    	
    	if( operation.equalsIgnoreCase( "update" ) ) {
    		int id = Integer.parseInt( docId );
    		ClientFile entity = repository.get( id );
    		
    		if( entity == null ) {
    			System.out.println( "Entity not found" );
        		request.setAttribute("error", "Client document not found.");
        		response.sendRedirect("/client-file/" + clientId + "/");
        	}
    		System.out.println( "Doing Entity Update" );
    		
    		entity.setTitle( title );
    		entity.setSource( source );
    		entity.setHasExpiry(hasExpiry);
    		entity.setExpiry(expDate);
    		
    		repository.update( entity );
    	}
    		
    	request.setAttribute("success", "Document saved successfully.");
        response.sendRedirect("/client-file/" + clientId + "/");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, Client client)
    		throws SQLException, IOException {
    	int clientId = client.getId();
        int id = Integer.parseInt(request.getParameter("id"));
        
        ClientFile entity = repository.get( id );
		if( entity == null ) {
			System.out.println( "Entity not found" );
    		request.setAttribute("error", "Client document not found.");
    		response.sendRedirect("/client-file/" + clientId + "/");
    	}
		
		try{
    		File f = new File( entity.getSource() );
    		f.delete();
    		
            repository.delete(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("success", "Document saved successfully.");
        response.sendRedirect("/client-file/" + clientId + "/");
    }
    
    public void download( HttpServletRequest request, HttpServletResponse response, Client entity ) throws IOException {
        String compressedSource = UPLOAD_PATH + File.separator + entity.getId() + File.separator;
        
        response.setContentType("application/zip");
        response.setHeader("Content-disposition", "attachment; filename=" + entity.getNumber().toLowerCase() + ".zip");

        ZipOutputStream zos = new 
        		ZipOutputStream(response.getOutputStream()); 
        
        compressFolder( compressedSource, zos );
        
        zos.close();
        
    }
    
    public void downloadSingle( HttpServletRequest request, HttpServletResponse response, Client entity ) throws IOException {
        String ref = request.getParameter( "reference" );
        int fileId = Integer.parseInt(ref);
        
        ClientFile clientFile = repository.get( fileId );
        if( null == clientFile ) {
        	request.setAttribute("error", "Document not found.");
            response.sendRedirect("/client-file/" + entity.getId() + "/");
        }
        
        String source = clientFile.getSource().split("/")[ clientFile.getSource().split("/").length - 1];
        String fName  = source.replace(".", "%").split("%")[0];
        String fExt   = source.replace(".", "%").split("%")[1];
        
        
        response.setContentType( "application/pdf" );
        response.setHeader( "Content-disposition", "attachment; filename=" + source );
        
        File file = new File ( clientFile.getSource() );
		if( !file.exists() ){
			file = new File ("/home/ebahn/goke/defaults/notFound.png");
			response.setContentType( "image/png" );
			response.setHeader( "Content-disposition", "attachment; filename=filenotfound.png" );
		}
		
	    byte [] bytearray  = new byte [(int)file.length()];
	    FileInputStream fis = new FileInputStream(file);
	    BufferedInputStream bis = new BufferedInputStream(fis);
	    bis.read(bytearray, 0, bytearray.length);
	    response.setContentLengthLong(file.length());
	    OutputStream outStream = response.getOutputStream();
    
        outStream.write(bytearray,0,bytearray.length);
 
        outStream.flush();
        outStream.close();
    }
    
    private ServletFileUpload setupUpload() {
    	DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);         
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        return upload;
    }
    
    private void compressFolder( String sourceFile, ZipOutputStream zos ) throws IOException {    	
    	try { 
            File zipDirectory = new File(sourceFile); 
            String[] fileList = zipDirectory.list(); 
            
            byte[] readBuffer = new byte[ ARBITARY_SIZE ]; 
            int bytesIn = 0; 
            for(int i=0; i < fileList.length; i++){ 
                File f = new File( zipDirectory, fileList[i] ); 
                if(f.isDirectory()) {  continue;  } 
                
                FileInputStream fis = new FileInputStream(f); 
                zos.putNextEntry( new ZipEntry( f.getName() ) ); 
                
                while((bytesIn = fis.read(readBuffer)) != -1) { 
                    zos.write(readBuffer, 0, bytesIn); 
                } 
                
                fis.close(); 
            } 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } 
    }

}
