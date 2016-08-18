package com.admissioncommission.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator {
	private final String RESOURCE_FILE = "com.admissioncommission.validation.validationExpressions";
	private ResourceBundle resource = null;
	
	private static final String KEY_PHONE = "phone_expression";
	private static final String KEY_EMAIL = "email_expression";
	private static final String KEY_MARK = "mark_expression";
	private static final String KEY_NAME = "name_expression";
	
	private int minPasswordLength = 8;
	private int minApplicantAge = 11;
	private int minYearValue = 1900;
	private double maxMarkValue = 200.0;
	
	public Validator() {
		resource = ResourceBundle.getBundle(RESOURCE_FILE);
	}
	
	public Validator(int minPasswordLength, int minApplicantAge, int minYearValue, double maxMarkValue) {
		resource = ResourceBundle.getBundle(RESOURCE_FILE);
		this.minPasswordLength = minPasswordLength;
		this.minApplicantAge = minApplicantAge;
		this.minYearValue = minYearValue;
		this.maxMarkValue = maxMarkValue;
	}

	public int getMinPasswordLength() {
		return minPasswordLength;
	}

	public void setMinPasswordLength(int minPasswordLength) {
		this.minPasswordLength = minPasswordLength;
	}

	public int getMinApplicantAge() {
		return minApplicantAge;
	}

	public void setMinApplicantAge(int minApplicantAge) {
		this.minApplicantAge = minApplicantAge;
	}

	public int getMinYearValue() {
		return minYearValue;
	}

	public void setMinYearValue(int minYearValue) {
		this.minYearValue = minYearValue;
	}

	public double getMaxMarkValue() {
		return maxMarkValue;
	}

	public void setMaxMarkValue(double maxMarkValue) {
		this.maxMarkValue = maxMarkValue;
	}

	public boolean checkEmail(String email){
		return email != null ? Pattern.matches(resource.getString(KEY_EMAIL), email) : false;
	}
	
	public boolean checkName(String name){
		return name != null ? Pattern.matches(resource.getString(KEY_NAME), name) : false;
	}
	
	public boolean checkPhone(String phone){
		return phone != null ? Pattern.matches(resource.getString(KEY_PHONE), phone) : false;
	}
	
	public boolean checkMark(String mark){
		if(mark == null){
			return false;
		} else if(!Pattern.matches(resource.getString(KEY_MARK), mark)){
			return false;
		} else if(Double.parseDouble(mark) > maxMarkValue){
			return false;
		}
		return true;
	}
	
	public boolean checkPassword(String password, String repeatedPassword){
		if(password == null || repeatedPassword == null){
			return false;
		} else if(!password.equals(repeatedPassword)){
			return false;
		} else if (password.length() < minPasswordLength){
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
		return month != null ? Integer.parseInt(month) <= 12 : false;
	}
	
	public boolean checkYear(String year){
		return year != null ? Integer.parseInt(year) >= minYearValue : false;
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
		
		if((currentYear - checkedYear) > minApplicantAge){
			return true;
		}
		if((currentYear - checkedYear) == minApplicantAge && currentMonth > checkedMonth){
			return true;
		}
		if((currentYear - checkedYear) == minApplicantAge && currentMonth > checkedMonth && currentDay > checkedDay){
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
	
	@Override
	public String toString() {
		return "Validator [minPasswordLength=" + minPasswordLength + ", minApplicantAge=" + minApplicantAge
				+ ", minYearValue=" + minYearValue + ", maxMarkValue=" + maxMarkValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(maxMarkValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + minApplicantAge;
		result = prime * result + minPasswordLength;
		result = prime * result + minYearValue;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Validator other = (Validator) obj;
		if (Double.doubleToLongBits(maxMarkValue) != Double.doubleToLongBits(other.maxMarkValue))
			return false;
		if (minApplicantAge != other.minApplicantAge)
			return false;
		if (minPasswordLength != other.minPasswordLength)
			return false;
		if (minYearValue != other.minYearValue)
			return false;
		return true;
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