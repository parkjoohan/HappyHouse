package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CCTVDto;

public interface CCTVService {
	
	List<CCTVDto> getCCTVInfo(String dongCode) throws Exception;
}
