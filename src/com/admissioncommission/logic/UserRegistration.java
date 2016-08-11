package com.admissioncommission.logic;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.enteties.ApplicantMark;
import com.admissioncommission.enteties.User;

public class UserRegistration {
	private HttpServletRequest request;
	private User newUser;
	private IDaoFactory daoFactory;
	
	private static final String PARAMETER_FIRST_NAME = "firstName";
	private static final String PARAMETER_LAST_NAME = "lastName";
	private static final String PARAMETER_SEX = "sex";
	private static final String PARAMETER_PHONE = "phone";
	private static final String PARAMETER_EMAIL = "email";
	private static final String PARAMETER_PASSWORD = "password_one";
	
	public UserRegistration(HttpServletRequest request){
		this.request = request;
		daoFactory = Factory.createDaoFactory(Factory.MYSQL);
	}
	
	public void register(){
		switch (request.getParameter("command")) {
		case "registration":
			registerAplicant();
			break;
		case "newAdministrator":
			registerAdministrator();
			break;
		default:
			break;
		}
	}
	
	private void registerAdministrator(){
		createUser("Administrator");
	}
	
	private void registerAplicant(){
		createUser("Applicant");
		setMarks();
	}
	
	private void createUser(String userType){
		newUser = new User();
		
		newUser.setFirstName(request.getParameter(PARAMETER_FIRST_NAME));
		newUser.setLastName(request.getParameter(PARAMETER_LAST_NAME));
		newUser.setEmail(request.getParameter(PARAMETER_EMAIL));
		newUser.setPhone(request.getParameter(PARAMETER_PHONE));
		newUser.setSex(request.getParameter(PARAMETER_SEX));
		newUser.setUserTypeId(daoFactory.getUserTypeDao().findByType(userType).getId());
		newUser.setDateOfBirth(getDateOfBirth());
		newUser.setPassword(request.getParameter(PARAMETER_PASSWORD));
		
		daoFactory.getUserDao().insert(newUser, userType);
	}
	
	private Date getDateOfBirth(){
		StringBuilder builder = new StringBuilder();
		
		builder.append(request.getParameter("year") + "-");
		builder.append(request.getParameter("month") + "-");
		builder.append(request.getParameter("day"));
		
		return Date.valueOf(builder.toString());
	}
	
	private void setMarks(){
		setMark("Certificate", Double.parseDouble(request.getParameter("certificate")));
		setMark(request.getParameter("subject_one"), Double.parseDouble(request.getParameter("mark_one")));
		setMark(request.getParameter("subject_two"), Double.parseDouble(request.getParameter("mark_two")));
		setMark(request.getParameter("subject_three"), Double.parseDouble(request.getParameter("mark_three")));
	}
		
	private void setMark(String subject, double mark){
		ApplicantMark newMark = new ApplicantMark();
		newMark.setMark(mark);
		newMark.setSubjectId(daoFactory.getSubjectDao().findByName(subject).getId());
		newMark.setApplicantId(daoFactory
										.getUserDao()
										.findByEmailPassword(request.getParameter(PARAMETER_EMAIL), 
												request.getParameter(PARAMETER_PASSWORD)).getId());
		daoFactory.getApplicantMarkDao().insert(newMark);
	}
}
