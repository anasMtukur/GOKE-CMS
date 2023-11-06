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

@Entity
@Table(name="client_files")
public class ClientFile {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;
	
	@Column(name="client")
    protected int client;
 
    @Column(name="title")
    protected String title;
 
    @Column(name="source")
    protected String source;

	@Column(name="expiry")
    protected Date expiry;

	@Column(name="has_expiry")
    protected boolean hasExpiry;
    
    @Column(name="created_at")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createdAt;
    
    @Column(name="updated_at")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    protected Date updatedAt;
    
    public ClientFile() {}
    
    public ClientFile(int client, String title, String source, Date expiry, boolean hasExpiry) {
    	this.client = client;
    	this.title = title;
    	this.source = source;
    	this.expiry = expiry;
    	this.hasExpiry = hasExpiry;
    }
    
    public ClientFile(int client, String title, String source) {
    	this.client = client;
    	this.title = title;
    	this.source = source;
    }
    
    public ClientFile(int id, int client, String title, String source, Date expiry, boolean hasExpiry) {
    	this.id = id;
    	this.client = client;
    	this.title = title;
    	this.source = source;
    	this.expiry = expiry;
    	this.hasExpiry = hasExpiry;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public boolean isHasExpiry() {
		return hasExpiry;
	}

	public void setHasExpiry(boolean hasExpiry) {
		this.hasExpiry = hasExpiry;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
