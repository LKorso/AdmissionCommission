package com.admc.dao;

import java.util.List;

import com.admc.enteties.UserType;

public interface IUserTypeDao {
	void delete(int id);
	void update(int id, String newType);
	void insert(UserType newType);
	List<UserType> selectAll();
	UserType findById(int id);
	UserType findByType(String type);
}
