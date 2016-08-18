package com.admissioncommission.validation.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.admissioncommission.validation.Validator;

public class ValidatorTest {
	private Validator testInstance;
	private String correctEmail;
	private String correctPhone;
	private String correctPassword;
	private String correctName;
	private String correctMark;
	private String correctDay;
	private String correctMonth;
	private String correctYear;
	private String incorrectEmail;
	private String incorrectPhone;
	private String incorrectPassword;
	private String incorrectName;
	private String incorrectMark;
	private String incorrectDay;
	private String incorrectMonth;
	private String incorrectYear;
	private String subjectOne;
	private String subjectTwo;
	private String subjectThree;
	
	@Before
	public void setUp() throws Exception {
		testInstance = new Validator();
		correctDay = "2";
		correctMonth = "2";
		correctYear = "1996";
		correctEmail = "user@gmail.com";
		correctMark = "175.23";
		correctName = "Peter";
		correctPassword = "00000000";
		correctPhone = "+38(067)549-86-52";
		incorrectDay = "31";
		incorrectMonth = "13";
		incorrectYear = "1896";
		incorrectEmail = "user12412.com";
		incorrectPassword = "0000000";
		incorrectPhone = "+38054(247)2752";
		incorrectName = "557=-0";
		incorrectMark = "1000";
		subjectOne = "Math";
		subjectTwo = "English";
		subjectThree = "History";
	}
	
	@Test
	public void testCheckEmail(){
		if(testInstance.checkEmail(correctEmail) != checkEmail(correctEmail) 
				|| testInstance.checkEmail(incorrectEmail) != checkEmail(incorrectEmail)){
			fail();
		}
	}
	
	@Test
	public void testCheckEmailForNullValue() {
		Assert.assertFalse(testInstance.checkEmail(null));
	}

	@Test
	public void testCheckName(){
		if(testInstance.checkName(correctName) != checkName(correctName) 
				|| testInstance.checkName(incorrectName) != checkName(incorrectName)){
			fail();
		}
	}
	
	@Test
	public void testCheckNameForNullValue() {
		Assert.assertFalse(testInstance.checkName(null));
	}
	
	@Test
	public void testCheckPhone(){
		if(testInstance.checkPhone(correctPhone) != checkPhone(correctPhone) 
				|| testInstance.checkPhone(incorrectPhone) != checkPhone(incorrectPhone)){
			fail();
		}
	}
	
	@Test
	public void testCheckPhoneForNullValue() {
		Assert.assertFalse(testInstance.checkPhone(null));
	}

	@Test
	public void testCheckMarkForNullValue() {
		Assert.assertFalse(testInstance.checkMark(null));
	}

	@Test
	public void testCheckPasswordForNullValue() {
		Assert.assertFalse(testInstance.checkPassword(null, null));
		Assert.assertFalse(testInstance.checkPassword(null, correctPassword));
		Assert.assertFalse(testInstance.checkPassword(correctPassword, null));
	}

	@Test
	public void testCheckDayInMonth() {
		if(testInstance.checkDayInMonth(correctDay, correctMonth, correctYear) != checkDayInMonth(correctDay, correctMonth, correctYear)
				|| testInstance.checkDayInMonth(incorrectDay, incorrectMonth, incorrectYear) != checkDayInMonth(incorrectDay, incorrectMonth, incorrectYear)){
			fail();
		}
	}

	@Test
	public void testCheckMonthForNullValue() {
		Assert.assertFalse(testInstance.checkMonth(null));
	}
	
	@Test
	public void testCheckMonth(){
		if(testInstance.checkMonth(correctMonth) != checkMonth(correctMonth) 
				|| testInstance.checkMonth(incorrectMonth) != checkMonth(incorrectMonth)){
			fail();
		}
	}

	@Test
	public void testCheckYearForNullValue() {
		Assert.assertFalse(testInstance.checkYear(null));
	}
	
	@Test
	public void testCheckYear(){
		if(testInstance.checkYear(correctYear) != checkYear(correctYear) 
				|| testInstance.checkYear(incorrectYear) != checkYear(incorrectYear)){
			fail();
		}
	}

	@Test
	public void testCheckDayForNullValue() {
		Assert.assertFalse(testInstance.checkDay(null));
	}
	
	@Test
	public void testCheckDay(){
		if(testInstance.checkDay(correctDay) != checkDay(correctDay) 
				|| testInstance.checkDay(incorrectDay) != checkYear(incorrectDay)){
			fail();
		}
	}
	
	@Test
	public void testCheckForMinAge(){
		if(testInstance.checkForMinAge(correctDay, correctMonth, correctYear) != checkForMinAge(correctDay, correctMonth, correctYear)
				|| testInstance.checkForMinAge(incorrectDay, incorrectMonth, incorrectYear) != checkForMinAge(incorrectDay, incorrectMonth, incorrectYear)){
			fail();
		}
	}
	
	@Test
	public void testCheckDateForNullValue() {
		Assert.assertFalse(testInstance.checkDate(null, null, null));
		Assert.assertFalse(testInstance.checkDate(correctDay, null, null));
		Assert.assertFalse(testInstance.checkDate(null, correctMonth, null));
		Assert.assertFalse(testInstance.checkDate(null, correctMonth, correctYear));
	}
	
	@Test
	public void testCheckDate(){
		if(testInstance.checkDate(correctDay, correctMonth, correctYear) != checkDate(correctDay, correctMonth, correctYear)
				|| testInstance.checkDate(incorrectDay, incorrectMonth, incorrectYear) != checkDate(incorrectDay, incorrectMonth, incorrectYear)){
			fail();
		}
	}
	
	@Test
	public void testCheckForInjectionsForNullValue() {
		Assert.assertFalse(testInstance.checkForInjections(null));
	}

	@Test
	public void testCheckSubjectsForNullValue() {
		Assert.assertFalse(testInstance.checkSubjects(null, null, null));
		Assert.assertFalse(testInstance.checkSubjects(subjectOne, null, subjectThree));
		Assert.assertFalse(testInstance.checkSubjects(null, subjectTwo, subjectThree));
		Assert.assertFalse(testInstance.checkSubjects(subjectOne, subjectTwo, null));
	}
	
	@Test
	public void testCheckSubjects(){
		if(testInstance.checkSubjects(subjectOne, subjectTwo, subjectThree) != checkSubjects(subjectOne, subjectTwo, subjectThree)
				|| testInstance.checkSubjects(subjectOne, subjectOne, subjectOne) != checkSubjects(subjectOne, subjectOne, subjectOne)
				|| testInstance.checkSubjects(subjectOne, subjectOne, subjectTwo) != checkSubjects(subjectOne, subjectOne, subjectTwo)
				|| testInstance.checkSubjects(subjectOne, subjectTwo, subjectTwo) != checkSubjects(subjectOne, subjectTwo, subjectTwo)
				|| testInstance.checkSubjects(subjectOne, subjectTwo, subjectOne) != checkSubjects(subjectOne, subjectTwo, subjectOne)){
			fail();
		}
	}
	
	@Test
	public void testCheckMark(){
		if(testInstance.checkMark(correctMark) != checkMark(correctMark)
				|| testInstance.checkMark(incorrectMark) != checkMark(incorrectMark)){
			fail();
		}
	}
	
	@Test
	public void testCheckPassword(){
		if(testInstance.checkPassword(correctPassword, correctPassword) != checkPassword(correctPassword, correctPassword)
				|| testInstance.checkPassword(correctPassword, incorrectPassword) != checkPassword(correctPassword, incorrectPassword)
				|| testInstance.checkPassword(incorrectPassword, incorrectPassword) != checkPassword(incorrectPassword, incorrectPassword)
				|| testInstance.checkPassword(incorrectPassword, correctPassword) != checkPassword(incorrectPassword, correctPassword)){
			fail();
		}
	}
	
	private  boolean checkPassword(String passOne, String passTwo){
		if(passOne.length() < 8 || !passOne.equals(passTwo)){
			return false;
		}
		return true;
	}
	
	private boolean checkMark(String mark){
		double markValue = Double.parseDouble(mark);
		if(markValue > 200 || markValue < 100){
			return false;
		}
		return true;
	}
	
	private boolean checkSubjects(String subjectOne, String subjectTwo, String subjectThree){
		if(!subjectOne.equals(subjectTwo) && !subjectOne.equals(subjectThree)
				&& !subjectThree.equals(subjectTwo)){
			return true;
		}
		return false;
	}
	
	private boolean checkDate(String day, String month, String year){
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
	
	private boolean checkForMinAge(String day, String month, String year){
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
		
		if((currentYear - checkedYear) >= testInstance.getMinApplicantAge() 
				|| currentMonth > checkedMonth
				|| currentDay > checkedDay){
			return true;
		}
			
		return false;
	}
	
	private boolean checkDay(String day){
		if(Integer.parseInt(day) <= 31 && Integer.parseInt(day) > 0){
			return true;
		}
		return false;
	}
	
	private boolean checkYear(String year){
		if(Integer.parseInt(year) <= testInstance.getMinYearValue()){
			return false;
		}
		return true;
	}
	
	private boolean checkMonth(String month){
		if(Integer.parseInt(month) > 12){
			return false;
		}
		return true;
	}
	
	private boolean checkDayInMonth(String day, String month, String year){
		StringBuilder builder = new StringBuilder();
		builder.append(year);
		builder.append("-");
		if(Integer.parseInt(month) < 10){
			builder.append("0");
		}
		builder.append(month);
		builder.append("-");
		if(Integer.parseInt(day) < 10){
			builder.append("0");
		}
		builder.append(day);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {	
			dateFormat.parse(builder.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean checkPhone(String phone){
		if(phone.indexOf('(') > 3 || phone.indexOf(')') > 7){
			return false;
		}
		String clearedNumber = phone.replaceAll("[\\s-)(]", "");
		if(!clearedNumber.substring(0, 1).matches("[+\\d]")){
			return false;
		}
		if(!clearedNumber.substring(1, clearedNumber.length()).matches("\\d{6,}")){
			return false;
		}
		return true;
	}
	
	private boolean checkName(String name){
		for(Character symbol : name.toCharArray()){
			if(!Character.isLetter(symbol)){
				return false;
			}
		}
		return true;
	}
	private boolean checkEmail(String email){
		int indexOfAmpersant = email.indexOf('@', 0);
		if(indexOfAmpersant == 0 || indexOfAmpersant == -1){
			return false;
		}
		if(email.indexOf('.', indexOfAmpersant) == -1){
			return false;
		}
		return true;
	}
}
