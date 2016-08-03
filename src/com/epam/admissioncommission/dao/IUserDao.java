package com.epam.admissioncommission.dao;

import java.util.HashMap;
import java.util.List;

import com.epam.admissioncommission.enteties.User;

public interface IUserDao {
	void delete(int id);
	void update(HashMap<String, String> changes, HashMap<String, String> criterions);
	void insert(User newUser, String userType);
	List<User> getAll();
	List<User> getSpecificUsers(String userType);
	User findById(int id);
	User findByEmailPassword(String email, String password);
}
