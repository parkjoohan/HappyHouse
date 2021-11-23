package com.ssafy.happyhouse.model.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CCTVDto;
import com.ssafy.happyhouse.model.mapper.CCTVMapper;

@Service
public class CCTVServiceImpl implements CCTVService {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CCTVDto> getCCTVInfo(String dongCode) throws Exception{
		return sqlSession.getMapper(CCTVMapper.class).getCCTVInfo(dongCode);
	}
}
