package com.ssafy.happyhouse.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CCTVDto;
import com.ssafy.happyhouse.model.service.CCTVService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/cctv")
public class CCTVController {
		
	private static final Logger logger = LoggerFactory.getLogger(CCTVController.class);
	
	@Autowired
	private CCTVService cctvService;
	
	@ApiOperation(value = "동의 모든 cctv 정보를 반환", response = List.class)
	@GetMapping("/cctvLocation/{dongCode}")
	public ResponseEntity<List<CCTVDto>> cctvList(@PathVariable String dongCode) throws Exception{
		logger.debug("cctvList - 호출");
		return new ResponseEntity<>(cctvService.getCCTVInfo(dongCode), HttpStatus.OK);
	}
}

