/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.dao;

import java.util.List;

import com.rajiv.apps.news.entity.News;

public interface INewsDao {
	
	/**
	 * @param fromTimestamp
	 * @param toTimestamp
	 * @return
	 */
	List<News> get(long fromTimestamp, long toTimestamp);

	/**
	 * @param news
	 */
	void save(News news);
}