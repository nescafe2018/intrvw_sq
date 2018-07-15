/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rajiv.apps.news.entity.News;

@Repository("newsDao")
public class NewsDaoImpl implements INewsDao{
	private static final String SELECT_QUERY = "select n from News n where n.timestamp >= :from and n.timestamp <= :to";
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public void save(News news){
		entityManager.persist(news);
	}

	@Override
	public List<News> get(long fromTimestamp, long toTimestamp) {
		return entityManager.createQuery(SELECT_QUERY, News.class).setParameter(
				"from", fromTimestamp).setParameter("to", toTimestamp).getResultList();
	}
}