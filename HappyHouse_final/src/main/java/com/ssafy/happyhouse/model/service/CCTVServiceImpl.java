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
	public List<CCTVDto> getCCTVInfo(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		int currentPage = Integer.parseInt(map.get("pg")); //문자열로 넘어와서 형변환!
//		System.out.println(currentPage);
		int sizePerPage = Integer.parseInt(map.get("spp"));
//		System.out.println(sizePerPage);
		int start = (currentPage - 1) * sizePerPage; //start = 0
		param.put("start", start);
		param.put("spp", sizePerPage);
		String dong = map.get("dong");
		param.put("dong", dong);
		return sqlSession.getMapper(CCTVMapper.class).getCCTVInfo(param);
	}

//  @Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		int naviSize = 5;
//		int currentPage = Integer.parseInt(map.get("pg"));
//		int sizePerPage = Integer.parseInt(map.get("spp"));
//		PageNavigation pageNavigation = new PageNavigation();
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		int totalCount = sqlSession.getMapper(CCTVMapper.class).getTotalCount(map);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//		return pageNavigation;
//	}


}
