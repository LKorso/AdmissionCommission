package com.admissioncommission.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.admissioncommission.dao.creators.Factory;
import com.admissioncommission.dao.creators.IDaoFactory;
import com.admissioncommission.enteties.ApplicantMark;
import com.admissioncommission.enteties.User;

public class InformationChanger {
	private HttpSession session = null;
	private IDaoFactory daoFactory = null;
	private User currentUser = null;
	private HttpServletRequest request = null;
	private List<ApplicantMark> marks = new ArrayList<>();
	private ApplicantMark currentMark;
	
	private HashMap<String, String> changes = new HashMap<>();
	
	private static final String LAST_NAME = "last_name";
	private static final String FIRST_NAME = "first_name";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String PASSWORD = "password";
	private static final String ID = "id";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	
	public InformationChanger(HttpServletRequest request){
		this.request = request;
		session = request.getSession();
		this.daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		currentUser = (User) session.getAttribute("user");
	}
	
	public void changeInformation(){
		if(request.getParameter("changeMarkId") != null){
			marks.addAll((List<ApplicantMark>) session.getAttribute("marks"));
			currentMark = marks.stream()
										.filter(mark -> mark.getId() == Integer.parseInt(request.getParameter("changeMarkId")))
										.findFirst()
										.get();
			changeMark();
			changeSubject();
		} else if(request.getParameter("addMark") != null){
			ApplicantMark newMark = new ApplicantMark();
			newMark.setApplicantId(currentUser.getId());
			newMark.setMark(Double.parseDouble(request.getParameter("mark")));
			newMark.setSubjectId(daoFactory.getSubjectDao().findByName(request.getParameter("subject")).getId());
			daoFactory.getApplicantMarkDao().insert(newMark);
		} else if(request.getParameter("deleteMark") != null) {
			daoFactory.getApplicantMarkDao().delete(Integer.parseInt(request.getParameter("deleteMark")));
		} else {
			check(currentUser.getLastName(), LAST_NAME);
			check(currentUser.getFirstName(), FIRST_NAME);
			check(currentUser.getSex(), SEX);
			check(currentUser.getEmail(), EMAIL);
			check(currentUser.getPhone(), PHONE);
			checkBirthDate();
			
			if(!changes.isEmpty()){
				HashMap<String, String> criterions = new HashMap<>();
				criterions.put(ID, currentUser.getId() + "");
				daoFactory.getUserDao().update(changes, criterions);
			}
		}
		
	}
	
	private void check(String currentValue, String parameter){
		if(!currentValue.equals(request.getParameter(parameter))){
			changes.put(parameter, request.getParameter(parameter));
		}
	}
	
	private void checkBirthDate(){
		String changedBirthDate = request.getParameter("year") + "-" + request.getParameter("month") 
									+ "-" + request.getParameter("day");
		if(!currentUser.getDateOfBirth().equals(changedBirthDate)){
			changes.put(DATE_OF_BIRTH, changedBirthDate);
		}
	}
	
	private void changeMark(){
		double currentMarkValue = Double.parseDouble(request.getParameter("mark"));
		if(currentMarkValue != currentMark.getMark()){
			daoFactory.getApplicantMarkDao().updateMark(currentMark.getId(), currentMarkValue);
		}
	}
	
	private void changeSubject(){
		String currentSubject = daoFactory.getSubjectDao().findById(currentMark.getSubjectId()).getName();
		String newSubject = request.getParameter("subject");
		if(!newSubject.equals(currentSubject)){
			daoFactory.getApplicantMarkDao().updateSubject(currentMark.getId(), 
						daoFactory.getSubjectDao().findByName(newSubject).getId());
		}
	}
}
