package com.admc.dao;

import java.util.List;

import com.admc.enteties.ApplicantMark;

public interface IApplicantMarkDao {
	void delete(int id);
	void updateMark(int id, double newMark);
	void updateSubject(int id, int subjectId);
	void insert(ApplicantMark newApplicantScore);
	List<ApplicantMark> selectAll();
	List<ApplicantMark> findByApplicantId(int applicantId);
	List<ApplicantMark> findBySubjectId(int subjectId);
	ApplicantMark findById(int id);
}
