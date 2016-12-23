package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admc.loader.ApplicantChangeInformationPageLoader;
import com.admc.loader.ApplicantPageLoader;
import com.admc.loader.FacultyPageLoader;
import com.admc.loader.IPageLoader;
import com.admc.logic.ApplicationProcessor;
import com.admc.logic.PageConfigurator;

public class CommandApplicant implements ICommand {
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("faculty") != null){
			IPageLoader pageLoader = new FacultyPageLoader(request, response);
			pageLoader.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.FACULTY_PAGE);
		} else if(request.getParameter("remove") != null) {
			ApplicationProcessor processor = new ApplicationProcessor();
			processor.deleteApplication(Integer.parseInt(request.getParameter("remove")));
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
			IPageLoader pageLoader = new ApplicantPageLoader(request, response);
			pageLoader.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
		} else {
			IPageLoader pageLoader = new ApplicantChangeInformationPageLoader(request, response);
			pageLoader.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_APPLICANT_INFORMATION_PAGE);
		}
	}

}
