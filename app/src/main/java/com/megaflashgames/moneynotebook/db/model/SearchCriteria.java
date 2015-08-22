package com.megaflashgames.moneynotebook.db.model;


public class SearchCriteria {
	
	public String whereClause;
	public String[] whereArgs;


	public SearchCriteria(String whereClause, String[] whereArgs) {
		this.whereClause = setWhereClause(whereClause);
		this.whereArgs = whereArgs;
	}
	
	
	public String setWhereClause(String whereClause) {
		if(whereClause!=null && whereClause.length()>0) {
			this.whereClause = whereClause;
		} else {
			this.whereClause = " id = ? ";
		}
		
		return this.whereClause;
	}
	
	
	public String getWhereClause() {
		return this.whereClause;
	}
	
	public String[] getWhereArgs() {
		return this.whereArgs;
	}
	
	

	
}
