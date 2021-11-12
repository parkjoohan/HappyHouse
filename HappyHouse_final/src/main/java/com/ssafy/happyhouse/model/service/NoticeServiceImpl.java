package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.happyhouse.model.NoticeDto;
//import com.ssafy.util.PageNavigation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeDto> listNotice() {
		return sqlSession.getMapper(NoticeMapper.class).listNotice();
	}
	
	@Override
	@Transactional
	public boolean registerNotice(NoticeDto noticeDto){
		NoticeMapper guestBookMapper = sqlSession.getMapper(NoticeMapper.class);
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

	
//	@Override
//	public List<NoticeDto> listNotice(Map<String, String> map){
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if("userid".equals(key))
//			key = "n.userid";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pg") == null ? "1" : map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		int start = (currentPage - 1) * sizePerPage;
//		param.put("start", start);
//		param.put("spp", sizePerPage);
//		return sqlSession.getMapper(NoticeMapper.class).listNotice(param);
//	}

//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//		
//		int naviSize = 10;
//		int currentPage = Integer.parseInt(map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		int totalCount = sqlSession.getMapper(NoticeMapper.class).getTotalCount(map);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//		
//		return pageNavigation;
//	}
}