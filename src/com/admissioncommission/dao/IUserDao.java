package com.admissioncommission.dao;

import java.util.HashMap;
import java.util.List;

import com.admissioncommission.enteties.User;

public interface IUserDao {
	void delete(int id);
	void update(HashMap<String, String> changes, HashMap<String, String> criterions);
	void insert(User newUser, String userType);
	List<User> selectAll();
	List<User> selectSpecificUsers(String userType);
	User findById(int id);
	User findByEmailPassword(String email, String password);
}
