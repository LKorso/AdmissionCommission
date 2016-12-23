package com.admc.dao;

import java.util.List;
import java.util.Map;

import com.admc.enteties.Application;

public interface IApplicationDao {
	void delete(int id);
	void update(Map<String, String> changes, Map<String, String> criterions);
	void insert(Application newApplication);
	void updateDescription(int id, String description);
	void notEqualUpdate(String field, String newValue, String criterion);
	List<Application> selectAll();
	Application findById(int id);
	List<Application> findByApplicantId(int applicantId);
	List<Application> findByFacultyId(int facultyId);
	List<Application> findByStatusID(int statusId);
	List<Application> customSelect(Map<String, String> criterion);
}
