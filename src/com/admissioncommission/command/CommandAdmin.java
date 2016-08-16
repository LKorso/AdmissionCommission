package com.admissioncommission.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admissioncommission.loader.AdministratorChangeInformationPageLoader;
import com.admissioncommission.loader.AdministratorPageLoader;
import com.admissioncommission.loader.IPageLoader;
import com.admissioncommission.loader.NewAdministratorPageLoader;
import com.admissioncommission.logic.ApplicationProcessor;
import com.admissioncommission.logic.ApplicationRecorder;
import com.admissioncommission.logic.PageConfigurator;

public class CommandAdmin implements ICommand {

	@Override
	public String render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("changeInformation") != null){
			IPageLoader pageLoader = new AdministratorChangeInformationPageLoader(request, response);
			pageLoader.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.CHANGE_ADMINISTRATOR_INFORMATION_PAGE);
		} else if(request.getParameter("submit") != null){
			ApplicationProcessor processor = new ApplicationProcessor();
			processor.processApplication(request.getParameter("submit"), "Passed");
			request.setAttribute(ATRIBUTE_REDIRECT, true);
		} else if(request.getParameter("cancel") != null){
			ApplicationProcessor processor = new ApplicationProcessor();
			processor.processApplication(request.getParameter("cancel"), "Rejected");
			processor.addDescription(Integer.parseInt(request.getParameter("cancel")), request.getParameter("description"));
			request.setAttribute(ATRIBUTE_REDIRECT, true);
		} else if (request.getParameter("newAdmininstrator") != null){
			IPageLoader pageLaoder = new NewAdministratorPageLoader(request, response);
			pageLaoder.setAtributes();
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.NEW_ADMINISTRATOR_PAGE);
		} else if (request.getParameter("enrollStudents") != null){
			ApplicationRecorder recorder = new ApplicationRecorder();
			recorder.enrollStudents();
			request.setAttribute(ATRIBUTE_REDIRECT, true);
			return PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
		}
		IPageLoader pageLoader = new AdministratorPageLoader(request, response);
		pageLoader.setAtributes();
		return PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
	}
}
