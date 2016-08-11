package com.epam.admissioncommission.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.admissioncommission.logic.PageConfigurator;

public class CommandHeader implements ICommand {
	private String page;
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("log_out") != null){
			request.getSession().invalidate();
			page = PageConfigurator.getConfigurator().getPage(PageConfigurator.LOGIN_PAGE);
		}
		return page;
	}

}
