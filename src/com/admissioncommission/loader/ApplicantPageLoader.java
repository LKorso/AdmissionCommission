package com.admissioncommission.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admissioncommission.dao.IApplicantMarkDao;
import com.admissioncommission.dao.IExtendedApplicationDao;
import com.admissioncommission.dao.IFacultyDao;
import com.admissioncommission.dao.ISubjectDao;
import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.dao.creators.QueryBuilder;
import com.admissioncommission.enteties.ApplicantMark;
import com.admissioncommission.enteties.ApplicationMark;
import com.admissioncommission.enteties.ExtendedApplication;
import com.admissioncommission.enteties.Faculty;
import com.admissioncommission.enteties.User;
import com.admissioncommission.logic.ApplicationProcessor;

public class ApplicantPageLoader implements IPageLoader{
	private IDaoFactory daoFactory;
	private IFacultyDao facultyDao;
	private IExtendedApplicationDao applicationDao;
	private User currentUser;
	private List<ExtendedApplication> allApplications = new ArrayList<>();
	private List<List<ExtendedApplication>> passedApplications = new ArrayList<>();
	private HashMap<Integer, Double> ratings = new HashMap<>();
	private List<Faculty> selectedFaculties = new ArrayList<>();
	private List<ApplicantMark> applicantMarks = new ArrayList<>();
	private List<ExtendedApplication> rejectedApplications = new ArrayList<>();
	private List<ExtendedApplication> unreviewedApplications = new ArrayList<>();
	private HttpServletRequest request; 
	private HttpServletResponse response;
	
	private static final String ATRIBUTE_USER = "user";
	private static final String ATRIBUTE_MARKS = "marks";
	private static final String ATRIBUTE_FACULTIES = "faculties";
	private static final String ATRIBUTE_SUBJECTS = "subjects";
	private static final String ATRIBUTE_PASSED_APPLICATIONS = "passed_applications";
	private static final String ATRIBUTE_REJECTED_APPLICATIONS = "rejected_applications";
	private static final String ATRIBUTE_UNREVIEWED_APPLICATIONS = "unreviewed_applications";
	private static final String ATRIBUTE_RATING = "rating";
	private static final String ATRIBUTE_APPLICATION_STATUS = "applicationStatus";
	
	private static final String STATUS_PASSED = "Passed";
	private static final String STATUS_UNREVIEWED = "Unreviewed";
	private static final String STATUS_REJECTED = "Rejected";
	private static final String STATUS_UNACCEPTED = "Unaccepted";
	
	private static final int MAX_APPLICATIONS_NUMBER = 3;
	
	private int numberOfApplications;
	
	public ApplicantPageLoader(HttpServletRequest request, HttpServletResponse response){
		this.currentUser = (User) request.getSession().getAttribute("user");
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		this.request = request;
		this.response = response;
		facultyDao = daoFactory.getFacultyDao();
		applicationDao = daoFactory.getExtendedApplicationDao();
	}
	
	@Override
	public void setAtributes(){
		load();
		HttpSession session = request.getSession(true);
		if(request.getParameter("remove") == null){
			if(session.getAttribute("reload") != null &&
					session.getAttribute("reload").equals("TRUE")){
				reloadUser();
				session.setAttribute(ATRIBUTE_USER, currentUser);
			}
			session.setAttribute(ATRIBUTE_MARKS, applicantMarks);
			session.setAttribute(ATRIBUTE_SUBJECTS, getSubjects(applicantMarks));
			if(numberOfApplications < MAX_APPLICATIONS_NUMBER){
				session.setAttribute(ATRIBUTE_FACULTIES, facultyDao.selectAll());
			}
			if(allApplications.isEmpty()){
				session.setAttribute(ATRIBUTE_APPLICATION_STATUS, true);
			} else {
				session.setAttribute(ATRIBUTE_APPLICATION_STATUS, false);
			}
			if(passedApplications.size() > 0){
				session.setAttribute(ATRIBUTE_PASSED_APPLICATIONS, passedApplications);
			} else {
				session.removeAttribute(ATRIBUTE_PASSED_APPLICATIONS);
			}
			session.setAttribute(ATRIBUTE_RATING, ratings);
		}
		if(rejectedApplications.size() > 0){
			session.setAttribute(ATRIBUTE_REJECTED_APPLICATIONS, rejectedApplications);
		} else {
			session.removeAttribute(ATRIBUTE_REJECTED_APPLICATIONS);
		}
		if(unreviewedApplications.size() > 0){
			session.setAttribute(ATRIBUTE_UNREVIEWED_APPLICATIONS, unreviewedApplications);
		} else {
			session.removeAttribute(ATRIBUTE_UNREVIEWED_APPLICATIONS);
		}
	}
	
	private void load(){
		setMarks();
		setAllApplicantAplications();
		numberOfApplications = countApplications();
		setSelectedFaculties();
		setPassedApplications();
		setRejectedApplications();
		setUnreviewedApplications();
		setRatings();
	}
	
	private void setMarks(){
		applicantMarks = daoFactory.getApplicantMarkDao().findByApplicantId(currentUser.getId());
	}
	
	private HashMap<Integer, String> getSubjects(List<ApplicantMark> marks){
		HashMap<Integer, String>  subjects = new HashMap<>();
		ISubjectDao subjectDao = daoFactory.getSubjectDao();
		
		for(ApplicantMark mark : marks){
			subjects.put(mark.getSubjectId(), subjectDao.findById(mark.getSubjectId()).getName());
		}
		
		return subjects;
	}
	
	private void setAllApplicantAplications(){
		allApplications = applicationDao.findByCustomId(currentUser.getId());
	}
	
	private int countApplications(){
		return allApplications.size();
	}
	
	private void setSelectedFaculties(){
		for(ExtendedApplication application : allApplications){
			if(application.getStatus().equals(STATUS_PASSED)){
				selectedFaculties.add(facultyDao.findById(application.getFacultyId()));
			}
		}
	}
	
	private void setPassedApplications(){
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("status", STATUS_PASSED);
		for(Faculty faculty : selectedFaculties){
			criterions.put("faculty_id", Integer.toString(faculty.getId()));
			List<ExtendedApplication> currentApplications = new ArrayList<>();
			currentApplications = applicationDao.customSelectSorted(criterions, QueryBuilder.ORDER_DESC);
			if(!currentApplications.isEmpty()){
				passedApplications.add(currentApplications);
			}
		}
	}
	
	private void setRejectedApplications(){
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("status", STATUS_REJECTED);
		criterions.put("user_id", Integer.toString(currentUser.getId()));
		rejectedApplications.addAll(applicationDao.customSelect(criterions));
	}
	
	private void setUnreviewedApplications(){
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("status", STATUS_UNREVIEWED);
		criterions.put("user_id", Integer.toString(currentUser.getId()));
		unreviewedApplications.addAll(applicationDao.customSelect(criterions));
	}
	
	private void setRatings(){
		for(List<ExtendedApplication> appList : passedApplications){
			for(ExtendedApplication application : appList){
				List<ApplicantMark> marks = new ArrayList<>();
				for(ApplicationMark applicationMark : daoFactory.getApplicationMarkDao().
						findByApplicationId(application.getApplicationId())){
					marks.add(daoFactory.getApplicantMarkDao().findById(applicationMark.getMarkId()));
				}
				ratings.put(application.getApplicationId(), 
						ApplicationProcessor.getRatingMark(marks));
			}
		}
	}
	
	private void reloadUser(){
		currentUser = daoFactory.getUserDao().findById(currentUser.getId());
	}
}
