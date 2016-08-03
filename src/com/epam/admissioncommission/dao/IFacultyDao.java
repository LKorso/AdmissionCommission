package com.epam.admissioncommission.dao;

import java.util.List;

import com.epam.admissioncommission.enteties.Faculty;

public interface IFacultyDao {
	void delete(int id);
	void update(int id, String newName);
	void insert(Faculty newFaculty);
	List<Faculty> selectAll();
	Faculty findById(int id);
}
