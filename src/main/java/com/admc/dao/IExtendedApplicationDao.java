package com.admc.dao;

import java.util.List;
import java.util.Map;

import com.admc.enteties.ExtendedApplication;

public interface IExtendedApplicationDao {
	String APPLICANT_ID = "applicant_id";
	String FACULTY_ID = "faculty_id";
	String STATUS_ID = "status_id";
	
	List<ExtendedApplication> selectAll();
	List<ExtendedApplication> selectAllSorted();
	List<ExtendedApplication> findByCustomId(int applicantId);
	List<ExtendedApplication> customSelect(Map<String, String> criterions);
	List<ExtendedApplication> customSelectSorted(Map<String, String> criterions, String order);
}
