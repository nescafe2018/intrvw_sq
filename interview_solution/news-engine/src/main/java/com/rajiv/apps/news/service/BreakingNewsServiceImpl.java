/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("breakingNewsService")
public class BreakingNewsServiceImpl implements IBreakingNewsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final int QUEUE_SIZE = 100;
	
	private List<String> breakingNewsCache;
	
	public BreakingNewsServiceImpl(){
		breakingNewsCache = new ArrayList<String>(QUEUE_SIZE);
	}
	
	@Override
	public void include(String newsHeading) {
		logger.info("Including this breaking news in cache");
		synchronized (breakingNewsCache) {
			if(breakingNewsCache.size() == QUEUE_SIZE){
				breakingNewsCache.remove(QUEUE_SIZE -1);
			}
			breakingNewsCache.add(0, newsHeading);
		}
	}

	@Override
	public List<String> get() {
		return Collections.unmodifiableList(breakingNewsCache);
	}
}