package com.admissioncommission.dao;

import java.util.HashMap;
import java.util.List;

import com.admissioncommission.enteties.Application;

public interface IApplicationDao {
	void delete(int id);
	void update(HashMap<String, String> changes, HashMap<String, String> criterions);
	void insert(Application newApplication);
	void updateDescription(int id, String description);
	void notEqualUpdate(String field, String newValue, String criterion);
	List<Application> selectAll();
	Application findbyId(int id);
	List<Application> findByApplicantId(int applicantId);
	List<Application> findByFacultyId(int facultyId);
	List<Application> findByStatusID(int statusId);
	List<Application> customSelect(HashMap<String, String> criterion);
}
