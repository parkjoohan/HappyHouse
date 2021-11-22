package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CCTVDto;

public interface CCTVService {

	List<CCTVDto> getCCTVInfo(Map<String, String> map) throws Exception;

//	PageNavigation makePageNavigation(Map<String, String> map)throws Exception;
}
