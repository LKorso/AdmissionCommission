package com.epam.admissioncommission.logic;

import java.util.regex.Pattern;

public class Validator {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_PATTERN = "^\\+\\d{2}\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
	private static final String MARK_PATTERN = "^\\\\d{2,3}\\.\\d{0,2}$";
	private static final int MIN_PASSWORD_LENGTH = 8;
	
	public boolean checkEmail(String email){
		return Pattern.matches(EMAIL_PATTERN, email);
	}
	
	public boolean checkName(String name){
		for(int i = 0; i < name.length(); i++){
			if(!(Character.isLetter(name.charAt(i)))){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkPhone(String phone){
		return Pattern.matches(PHONE_PATTERN, phone);
	}
	
	public boolean checkPassword(String password){
		return (password.length() < MIN_PASSWORD_LENGTH) ? false : true;
	}
	
	public boolean checkMark(String mark){
		return Pattern.matches(MARK_PATTERN, mark);
	}
}
