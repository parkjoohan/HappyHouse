package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
		
	boolean registerMember(MemberDto memberDto);
	int idCheck(String id);
	MemberDto getMember(String userId);
	boolean modifyInfo(MemberDto memberDto);
	boolean deleteMember(String id);
	boolean login(Map<String, String> map);
}