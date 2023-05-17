package com.factory.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserAuthenticationFilter
 */
@WebFilter("/UserAuthenticationFilter")
public class UserAuthenticationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public UserAuthenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("userId") != null);
        System.out.println("isLoggedIn: " + isLoggedIn);

        String loginPage = httpRequest.getContextPath() + "/login.jsp";
        String homePage = httpRequest.getContextPath() + "/Home.jsp";
        // Exclude login.jsp from redirection
        String requestURI = httpRequest.getRequestURI();
        boolean isLoginPage = requestURI.endsWith("/login.jsp") || requestURI.endsWith("/login");
        boolean isLoginAction = requestURI.endsWith("/validateLogin.action") || requestURI.endsWith("/validateLogin.action");
        boolean isStyleCSS = requestURI.endsWith("/Style.css") || requestURI.endsWith("/Style.css");
        System.out.println("request URI :"+requestURI);
        System.out.println("isLoginAction : "+isLoginAction+" isStyle.css : "+isStyleCSS);
        //redirecting the user to login page if the user has not logged in
        if (!isLoggedIn && !isLoginPage && !isLoginAction && !isStyleCSS) {
            httpResponse.sendRedirect(loginPage);
            return;
        }
        
        //redirecting the user to home page if the user has logged in already
        if(isLoggedIn && isLoginPage) {
        	httpResponse.sendRedirect(homePage);
            return;
        }

        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
