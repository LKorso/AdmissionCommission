package com.admc.loader;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admc.dao.creators.Factory;
import com.admc.dao.creators.IDaoFactory;
import com.admc.enteties.Subject;

public class RegistrationPageLoader implements IPageLoader {
		private List<String> months;
		private List<String> sex;
		private HttpSession session;
		private List<String> subjects;
		private IDaoFactory daoFactory;
		
		private static final String ATRIBUTE_MONTHS = "months";
		private static final String ATRIBUTR_SEX = "sex";
		private static final String ATRIBUTE_SUBJECTS_LIST = "subjects_list";
		
		public RegistrationPageLoader(HttpServletRequest request, HttpServletResponse response){ 
			session = request.getSession();
			daoFactory = Factory.createDaoFactory(Factory.MYSQL);
		}
		
		@Override
		public void setAtributes() {
			load();
			
			session.setAttribute(ATRIBUTE_MONTHS, months);
			session.setAttribute(ATRIBUTR_SEX, sex);
			session.setAttribute(ATRIBUTE_SUBJECTS_LIST, subjects);
		}
		
		private void load(){
			setMonths();
			setSex();
			setSubjectsList();
		}
		
		private void setSubjectsList(){
			subjects = new ArrayList<>();
			for(Subject subject : daoFactory.getSubjectDao().selectAll()){
				subjects.add(subject.getName());
			}
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
