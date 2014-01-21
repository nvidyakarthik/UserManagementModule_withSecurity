package com.epicproportionstour.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epicproportionstour.model.Role;
import com.epicproportionstour.model.Users;
import com.epicproportionstour.service.LoginService;
import com.epicproportionstour.dao.UsersDao;

@Service
public class LoginServiceImpl implements LoginService{
    
	@Autowired
	private UsersDao userDao;
	@Transactional
	public void addUser(Users user) {
		userDao.addUser(user);
		
	}

	@Transactional
	public void editUser(Users user) {
		userDao.editUser(user);
		
	}

	@Transactional
	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
		
	}

	@Transactional
	public Users findUser(int userid) {
		userDao.findUser(userid);
		return null;
	}

	@Transactional
	public Users findUserByName(String userName) {
		return userDao.findUserByName(userName);
		
	}

	@Transactional
	public List<Users> getAllUsers() {
		return userDao.getAllUsers();
		
	}

	@Transactional
	public void updateLastLogin(String lastLogin, String userName) {
		userDao.updateLastLogin(lastLogin, userName);
		
	}

	@Transactional
	public List<Role> getUserRoles() {
		return userDao.getUserRoles();
	}

	@Transactional
	public Role findRole(int roleid) {
		return userDao.findRole(roleid);
		
	}

	@Transactional
	public void changePassword(String userName, String newPassword) {
		userDao.changePassword(userName, newPassword);
		
	}

}
