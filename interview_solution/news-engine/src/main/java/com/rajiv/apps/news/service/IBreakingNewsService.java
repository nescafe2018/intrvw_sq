/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.service;

import java.util.List;

public interface IBreakingNewsService {
	void include(String news);
	List<String> get();
}