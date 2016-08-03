package com.epam.admissioncommission.dao.creators;

public class Factory {
	public static final String MYSQL = "MySql";
	
	public static IDaoFactory createDaoFactory(String dataBaseType){
		switch (dataBaseType) {
		case MYSQL:
			return new MySqlDaoFactory();
		default:
			return null;
		}
	}
}
