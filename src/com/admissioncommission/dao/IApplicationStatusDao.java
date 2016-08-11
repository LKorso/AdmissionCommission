package com.admissioncommission.dao;

import java.util.List;

import com.admissioncommission.enteties.ApplicationStatus;

public interface IApplicationStatusDao {
	void delete(int id);
	void update(int id, String newStatus);
	void insert(ApplicationStatus newStatus);
	List<ApplicationStatus> selectAll();
	ApplicationStatus findById(int id);
	ApplicationStatus findByStatus(String status);
}
