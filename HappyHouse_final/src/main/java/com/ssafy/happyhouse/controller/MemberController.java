package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "login 성공 여부", response = String.class)
	@GetMapping("login")
	public ResponseEntity<String> login(@RequestBody MemberDto memberDto) {
		logger.debug("login - 호출");
		Map<String, String> map = new HashMap<>();
		map.put("userId", memberDto.getUserId());
		map.put("userPwd", memberDto.getUserPwd());
 		if(memberService.login(map)) {
 			System.out.println("성공");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}

	@ApiOperation(value = "새로운 회원 정보를 입력(등록)한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
		logger.debug("registerMember - 호출");
		if(memberService.registerMember(memberDto)) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "아이디 사용 가능 여부(존재하는 해당 아이디 개수 : 0 또는 1)")
	@GetMapping("idCheck/{checkId}")
	public ResponseEntity<Map<String, String>> idCheck2(@PathVariable String checkId){
		logger.debug("idCheck - 호출");
		int idCount = memberService.idCheck(checkId);
		System.out.println("id개수("+ checkId +"): " +idCount);
		Map<String, String> map = new HashMap<>();
		map.put("idcount", Integer.toString(idCount));
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인한 회원 정보를 반환한다.", response = MemberDto.class)
	@GetMapping("info/{userId}")
	public ResponseEntity<MemberDto> memberInfo(@PathVariable String userId){
		//원래는 session에서 가져옴..
		//MemberDto currentMember = (MemberDto)session.getAttribute("userinfo");
		//String id = currentMember.getUserId();
		logger.debug("memberInfo - 호출");
		return new ResponseEntity<>(memberService.getMember(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보를 수정한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{userId}")
	public ResponseEntity<String> modifyMember(@RequestBody MemberDto memberDto){
		logger.debug("modifyMember - 호출");
		if(memberService.modifyInfo(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{userId}")
	public ResponseEntity<String> deleteMember(@PathVariable("userId") String userId){
		logger.debug("deleteMember - 호출");
		if(memberService.deleteMember(userId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}