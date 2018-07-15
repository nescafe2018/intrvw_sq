/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajiv.apps.news.entity.News;
import com.rajiv.apps.news.service.IBreakingNewsService;
import com.rajiv.apps.news.service.INewsComponentService;

@RestController
public class NewsPublisherRestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IBreakingNewsService breakingNewsService;
	
	@Autowired
	private INewsComponentService newsComponentService;
	
	@GetMapping("/getAllNews")
	public List<News> listAll(@RequestParam Long fromTimestamp,
			@RequestParam(name = "toTimestamp", required = false) Long toTimestamp){
		logger.debug("serving request for getAllNews");
		if(fromTimestamp == null){
			fromTimestamp = new Long(1);
		}
		if(toTimestamp == null || toTimestamp < fromTimestamp){
			toTimestamp = System.currentTimeMillis();
		}
		
		return newsComponentService.get(fromTimestamp, toTimestamp);
	}
	
	@GetMapping("/getBreakingNews")
	public List<String> getBreakingNews(){
		logger.debug("serving request for getBreakingNews");
		return breakingNewsService.get();
	}
}