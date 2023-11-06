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
@Table(name="document_types")
public class DocumentType {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;
 
    @Column(name="title")
    protected String title;
    
    @Column(name="description")
    protected String description;
    
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
    
    public DocumentType() {}
    
    public DocumentType(String title, String description) {
    	this.title = title;
    	this.description = description;
    }
    
    public DocumentType(int id, String title, String description) {
    	this.id = id;
    	this.title = title;
    	this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
