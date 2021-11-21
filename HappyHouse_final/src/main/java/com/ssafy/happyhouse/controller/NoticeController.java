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

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
//	@GetMapping("/list")
//	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		System.out.println(map);
//		String spp = map.get("spp"); // size per page (페이지당 글갯수)
//		map.put("spp", spp != null ? spp : "5");
//		List<NoticeDto> list = noticeService.listArticle(map);
//		System.out.println(list);
//		PageNavigation pageNavigation = noticeService.makePageNavigation(map);
//		mav.addObject("articles", list);
//		mav.addObject("navigation", pageNavigation);
//		mav.addObject("key", map.get("key"));
//		mav.addObject("word", map.get("word"));
//		mav.setViewName("notice/noticeboard");
//		return mav;
//	}
	
	@ApiOperation(value = "모든 게시글의 정보를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> listNotice(){
		logger.debug("listNotice - 호출");
		return new ResponseEntity<>(noticeService.listNotice(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 게시글 등록", notes = "DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerNotice(@RequestBody NoticeDto noticeDto){
		logger.debug("registerNotice - 호출");
		if(noticeService.registerNotice(noticeDto)) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "noticeNo에 해당하는 게시글 반환", response = NoticeDto.class)
	@GetMapping("/{noticeNo}")
	public ResponseEntity<NoticeDto> noticeView(@PathVariable int noticeNo){
		logger.debug("noticeView - 호출");
		return new ResponseEntity<>(noticeService.getNotice(noticeNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "noticeNo에 해당하는 게시글 수정", notes ="DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{noticeNo}")
	public ResponseEntity<String> modifyNotice(@RequestBody NoticeDto noticeDto){
		logger.debug("modifyNotice - 호출");
		if(noticeService.modifyNotice(noticeDto)) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "noticeNo에 해당하는 게시글 삭제", notes = "DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable int noticeNo){
		logger.debug("deleteNotice - 호출");
		if(noticeService.deleteNotice(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}