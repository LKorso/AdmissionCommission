package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admc.loader.IPageLoader;
import com.admc.loader.RejectedApplicationsPageLoader;
import com.admc.logic.ApplicationProcessor;
import com.admc.logic.PageConfigurator;

public class CommandRejectedApplications implements ICommand {

	@Override
	public String render(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("submit") != null){
			ApplicationProcessor processor = new ApplicationProcessor();
			processor.processApplication(request.getParameter("submit"), "Passed");
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
		} else if(request.getParameter("cancel") != null){
			ApplicationProcessor processor = new ApplicationProcessor();
			processor.addDescription(Integer.parseInt(request.getParameter("cancel")), request.getParameter("description"));
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
		}
		IPageLoader pageLoader = new RejectedApplicationsPageLoader(request, response);
		pageLoader.setAtributes();
		return PageConfigurator.getConfigurator().getPage(PageConfigurator.REJECTED_APPLICATIONS_PAGE);
	}

}
