/**
 * @Author 	: Rajiv Kumar
 * @Version : 1.0.0
 */
package com.rajiv.apps.news.main.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rajiv.apps.news.controller.NewsEngineRestController;
import com.rajiv.apps.news.controller.NewsPublisherRestController;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestServiceTestClass {

	@Autowired
	private NewsEngineRestController newsEngineController;

	@Autowired
	private NewsPublisherRestController newsPublisherController;

	@Test
	public void controllerInitializedCorrectly() {
		assertThat(newsEngineController).isNotNull();
		assertThat(newsPublisherController).isNotNull();
	}
	
	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("contentType", "finance");
		map.add("header", "Breaking news from finance");
		map.add("content", "finance news description");
		map.add("timestamp", "" + System.currentTimeMillis());
		map.add("priority", "1");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/newsSubmit", request, String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).contains("success");
	}
}