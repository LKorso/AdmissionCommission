package com.admc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.admc.localization.Localizer;

public class LocalizationFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if (request.getParameter("locale") != null){
			session.setAttribute("locale", request.getParameter("locale"));
			session.setAttribute("content", Localizer.
					loadResources(Localizer.getLocale((String) session.getAttribute("locale"))));
		} else if(session.getAttribute("content") == null || session.getAttribute("locale") == null){
			session.setAttribute("content", Localizer.loadResources(httpRequest.getLocale()));
		} 
		chain.doFilter(request, response);
	}
	
	public void destroy() {
	}
}
