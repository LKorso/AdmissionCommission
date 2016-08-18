package com.admissioncommission.loader;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admissioncommission.enteties.User;

public class AdministratorChangeInformationPageLoader implements IPageLoader {
	private String monthOfBirth;
	private String dayOfBirth;
	private String yearOfBirth;
	private List<String> months;
	private List<String> sex;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private User currentUser;
	
	private static final String ATRIBUTE_YEAR_OF_BIRTH = "yearOfBirth";
	private static final String ATRIBUTE_MONTH_OF_BIRTH = "monthOfBirth";
	private static final String ATRIBUTE_DAY_OF_BIRTH = "dayOfBirth";
	private static final String ATRIBUTE_MONTHS = "months";
	private static final String ATRIBUTR_SEX = "sex";
	
	public AdministratorChangeInformationPageLoader(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		session = request.getSession();
	}
	
	@Override
	public void setAtributes() {
		load();
		
		session.setAttribute(ATRIBUTE_MONTH_OF_BIRTH, monthOfBirth);
		session.setAttribute(ATRIBUTE_MONTHS, months);
		session.setAttribute(ATRIBUTE_YEAR_OF_BIRTH, yearOfBirth);
		session.setAttribute(ATRIBUTE_DAY_OF_BIRTH, dayOfBirth);
		session.setAttribute(ATRIBUTR_SEX, sex);
	}
	
	private void load(){
		getCurrentUser();
		setMonthOfBirth();
		setDayOfBirth();
		setYearOfBirth();
		setMonths();
		setSex();
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
		sex.add("F");
	}
}
