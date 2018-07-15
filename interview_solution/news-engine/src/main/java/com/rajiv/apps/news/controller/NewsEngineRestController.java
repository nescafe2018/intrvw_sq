/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajiv.apps.news.entity.ContentType;
import com.rajiv.apps.news.entity.News;
import com.rajiv.apps.news.entity.NewsPriority;
import com.rajiv.apps.news.service.IBreakingNewsService;
import com.rajiv.apps.news.service.INewsComponentService;

@RestController
public class NewsEngineRestController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private INewsComponentService newsComponentService;

	@Autowired
	private IBreakingNewsService breakingNewsService;

	@PostMapping(path = "/newsSubmit")
	public ResponseEntity<?> newsSubmit(
			@RequestParam String contentType, @RequestParam String header, 
			@RequestParam String content, @RequestParam long timestamp,
			@RequestParam(name = "priority", required = false, defaultValue = "2") int priority) {
		logger.debug("serving request for newsSubmit");
		
		header = header.trim();
		content = content.trim();

		if (header.isEmpty() || content.isEmpty()) {
			logger.info("Empty news received");
			return new ResponseEntity<String>("Empty news received", HttpStatus.BAD_REQUEST);
		}

		NewsPriority newsPriority = NewsPriority.get(priority);
		ContentType type = ContentType.get(contentType);

		logger.info("Processing received news");

		News news = new News();
		news.setContentType(type.title);
		news.setHeader(header);
		news.setContent(content);
		news.setTimestamp(timestamp);
		news.setPriority(newsPriority.priority);

		newsComponentService.publish(news);

		if (newsPriority == NewsPriority.BREAKING) {
			breakingNewsService.include(news.getHeader());
		}
		return ResponseEntity.ok("success");
	}

}