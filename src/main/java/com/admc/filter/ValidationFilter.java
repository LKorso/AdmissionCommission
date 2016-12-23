package com.admc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admc.validation.Validator;

public class ValidationFilter implements Filter {
	private static final String PARAMETER_FIRST_NAME = "firstName";
	private static final String PARAMETER_LAST_NAME = "lastName";
	private static final String PARAMETER_SEX = "sex";
	private static final String PARAMETER_PHONE = "phone";
	private static final String PARAMETER_EMAIL = "email";
	private static final String PARAMETER_PASSWORD = "password_one";
	private static final String PARAMETER_REPEATED_PASSWORD = "password_two";
	private static final String PARAMETER_YEAR = "year";
	private static final String PARAMETER_DAY = "day";
	private static final String PARAMETER_MONTH = "month";
	private static final String PARAMETER_SUBJECT_ONE = "subject_one";
	private static final String PARAMETER_SUBJECT_TWO = "subject_two";
	private static final String PARAMETER_SUBJECT_THREE = "subject_three";
	private static final String PARAMETER_MARK_ONE = "mark_one";
	private static final String PARAMETER_MARK_TWO = "mark_two";
	private static final String PARAMETER_MARK_THREE = "mark_three";
	private static final String PARAMETER_CERTIFICATE = "certificate";
	private static final String PARAMETER_MARK = "mark";
	private static final String COMMAND_PARAMETER = "command";
	private static final String ATRIBUTE_CURRENT_PAGE = "currentPage";
	private static final String ATRIBUTE_WRONG_DATA = "wrongData";
	private static final String ATRIBUTE_CURRENT_SEX = "currentSex";
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		Validator validator = Validator.getValidator();
		String previousPage = request.getParameter(COMMAND_PARAMETER);
		
		if(previousPage != null){
			if(previousPage.equals("changeInformation") 
					&& httpRequest.getParameter(PARAMETER_MARK) != null){
				if(validator.checkMark(request.getParameter(PARAMETER_MARK))){
					chain.doFilter(request, response);
				} else {
					session.setAttribute(ATRIBUTE_WRONG_DATA, "mark");
					httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + 
							session.getAttribute(ATRIBUTE_CURRENT_PAGE).toString());
				}
			} else if(previousPage.equals("changeInformation") || previousPage.equals("newAdministrator")){
				if(checkUserProfileInfomation(httpRequest, validator, session)){
					chain.doFilter(request, response);
				} else {
					if(previousPage.equals("newAdministrator")){
						setProfileDataToSession(httpRequest, session);
					}
					httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + 
							session.getAttribute(ATRIBUTE_CURRENT_PAGE).toString());
				}
			} else if(previousPage.equals("registration")){
				if(checkUserProfileInfomation(httpRequest, validator, session)
						&& checkUserMarksInformation(httpRequest, validator, session)){
					chain.doFilter(request, response);
				} else {
					setProfileDataToSession(httpRequest, session);
					setMarksToSession(httpRequest, session);
					httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + 
							session.getAttribute(ATRIBUTE_CURRENT_PAGE).toString());
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	private boolean checkUserMarksInformation(HttpServletRequest request, Validator validator, HttpSession session){
		if(!validator.checkMark(request.getParameter(PARAMETER_MARK_ONE))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_MARK_ONE))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "mark");
			return false;
		}
		if(!validator.checkMark(request.getParameter(PARAMETER_MARK_TWO))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_MARK_TWO))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "mark");
			return false;
		}
		if(!validator.checkMark(request.getParameter(PARAMETER_MARK_THREE))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_MARK_THREE))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "mark");
			return false;
		}
		if(!validator.checkMark(request.getParameter(PARAMETER_CERTIFICATE))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_CERTIFICATE))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "mark");
			return false;
		}
		if(!validator.checkSubjects(request.getParameter(PARAMETER_SUBJECT_ONE), 
									request.getParameter(PARAMETER_SUBJECT_TWO), 
									request.getParameter(PARAMETER_SUBJECT_THREE))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_SUBJECT_ONE))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_SUBJECT_TWO))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_SUBJECT_THREE))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "subject");
			return false;
		}
		return true;
	}
	
	private boolean checkUserProfileInfomation(HttpServletRequest request, Validator validator, HttpSession session){
		if(!validator.checkEmail(request.getParameter(PARAMETER_EMAIL))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_EMAIL))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "email");
			return false;
		}
		if(!validator.checkName(request.getParameter(PARAMETER_LAST_NAME))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_LAST_NAME))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "lastName");
			return false;
		}
		if(!validator.checkName(request.getParameter(PARAMETER_FIRST_NAME))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_FIRST_NAME))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "firstName");
			return false;
		}
		if(!validator.checkPhone(request.getParameter(PARAMETER_PHONE))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_PHONE))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "phone");
			return false;
		}
		if(!validator.checkPassword(request.getParameter(PARAMETER_PASSWORD), 
									request.getParameter(PARAMETER_REPEATED_PASSWORD))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_PASSWORD))
				|| !validator.checkForInjections(request.getParameter(PARAMETER_REPEATED_PASSWORD))){
			session.setAttribute(ATRIBUTE_WRONG_DATA, "password");
			return false;
		}
		return true;
	}
	
	private void setProfileDataToSession(HttpServletRequest request, HttpSession session){
		session.setAttribute(PARAMETER_DAY, request.getParameter(PARAMETER_DAY));
		session.setAttribute(PARAMETER_MONTH, request.getParameter(PARAMETER_MONTH));
		session.setAttribute(PARAMETER_LAST_NAME, request.getParameter(PARAMETER_LAST_NAME));
		session.setAttribute(PARAMETER_FIRST_NAME, request.getParameter(PARAMETER_FIRST_NAME));
		session.setAttribute(PARAMETER_PHONE, request.getParameter(PARAMETER_PHONE));
		session.setAttribute(PARAMETER_YEAR, request.getParameter(PARAMETER_YEAR));
		session.setAttribute(PARAMETER_EMAIL, request.getParameter(PARAMETER_EMAIL));
		session.setAttribute(ATRIBUTE_CURRENT_SEX, request.getParameter(PARAMETER_SEX));
		session.setAttribute(PARAMETER_PASSWORD, request.getParameter(PARAMETER_PASSWORD));
		session.setAttribute(PARAMETER_REPEATED_PASSWORD, request.getParameter(PARAMETER_REPEATED_PASSWORD));
	}
	
	private void setMarksToSession(HttpServletRequest request, HttpSession session){
		session.setAttribute(PARAMETER_MARK_ONE, request.getParameter(PARAMETER_MARK_ONE));
		session.setAttribute(PARAMETER_MARK_TWO, request.getParameter(PARAMETER_MARK_TWO));
		session.setAttribute(PARAMETER_MARK_THREE, request.getParameter(PARAMETER_MARK_THREE));
		session.setAttribute(PARAMETER_SUBJECT_ONE, request.getParameter(PARAMETER_SUBJECT_ONE));
		session.setAttribute(PARAMETER_SUBJECT_TWO, request.getParameter(PARAMETER_SUBJECT_TWO));
		session.setAttribute(PARAMETER_SUBJECT_THREE, request.getParameter(PARAMETER_SUBJECT_THREE));
		session.setAttribute(PARAMETER_CERTIFICATE, request.getParameter(PARAMETER_CERTIFICATE));
	}
}
