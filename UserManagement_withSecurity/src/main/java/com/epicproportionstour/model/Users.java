package com.epicproportionstour.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="Users")
public class Users implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String emailid;
	private String password;
	private String lastlogin;
	
	private boolean isactive;
	@ManyToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@JoinTable(name="UsersAndRoles",
    joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns={@JoinColumn(name="role_id",insertable=false)})
	private Set<Role> roles = new HashSet<Role>();
	public Users(){
		
	}
	
	public Users(String firstname, String lastname, String username,
			String emailid, String password, boolean isactive,
			 String lastlogin) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.emailid = emailid;
		this.password = password;
		this.isactive = isactive;
		this.lastlogin = lastlogin;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
	
	
}
