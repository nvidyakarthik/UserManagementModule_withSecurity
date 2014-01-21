package com.epicproportionstour.service;
import java.util.List;

import com.epicproportionstour.model.Role;
import com.epicproportionstour.model.Users;

public interface LoginService {
	void addUser(Users user);
	void editUser(Users user);
	void deleteUser(int userId);
	Users findUser(int userid);
	Users findUserByName(String userName);
	List<Users> getAllUsers();
	void updateLastLogin(String lastLogin, String userName);
	List<Role> getUserRoles();
	Role findRole(int roleid);
	public void changePassword(String userName,String newPassword);

}

