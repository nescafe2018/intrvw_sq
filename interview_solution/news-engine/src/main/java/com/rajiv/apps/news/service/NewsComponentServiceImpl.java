/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rajiv.apps.news.dao.INewsDao;
import com.rajiv.apps.news.entity.News;

@Service("newsComponentService")
@Transactional
public class NewsComponentServiceImpl implements INewsComponentService{
	
	@Autowired
	private INewsDao newsDao;
	
	@Override
	public void publish(News news) {
		newsDao.save(news);
	}

	@Override
	public List<News> get(long fromTimestamp, long toTimestamp) {
		return newsDao.get(fromTimestamp, toTimestamp);
	}
}