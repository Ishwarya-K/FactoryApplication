package com.logout.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.servletcomponents.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport{
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidates the session
        }
        return SUCCESS;
	}
}
