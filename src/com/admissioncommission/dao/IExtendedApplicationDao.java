package com.admissioncommission.dao;

import java.util.HashMap;
import java.util.List;

import com.admissioncommission.enteties.ExtendedApplication;

public interface IExtendedApplicationDao {
	public static final String APPLICANT_ID = "applicant_id"; 
	public static final String FACULTY_ID = "faculty_id";
	public static final String STATUS_ID = "status_id";
	
	List<ExtendedApplication> selectAll();
	List<ExtendedApplication> selectAllSorted();
	List<ExtendedApplication> findByCustomId(int applicantId);
	List<ExtendedApplication> customSelect(HashMap<String, String> criterions);
	List<ExtendedApplication> customSelectSorted(HashMap<String, String> criterions, String order);
}
