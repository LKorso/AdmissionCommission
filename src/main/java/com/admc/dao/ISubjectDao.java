package com.admc.dao;

import java.util.List;

import com.admc.enteties.Subject;

public interface ISubjectDao {
	void delete(int id);
	void update(int id, String newName);
	void insert(Subject newSubject);
	List<Subject> selectAll();
	Subject findById(int id);
	Subject findByName(String name);
}
