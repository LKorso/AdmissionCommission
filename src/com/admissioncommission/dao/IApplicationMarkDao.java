package com.admissioncommission.dao;

import java.util.List;

import com.admissioncommission.enteties.ApplicationMark;

public interface IApplicationMarkDao {
	void delete(int applicationId, int markId);
	void updateByApplicationId(int applicationId, int newMarkId);
	void updateByMarkId(int markId, int newApplicationId);
	void insert(ApplicationMark newApplicationMark);
	List<ApplicationMark> selectAll();
	List<ApplicationMark> findByApplicationId(int applicationId);
	List<ApplicationMark> findbyMarkid(int markId);
 }
