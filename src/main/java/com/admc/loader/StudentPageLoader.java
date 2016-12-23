package com.admc.loader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admc.dao.creators.Factory;
import com.admc.dao.creators.IDaoFactory;
import com.admc.enteties.Faculty;
import com.admc.enteties.User;

public class StudentPageLoader implements IPageLoader{
	private User currentUser;
	private IDaoFactory daoFactory;
	private Faculty faculty;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	
	private static final String ATRIBUTE_USER = "user";
	private static final String FACULTY = "faculty";
	
	public StudentPageLoader(HttpServletRequest request, HttpServletResponse response){
		this.currentUser = (User) request.getSession().getAttribute("user");
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void setAtributes(){
		HttpSession session = request.getSession();
		if(session.getAttribute("reload") == null){
			load();
			session.setAttribute(FACULTY, faculty.getName());
		} else if(session.getAttribute("reload").equals("TRUE")){
			reloadUser();
			load();
			session.setAttribute(ATRIBUTE_USER, currentUser);
		}
	}
	
	private void load(){
		setFaculty();
	}
	
	private void setFaculty(){
		faculty = daoFactory.getFacultyDao().findById(currentUser.getFacultyId());
	}
	
	private void reloadUser(){
		currentUser = daoFactory.getUserDao().findById(currentUser.getId());
	}
}
