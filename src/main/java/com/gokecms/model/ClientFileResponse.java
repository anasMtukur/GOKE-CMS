package com.gokecms.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ClientFileResponse {
    protected String title; 
    protected String source;
    protected Date createdAt;
    protected Date updatedAt;
    
    public ClientFileResponse() {}
    
    public ClientFileResponse( String title, String source, Date createdAt, Date updatedAt ) {
    	this.title = title;
    	this.source = source;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }
}
