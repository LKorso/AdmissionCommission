package com.epam.admissioncommission.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.admissioncommission.loader.AdministratorPageLoader;
import com.epam.admissioncommission.loader.ApplicantPageLoader;
import com.epam.admissioncommission.loader.StudentPageLoader;
import com.epam.admissioncommission.logic.InformationChanger;
import com.epam.admissioncommission.logic.PageConfigurator;

public class CommandChangeInformation implements ICommand {
	private String page = null;
	
	@Override
	public String render(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		InformationChanger changer = new InformationChanger(request);
		changer.changeInformation();
		request.getSession().setAttribute("reload", "TRUE");
		request.setAttribute(ATRIBUTE_REDIRECT, true);
		
		switch ((String) request.getSession().getAttribute("userType")) {
		case "Student":
			renderStudentPage(request, response);
			page = PageConfigurator.getConfigurator().getPage(PageConfigurator.STUDENT_PAGE);
			break;
		case "Administrator":
			renderAdministratorPage(request, response);
			page = PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
			break;
		case "Applicant":
			if(request.getParameter("changeMarkId") != null ||
					request.getParameter("addMark") != null ||
					request.getParameter("deleteMark") != null){
				page = new CommandApplicant().render(request, response);
			} else{
				renderApplicantPage(request, response);
				page = PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
			}
			break;
		default:
			break;
		}
		
		return page;
	}
	
	private void renderStudentPage(HttpServletRequest request, HttpServletResponse response){
		StudentPageLoader pageLoader = new StudentPageLoader(request, response);
		pageLoader.setAtributes();
	}
	
	private void renderApplicantPage(HttpServletRequest request, HttpServletResponse response){
		ApplicantPageLoader pageLoader = new ApplicantPageLoader(request, response);
		pageLoader.setAtributes();
	}
	
	private void renderAdministratorPage(HttpServletRequest request, HttpServletResponse response){
		AdministratorPageLoader pageLoader = new AdministratorPageLoader(request, response);
		pageLoader.setAtributes();
	}
}
