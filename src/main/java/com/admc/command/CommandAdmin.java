package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory;

import com.admc.loader.AdministratorChangeInformationPageLoader;
import com.admc.loader.AdministratorPageLoader;
import com.admc.loader.IPageLoader;
import com.admc.loader.NewAdministratorPageLoader;
import com.admc.loader.RejectedApplicationsPageLoader;
import com.admc.logic.ApplicationProcessor;
import com.admc.logic.ApplicationRecorder;
import com.admc.logic.PageConfigurator;

public class CommandAdmin implements ICommand {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandAdmin.class); 

	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("changeInformation") != null){
			IPageLoader pageLoader = new AdministratorChangeInformationPageLoader(request, response);
			pageLoader.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_ADMINISTRATOR_INFORMATION_PAGE);
		} else if(request.getParameter("submit") != null){
			ApplicationProcessor processor = new ApplicationProcessor();
			if(processor.canChange(request.getParameter("submit"), "Passed")){
				processor.processApplication(request.getParameter("submit"), "Passed");
			} else {
				LOGGER.info("Can't change this application, it was already reviewed!");
			}
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
		} else if(request.getParameter("cancel") != null){
			ApplicationProcessor processor = new ApplicationProcessor();
			if(processor.canChange(request.getParameter("submit"), "Rejected")){
				processor.processApplication(request.getParameter("cancel"), "Rejected");
			}
			processor.addDescription(Integer.parseInt(request.getParameter("cancel")), request.getParameter("description"));
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
		} else if (request.getParameter("newAdmininstrator") != null){
			IPageLoader pageLaoder = new NewAdministratorPageLoader(request, response);
			pageLaoder.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.NEW_ADMINISTRATOR_PAGE);
		} else if (request.getParameter("enrollStudents") != null){
			ApplicationRecorder recorder = new ApplicationRecorder();
			recorder.enrollStudents();
			request.setAttribute(ATTRIBUTE_REDIRECT, true);
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
		} else if (request.getParameter("rejectedApplications") != null){
			IPageLoader pageLaoder = new RejectedApplicationsPageLoader(request, response);
			pageLaoder.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.REJECTED_APPLICATIONS_PAGE);
		}
		IPageLoader pageLoader = new AdministratorPageLoader(request, response);
		pageLoader.setAtributes();
		return PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
	}
}
