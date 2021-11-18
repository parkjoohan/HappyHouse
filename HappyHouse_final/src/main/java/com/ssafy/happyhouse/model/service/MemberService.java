package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
		
	boolean registerMember(MemberDto memberDto);
	int idCheck(String id);
	//MemberDto getMember(String userId);
	boolean modifyInfo(MemberDto memberDto);
	boolean deleteMember(String id);
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
}