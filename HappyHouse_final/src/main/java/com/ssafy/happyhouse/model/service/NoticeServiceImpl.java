package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.happyhouse.model.NoticeParameterDto;
import com.ssafy.happyhouse.model.NoticeDto;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto) {
		return sqlSession.getMapper(NoticeMapper.class).listNotice(noticeParameterDto);
	}
	
	@Override
	@Transactional
	public boolean registerNotice(NoticeDto noticeDto){
		return sqlSession.getMapper(NoticeMapper.class).registerNotice(noticeDto) == 1;
	}
	
	@Override
	public NoticeDto getNotice(int noticeNo) {
		return sqlSession.getMapper(NoticeMapper.class).getNotice(noticeNo);
	}

	@Override
	public boolean modifyNotice(NoticeDto noticeDto){
		return sqlSession.getMapper(NoticeMapper.class).modifyNotice(noticeDto) == 1;
	}
	
	@Override
	public boolean deleteNotice(int noticeNo) {
		return sqlSession.getMapper(NoticeMapper.class).deleteNotice(noticeNo) == 1;
	}
}