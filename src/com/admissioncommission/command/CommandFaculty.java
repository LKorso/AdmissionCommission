package com.admissioncommission.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admissioncommission.loader.ApplicantChangeInformationPageLoader;
import com.admissioncommission.loader.ApplicantPageLoader;
import com.admissioncommission.loader.IPageLoader;
import com.admissioncommission.logic.ApplicationProcessor;
import com.admissioncommission.logic.PageConfigurator;

public class CommandFaculty implements ICommand {
	private static final String BUTTON_VALUE_ADD_MARK = "add_mark";
	private static final String BUTTON_VALUE_TO_PROFILE = "to_profile";
	
	private String page = null;
	
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPageLoader pageLoader;
		if(request.getParameter("modal_button") != null){
			switch (request.getParameter("modal_button")) {
			case BUTTON_VALUE_ADD_MARK :
				pageLoader = new ApplicantChangeInformationPageLoader(request, response);
				pageLoader.setAtributes();
				return PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_APPLICANT_INFORMATION_PAGE);
			case BUTTON_VALUE_TO_PROFILE :
				break;
			default:
				break;
			}
		} else if (request.getParameter("applay").equals("applay")) {
			ApplicationProcessor processor = new ApplicationProcessor(request);
			processor.addApplication();
			processor.attachMarks();
			request.setAttribute(ATRIBUTE_REDIRECT, true);
		}
		pageLoader = new ApplicantPageLoader(request, response);
		pageLoader.setAtributes();
		return PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
	}

}
