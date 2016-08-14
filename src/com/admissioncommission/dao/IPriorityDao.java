package com.admissioncommission.dao;

import java.util.List;

import com.admissioncommission.enteties.Priority;

public interface IPriorityDao {
	void delete(int id);
	void update(int id, String newPriority);
	void insert(Priority newPriority);
	List<Priority> selectAll();
	Priority findById(int id);
}
