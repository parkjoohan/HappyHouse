package com.ssafy.happyhouse.model.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssafy.happyhouse.model.NewsDto;

@Service
public class NewsServiceImpl implements NewsService {
	private static RestTemplate restTemplate = new RestTemplate();

	private final String CLIENT_ID = "cFM85N04eq7qANZ4tVIS";
	private final String CLIENT_SECRET = "SyFsLAquQu";
	
	
	@Override
	public NewsDto get(String word, String sortkey) throws Exception {
		final String API_URL = "https://openapi.naver.com/v1/search/news.json?display=100&query={word}&sort={sortkey}";
		
		final HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);
        
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        
		return restTemplate.exchange(API_URL, HttpMethod.GET, entity, NewsDto.class, word, sortkey).getBody();
	}

	@Override
	public NewsDto get() throws Exception {
		final String API_URL = "https://openapi.naver.com/v1/search/news.json?display=5&query=부동산";
		
		final HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);
        
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        
		return restTemplate.exchange(API_URL, HttpMethod.GET, entity, NewsDto.class).getBody();
	}

}