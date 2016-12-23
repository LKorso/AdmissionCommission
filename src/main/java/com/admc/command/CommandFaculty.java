package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admc.loader.ApplicantChangeInformationPageLoader;
import com.admc.loader.ApplicantPageLoader;
import com.admc.loader.IPageLoader;
import com.admc.logic.ApplicationProcessor;
import com.admc.logic.PageConfigurator;

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
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
		}
		pageLoader = new ApplicantPageLoader(request, response);
		pageLoader.setAtributes();
		return PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
	}

}
