/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.service;

import java.util.List;

import com.rajiv.apps.news.entity.News;

public interface INewsComponentService {
	void publish(News news);

	List<News> get(long fromTimestamp, long toTimestamp);
}