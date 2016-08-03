package com.epam.admissioncommission.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.admissioncommission.loader.ApplicantChangeInformationPageLoader;
import com.epam.admissioncommission.loader.ApplicantPageLoader;
import com.epam.admissioncommission.loader.FacultyPageLoader;
import com.epam.admissioncommission.loader.IPageLoader;
import com.epam.admissioncommission.loader.StudentChangeInformationPageLoader;
import com.epam.admissioncommission.logic.ApplicationProcessor;
import com.epam.admissioncommission.logic.PageConfigurator;

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
			request.setAttribute(ATRIBUTE_REDIRECT, true);
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
