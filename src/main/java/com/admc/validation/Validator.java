package com.admc.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator {
	private final static String RESOURCE_FILE = "validation/validationExpressions";
	private static Validator validatorInstance;
	private ResourceBundle resource = null;
	
	private static final String KEY_PHONE = "phone_expression";
	private static final String KEY_EMAIL = "email_expression";
	private static final String KEY_MARK = "mark_expression";
	private static final String KEY_NAME = "name_expression";
	
	private static final int MIN_PASSWORD_LENGTH = 8;
	private static final int MIN_APPLICANT_AGE = 11;
	private static final int MIN_YEAR_VALUE = 1900;
	private static final double MAX_MARK_VALUE = 200.0;
	
	private Validator() {
		resource = ResourceBundle.getBundle(RESOURCE_FILE);
	}

	public static synchronized Validator getValidator() {
		if (validatorInstance == null) {
			validatorInstance = new Validator();
		}
		return validatorInstance;
	}

	public boolean checkEmail(String email){
		return email != null && Pattern.matches(resource.getString(KEY_EMAIL), email);
	}
	
	public boolean checkName(String name){
		return name != null && Pattern.matches(resource.getString(KEY_NAME), name);
	}
	
	public boolean checkPhone(String phone){
		return phone != null && Pattern.matches(resource.getString(KEY_PHONE), phone);
	}
	
	public boolean checkMark(String mark){
		if(mark == null){
			return false;
		} else if(!Pattern.matches(resource.getString(KEY_MARK), mark)){
			return false;
		} else if(Double.parseDouble(mark) > MAX_MARK_VALUE){
			return false;
		}
		return true;
	}
	
	public boolean checkPassword(String password, String repeatedPassword){
		if(password == null || repeatedPassword == null){
			return false;
		} else if(!password.equals(repeatedPassword)){
			return false;
		} else if (password.length() < MIN_PASSWORD_LENGTH){
			return false;
		}
		return true;
	}
	
	public boolean checkDayInMonth(String day, String month, String year){
		if(!checkYear(year)){
			return false;
		}
		if(!checkDay(day)){
			return false;
		}
		if(!checkMonth(month)){
			return false;
		}
		
		int checkedDay = Integer.parseInt(day);
		int checkedMonth = Integer.parseInt(month);
		int checkedYear = Integer.parseInt(year);
		
		if(checkedMonth == 2 && (checkedYear % 4) == 0 && checkedDay > 29){
			return false;
		} else if(checkedMonth == 2 && (checkedYear % 4) != 0 && checkedDay > 28){
			return false;
		} else if((checkedMonth == 4 || checkedMonth == 6 || checkedMonth == 9 || checkedMonth == 11) && checkedDay > 30){
			return false;
		}
		return true;
	}
	
	public boolean checkMonth(String month){
		return (month != null) && (Integer.parseInt(month) <= 12);
	}
	
	public boolean checkYear(String year){
		return (year != null) && (Integer.parseInt(year) >= MIN_YEAR_VALUE);
	}
	
	public boolean checkDay(String day){
		if(day != null) {
			int date = Integer.parseInt(day);
			return date > 0 && date < 31;
		}
		return false;
	}
	
	public boolean checkForMinAge(String day, String month, String year){
		if(!checkYear(year)){
			return false;
		}
		if(!checkDay(day)){
			return false;
		}
		if(!checkMonth(month)){
			return false;
		}
		
		int checkedDay = Integer.parseInt(day);
		int checkedMonth = Integer.parseInt(month);
		int checkedYear = Integer.parseInt(year);
		
		Date date = new Date();
		int currentDay = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		int currentMonth = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int currentYear = Integer.parseInt(new SimpleDateFormat("yyy").format(date));
		
		if((currentYear - checkedYear) > MIN_APPLICANT_AGE){
			return true;
		}
		if((currentYear - checkedYear) == MIN_APPLICANT_AGE && currentMonth > checkedMonth){
			return true;
		}
		if((currentYear - checkedYear) == MIN_APPLICANT_AGE && currentMonth > checkedMonth && currentDay > checkedDay){
			return true;
		}
		return false;
	}
	
	public boolean checkDate(String day, String month, String year){
		if(!checkYear(year)){
			return false;
		}
		if(!checkDay(day)){
			return false;
		}
		if(!checkMonth(month)){
			return false;
		}
		
		if(!checkDayInMonth(day, month, year)){
			return false;
		}
		
		if(!checkForMinAge(day, month, year)){
			return false;
		}
		
		return true;
	}
	
	public boolean checkForInjections(String checkedValue){
		if(checkedValue == null){
			return false;
		}
		for(String bannedWord : getBunnedWords()){
			if(checkedValue.indexOf(bannedWord) != -1){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkSubjects(String subjectOne, String subjectTwo, String subjectThree){
		if(subjectOne == null || subjectTwo == null || subjectThree == null){
			return false;
		}
		if(!subjectOne.equals(subjectTwo) && !subjectOne.equals(subjectThree)
				&& !subjectTwo.equals(subjectThree)){
			return true;
		}
		return false;
	}

	private Set<String> getBunnedWords(){
		Set<String> bannedWords = new HashSet<>();
		for(String key : resource.keySet()){
			if(key.startsWith("banned_word.sql.")){
				bannedWords.add(resource.getString(key));
			}
		}
		return bannedWords;
	}
}