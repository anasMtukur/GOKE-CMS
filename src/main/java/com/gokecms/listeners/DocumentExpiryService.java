package com.gokecms.listeners;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gokecms.model.Client;
import com.gokecms.model.ClientFile;
import com.gokecms.repository.ClientFileRepository;
import com.gokecms.repository.ClientRepository;

public class DocumentExpiryService {
	private final int minutes = 360;
	private final Timer timer = new Timer();
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private final List<Long> notifyDays = Arrays.asList(new Long[] {60l, 30l, 1l, 0l});
	private final ClientFileRepository cfr;
	private final ClientRepository cr;	

	public DocumentExpiryService() {
		this.cfr = new ClientFileRepository();
		this.cr = new ClientRepository();
	}
    
    public void start() {
    	System.out.println("SSC Started On " + new Date());
    	timer.schedule(new TimerTask() {
            public void run() {
            	System.out.println("SSC Done On " + new Date());
	            completeDocumentExpiryCheck();
            }
            private void completeDocumentExpiryCheck() {
            	List<ClientFile> files = cfr.find("has_expiry", "1");
            	for(ClientFile file: files) {
            		long daysToExpiry = calculateDaysToExpiry(file.getExpiry());
            		if( notifyDays.contains(daysToExpiry) ) {
            			notifyClient( file.getClient() );
            		}
            	}
            	
            }
    	}, minutes * 60 * 1000);
    }
    
    private long calculateDaysToExpiry( Date date ) {
    	LocalDate start = LocalDate.now();
    	LocalDate end   = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;

    	long days = ChronoUnit.DAYS.between(start, end);

    	System.out.println(days);  //14;
    	
    	return days;
    }
    
    private void notifyClient( int clientId ) {
    	Client client = cr.get( clientId );
    	
    	//Send mail
    }
    
    public static void main(String[] args) {
    	DocumentExpiryService s = new DocumentExpiryService();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date expDate;
		try {
			expDate = formatter.parse("2023-11-01");
		
	    	long days = s.calculateDaysToExpiry( expDate );
	    	
	    	System.out.print( days );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
