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
import com.epam.admissioncommission.enteties.Faculty;
import com.epam.admissioncommission.enteties.FacultySubject;
import com.epam.admissioncommission.enteties.Subject;
import com.epam.admissioncommission.enteties.User;

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
	private ApplicantMark certificate;
	
	private static final String ATRIBUTE_FACULTY = "faculty";
	private static final String ATRIBUTE_MISSING_MARKS = "missing_marks";
	private static final String ATRIBUTE_SUBJECTS = "subjects";
	private static final String ATRIBUTE_CURRENT_MARKS = "current_marks";
	private static final String ATRIBUTE_CERTIFICATE = "certificate";
	private static final String ATRIBUTE_LOW_RATING = "low_rating";
	private static final String ATRIBUTE_MIN_RATING = "min_marks";
	
	private static int certificetId;
	
	public FacultyPageLoader(HttpServletRequest request, HttpServletResponse response){
		this.currentUser = (User) request.getSession().getAttribute("user");
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		this.request = request;
		this.response = response;
		session = request.getSession();
		faculty = loadFaculty();
		facultySubjects.addAll(loadFacultySubjects());
		applicantMarks.addAll((List<ApplicantMark>) session.getAttribute("marks"));
		certificetId = daoFactory.getSubjectDao().findByName("Certificate").getId();
		certificate = loadCertificate();
	}
	
	
	@Override
	public void setAtributes() {
		session.setAttribute(ATRIBUTE_FACULTY, faculty);
		loadSubjectsAndMarks();
		session.setAttribute(ATRIBUTE_SUBJECTS, subjects);
		session.setAttribute(ATRIBUTE_CURRENT_MARKS, marks);
		session.setAttribute(ATRIBUTE_CERTIFICATE, certificate);
		session.setAttribute(ATRIBUTE_MIN_RATING, minMarks);
		session.setAttribute(ATRIBUTE_MISSING_MARKS, arentMarksCorrect());
		session.setAttribute(ATRIBUTE_LOW_RATING, whetherntEnoughPoints());
	}
	
	private boolean whetherntEnoughPoints(){
		for(FacultySubject subject : facultySubjects){
			if(marks.get(subject.getSubjectId()).getMark() < subject.getMinMark()){
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
	
	private List<FacultySubject> loadFacultySubjects(){
		return daoFactory.getFacultySubjectDao().findByFacultyId(faculty.getId());
	}
	
	private Faculty loadFaculty(){
		return daoFactory.getFacultyDao().findById(Integer.parseInt(request.getParameter("faculty_id")));
	}
	
	private void loadSubjectsAndMarks(){
		for(FacultySubject subject : facultySubjects){
			subjects.add(daoFactory.getSubjectDao().findById(subject.getSubjectId()));
			minMarks.put(subject.getSubjectId(), subject);
			loadMark(subject);
		}
	}
	
	private void loadMark(FacultySubject subject){
		ApplicantMark currentMark = null;
		for(ApplicantMark mark : applicantMarks){
			if(mark.getSubjectId() == subject.getSubjectId()){
				currentMark = mark;
			}
		}  
		marks.put(subject.getSubjectId(), currentMark);
	}
	
	private ApplicantMark loadCertificate(){
		return applicantMarks.stream().filter(mark -> mark.getSubjectId() == certificetId).findFirst().get();
	}
}
