package com.admc.dao;

import java.util.List;

import com.admc.enteties.Priority;

public interface IPriorityDao {
	void delete(int id);
	void update(int id, String newPriority);
	void insert(Priority newPriority);
	List<Priority> selectAll();
	List<Priority> selectAllSorted();
	Priority findById(int id);
}
