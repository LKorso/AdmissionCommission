package com.admc.dao;

import java.util.HashMap;
import java.util.List;

import com.admc.enteties.User;

public interface IUserDao {
	void delete(int id);
	void update(HashMap<String, String> changes, HashMap<String, String> criterions);
	void insert(User newUser);
	List<User> selectAll();
	List<User> selectSpecificUsers(User.UserRole role);
	User findById(int id);
	User findByEmailPassword(String email, String password);
}
