/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.main.test;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rajiv.apps.news.dao.INewsDao;
import com.rajiv.apps.news.entity.ContentType;
import com.rajiv.apps.news.entity.News;
import com.rajiv.apps.news.entity.NewsPriority;
import com.rajiv.apps.news.service.IBreakingNewsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class NewsAppTestClass {
	@Autowired
	private INewsDao newsDao;
	
	@Autowired
	private IBreakingNewsService breakingNewsService;
	
	private News createDummy(NewsPriority newsPriority, ContentType contentType){
		News news = new News();
		news.setPriority(newsPriority.priority);
		news.setContentType(contentType.title);
		news.setTimestamp(System.currentTimeMillis());
		return news;
	}
	
	@Test
	public void save_general_news(){
		News news = createDummy(NewsPriority.GENERAL, ContentType.GENERAL);
		news.setHeader("General news about other category");
		news.setContent("General news description");
		newsDao.save(news);
		
		news = createDummy(NewsPriority.BREAKING, ContentType.SPORTS);
		news.setHeader("Breaking news about Sports");
		news.setContent("Sports news description");
		newsDao.save(news);
	}
	
	@Test
	public void show_all_news(){
		List<News> newsCol = newsDao.get(1, System.currentTimeMillis());
		newsCol.forEach(System.out::println);
	}
	
	@Test
	public void show_breaking_news(){
		List<String> newsCol = breakingNewsService.get();
		newsCol.forEach(System.out::println);
	}
}