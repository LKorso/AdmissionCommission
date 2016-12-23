package com.admc.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admc.dao.IUserDao;
import com.admc.dao.IUserTypeDao;
import com.admc.dao.creators.Factory;
import com.admc.dao.creators.IDaoFactory;
import com.admc.enteties.User;
import com.admc.loader.AdministratorPageLoader;
import com.admc.loader.ApplicantPageLoader;
import com.admc.loader.IPageLoader;
import com.admc.loader.RegistrationPageLoader;
import com.admc.loader.StudentPageLoader;
import com.admc.logic.PageConfigurator;

public class CommandLogin implements ICommand {
	private IDaoFactory daoFactory = Factory.createDaoFactory(Factory.MYSQL);
	private IUserDao userDao = daoFactory.getUserDao();
	private IUserTypeDao userTypeDao = daoFactory.getUserTypeDao();
	private User currentUser;

	private static final String ATRIBUTE_EMAIL = "email";
	private static final String ATRIBUTE_PASSWORD = "password";
	private static final String ATRIBUTE_USER = "user";
	private static final String ATRIBUTE_USER_TYPE = "userType";

	private static final String USER_TYPE_ADMINISTRATOR = "Administrator";
	private static final String USER_TYPE_APPLICANT = "Applicant";
	private static final String USER_TYPE_STUDENT = "Student";

	private String page = null;

	@Override
	public String render(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getParameter("signUp") != null){
			IPageLoader pageLoader = new RegistrationPageLoader(request, response);
			pageLoader.setAtributes();
			page = PageConfigurator.getConfigurator().getPage(PageConfigurator.REGISTRATION_PAGE);
			return page;
		}
		
		currentUser = userDao.findByEmailPassword(request.getParameter(ATRIBUTE_EMAIL),
					request.getParameter(ATRIBUTE_PASSWORD));

		if (currentUser == null) {
			page = PageConfigurator.getConfigurator().getPage(PageConfigurator.LOGIN_PAGE);
			return page;
		}
		
		request.getSession().setAttribute(ATRIBUTE_USER, currentUser);
		request.setAttribute(ATTRIBUTE_REDIRECT, true);
		
		switch (identifyUser(currentUser)) {
		case USER_TYPE_ADMINISTRATOR:
			renderAdminPage(request, response);
			request.getSession().setAttribute(ATRIBUTE_USER_TYPE, USER_TYPE_ADMINISTRATOR);
			break;
		case USER_TYPE_APPLICANT:
			renderApplicantPage(request, response);
			request.getSession().setAttribute(ATRIBUTE_USER_TYPE, USER_TYPE_APPLICANT);
			break;
		case USER_TYPE_STUDENT:
			renderStudentPage(request, response);
			request.getSession().setAttribute(ATRIBUTE_USER_TYPE, USER_TYPE_STUDENT);
			break;
		default:
			page = PageConfigurator.getConfigurator().getPage(PageConfigurator.ERROR_PAGE);
			break;
		}
		
		return page;
	}

	private String identifyUser(User currentUser) {
		return userTypeDao.findById(currentUser.getUserTypeId()).getType();
	}

	private void renderAdminPage(HttpServletRequest request, HttpServletResponse response) {
		AdministratorPageLoader pageLoader = new AdministratorPageLoader(request, response);
		pageLoader.setAtributes();
		page = PageConfigurator.getConfigurator().getPage(PageConfigurator.ADMIN_PAGE);
	}

	private void renderApplicantPage(HttpServletRequest request, HttpServletResponse response) {
		ApplicantPageLoader pageLoader = new ApplicantPageLoader(request, response);
		pageLoader.setAtributes();
		page = PageConfigurator.getConfigurator().getPage(PageConfigurator.APPLICANT_PAGE);
	}

	private void renderStudentPage(HttpServletRequest request, HttpServletResponse response) {
		StudentPageLoader pageLoader = new StudentPageLoader(request, response);
		pageLoader.setAtributes();
		page = PageConfigurator.getConfigurator().getPage(PageConfigurator.STUDENT_PAGE);
	}

}
