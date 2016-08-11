package com.admissioncommission.dao.creators;

import java.util.HashMap;
import java.util.Set;

public class QueryBuilder {
	public static final int SELECT = 1;
	public static final int UPDATE = 2;
	
	private StringBuilder selectQuery = new StringBuilder();
	private boolean isFirstCriterion = true;
	private boolean isFirstChange = true;
	
	private static final String SELECT_QUERY_BEGINING = "SELECT * FROM ";
	private static final String UPDATE_QUERY_BEGINING = "UPDATE ";
	
	public QueryBuilder(int queryType, String tableName){
		
		switch (queryType) {
		case SELECT:
			selectQuery.append(SELECT_QUERY_BEGINING + tableName);
			break;
		case UPDATE:
			selectQuery.append(UPDATE_QUERY_BEGINING + tableName + " SET ");
			break;
		default:
			selectQuery.append(SELECT_QUERY_BEGINING + tableName);
			break;
		}
		
	}
	
	public void addCriterion(HashMap<String, String> criterions){
		Set<String> keys = criterions.keySet();
		
		for(String key : keys){
			addCriterion(key, criterions.get(key));
		}
	}
	
	public void addCriterion(String fieldName, String value){
		if(isFirstCriterion){
			selectQuery.append(" WHERE ");
			isFirstCriterion = false;
		} else {
			selectQuery.append(" AND ");
		}
		selectQuery.append(fieldName);
		selectQuery.append(" = \"");
		selectQuery.append(value);
		selectQuery.append("\"");
	}
	
	public void addChanges(HashMap<String, String> changes){
		Set<String> keys = changes.keySet();
		
		for(String key : keys){
			add—hanges(key, changes.get(key));
		}
	}
	
	public void add—hanges(String fieldName, String value){
		if(!isFirstChange){
			selectQuery.append(", ");
		} else {
			isFirstChange = false;
		}
		selectQuery.append(fieldName);
		selectQuery.append(" = \"");
		selectQuery.append(value);
		selectQuery.append("\"");
	}

	@Override
	public String toString() {
		return selectQuery.toString();
	} 
}
