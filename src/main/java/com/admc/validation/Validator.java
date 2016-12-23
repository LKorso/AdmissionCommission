package com.admc.validation;

import java.text.SimpleDateFormat;
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