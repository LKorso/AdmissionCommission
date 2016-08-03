package com.epam.admissioncommission.dao;

import java.util.HashMap;
import java.util.List;

import com.epam.admissioncommission.enteties.ExtendedApplication;

public interface IExtendedApplicationDao {
	public static final String APPLICANT_ID = "applicant_id"; 
	public static final String FACULTY_ID = "faculty_id";
	public static final String STATUS_ID = "status_id";
	
	List<ExtendedApplication> selectAll();
	List<ExtendedApplication> findByCustomId(int applicantId);
	List<ExtendedApplication> customSelect(HashMap<String, String> criterions);
}
