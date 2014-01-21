package com.epicproportionstour.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epicproportionstour.dao.UsersDao;
import com.epicproportionstour.model.Role;
import com.epicproportionstour.model.Users;


@Repository
public class UsersDaoImpl implements UsersDao{
	
	@Autowired
	private SessionFactory session;

	@Override
	public void addUser(Users user) {
		session.getCurrentSession().saveOrUpdate(user);
		
	}

	@Override
	public void editUser(Users user) {
		session.getCurrentSession().update(user);
		
	}

	@Override
	public void deleteUser(int userId) {
		session.getCurrentSession().delete(findUser(userId));
		
	}

	@Override
	public Users findUser(int userid) {
		
		return (Users) session.getCurrentSession().get(Users.class,userid);
	}

	@Override
	public Users findUserByName(String username) {
		Criteria criteria = session.getCurrentSession().createCriteria(Users.class);
		criteria.add(Restrictions.eq("username", username));		
		return (Users) criteria.uniqueResult();
	}

	@Override
	public List<Users> getAllUsers() {
		return session.getCurrentSession().createQuery("from Users").list();
		
	}
    
	@Override
	public void updateLastLogin(String lastLogin,String userName) {
		Users getUser=findUserByName(userName);
		getUser.setLastlogin(lastLogin);
		session.getCurrentSession().saveOrUpdate(getUser);
		
		
		
		
	}
	@Override
	public List<Role> getUserRoles() {
		System.out.println("                   In userdao                ");
		return  session.getCurrentSession().createQuery("from role").list();
		
		
	}
	
	@Override
	public Role findRole(int roleid) {
		
		return (Role) session.getCurrentSession().get(Role.class,roleid);
	}
	
	@Override
	public void changePassword(String userName,String newPassword) {
		
		Users getUser=findUserByName(userName);
		getUser.setPassword(newPassword);
		session.getCurrentSession().saveOrUpdate(getUser);
	}
	

}
