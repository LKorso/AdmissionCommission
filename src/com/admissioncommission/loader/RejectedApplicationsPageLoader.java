package com.admissioncommission.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.enteties.ApplicantMark;
import com.admissioncommission.enteties.ApplicationMark;
import com.admissioncommission.enteties.ExtendedApplication;
import com.admissioncommission.enteties.Subject;

public class RejectedApplicationsPageLoader implements IPageLoader {
	private IDaoFactory daoFactory;
	private List<ExtendedApplication> rejectedApplications = new ArrayList<>();
	private HashMap<Integer, List<ApplicantMark>> marks = new HashMap<>();
	private HashMap<Integer, Subject> subjects = new HashMap<>();
	private HttpSession session;
	
	private static final String ATRIBUTE_APPLICATIONS = "rejectedApplications";
	private static final String ATRIBUTE_MARKS = "applicationMarks";
	private static final String ATRIBUTE_SUBJECTS = "subjects";
	
	private static final String STATUS_REJECTED = "Rejected";
	
	public RejectedApplicationsPageLoader(HttpServletRequest request, HttpServletResponse response){
		session = request.getSession();
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
	}
	
	@Override
	public void setAtributes() {
		load();
		session.setAttribute(ATRIBUTE_APPLICATIONS, rejectedApplications);
		session.setAttribute(ATRIBUTE_MARKS, marks);
		session.setAttribute(ATRIBUTE_SUBJECTS, subjects);
	}
	
	private void load(){
		setRejectedApplications();
		setSubjects();
		setMarks();
	}
	
	private void setRejectedApplications(){
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("status", STATUS_REJECTED);
		rejectedApplications = daoFactory.getExtendedApplicationDao().customSelect(criterions);
	}
	
	private void setMarks(){
		List<ApplicantMark> currentMarks;
		for(ExtendedApplication application : rejectedApplications){
			currentMarks = new ArrayList<>();
			for(ApplicationMark applicationMark : daoFactory.getApplicationMarkDao().findByApplicationId(application.getApplicationId())){
				currentMarks.add(daoFactory.getApplicantMarkDao().findById(applicationMark.getMarkId()));
			}
			marks.put(application.getApplicationId(), currentMarks);
		}
	}
	
	private void setSubjects(){
		for(Subject subject : daoFactory.getSubjectDao().selectAll()){
			subjects.put(subject.getId(), subject);
		}
	}
}
