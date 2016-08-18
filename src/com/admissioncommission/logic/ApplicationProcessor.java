package com.admissioncommission.logic;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.enteties.ApplicantMark;
import com.admissioncommission.enteties.Application;
import com.admissioncommission.enteties.ApplicationMark;
import com.admissioncommission.enteties.Faculty;
import com.admissioncommission.enteties.User;

public class ApplicationProcessor {
	private User currentUser;
	private Faculty currentFaculty;
	private HttpSession session;
	private HttpServletRequest request;
	private IDaoFactory daoFactory;
	
	public ApplicationProcessor(HttpServletRequest request){
		this.request = request;
		session = request.getSession();
		currentUser = (User) session.getAttribute("user");
		daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		currentFaculty = (Faculty) session.getAttribute("faculty");
	}
	
	public ApplicationProcessor() {
		daoFactory = Factory.createDaoFactory(Factory.MYSQL);
	}
	
	public static double getRatingMark(List<ApplicantMark> marks){
		double rating = 0;
		
		for(ApplicantMark mark : marks){
			rating += mark.getMark();
		}
		rating /= marks.size();
		
		return rating;
	}
	
	public void processApplication(String applicationId, String status){
		HashMap<String, String> changes = new HashMap<>();
		changes.put("status_id", Integer.toString(daoFactory.getApplicationStatusDao().findByStatus(status).getId()));
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("id", applicationId);
		daoFactory.getApplicationDao().update(changes, criterions);
	}
	
	public void addDescription(int applicationId, String description){
		daoFactory.getApplicationDao().updateDescription(applicationId, description);
	}
	
	public void deleteApplication(int applicationId){
		daoFactory.getApplicationDao().delete(applicationId);
	}
	
	public void addApplication(){
		Application newApplication = new Application();
		newApplication.setApplicantId(currentUser.getId());
		newApplication.setDate(Date.valueOf(getCurrentDate()));
		newApplication.setFacultyId(currentFaculty.getId());
		newApplication.setStatusId(daoFactory.getApplicationStatusDao().findByStatus("Unreviewed").getId());
		newApplication.setPriorityId(Integer.parseInt(request.getParameter("priority_id")));
		daoFactory.getApplicationDao().insert(newApplication);
	}
	
	public void attachMarks(){
		HashMap<Integer, ApplicantMark> marks = (HashMap<Integer, ApplicantMark>) session.getAttribute("current_marks");
		Set<Integer> keys = marks.keySet();
		for(Integer key : keys){
			attacheMark(marks.get(key));
		}
		attacheMark((ApplicantMark) session.getAttribute("certificate"));
	}
	
	private void attacheMark(ApplicantMark mark){
		ApplicationMark newMark = new ApplicationMark();
		newMark.setMarkId(mark.getId());
		newMark.setApplicationId(daoFactory.getApplicationDao().customSelect(createCriterions()).get(0).getId());
		daoFactory.getApplicationMarkDao().insert(newMark);
	}
	
	private HashMap<String, String> createCriterions(){
		HashMap<String, String> criterions = new HashMap<>();
		
		criterions.put("applicant_id", Integer.toString(currentUser.getId()));
		criterions.put("faculty_id", Integer.toString(currentFaculty.getId()));
		
		return criterions;
	}
	
	private String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		return dateFormat.format(date);
	}
}
