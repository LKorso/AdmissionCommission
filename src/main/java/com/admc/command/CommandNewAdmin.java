package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admc.logic.PageConfigurator;
import com.admc.logic.UserRegistration;

public class CommandNewAdmin implements ICommand {
	private String page;
	
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRegistration registration = new UserRegistration(request);
		registration.register();
		request.setAttribute(ATTRIBUTE_REDIRECT, true);
		page = PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
		return page;
	}
}
