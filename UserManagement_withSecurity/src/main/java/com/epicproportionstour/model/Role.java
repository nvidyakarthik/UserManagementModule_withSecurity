package com.epicproportionstour.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.transaction.annotation.Transactional;
@Entity(name="role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	private String roleName;
	@ManyToMany(mappedBy="roles")
	private Set<Users> userList = new HashSet<Users>();
	public Role(){
		
	}
	
	public Role(int id,String roleName) {
		super();
		
		this.roleName = roleName;
		this.id=id;
		
		
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Users> getUserList() {
		return userList;
	}

	public void setUserList(Set<Users> userList) {
		this.userList = userList;
	}
	
	

	

}
