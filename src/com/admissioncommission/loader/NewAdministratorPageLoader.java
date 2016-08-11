package com.admissioncommission.loader;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewAdministratorPageLoader implements IPageLoader {
	private List<String> months;
	private List<String> sex;
	private HttpSession session;
	
	private static final String ATRIBUTE_MONTHS = "months";
	private static final String ATRIBUTR_SEX = "sex";
	
	public NewAdministratorPageLoader(HttpServletRequest request, HttpServletResponse response){ 
		session = request.getSession();
	}
	
	@Override
	public void setAtributes() {
		load();
		
		session.setAttribute(ATRIBUTE_MONTHS, months);
		session.setAttribute(ATRIBUTR_SEX, sex);
	}
	
	private void load(){
		setMonths();
		setSex();
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
}
