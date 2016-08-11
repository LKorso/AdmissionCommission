package com.admissioncommission.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admissioncommission.logic.PageConfigurator;
import com.admissioncommission.logic.UserRegistration;

public class CommandRegistaration implements ICommand {
	String page;
	
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRegistration registration = new UserRegistration(request);
		registration.register();
		request.setAttribute(ATRIBUTE_REDIRECT, true);
		page = PageConfigurator.getConfigurator().getPage(PageConfigurator.LOGIN_PAGE);
		return page;
	}

}
