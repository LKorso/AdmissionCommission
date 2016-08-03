package com.epam.admissioncommission.dao;

import java.util.List;

import com.epam.admissioncommission.enteties.ApplicationStatus;

public interface IApplicationStatusDao {
	void delete(int id);
	void update(int id, String newStatus);
	void insert(ApplicationStatus newStatus);
	List<ApplicationStatus> selectAll();
	ApplicationStatus findById(int id);
	ApplicationStatus findByStatus(String status);
}
