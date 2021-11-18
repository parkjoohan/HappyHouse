package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean registerMember(MemberDto memberDto) {
		return sqlSession.getMapper(MemberMapper.class).registerMember(memberDto) == 1;
	}
	
	@Override
	public int idCheck(String id) {
		return sqlSession.getMapper(MemberMapper.class).idCheck(id);
	}
	
//	@Override
//	public MemberDto getMember(String userId) {
//		return sqlSession.getMapper(MemberMapper.class).getMember(userId);
//	}
	
	@Override
	public boolean modifyInfo(MemberDto memberDto) {
		return sqlSession.getMapper(MemberMapper.class).modifyInfo(memberDto) == 1;
	}

	@Override
	public boolean deleteMember(String userId) {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(userId) == 1;
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserId() == null || memberDto.getUserPwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}
	
	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}
}
