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
		} else if (request.getParameter("home") != null){
			if(request.getSession().getAttribute("userType") == null){
				page = PageConfigurator.getConfigurator().getPage(PageConfigurator.LOGIN_PAGE);
			} else {
				switch (request.getSession().getAttribute("userType").toString()) {
				case "Administrator":
					page = PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
					break;
				case "Applicant":
					page = PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
					break;
				case "Student":
					page = PageConfigurator.getConfigurator().getPage(PageConfigurator.STUDENT_PAGE);
					break;
				default:
					page = PageConfigurator.getConfigurator().getPage(PageConfigurator.ERROR_PAGE);
					break;
				}
			}
		}
		return page;
	}

}
