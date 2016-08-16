package com.admissioncommission.dao.creators;

import java.util.HashMap;
import java.util.Set;

public class QueryBuilder {
	public static final int SELECT = 1;
	public static final int UPDATE = 2;
	public static final String ORDER_DESC = "DESC";
	public static final String ORDER_ASC = "ASC";
	
	private StringBuilder query = new StringBuilder();
	private boolean isFirstCriterion = true;
	private boolean isFirstChange = true;
	
	private static final String SELECT_QUERY_BEGINING = "SELECT * FROM ";
	private static final String UPDATE_QUERY_BEGINING = "UPDATE ";
	
	public QueryBuilder(int queryType, String tableName){
		
		switch (queryType) {
		case SELECT:
			query.append(SELECT_QUERY_BEGINING + tableName);
			break;
		case UPDATE:
			query.append(UPDATE_QUERY_BEGINING + tableName + " SET ");
			break;
		default:
			query.append(SELECT_QUERY_BEGINING + tableName);
			break;
		}
		
	}
	
	public void addCriterion(HashMap<String, String> criterions){
		Set<String> keys = criterions.keySet();
		
		for(String key : keys){
			addCriterion(key, criterions.get(key));
		}
	}
	
	public void addNotEqual(String fieldName, String value){
		firstCriterionCheck();
		query.append(fieldName);
		query.append(" != \"");
		query.append(value);
		query.append("\" ");
	}
	
	public void addCriterion(String fieldName, String value){
		firstCriterionCheck();
		query.append(fieldName);
		query.append(" = \"");
		query.append(value);
		query.append("\" ");
	}
	
	public void addChanges(HashMap<String, String> changes){
		Set<String> keys = changes.keySet();
		
		for(String key : keys){
			addChanges(key, changes.get(key));
		}
	}
	
	public void addOrderBy(String criterion, String order){
		query.append("ORDER BY ");
		query.append(criterion);
		query.append(" " + order);
	}
	
	public void addChanges(String fieldName, String value){
		if(!isFirstChange){
			query.append(", ");
		} else {
			isFirstChange = false;
		}
		query.append(fieldName);
		query.append(" = \"");
		query.append(value);
		query.append("\" ");
	}

	@Override
	public String toString() {
		return query.toString();
	} 
	
	private void firstCriterionCheck(){
		if(isFirstCriterion){
			query.append(" WHERE ");
			isFirstCriterion = false;
		} else {
			query.append(" AND ");
		}
	}
}
