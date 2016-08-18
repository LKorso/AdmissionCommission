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
import com.admissioncommission.enteties.Application;
import com.admissioncommission.enteties.ExtendedApplication;
import com.admissioncommission.enteties.Faculty;
import com.admissioncommission.enteties.FacultySubject;
import com.admissioncommission.enteties.Priority;
import com.admissioncommission.enteties.Subject;
import com.admissioncommission.enteties.User;

public class FacultyPageLoader implements IPageLoader {
	private User currentUser;
	private IDaoFactory daoFactory;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private HttpSession session;
	private Faculty faculty;
	private List<FacultySubject> facultySubjects = new ArrayList<>();
	private List<Subject> subjects = new ArrayList<>();
	private HashMap<Integer, ApplicantMark> marks = new HashMap<>();
	private HashMap<Integer, FacultySubject> minMarks = new HashMap<>();
	private List<ApplicantMark> applicantMarks = new ArrayList<>();
	private List<Priority> vacantPriorities = new ArrayList<>();
	private ApplicantMark certificate;
	
	private static final String ATRIBUTE_FACULTY = "faculty";
	private static final String ATRIBUTE_MISSING_MARKS = "missing_marks";
	private static final String ATRIBUTE_SUBJECTS = "faculty_subjects";
	private static final String ATRIBUTE_CURRENT_MARKS = "current_marks";
	private static final String ATRIBUTE_CERTIFICATE = "certificate";
	private static final String ATRIBUTE_LOW_RATING = "low_rating";
	private static final String ATRIBUTE_MIN_RATING = "min_marks";
	private static final String ATRIBUTE_PRIORITIES = "priorities";
	
	private static int certificateId;
	
	public FacultyPageLoader(HttpServletRequest request, HttpServletResponse response){
		this.currentUser = (User) request.getSession().getAttribute("user");
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		this.request = request;
		this.response = response;
		session = request.getSession();
		faculty = getFaculty();
		facultySubjects.addAll(getFacultySubjects());
		applicantMarks.addAll((List<ApplicantMark>) session.getAttribute("marks"));
		certificateId = daoFactory.getSubjectDao().findByName("Certificate").getId();
		certificate = getCertificate();
		setVacantPriorities();
	}
	
	
	@Override
	public void setAtributes() {
		session.setAttribute(ATRIBUTE_FACULTY, faculty);
		setSubjectsAndMarks();
		session.setAttribute(ATRIBUTE_SUBJECTS, subjects);
		session.setAttribute(ATRIBUTE_CURRENT_MARKS, marks);
		session.setAttribute(ATRIBUTE_CERTIFICATE, certificate);
		session.setAttribute(ATRIBUTE_MIN_RATING, minMarks);
		session.setAttribute(ATRIBUTE_MISSING_MARKS, arentMarksCorrect());
		session.setAttribute(ATRIBUTE_LOW_RATING, whetherntEnoughPoints());
		session.setAttribute(ATRIBUTE_PRIORITIES, vacantPriorities);
	}
	
	private boolean whetherntEnoughPoints(){
		for(FacultySubject subject : facultySubjects){
			if((marks.get(subject.getSubjectId()) != null) 
					&& (marks.get(subject.getSubjectId()).getMark() < subject.getMinMark())){
				return true;
			}
		}
		return false;
	}
	
	private boolean arentMarksCorrect(){
		for(FacultySubject facultySubject : facultySubjects){
			if(marks.get(facultySubject.getSubjectId()) == null){
				return true;
			}
		}
		return false;
	}
	
	private void setVacantPriorities(){
		for(Priority priority : daoFactory.getPriorityDao().selectAll()){
			boolean additionalParameter = true;
			for(Application application : daoFactory.getApplicationDao().findByApplicantId(currentUser.getId())){
				if(priority.getId() == application.getPriorityId()){
					additionalParameter = false;
					break;
				}
			}
			if(additionalParameter){
				vacantPriorities.add(priority);
			}
		}
	}
	
	private List<FacultySubject> getFacultySubjects(){
		return daoFactory.getFacultySubjectDao().findByFacultyId(faculty.getId());
	}
	
	private Faculty getFaculty(){
		return daoFactory.getFacultyDao().findById(Integer.parseInt(request.getParameter("faculty_id")));
	}
	
	private void setSubjectsAndMarks(){
		for(FacultySubject subject : facultySubjects){
			subjects.add(daoFactory.getSubjectDao().findById(subject.getSubjectId()));
			minMarks.put(subject.getSubjectId(), subject);
			setMark(subject);
		}
	}
	
	private void setMark(FacultySubject subject){
		ApplicantMark currentMark = null;
		for(ApplicantMark mark : applicantMarks){
			if(mark.getSubjectId() == subject.getSubjectId()){
				currentMark = mark;
			}
		}  
		marks.put(subject.getSubjectId(), currentMark);
	}
	
	private ApplicantMark getCertificate(){
		return applicantMarks.stream().filter(mark -> mark.getSubjectId() == certificateId).findFirst().get();
	}
}
