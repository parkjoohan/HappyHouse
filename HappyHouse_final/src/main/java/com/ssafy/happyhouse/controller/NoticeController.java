package com.ssafy.happyhouse.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.NoticeService;
//import com.ssafy.util.PageNavigation;
import com.ssafy.happyhouse.model.NoticeParameterDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "?????? ???????????? ????????? ??????", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> listNotice(@ApiParam(value = "???????????? ???????????? ????????????.", required = true)NoticeParameterDto noticeParameterDto){
		logger.debug("listNotice - ??????");
		System.out.println(noticeParameterDto);
		return new ResponseEntity<>(noticeService.listNotice(noticeParameterDto), HttpStatus.OK);
	}
	
	@ApiOperation(value = "????????? ????????? ??????", notes = "DB?????? ??????????????? ?????? 'success' ?????? 'fail' ???????????? ????????????.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerNotice(@RequestBody NoticeDto noticeDto){
		logger.debug("registerNotice - ??????");
		if(noticeService.registerNotice(noticeDto)) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "noticeNo??? ???????????? ????????? ??????", response = NoticeDto.class)
	@GetMapping("/{noticeNo}")
	public ResponseEntity<NoticeDto> noticeView(@PathVariable int noticeNo){
		logger.debug("noticeView - ??????");
		return new ResponseEntity<>(noticeService.getNotice(noticeNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "noticeNo??? ???????????? ????????? ??????", notes ="DB?????? ??????????????? ?????? 'success' ?????? 'fail' ???????????? ????????????.", response = String.class)
	@PutMapping("{noticeNo}")
	public ResponseEntity<String> modifyNotice(@RequestBody NoticeDto noticeDto){
		logger.debug("modifyNotice - ??????");
		if(noticeService.modifyNotice(noticeDto)) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "noticeNo??? ???????????? ????????? ??????", notes = "DB?????? ??????????????? ?????? 'success' ?????? 'fail' ???????????? ????????????.", response = String.class)
	@DeleteMapping("{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable int noticeNo){
		logger.debug("deleteNotice - ??????");
		if(noticeService.deleteNotice(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}