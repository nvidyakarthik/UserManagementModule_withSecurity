package com.epicproportionstour.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.epicproportionstour.model.Role;
import com.epicproportionstour.model.Users;
import com.epicproportionstour.service.LoginService;
import com.epicproportionstour.service.impl.EmailServiceImpl;
import com.epicproportionstour.util.*;





@Controller
public class LoginController {
	
	CommonUtils cUtils = new CommonUtils();
	private String lastLogin;
	private Users user;
	private String fullName;
	private String userName=null;
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping("/secured/mypage")
	public String mypage(Model model, Principal principal,
			HttpServletRequest req) {
		userName = principal.getName();
		lastLogin = cUtils.loginDateTime();
		loginService.updateLastLogin(lastLogin, userName);
		user = loginService.findUserByName(userName);
		fullName = user.getFirstname() + " " + user.getLastname();
		model.addAttribute("fullName", fullName);
		req.getSession().setAttribute("userName", userName);
		Set<Role> userRoleList = user.getRoles();
		for (Role temp : userRoleList) {
			if (temp.getRoleName().equals("ADMIN")) {
				return "secured/userHome";
			}
		}
		return "secured/appUserHome";
	}
	
	
	@RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
	public String logoutPage() {
		return "logoutPage";
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage() {
		return "loginPage";
	}
	
	@RequestMapping("/")
	public String homePage() {
		return "loginPage";
	}
	
	@RequestMapping("/secured/users")
	public String AppUsers(Model model, Principal principal){
		
		List<Users> users = new ArrayList<Users>();
		users = loginService.getAllUsers();
		model.addAttribute("ApplicationUsers", users);
       	return "secured/users";
	}
	
	@RequestMapping(value = "/secured/addUser", method = RequestMethod.GET)
	public String addNewUser(Model model, Principal principal) {
		model.addAttribute("roleList",loginService.getUserRoles());
		return "secured/addUser";
	}
	
	@RequestMapping(value = "/secured/addUser", method = RequestMethod.POST)
	public String addNewUser(@ModelAttribute("newUser") Users newUser,
			BindingResult result, Model model) {
		String[] items = result.getFieldValue("roles").toString().split(",");
		Role role1 = null;
		int id = 0;
		List<String> container = Arrays.asList(items);
		for (String roleid : container) {
			id = Integer.parseInt(roleid);
			role1 = new Role(id, (loginService.findRole(id)).getRoleName());
			newUser.getRoles().add(role1);
		}

		String securePassword = Long.toHexString(Double.doubleToLongBits(Math
				.random()));
		newUser.setLastlogin(cUtils.loginDateTime());
		newUser.setPassword(MD5Hash.md5(securePassword));
		newUser.setIsactive(true);
		// We set the Sender Email Id and password, but we would put them in
		// loginCredentials.property file.
		// Update your emailId and Password before you use.
		// Comment on 21 Dec, 2013--- NO mail functionality used
		String sSenderEmailId = "GMail Email ID"; // Valid Gmail account ID
		String sSenderPassword = "Password";// Corresponding Password.
		
		String userName = result.getFieldValue("username").toString();

		EmailServiceImpl objSendPWD = new EmailServiceImpl(result.getFieldValue("emailid").toString(), sSenderEmailId,
				sSenderPassword, securePassword, userName);
		objSendPWD.SendPwdByMail();
		loginService.addUser(newUser);
		model.addAttribute("ModelMessage",
				"User added successfully.Please check you mail for the new Password");
		return "secured/successPage";

	}
	
	@RequestMapping(value = "/secured/changePassword", method = RequestMethod.GET)
	public String changePasswordPage(HttpServletRequest req) {
		
		return "secured/userChangePassword";
	}
	
	@RequestMapping(value="/secured/changePassword", method = RequestMethod.POST)
	public String ChangeUserPassword(HttpServletRequest req,Model model) throws Exception {
	
	    String sCurrentPassword=req.getParameter("currentPassword");
        String sNewPassword = req.getParameter("newPassword");
        sNewPassword=MD5Hash.md5(sNewPassword);
        String userName=req.getSession().getAttribute("userName").toString();
        String sNewConfirmPassword = req.getParameter("confirmPassword");
        System.out.println("username =============="+userName);
        System.out.println("currentPassword =============="+sCurrentPassword);
        System.out.println("newPassword =============="+sNewPassword);
        System.out.println("confirmpassword =============="+sNewConfirmPassword);
        loginService.changePassword(userName, sNewPassword);
        model.addAttribute("ModelMessage","Password changed successfully");
        return "secured/successPage";
       
 	}
	

}
