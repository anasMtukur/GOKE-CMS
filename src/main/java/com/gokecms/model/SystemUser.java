package com.gokecms.model;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.NumericBooleanConverter;

import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
@Table(name="system_users")
public class SystemUser {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;
 
    @Column(name="name")
    protected String name;
 
    @Column(name="username")
    protected String username;
 
    @Column(name="password")
    protected String password;
    
    @Column(name="is_blocked")
    @Convert(converter = NumericBooleanConverter.class)
    protected boolean isBlocked;
    
    @Column(name="is_super_admin")
    @Convert(converter = NumericBooleanConverter.class)
    protected boolean isSuperAdmin;
    
    @Column(name="is_business_unit")
    @Convert(converter = NumericBooleanConverter.class)
    protected boolean isBusinessUnit;
    
    @Column(name="is_admin")
    @Convert(converter = NumericBooleanConverter.class)
    protected boolean isAdmin;
    
    @Column(name="is_hr")
    @Convert(converter = NumericBooleanConverter.class)
    protected boolean isHr;
    
    @Column(name="is_finance")
    @Convert(converter = NumericBooleanConverter.class)
    protected boolean isFinance;
    
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
 
    public SystemUser() {
    }
 
    public SystemUser(String name, String username, String password, boolean isBlocked, boolean isSuperAdmin, boolean isBusinessUnit, boolean isAdmin, boolean isHr, boolean isFinance) {
        super();
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isSuperAdmin = isSuperAdmin;
        this.isBusinessUnit = isBusinessUnit;
        this.isAdmin = isAdmin;
        this.isHr = isHr;
        this.isFinance = isFinance;
    }

    public SystemUser(int id, String name, String username, String password, boolean isBlocked, boolean isSuperAdmin, boolean isBusinessUnit, boolean isAdmin, boolean isHr, boolean isFinance) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isSuperAdmin = isSuperAdmin;
        this.isBusinessUnit = isBusinessUnit;
        this.isAdmin = isAdmin;
        this.isHr = isHr;
        this.isFinance = isFinance;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public boolean isBusinessUnit() {
		return isBusinessUnit;
	}

	public void setBusinessUnit(boolean isBusinessUnit) {
		this.isBusinessUnit = isBusinessUnit;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isHr() {
		return isHr;
	}

	public void setHr(boolean isHr) {
		this.isHr = isHr;
	}

	public boolean isFinance() {
		return isFinance;
	}

	public void setFinance(boolean isFinance) {
		this.isFinance = isFinance;
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
