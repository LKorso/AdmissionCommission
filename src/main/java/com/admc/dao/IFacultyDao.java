package com.admc.dao;

import java.util.List;

import com.admc.enteties.Faculty;

public interface IFacultyDao {
	void delete(int id);
	void update(int id, String newName);
	void insert(Faculty newFaculty);
	List<Faculty> selectAll();
	Faculty findById(int id);
}
