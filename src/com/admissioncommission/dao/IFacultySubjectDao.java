package com.admissioncommission.dao;

import java.util.List;

import com.admissioncommission.enteties.FacultySubject;

public interface IFacultySubjectDao {
	void delete(int facultyId, int subjectId);
	void changeSubject(int facultyId, int subjectId, int newSubjectId);
	void changeFaculty(int subjectId, int facultyId, int newFacultyId);
	void insert(FacultySubject newFacultySubject);
	List<FacultySubject> selectAll();
	List<FacultySubject> findByFacultyId(int facultyId);
	List<FacultySubject> findBySubjectId(int subjectId);
}
