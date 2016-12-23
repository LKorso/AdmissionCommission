package com.admc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicantAccessFilter implements Filter {
	 
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response; 
		HttpSession session = httpRequest.getSession();
		
		if(session.getAttribute("user") == null || !session.getAttribute("userType").equals("Applicant")){
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath());
		}else if(httpRequest.getRequestURI().indexOf("/faculty") != -1 && session.getAttribute("faculty") == null){
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath());
		}else {
			chain.doFilter(request, response);
		} 
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
