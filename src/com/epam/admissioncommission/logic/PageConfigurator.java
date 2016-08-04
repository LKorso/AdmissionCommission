package com.epam.admissioncommission.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PageConfigurator {
	public static final String LOGIN_PAGE = "LOGIN";
	public static final String ADMIN_PAGE = "ADMIN";
	public static final String APPLICANT_PAGE = "APPLICANT";
	public static final String ERROR_PAGE = "ERROR";
	public static final String FACULTY_PAGE = "FACULTY";
	public static final String REGISTRATION_PAGE = "REGISTRATION";
	public static final String STUDENT_PAGE = "STUDENT";
	public static final String CHANGE_STUDENT_INFORMATION_PAGE = "CHANGE_STUDENT_INFORMATION";
	public static final String CHANGE_APPLICANT_INFORMATION_PAGE = "CHANGE_APPLICANT_INFORMATION";
	public static final String CHANGE_ADMINISTRATOR_INFORMATION_PAGE = "CHANGE_ADMINISTRATOR_INFORMATION";
	public static final String NEW_ADMINISTRATOR_PAGE = "NEW_ADMINISTRATOR";
	
	private static final String RESOURCE_FILE = "pages.properties";
	private Properties resource;
	private static PageConfigurator configurator;
	
	private PageConfigurator(){
	}
	
	/*public static PageConfigurator getConfigurator(){
		if(configurator == null){
			configurator = new PageConfigurator();
			try(FileInputStream stream = new FileInputStream(new File(RESOURCE_FILE))){
				configurator.resource = new Properties();
				configurator.resource.load(stream);
			}  catch (FileNotFoundException exception){
				exception.printStackTrace();
			} catch (IOException exception){
				exception.printStackTrace();
			} 
		}
		
		return configurator;
	}
	
	public String getPage(String key){
		return resource.getProperty(key);
	}*/
	
	public static PageConfigurator getConfigurator(){
		return new PageConfigurator();
	}
	
	public String getPage(String key){
		HashMap<String, String> pages = new HashMap<String, String>();
		pages.put("ADMIN", "/admin");
		pages.put("APPLICANT", "/applicant");
		pages.put("ERROR", "/error");
		pages.put("FACULTY", "/faculty");
		pages.put("LOGIN", "/login");
		pages.put("REGISTRATION", "/registration");
		pages.put("STUDENT", "/student");
		pages.put("CHANGE_STUDENT_INFORMATION", "/changeStudentInformation");
		pages.put("CHANGE_APPLICANT_INFORMATION", "/changeApplicantInformation");
		pages.put("CHANGE_ADMINISTRATOR_INFORMATION", "/changeAdministratorInformation");
		pages.put("NEW_ADMINISTRATOR", "/newAdministrator");
		return pages.get(key);
	}
}
