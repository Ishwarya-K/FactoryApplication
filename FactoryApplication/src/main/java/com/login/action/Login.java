package com.login.action;

import java.util.logging.Level;

import javax.servlet.http.HttpSession;

import org.apache.struts2.servletcomponents.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.payroll.action.PayRollService;

public class Login extends ActionSupport{
	private String userId;
	private String passwd;
	private boolean isValidLogin;
	public boolean isValidLogin() {
		return isValidLogin;
	}
	public void setValidLogin(boolean isValidLogin) {
		this.isValidLogin = isValidLogin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String validateLogin() {
		PayRollService payrollService = new PayRollService();
		HttpSession session = ServletActionContext.getRequest().getSession();
	    session.setAttribute("userId", userId);
	    session.setMaxInactiveInterval(300);
		isValidLogin = payrollService.isValidLogin(userId, passwd);
		System.out.println("User id : "+session.getAttribute("userId"));
		if(isValidLogin) {
			return SUCCESS;
		}
		else {
			return ERROR;
		}
	}
}
