/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.entity;

public enum ContentType {
	SPORTS("sports"),
	FINANCE("finance"),
	GENERAL("general")
	;
	
	private ContentType(String title){
		this.title = title;
	}
	public final String title;
	
	public static ContentType get(String contentType){
		if(contentType == null){
			return GENERAL;
		}
		contentType = contentType.trim().toLowerCase();
		
		for(ContentType type : values()){
			if(type.title.equals(contentType)){
				return type;
			}
		}
		return GENERAL;
	}
}