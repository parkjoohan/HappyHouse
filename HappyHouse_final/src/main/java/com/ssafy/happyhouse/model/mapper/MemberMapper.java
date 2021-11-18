package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberMapper {
	
	int registerMember(MemberDto memberDto);
	int idCheck(String id);
	//MemberDto getMember(String userId);
	int modifyInfo(MemberDto memberDto);
	int deleteMember(String id);
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
}
