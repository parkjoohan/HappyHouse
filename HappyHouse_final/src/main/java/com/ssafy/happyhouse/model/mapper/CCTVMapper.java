package com.ssafy.happyhouse.model.mapper;

import java.util.*;
import com.ssafy.happyhouse.model.CCTVDto;

public interface CCTVMapper {
	
	List<CCTVDto> getCCTVInfo(Map<String, Object> param)throws Exception;

	int getTotalCount(Map<String, String> map)throws Exception;
}
