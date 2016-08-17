package com.admissioncommission.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admissioncommission.dao.ISubjectDao;
import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.enteties.ApplicantMark;
import com.admissioncommission.enteties.Subject;
import com.admissioncommission.enteties.User;

public class ApplicantChangeInformationPageLoader implements IPageLoader {
	private String monthOfBirth;
	private String dayOfBirth;
	private String yearOfBirth;
	private List<String> months;
	private List<String> sex;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private List<String> subjects;
	private List<String> vacantSubjects = new ArrayList<>();
	private List<ApplicantMark> marks = new ArrayList<>();
	private IDaoFactory daoFactory;
	private User currentUser;
	
	
	private static final String ATRIBUTE_YEAR_OF_BIRTH = "yearOfBirth";
	private static final String ATRIBUTE_MONTH_OF_BIRTH = "monthOfBirth";
	private static final String ATRIBUTE_DAY_OF_BIRTH = "dayOfBirth";
	private static final String ATRIBUTE_MONTHS = "months";
	private static final String ATRIBUTR_SEX = "sex";	
	private static final String ATRIBUTE_SUBJECTS_LIST = "subjects_list";
	private static final String ATRIBUTE_VACANT_SUBJECTS = "vacant_subjects";
	
	public ApplicantChangeInformationPageLoader(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		session = request.getSession();
		daoFactory = Factory.createDaoFactory(Factory.MYSQL);
	}

	@Override
	public void setAtributes() {
		load();
		
		if(request.getParameter("changeMarkId") != null || 
				request.getParameter("addMark") != null ||
				request.getParameter("deleteMark") != null){
			session.setAttribute("marks", marks);
			session.setAttribute("subjects", getSubjects(marks));
		}
		
		session.setAttribute(ATRIBUTE_MONTH_OF_BIRTH, monthOfBirth);
		session.setAttribute(ATRIBUTE_MONTHS, months);
		session.setAttribute(ATRIBUTE_YEAR_OF_BIRTH, yearOfBirth);
		session.setAttribute(ATRIBUTE_DAY_OF_BIRTH, dayOfBirth);
		session.setAttribute(ATRIBUTR_SEX, sex);
		session.setAttribute(ATRIBUTE_SUBJECTS_LIST, subjects);
		session.setAttribute(ATRIBUTE_VACANT_SUBJECTS, vacantSubjects);
	}
	
	private void load(){
		getCurrentUser();
		setMonthOfBirth();
		setDayOfBirth();
		setYearOfBirth();
		setMonths();
		setSex();
		setSubjectsList();
		setMarks();
		setVacantSubjects();
	}
	
	private void setSubjectsList(){
		subjects = new ArrayList<>();
		for(Subject subject : daoFactory.getSubjectDao().selectAll()){
			subjects.add(subject.getName());
		}
	}
	
	private void getCurrentUser(){
		currentUser = (User) session.getAttribute("user");
	}
	
	private void setMonthOfBirth() {
		monthOfBirth = currentUser.getDateOfBirth().toString().substring(5, 7);
	}

	private void setDayOfBirth() {
		dayOfBirth = currentUser.getDateOfBirth().toString().substring(8, 10);
	}

	private void setYearOfBirth() {
		yearOfBirth = currentUser.getDateOfBirth().toString().substring(0, 4);
	}

	private void setMonths() {
		months = new ArrayList<>();
		for(int i = 1; i <= 12; i++){
			if(i < 10){
				months.add("0" + i);
			}else {
				months.add(i + "");
			}	
		}
	}
	
	private void setSex(){
		sex = new ArrayList<>();
		sex.add("M");
		sex.add("W");
	}
	
	private HashMap<Integer, String> getSubjects(List<ApplicantMark> marks){
		HashMap<Integer, String>  subjects = new HashMap<>();
		ISubjectDao subjectDao = daoFactory.getSubjectDao();
		
		for(ApplicantMark mark : marks){
			subjects.put(mark.getSubjectId(), subjectDao.findById(mark.getSubjectId()).getName());
		}
		
		return subjects;
	}
	
	private void setVacantSubjects(){
		vacantSubjects.addAll(subjects);
		
		for(String subject : getSubjects(marks).values()){
			for(int i = 0; i < vacantSubjects.size(); i++){
				if(vacantSubjects.get(i).equals(subject)){
					vacantSubjects.remove(i);
				}
			}
		}
	}
	
	private void setMarks(){
		marks = daoFactory.getApplicantMarkDao().findByApplicantId(currentUser.getId());
	}
}
