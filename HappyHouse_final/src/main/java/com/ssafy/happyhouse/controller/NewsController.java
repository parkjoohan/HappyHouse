package com.ssafy.happyhouse.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.service.NewsService;


@RestController
@RequestMapping("/news")
@CrossOrigin("*")
public class NewsController {
	
	private final NewsService newsService;
	
	@Autowired
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping("list/{word}/{sortkey}")
	public ResponseEntity<NewsDto> searchNews(@PathVariable("word") String word, @PathVariable("sortkey") String sortkey) throws Exception{
		word = URLDecoder.decode(word, "utf-8");
		System.out.println(word);
		System.out.println(sortkey);
		NewsDto response = newsService.get(word, sortkey);
		return new ResponseEntity<NewsDto>(response, HttpStatus.OK);
	}
	
	@GetMapping("home")
	public ResponseEntity<NewsDto> searchNews() throws Exception{
		NewsDto response = newsService.get();
		return new ResponseEntity<NewsDto>(response, HttpStatus.OK);
	}
}