package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.NewsDto;

public interface NewsService {
	
	NewsDto get(String word, String sortKey) throws Exception;
	
	NewsDto get() throws Exception;
}