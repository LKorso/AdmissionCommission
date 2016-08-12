package com.admissioncommission.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.admissioncommission.localization.Localizer;

public class LocalizationFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		System.out.println("in localliz filter");
		if(session.getAttribute("content") == null){
			session.setAttribute("content", Localizer.loadResources(httpRequest.getLocale()));
		} else if (httpRequest.getParameter("country") != null){
			System.out.println("next adding content");
			session.setAttribute("content", Localizer.
					loadResources(Localizer.getLocale(httpRequest.getParameter("country"))));
		}
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}
}
