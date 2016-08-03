package com.epam.admissioncommission.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.admissioncommission.dao.creators.Factory;
import com.epam.admissioncommission.dao.creators.IDaoFactory;
import com.epam.admissioncommission.enteties.ApplicantMark;
import com.epam.admissioncommission.enteties.ApplicationMark;
import com.epam.admissioncommission.enteties.ExtendedApplication;
import com.epam.admissioncommission.enteties.Subject;
import com.epam.admissioncommission.enteties.User;

public class AdministratorPageLoader implements IPageLoader{
	private IDaoFactory daoFactory;
	private List<ExtendedApplication> unreviewedApplications = new ArrayList<>();
	private HashMap<Integer, List<ApplicantMark>> marks = new HashMap<>();
	private HashMap<Integer, Subject> subjects = new HashMap<>();
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private HttpSession session;
	
	private static final String ATRIBUTE_USER = "user";
	private static final String ATRIBUTE_APPLICATIONS = "unreviewedApplications";
	private static final String ATRIBUTE_MARKS = "applicationMarks";
	private static final String ATRIBUTE_SUBJECTS = "subjects";
	
	private static final String STATUS_UNREVIEWED = "Unreviewed";
	
	public AdministratorPageLoader(HttpServletRequest request, HttpServletResponse response){
		session = request.getSession();
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		this.request = request;
		this.response = response;
	}
	
	@Override
	public void setAtributes(){
		load();
		if(session.getAttribute("reload") != null){
			User currentUser = daoFactory.getUserDao().findById(((User) session.getAttribute("user")).getId());
			session.removeAttribute("reload");
			session.setAttribute(ATRIBUTE_USER, currentUser);
		}
		session.setAttribute(ATRIBUTE_APPLICATIONS, unreviewedApplications);
		session.setAttribute(ATRIBUTE_MARKS, marks);
		session.setAttribute(ATRIBUTE_SUBJECTS, subjects);
	}
	
	private void load(){
		setUnreviewedApplications();
		setSubjects();
		setMarks();
	}
	
	private void setUnreviewedApplications(){
		HashMap<String, String> criterions = new HashMap<>();
		criterions.put("status", STATUS_UNREVIEWED);
		unreviewedApplications = daoFactory.getExtendedApplicationDao().customSelect(criterions);
	}
	
	
	private void setMarks(){
		List<ApplicantMark> currentMarks;
		for(ExtendedApplication application : unreviewedApplications){
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
	
	/*private void setMarksAndSubjects(){
		
		subjects = new HashMap<>();
		for(ExtendedApplication application : unreviewedApplications){
			List<Double> appMarks = new ArrayList<>();
			List<String> appSubjects = new ArrayList<>();
			final IApplicantMarkDao APPLICANT_MARK_DAO = daoFactory.getApplicantMarkDao();
			final IApplicationMarkDao APPLICATION_MARK_DAO = daoFactory.getApplicationMarkDao();
			final ISubjectDao SUBJECT_DAO = daoFactory.getSubjectDao();
			
			for(ApplicationMark applicationMarks : APPLICATION_MARK_DAO.
					findByApplicationId(application.getApplicationId())){
				ApplicantMark currentMark = APPLICANT_MARK_DAO.findById(applicationMarks.getMarkId());
				appMarks.add(currentMark.getMark());
				appSubjects.add(SUBJECT_DAO.findById(currentMark.getSubjectId()).getName());
			}
			
			marks.put(application.getApplicationId(), appMarks);
			subjects.put(application.getApplicationId(), appSubjects);
		}
	}*/
}
