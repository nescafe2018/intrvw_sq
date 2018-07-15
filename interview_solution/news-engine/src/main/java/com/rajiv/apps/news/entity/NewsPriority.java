/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.entity;

public enum NewsPriority {
	BREAKING(1),
	GENERAL(2);
	
	private NewsPriority(int priority){
		this.priority = priority;
	}
	public final int priority;
	
	public static NewsPriority get(int priority){
		if(priority == BREAKING.priority){
			return BREAKING;
		}
		return GENERAL;
	}
}